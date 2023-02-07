package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	public static String MAIN = "main";
	public static String THIS = "this";
	public static String TVF = "$tvf";

	public static Struct boolType = Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool)).getType();

	private int nVars;
	private boolean isMainDefined = false;

	private Obj currentClass = null;
	private ArrayList<Obj> classList = new ArrayList<>();

	private ArrayList<Obj> currentClassConstructors = new ArrayList<>();
	private Map<String, List<Obj>> classConstructorsMap = new HashMap<>();
	boolean insideConstructor = false;

	private boolean defaultConstructorExists = false;

	private Set<Obj> globalFunctions = new HashSet<>();

	private ArrayList<Struct> designatorListTypes = new ArrayList<>();

	private Obj currentMethod = null;
	private Stack<ArrayList<Struct>> actualParametersListStack = new Stack<>();
	private Struct returnType = null;

	private Struct declarationType = null;

	private int loopDepth = 0;

	Logger log = Logger.getLogger(getClass());

	private boolean errorDetected = false;

	public SemanticAnalyzer() {
		globalFunctions.add(Tab.chrObj);
		globalFunctions.add(Tab.ordObj);
		globalFunctions.add(Tab.lenObj);
	}

	public Set<Obj> getGlobalFunctions() {
		return globalFunctions;
	}

	public List<Obj> getClassList() {
		return classList;
	}

	public Map<String, List<Obj>> getClassConstructorsMap() {
		return classConstructorsMap;
	}

	public boolean passed() {
		return !errorDetected;
	}

	public int getNVars() {
		return nVars;
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	/* Program grammar, global declarations (const, variable, class) */
	public void visit(Program program) {
		Code.dataSize = Tab.currentScope.getnVars();
		if (!isMainDefined) { // TODO: proveriti da li main ima parametre! (ako se ima vremena)
			report_error("Semanticka greska: Program mora imati definisanu main() metodu", program);
		}
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(ConstDeclaration constDecl) {
		if (Tab.find(constDecl.getConstName()) != Tab.noObj) {
			report_error("Semanticka greska: Konstanta " + constDecl.getConstName() + " je vec deklarisana", constDecl);
			return;
		}
		if (constDecl.getConstType().obj.getType().assignableTo(declarationType)) {
			Obj obj = Tab.insert(Obj.Con, constDecl.getConstName(), declarationType);
			obj.setAdr(constDecl.getConstType().obj.getAdr());
			report_info("Definisana const", constDecl);
		} else {
			report_error("Semanticka greska: Tip konstante i dodeljena vrednost su nekompatibilne", constDecl);
		}
	}

	public void visit(ConstDeclarationStart constStart) {
		declarationType = null;
	}

	/* Standard types: int, char, bool */
	private boolean isCurrVarField() {
		return currentClass != null && currentMethod == null;
	}

	public void visit(ConstIntType cnstInt) {
		Obj obj = new Obj(Obj.Con, "", Tab.intType);
		obj.setAdr(cnstInt.getN1());
		cnstInt.obj = obj;
	}

	public void visit(ConstCharType cnstChar) {
		Obj obj = new Obj(Obj.Con, "", Tab.charType);
		obj.setAdr(cnstChar.getC1());
		cnstChar.obj = obj;
	}

	public void visit(ConstBoolType cnstBool) {
		Obj obj = new Obj(Obj.Con, "", boolType);
		obj.setAdr(cnstBool.getB1() ? 1 : 0);
		cnstBool.obj = obj;
	}

	/* Array and normal variables checks */
	public void visit(VarDeclarationStart varStart) {
		declarationType = null;
	}

	public void visit(VarNameSingle var) {
		Obj obj = Tab.currentScope.findSymbol(var.getVarName());
		if (obj != null) {
			report_error("Semanticka greska: Varijabla " + var.getVarName() + " je vec deklarisana u opsegu", var);
			return;
		}
		if (isCurrVarField()) {
			Tab.insert(Obj.Fld, var.getVarName(), declarationType);
			report_info("Definicija polja " + var.getVarName(), var);
		} else {
			Tab.insert(Obj.Var, var.getVarName(), declarationType);
			report_info("Definicija promenljive " + var.getVarName(), var);
		}
	}

	public void visit(VarNameArray var) {
		Obj obj = Tab.currentScope.findSymbol(var.getVarName());
		if (obj != null) {
			report_error("Semanticka greska: Varijabla " + var.getVarName() + " je vec deklarisana", var);
			return;
		}
		if (isCurrVarField()) {
			Tab.insert(Obj.Fld, var.getVarName(), new Struct(Struct.Array, declarationType));
			report_info("Definicija niz polja " + var.getVarName(), var);
		} else {
			Tab.insert(Obj.Var, var.getVarName(), new Struct(Struct.Array, declarationType));
			report_info("Definicija niz promenljive " + var.getVarName(), var);
		}
	}

	/* Type */
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getTypeName());
		if (typeObj == Tab.noObj) {
			report_error("Semanticka greska: Nije pronadjen tip" + type.getTypeName() + "u tabeli simbola", null);
			type.struct = Tab.noType;
			declarationType = Tab.noType;
		} else if (Obj.Type != typeObj.getKind()) {
			report_error("Semanticka greska: " + type.getTypeName() + "ne predstavlja nijedan postojeci tip", type);
			type.struct = Tab.noType;
			declarationType = Tab.noType;
		} else {
			type.struct = typeObj.getType();
			declarationType = typeObj.getType();
		}
	}

	/* Class */
	private boolean checkIfSubclass(Struct superClass, Struct potentialSubClass) {
		if (superClass.getKind() != Struct.Array && potentialSubClass.getKind() != Struct.Array) {
			boolean found = false;
			while (potentialSubClass != null) {
				if (potentialSubClass.assignableTo(superClass)) {
					found = true;
					break;
				}
				potentialSubClass = potentialSubClass.getElemType();
			}
			return found;
		}
		else if (superClass.getKind() == Struct.Array && potentialSubClass.getKind() == Struct.Array ) {
			return checkIfSubclass(superClass.getElemType(), potentialSubClass.getElemType());
		}
		return false;
	}

	private boolean constructorParametersMatch(Obj firstConstructor, Obj secondConstructor) {
		if (firstConstructor.getLevel() != secondConstructor.getLevel()) {
			return false;
		}

		boolean match = true;
		Iterator<Obj> currConstructorIterator = firstConstructor.getLocalSymbols().iterator();
		Iterator<Obj> nextConstructorIterator = secondConstructor.getLocalSymbols().iterator();
		for (int i = 0; i < firstConstructor.getLevel(); ++i) {
			Obj currParameter = currConstructorIterator.next();
			Obj nextParameter = nextConstructorIterator.next();

			if (currParameter.getType() != nextParameter.getType()) {
				match = false;
				break;
			}
		}
		return match;
	}

	private boolean sameConstructorExists() {
		// Supposes "currentMethod" hold constructor's Obj object
		// and "currentClassConstructors" constructors that have been defined so far
		for (Obj nextConstructor : currentClassConstructors) {
			if (constructorParametersMatch(currentMethod, nextConstructor)) {
				return true;
			}
		}
		return false;
	}

	private void addDefaultConstructor(String constructorIdent) {
		String constructorName = constructorIdent + "#" + Integer.toString(currentClassConstructors.size());

		// Empty body function
		currentMethod = Tab.insert(Obj.Meth, constructorName, Tab.nullType);
		Tab.openScope();
		Tab.insert(Obj.Var, THIS, currentClass.getType());

		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();

		currentClassConstructors.add(currentMethod);

		currentMethod = null;

	}

	public void visit(ClassName clsName) {
		if (Tab.currentScope.findSymbol(clsName.getName()) != null) {
			report_error("Semanticka greska: Redefinicija klase " + clsName.getName(), clsName);
		}
		currentClass = Tab.insert(Obj.Type, clsName.getName(), new Struct(Struct.Class));
		clsName.obj = currentClass;
		Tab.openScope();
		Tab.insert(Obj.Fld, TVF + clsName.getName(), Tab.nullType);
	}

	public void visit(SuperClassIdent sup) {
		Obj obj = Tab.find(sup.getName());
		if (obj != Tab.noObj && obj.getType().getKind() == Struct.Class) {
			if (obj.getName() != currentClass.getName()) {
				currentClass.getType().setElementType(obj.getType());

				// Add fields from super class (subclass
				// can't have same name fields!)
				for (Obj classMember : obj.getType().getMembers()) {
					if (classMember.getKind() == Obj.Fld && !classMember.getName().startsWith(TVF)) {
						Tab.insert(Obj.Fld, classMember.getName(), classMember.getType());
					}
				}
			} else {
				report_error("Semanticka greska: Klasa se ne moze izvoditi iz sama sebe", sup);
			}
		} else {
			report_error("Semanticka greska: Klasa iz koje se pokusava izvodjenje je nepostojeca", sup);
		}
	}

	public void visit(ClassDeclaration classDecl) {
		Struct superClass = currentClass.getType().getElemType();
		if (superClass != null) {
			for (Obj classMember : superClass.getMembers()) {
				if (classMember.getKind() == Obj.Meth && Tab.currentScope.findSymbol(classMember.getName()) == null
						&& !classMember.getName().contains("#")) { // # = Only in constructors, don't include!
					Tab.currentScope().addToLocals(classMember);
				}
			}
		}

		classDecl.getClassName().getName();
		if (!defaultConstructorExists) {
			String constructorIdent = classDecl.getClassName().getName();
			addDefaultConstructor(constructorIdent);
			report_info("Dodat je podrazumevani konstruktor za klasu " + constructorIdent, classDecl);
		}

		Tab.chainLocalSymbols(currentClass.getType());
		Tab.closeScope();

		classList.add(currentClass);

		// Add to hash map for later constructor matching
		classConstructorsMap.put(currentClass.getName(), currentClassConstructors);

		currentClassConstructors = new ArrayList<>();
		currentClass = null;
		defaultConstructorExists = false;
	}

	// Constructors
	public void visit(ConstructorDeclarationStart constructorDeclStart) {
		String constructorIdent = constructorDeclStart.getI1();
		if (!currentClass.getName().equals(constructorIdent)) {
			report_error("Semanticka greska: Konstruktor se mora zvati isto kao i klasa", constructorDeclStart);
		}
		// Unique name for every constructor
		String constructorName = constructorIdent + "#" + Integer.toString(currentClassConstructors.size());
		insideConstructor = true;

		constructorDeclStart.obj = currentMethod = Tab.insert(Obj.Meth, constructorName, Tab.nullType);

		Tab.openScope();
		Tab.insert(Obj.Var, THIS, currentClass.getType());
	}

	public void visit(ConstructorDeclarationInst constructorDeclaration) {
		if (returnType != null) {
			report_error("Semanticka greska: Konstruktor ne sme da ima return naredbu sa izrazom",
					constructorDeclaration);
		}

		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();

		if (sameConstructorExists()) {
			report_error("Semanticka greska: Redifinicija konstruktora sa istim parametrima", constructorDeclaration);
		} else {
			currentClassConstructors.add(currentMethod);
		}

		// Needed later for knowing if default constr. should be generated at the end
		if (currentMethod.getLevel() == 1) { // TODO: possibly go back to 0
			defaultConstructorExists = true;
		}

		returnType = null;
		currentMethod = null;
		insideConstructor = false;
	}

	/* Methods */
	private void processMethodAndTypeName(MethodTypeAndName method, Struct type, String methName) {

		if (Tab.currentScope.findSymbol(methName) != null) {
			report_error("Semanticka greska: Redefinicija metode " + methName, method);
		}
		method.obj = currentMethod = Tab.insert(Obj.Meth, methName, type);

		Tab.openScope();

		if (currentClass != null) {
			Tab.insert(Obj.Var, THIS, currentClass.getType());
		}
	}

	public void visit(MethodTypeName method) {
		Struct type = method.getType().struct;
		processMethodAndTypeName(method, type, method.getMethName());
	}

	public void visit(MethodVoidName method) {
		processMethodAndTypeName(method, Tab.noType, method.getMethName());
	}
	
	private boolean isOverrideCorrect(Obj superMethod, Obj overrideMethod) {
		// Second parameters is Obj in currentMethod attribute
		if (superMethod.getLevel() != overrideMethod.getLevel()) {
			return false;
		}
		
		Iterator<Obj> superParamIterator = superMethod.getLocalSymbols().iterator();
		// Haven't connected locals to currentMethod yet
//		Iterator<Obj> overrideParamIterator = Tab.currentScope().getLocals().symbols().iterator();
		Iterator<Obj> overrideParamIterator = overrideMethod.getLocalSymbols().iterator();
		for (int i = 0; i < superMethod.getLevel(); ++i) {
			Obj superParam = superParamIterator.next();
			Obj overrideParam = overrideParamIterator.next();
			
			if (!checkIfSubclass(superParam.getType(), overrideParam.getType())) {
				return false;
			}
		}
		return true;
	}

	public void visit(MethodDecl methodDeclaration) {
		if (returnType == null && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska: Nije pronadjen return tipa " + currentMethod.getType().getKind(),
					methodDeclaration);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		if (currentClass != null && currentClass.getType().getElemType() != null) {
			// Check if currentMethod represents override function
			Obj superMethod = currentClass.getType().getElemType().getMembersTable().searchKey(currentMethod.getName());
			if (superMethod != null && superMethod.getKind() == Obj.Meth) {
				if (!isOverrideCorrect(superMethod, currentMethod)) {
					report_error("Semanticka greska: Preklopljena methoda nema isti potpis kao metoda natklase", methodDeclaration);
				}
			}
		}
		
		// Add to global functions and check if this is the main() function
		if (currentClass == null) {
			globalFunctions.add(currentMethod);
			if (currentMethod.getName().equals(MAIN))
				isMainDefined = true;
		}

		returnType = null;
		currentMethod = null;
	}

	/* Return statements */
	public void visit(ReturnStatement ret) {
		if (currentMethod == null) {
			report_error("Semanticka greska: return pronadjen van funkcije", ret);
		}
		if (currentMethod == null && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska: return vraca nekompatibilan tip", ret);
		}

	}

	public void visit(ReturnExprStatement ret) {
		if (currentMethod == null) {
			report_error("Semanticka greska: return pronadjen van funkcije", ret);
		}
		
		// !ret.getExpr().struct.compatibleWith(currentMethod.getType())
		if (currentMethod == null && !checkIfSubclass(currentMethod.getType(), ret.getExpr().struct)) {
			report_error("Semanticka greska: return vraca nekompatibilan tip", ret);
		}
		if (insideConstructor) {
			report_error("Semanticka greska: return sa izrazom ne sme da se nalazi u konstruktoru klase", ret);
		}
		returnType = ret.getExpr().struct;
	}

	/* Formal parameters */
	public void visit(FormalParamDeclSingle formalParam) {
		Obj obj = Tab.currentScope.findSymbol(formalParam.getName());
		if (obj != null) {
			report_error("Semanticka greska: vec postoji formalni parametar sa istim imenom", formalParam);
		}
		Tab.insert(Obj.Var, formalParam.getName(), formalParam.getType().struct);
	}

	public void visit(FormalParamDeclArray formalParam) {
		Obj obj = Tab.currentScope.findSymbol(formalParam.getName());
		if (obj != null) {
			report_error("Semanticka greska: vec postoji formalni parametar sa istim imenom", formalParam);
		}
		Tab.insert(Obj.Var, formalParam.getName(), new Struct(Struct.Array, formalParam.getType().struct));
	}

	public void visit(OptFormalParams formalParamList) {
		int numberOfParameters = Tab.currentScope.getnVars();
		currentMethod.setLevel(numberOfParameters);
	}

	public void visit(NoFormalParam formalParamList) {
		int numberOfParameters = Tab.currentScope.getnVars();
		currentMethod.setLevel(numberOfParameters); // If "this" exists
	}

	public void visit(FunctionName funcName) {
		if (funcName.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Semanticka greska: designator ne predstavlja ime funkcije", funcName);
		}
		actualParametersListStack.push(new ArrayList<Struct>());
	}

	/* Designator, DesignatorStatement (everything where it is involved) */
	public void visit(DesignatorSingle designator) {
		Obj obj = Tab.find(designator.getName());

		if (obj == Tab.noObj) {
			report_error("Promenljiva " + designator.getName() + " nije deklarisana", designator);
		}
		designator.obj = obj;
	}

	public void visit(DesignatorArray designator) {
		Obj obj = designator.getDesignator().obj;
		if (obj.getType().getKind() != Struct.Array) {
			report_error("Semanticka greska: Promenljiva " + obj.getName() + " mora biti tipa niz", designator);
			designator.obj = Tab.noObj;
			return;
		}
		if (designator.getExpr().struct != Tab.intType) {
			report_error("Semanticka greska: Indeks niza mora biti tipa int", designator);
			designator.obj = Tab.noObj;
			return;
		}

		// Changing objects type from Array type to Elem type
		designator.obj = new Obj(Obj.Elem, designator.getDesignator().obj.getName(),
				designator.getDesignator().obj.getType().getElemType());
	}

	// TODO: Ovo OBAVEZNO istestirati fino! (Moraju najpre i konstruktori, ima
	// posla)
	public void visit(DesignatorField designator) {
		Obj obj;
		if (currentClass != null && designator.getDesignator().obj.getName().equals(THIS)) {
			// Search for variable in scope
			obj = Tab.currentScope().getOuter().findSymbol(designator.getRightField());
		} else {
			Struct classType = designator.getDesignator().obj.getType();

			obj = classType.getMembersTable().searchKey(designator.getRightField());
		}
		if (obj == null) {
			report_error("Semanticka greska: Ne postoji polje sa imenom " + designator.getRightField(), designator);
			designator.obj = Tab.noObj;
		} else {
			designator.obj = obj;
		}
	}

	public void visit(AssignmentExpr ass) {
		Struct expressionStruct = ass.getExpr().struct;
		Struct designatorStruct = ass.getDesignator().obj.getType();
		int expressionKind = ass.getExpr().struct.getKind();
		int designatorKind = ass.getDesignator().obj.getKind();

		if (expressionKind == Struct.Class) {
			if (!checkIfSubclass(designatorStruct, expressionStruct)) {
				report_error("Semanticka greska: klasni tip izraza nije kompatibilan sa dezignatorom", ass);
			}
		} else {
			if (!expressionStruct.assignableTo(designatorStruct)) {
				report_error("Semanticka greska: Izraz je nemoguce dodeliti dezignatoru", ass);
			} else {
				if (designatorKind != Obj.Var && designatorKind != Obj.Fld && designatorKind != Obj.Elem) {
					report_error("Semanticka greska: Dezignatoru se ne moze dodeliti nova vrednost", ass);
				}
			}
			ass.struct = designatorStruct;
		}
	}

	// Increment and decrement
	private void checkIncrementAndDecrement(SyntaxNode node, int kind, Struct type, String op) {
		if (kind != Obj.Var && kind != Obj.Fld && kind != Obj.Elem) {
			report_error("Semanticka greska: Dezignator nije odgovarajuce vrste za operaciju " + op, node);
		}
		if (type != Tab.intType) {
			report_error("Semanticka greska: Dezignatoru mora biti tipa int za " + op, node);
		}
	}

	public void visit(IncrementDesignator incDesignator) {
		int kind = incDesignator.getDesignator().obj.getKind();
		Struct type = incDesignator.getDesignator().obj.getType();
		checkIncrementAndDecrement(incDesignator, kind, type, "inkrement");
	}

	public void visit(DecrementDesignator decDesignator) {
		int kind = decDesignator.getDesignator().obj.getKind();
		Struct type = decDesignator.getDesignator().obj.getType();
		checkIncrementAndDecrement(decDesignator, kind, type, "dekrement");
	}

	// Designator assignment list: "[" [Designator] {"," [Designator]}"]" "="
	// Designator
	public void visit(DesignatorReal designator) {
		Obj obj = designator.getDesignator().obj;
		if (obj.getKind() != Obj.Var && obj.getKind() != Obj.Fld && obj.getKind() != Obj.Elem) {
			report_error("Semanticka greska: Nekompatibilni designator za read", designator);
		}
		// Later to compare with right designator element type
		designatorListTypes.add(obj.getType());
	}

	public void visit(MultipleAssignmentStatement multipleAssignmentStmt) {
		Struct designatorType = multipleAssignmentStmt.getDesignator().obj.getType();

		if (designatorType.getKind() != Struct.Array) {
			report_error("Semanticka greska: Dezignator sa desne strane mora biti tipa niza", multipleAssignmentStmt);
		} else {
			Struct designatorElementType = designatorType.getElemType();
			for (Struct designatorListElementType : designatorListTypes) {
				if (!designatorElementType.assignableTo(designatorListElementType) 
					&& !checkIfSubclass(designatorListElementType, designatorElementType)) {
					report_error("Semanticka greska: nekompatibilan tip u okviru liste dezignatora",
							multipleAssignmentStmt);
					break;
				}
			}
		}
		// Prepare for next potential designator list
		designatorListTypes.clear();
	}

	// Function designator
	public void visit(ActualParamSingle actualParam) {
		actualParametersListStack.peek().add(actualParam.getActualParameter().getExpr().struct);
	}

	public void visit(ActualParamListRec actualParam) {
		actualParametersListStack.peek().add(actualParam.getActualParameter().getExpr().struct);
	}

	public void visit(FunctionCall functionCall) {
		Obj functionDesignator = functionCall.getFunctionName().getDesignator().obj;
		ArrayList<Struct> actualParametersList = actualParametersListStack.pop();

		if (functionDesignator.getKind() != Obj.Meth) {
			report_error("Semanticka greska: Dezignator ne predstavlja ime funkcije i ne moze se pozvati",
					functionCall);
			return;
		}

		int formalParametersNum = functionDesignator.getLevel();
		if (!globalFunctions.contains(functionDesignator))
			formalParametersNum--;

		if (formalParametersNum != actualParametersList.size()) {
			report_error("Semanticka greska: Pogresan broj parametara za datu funkciju", functionCall);
			return;
		}

		if (formalParametersNum > 0) {
			Iterator<Obj> localSymbolsIter = functionDesignator.getLocalSymbols().iterator();
			Iterator<Struct> actualParamIterator = actualParametersList.iterator();

			// If method, skip "this"
			if (!globalFunctions.contains(functionDesignator))
				localSymbolsIter.next();

			while (localSymbolsIter.hasNext() && actualParamIterator.hasNext()) {
				Obj parameter = localSymbolsIter.next();
				Struct actualType = actualParamIterator.next();


				boolean assignable = checkIfSubclass(parameter.getType(), actualType);
				if (!assignable) {
					report_error("Semanticka greska: Stvarni i formalni parametri se ne poklapaju", functionCall);
				}
			}
		}
		functionCall.struct = functionDesignator.getType();
	}

	/* Expr, term, factor */
	public void visit(DesignatorFactor desFactor) {
		int kind = desFactor.getDesignator().obj.getKind();
		if (kind == Obj.Meth) {
			desFactor.struct = Tab.noType;
		} else {
			desFactor.struct = desFactor.getDesignator().obj.getType();
		}
	}

	public void visit(FactorFuncCall funcCall) {
		Obj obj = funcCall.getFunctionCall().getFunctionName().getDesignator().obj;
		if (obj.getType() == Tab.noType) {
			report_error("Semanticka greska: void funkcija se ne sme koristiti u okviru izraza", funcCall);
		}
		funcCall.struct = funcCall.getFunctionCall().struct;
	}

	public void visit(ConstFactor cnstFactor) {
		cnstFactor.struct = cnstFactor.getConstType().obj.getType();
	}

	public void visit(ExprFactor exprFactor) {
		exprFactor.struct = exprFactor.getExpr().struct;
	}

	public void visit(ClassNewType clsNewType) {
		Struct type = clsNewType.getType().struct;
		if (type.getKind() != Struct.Class) {
			report_error("Semanticka greska: tip uz new mora biti klasa", clsNewType);
		}
		actualParametersListStack.push(new ArrayList<Struct>());
	}

	public void visit(OperatorNewClass newClass) {
		// Propagate type, check has passed
		Struct type = newClass.getClassNewType().getType().struct;
		newClass.struct = type;

		// Check if constructor exists based on actual parameters
		String className = newClass.getClassNewType().getType().getTypeName();
		ArrayList<Struct> actualParameters = actualParametersListStack.pop();

		boolean match = false;
		for (Obj classConstructor : classConstructorsMap.get(className)) {
			int formalParametersNum = classConstructor.getLevel() - 1;

			if (actualParameters.size() != formalParametersNum) // TODO: classConstructor.getLevel()
				continue;

			match = true;
			Iterator<Struct> actualParamIterator = actualParameters.iterator();
			Iterator<Obj> constructorParamIterator = classConstructor.getLocalSymbols().iterator();

			// Skipping "this"
			constructorParamIterator.next();

			for (int i = 0; i < formalParametersNum; ++i) {
				Struct actualParamter = actualParamIterator.next();
				Obj constructorParameter = constructorParamIterator.next();
				
				// Statically connected, need to be exactly the same type
				if (!actualParamter.assignableTo(constructorParameter.getType())) {
					match = false;
					break;
				}
			}
			if (match) {
				break;
			}
		}
		if (!match) {
			report_error("Semanticka greska: Ne moze se pronaci nijedan konstruktor koji odgovara stvarnim parametrima",
					newClass);
		}
	}

	public void visit(OperatorNewArr newArr) {
		Struct type = newArr.getType().struct;

		if (newArr.getExpr().struct == Tab.intType) {
			newArr.struct = new Struct(Struct.Array, type);
		} else {
			report_error("Semanticka greska: Velicina niza mora biti tipa int", newArr);
			// In case of an error, propagate
			newArr.struct = type;
		}
	}

	public void visit(FactorTerm factorTerm) {
		factorTerm.struct = factorTerm.getFactor().struct;
	}

	public void visit(MulTerm mulExpr) {
		if (!mulExpr.getTerm().struct.compatibleWith(mulExpr.getFactor().struct)) {
			report_error("Semanticka greska: Nekompatibilni podaci pri racunanju izraza", mulExpr);
		}
		mulExpr.struct = mulExpr.getTerm().struct;
	}

	public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getTerm().struct;
	}

	public void visit(NegExpr negExpr) {
		negExpr.struct = negExpr.getTerm().struct;
	}

	public void visit(AddExpr addExpr) {
		if (!addExpr.getExpr().struct.compatibleWith(addExpr.getTerm().struct)) {
			report_error("Semanticka greska: Nekompatibilni podaci pri racunanju izraza", addExpr);
		}

		addExpr.struct = addExpr.getExpr().struct;
	}

	/* Read and Print */
	public void visit(PrintStatement prntStmt) {
		Struct type = prntStmt.getExpr().struct;
		if (type != Tab.charType && type != Tab.intType && type != boolType)
			report_error("Semanticka greska: Nekompatibilni tipovi za print", prntStmt);
	}

	public void visit(PrintWidthStatement prntStmt) {
		Struct type = prntStmt.getExpr().struct;
		if (type != Tab.charType && type != Tab.intType && type != boolType)
			report_error("Semanticka greska: Nekompatibilni tipovi za print", prntStmt);
	}

	public void visit(ReadStatement rdStmt) {
		Obj obj = rdStmt.getDesignator().obj;
		if (obj.getKind() == Obj.Var || obj.getKind() == Obj.Fld || obj.getKind() == Obj.Elem) {
			Struct type = obj.getType();
			if (type != Tab.charType && type != Tab.intType && type != boolType)
				report_error("Semanticka greska: Nekompatibilni designator za read", rdStmt);
		} else {
			report_error("Semanticka greska: Nekompatibilni designator za read", rdStmt);
		}
	}

	/* Conditions */
	public void visit(ConditionFactorSingle conditionFactor) {
		Struct type = conditionFactor.getExpr().struct;
		if (type != boolType) {
			report_error("Semanticka greska: Nekompatibilan tipa za uslov", conditionFactor);
		}
		conditionFactor.struct = boolType;
	}

	public void visit(ConditionFactorRel conditionFactor) {
		Struct type_first = conditionFactor.getExpr().struct;
		Struct type_second = conditionFactor.getExpr1().struct;
		if (!type_first.compatibleWith(type_second) 
			&& !checkIfSubclass(type_first, type_second)
			&& !checkIfSubclass(type_second, type_first)) {
			report_error("Semanticka greska: Nekompatibilni tipovi u relaciji", conditionFactor);
		}
		else if (type_first.getKind() == Struct.Array || type_first.getKind() == Struct.Class) {
			Relop relop = conditionFactor.getRelop();
			if (!(relop instanceof EqualsOp) && !(relop instanceof NotEqualsOp)) {
				report_error("Semanticka greska: objekti klasa i nizovi mogu da se porede samo sa operatorima '==' ili '!='", conditionFactor);
			}
		}
		
		conditionFactor.struct = boolType;
	}

	public void visit(ConditionTermSingle conditionTerm) {
		Struct type = conditionTerm.getConditionFactor().struct;
		if (type != boolType) {
			report_error("Semanticka greska: Nekompatibilan tipa za uslov", conditionTerm);
		}
		conditionTerm.struct = boolType;
	}

	public void visit(ConditionTermAnd conditionTerm) {
		Struct type_first = conditionTerm.getConditionTerm().struct;
		Struct type_second = conditionTerm.getConditionFactor().struct;
		if (type_first != boolType || !type_first.compatibleWith(type_second)) {
			report_error("Semanticka greska: Nekompatibilni tipovi u relaciji (and)", conditionTerm);
		}
		conditionTerm.struct = boolType;
	}

	public void visit(ConditionSingle condition) {
		Struct type = condition.getConditionTerm().struct;
		if (type != boolType) {
			report_error("Semanticka greska: Nekompatibilan tipa za uslov", condition);
		}
		condition.struct = boolType;
	}

	public void visit(ConditionOr condition) {
		Struct type_first = condition.getCondition().struct;
		Struct type_second = condition.getConditionTerm().struct;
		if (type_first != boolType || !type_first.compatibleWith(type_second)) {
			report_error("Semanticka greska: Nekompatibilni tipovi u relaciji (or)", condition);
		}
		condition.struct = boolType;
	}

	/* Control statements */
	public void visit(ControlConditionValid controlCondition) {
		if (!controlCondition.getCondition().struct.equals(boolType)) {
			report_error("Semanticka greska: Kontrolni uslove nije tipa bool", controlCondition);
		}
	}

	public void visit(WhileKeyWord whileKeyWord) {
		loopDepth++;
	}

	public void visit(ForIdent forIdent) {
		Obj obj = Tab.find(forIdent.getName());

		if (obj == Tab.noObj) {
			report_error("Promenljiva " + forIdent.getName() + " nije deklarisana", forIdent);
		}
		forIdent.obj = obj;
	}

	public void visit(ForeachConstruct foreach) {
		loopDepth++;

		Obj designatorObj = foreach.getForeachDesignator().getDesignator().obj;
		if (designatorObj.getType().getKind() != Struct.Array) {
			report_error("Semanticka greska: designator u foreach petlji mora biti nizovskog tipa", foreach);
		}
		Struct elemType = designatorObj.getType().getElemType();
		Struct identType = foreach.getForIdent().obj.getType();
		if (!checkIfSubclass(identType, elemType)) { // TODO: Should include normal types?
			report_error("Semanticka greska: Nekompatibilni tipovi u foreach petlji", foreach);
		}
	}

	public void visit(ContinueStatement stmt) {
		if (loopDepth < 0) {
			report_error("Semanticka greska: continue se nalazi van petlje", stmt);
		}
	}

	public void visit(BreakStatement stmt) {
		if (loopDepth < 0) {
			report_error("Semanticka greska: break se nalazi van petlje", stmt);
		}
	}
}
