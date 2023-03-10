package rs.ac.bg.etf.pp1;

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
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;

	private List<Obj> classList;
	private Set<Obj> globalFunctions;
	private Map<String, Integer> tvfStartAddressMap = new HashMap<>();
	private Map<String, List<Obj>> classConstructorsMap;

	private Struct newInstanceType = null;
	private ArrayList<Struct> actualParametersList = new ArrayList<>();

	private boolean insideClass = false;

	private ArrayList<DesignatorReal> designatorLeft = new ArrayList<>();
	private boolean inDesignatorList = false;

	public int getMainPc() {
		return mainPc;
	}

	CodeGenerator(List<Obj> classList, Set<Obj> globalFunctions, Map<String, List<Obj>> classConstructorsMap) {
		this.classList = classList;
		this.globalFunctions = globalFunctions;
		this.classConstructorsMap = classConstructorsMap;
	}

	private void generateChrAndOrdCode() {
		Tab.find("chr").setAdr(Code.pc);
		Tab.find("ord").setAdr(Code.pc);

		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);

		Code.put(Code.load_n);

		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	private void generateLenCode() {
		Tab.find("len").setAdr(0);

		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);

		Code.put(Code.load_n);
		Code.put(Code.arraylength);

		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(ProgName progName) {
		generateLenCode();
		generateChrAndOrdCode();
	}

	/* TVF table */
	private void addTvfConstant(int value, int num) {
		Code.loadConst(value);
		Code.put(Code.putstatic);
		Code.put2(num);
	}

	private void generateTVF() {
		// Code.dataSize has already been updated during semantic analysis
		int cursor = Code.dataSize;

		for (Obj cls : classList) {
			tvfStartAddressMap.put(cls.getName(), cursor);
			for (Obj clsMember : cls.getType().getMembers()) {
				if (clsMember.getKind() != Obj.Meth || clsMember.getName().contains("#"))
					continue;

				for (char character : clsMember.getName().toCharArray()) {
					addTvfConstant(character, cursor++);
				}
				// End of string
				addTvfConstant(-1, cursor++);

				// Addr field
				addTvfConstant(clsMember.getAdr(), cursor++);
			}
			addTvfConstant(-2, cursor++);
		}
		Code.dataSize = cursor;
	}

	/* Class declaration */
	public void visit(ClassName className) {
		insideClass = true;
	}

	public void visit(ClassDeclaration classDeclaration) {
		Struct classType = classDeclaration.getClassName().obj.getType();
		if (classType.getElemType() != null) {
			// Initialize copied methods starting address in subclass!
			Struct superClassType = classType.getElemType();
			for (Obj superClassMember : superClassType.getMembers()) {
				if (superClassMember.getKind() != Obj.Meth)
					continue;
				for (Obj classMember : classType.getMembers()) {
					if (classMember.getKind() != Obj.Meth || !classMember.getName().equals(superClassMember.getName())
							|| classMember.getAdr() != 0) {
						continue;
					}
					classMember.setAdr(superClassMember.getAdr());
				}
			}
		}
		insideClass = false;
	}

	public void visit(ConstructorDeclarationStart constructorDeclStart) {
		Obj obj = constructorDeclStart.obj;

		obj.setAdr(Code.pc);

		Code.put(Code.enter);
		Code.put(obj.getLevel());
		Code.put(obj.getLocalSymbols().size());
	}

	public void visit(ConstructorDeclarationInst constructorDeclaration) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	/* Print and read */
	public void visit(PrintStatement print) {
		// Default width for each type
		if (print.getExpr().struct.equals(Tab.charType)) {
			Code.put(Code.const_1);
			Code.put(Code.bprint);
		} else {
			Code.put(Code.const_5);
			Code.put(Code.print);
		}
	}

	public void visit(PrintWidthStatement print) {
		Code.load(new Obj(Obj.Con, "", Tab.intType, print.getN2(), 0));
		if (print.getExpr().struct.equals(Tab.charType)) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}

	public void visit(ReadStatement read) {
		Obj designatorObj = read.getDesignator().obj;
		if (designatorObj.getType().equals(Tab.charType)) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}
		Code.store(designatorObj);
	}

	/* Return */
	public void visit(ReturnExprStatement ret) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(ReturnStatement ret) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	/* Basic expressions */
	public void visit(ConstIntType cnstInt) {
		Code.load(new Obj(Obj.Con, "", Tab.intType, cnstInt.getN1(), 0));
	}

	public void visit(ConstCharType cnstChar) {
		Code.load(new Obj(Obj.Con, "", Tab.charType, cnstChar.getC1(), 0));
	}

	public void visit(ConstBoolType cnstBool) {
		Code.load(new Obj(Obj.Con, "", SemanticAnalyzer.boolType, (cnstBool.getB1()) ? 1 : 0, 0));
	}

	public void visit(FactorTerm term) {
		if (term.getParent() instanceof NegExpr) {
			Code.put(Code.neg);
		}
	}

	public void visit(AddExpr addExpression) {
		Addop op = addExpression.getAddop();
		if (op instanceof PlusOp) {
			Code.put(Code.add);
		} else {
			Code.put(Code.sub);
		}
	}

	public void visit(MulTerm term) {
		Mulop op = term.getMulop();
		if (op instanceof MultiplyOp) {
			Code.put(Code.mul);
		} else if (op instanceof DivisionOp) {
			Code.put(Code.div);
		} else {
			Code.put(Code.rem);
		}
	}

	// New Operators
	private int calculateTypeSize(Struct type) {
		int size = 0;
		for (Obj member : type.getMembers()) {
			if (member.getKind() == Obj.Fld)
				size += member.getType().equals(Tab.charType) ? 1 : 4;
		}
		return size;
	}

	public void visit(OperatorNewArr newArr) {
		// 0 - char, 1 - int, bool
		int arrayType = 0;
		if (!newArr.getType().struct.equals(Tab.charType)) {
			arrayType = 1;
		}
		Code.put(Code.newarray);
		Code.put(arrayType);
	}

	public void visit(ClassNewType classNewType) {
		Struct type = classNewType.getType().struct;
		int size = calculateTypeSize(type);
		Code.put(Code.new_);
		Code.put2(size);
		// dup for VTF
		Code.put(Code.dup);
		Code.loadConst(tvfStartAddressMap.get(classNewType.getType().getTypeName()));
		Code.put(Code.putfield);
		Code.put2(0);
		Code.put(Code.dup);

		newInstanceType = type;
	}

	public void visit(OperatorNewClass newClass) {

		Struct type = newClass.getClassNewType().getType().struct;
		newClass.struct = type;

		// Check if constructor exists based on actual parameters
		String className = newClass.getClassNewType().getType().getTypeName();

		boolean match = false;
		for (Obj classConstructor : classConstructorsMap.get(className)) {
			int formalParametersCount = classConstructor.getLevel() - 1;
			if (actualParametersList.size() != formalParametersCount)
				continue;

			match = true;
			Iterator<Struct> actualParamIterator = actualParametersList.iterator();
			Iterator<Obj> constructorParamIterator = classConstructor.getLocalSymbols().iterator();

			// Skip this
			constructorParamIterator.next();

			for (int i = 0; i < formalParametersCount; ++i) {
				Struct actualParamter = actualParamIterator.next();
				Obj constructorParameter = constructorParamIterator.next();

				if (!actualParamter.assignableTo(constructorParameter.getType())) {
					match = false;
					break;
				}
			}
			if (match) {
				int offset = classConstructor.getAdr() - Code.pc;
				Code.put(Code.call);
				Code.put2(offset);
				break;
			}
		}

		actualParametersList = new ArrayList<>();
		newInstanceType = null;
	}

	public void visit(ActualParamSingle actualParam) {
		if (newInstanceType != null)
			actualParametersList.add(actualParam.getActualParameter().getExpr().struct);
	}

	public void visit(ActualParamListRec actualParam) {
		if (newInstanceType != null)
			actualParametersList.add(actualParam.getActualParameter().getExpr().struct);
	}

	// Increment and decrement
	public void visit(IncrementDesignator incDesignator) {
		Code.put(Code.const_1);
		Code.put(Code.add);
		Code.store(incDesignator.getDesignator().obj);
	}

	public void visit(DecrementDesignator decDesignator) {
		Code.put(Code.const_1);
		Code.put(Code.sub);
		Code.store(decDesignator.getDesignator().obj);
	}

	/* Designator */

	@Override
	public void visit(AssignmentExpr assignment) {
		Code.store(assignment.getDesignator().obj);
	}

	private boolean isClassFieldOrMethod(Obj obj) {
		Obj first_param = null;
		Iterator<Obj> it = obj.getLocalSymbols().iterator();
		if (it.hasNext()) {
			first_param = it.next();
		}

		if (insideClass && (obj.getKind() == Obj.Fld || obj.getKind() == Obj.Meth && first_param != null
				&& first_param.getName().equals(SemanticAnalyzer.THIS))) {
			return true;
		} else {
			return false;
		}
	}

	private boolean shouldLoadDesignator(Designator designator) {
		if (inDesignatorList) {
			return false;
		} else if (inDesignatorList || designator.obj.getKind() == Obj.Meth
				|| designator.getParent() instanceof DesignatorReal || designator.getParent() instanceof AssignmentExpr
				|| designator.getParent() instanceof DesignatorStatementStmt
				|| designator.getParent() instanceof ReadStatement) {
			return false;
		}
		return true;
	}

	public void visit(DesignatorListStartDummy dummy) {
		inDesignatorList = true;
	}

	public void visit(DesignatorDummy dummy) {
		// Filler spot, so we can know if we should skip element
		designatorLeft.add(null);
	}

	public void visit(DesignatorReal real) {
		if (inDesignatorList)
			designatorLeft.add(real);
	}

	public void visit(MultipleAssignmentStatement stmt) {
		Struct elemType = stmt.getDesignator().obj.getType().getElemType();
		// Now we change the flag so that designators load for repeated traversal!
		inDesignatorList = false;
		// Needed for the later load
		Obj elemObj = new Obj(Obj.Elem, stmt.getDesignator().obj.getName(), elemType);

		int i = -1;
		
		// Check if array is longer than list, otherwise throw trap
		// Depending if we want to detect error with or without dummy elements
//		Code.loadConst(designatorLeft.stream().filter(node -> node != null).toList().size());
		Code.loadConst(designatorLeft.size());
		Code.load(stmt.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.putFalseJump(Code.gt, Code.pc + 5);
		Code.put(Code.trap);
		Code.put(Code.const_1);
		
		for (DesignatorReal node : designatorLeft) {
			++i;

			if (node == null) {
				continue;
			}

			node.traverseBottomUp(this);
			stmt.getDesignator().traverseBottomUp(this);
			Code.loadConst(i);
			Code.load(elemObj);
			Code.store(node.getDesignator().obj);
		}
		// Prepare for next designator list
		designatorLeft.clear();
	}

	public void visit(DesignatorSingle designator) {
		if (isClassFieldOrMethod(designator.obj)) {
			Code.put(Code.load_n);
		}

		if (shouldLoadDesignator(designator)) {
			Code.load(designator.obj);
		}
	}

	public void visit(DesignatorArray designator) {
		if (shouldLoadDesignator(designator)) {
			Code.load(designator.obj);
		}
	}

	public void visit(DesignatorField designator) {
		if (shouldLoadDesignator(designator)) {
			Code.load(designator.obj);
		}
	}

	private void processMethodTypeAndName(SyntaxNode node, String name, Obj obj) {
		if (SemanticAnalyzer.MAIN.equalsIgnoreCase(name)) {
			Code.mainPc = mainPc = Code.pc;

			// When main is defined, all global declaration have been processed
			generateTVF();
		}
		obj.setAdr(Code.pc);

		// Generate the entry.
		Code.put(Code.enter);
		Code.put(obj.getLevel());
		Code.put(obj.getLocalSymbols().size());
	}

	public void visit(MethodTypeName methodTypeName) {
		processMethodTypeAndName(methodTypeName, methodTypeName.getMethName(), methodTypeName.obj);
	}

	public void visit(MethodVoidName methodVoidName) {
		processMethodTypeAndName(methodVoidName, methodVoidName.getMethName(), methodVoidName.obj);
	}

	public void visit(MethodDecl methodDecl) {
		if (methodDecl.getMethodTypeAndName().obj.getType().equals(Tab.noType)) {
			Code.put(Code.exit);
			Code.put(Code.return_);
		} else {
			Code.put(Code.trap);
			Code.put(Code.const_n);
		}
	}

	public void visit(FunctionCall functionCall) {
		Designator designator = functionCall.getFunctionName().getDesignator();
		Obj functionObj = designator.obj;
		if (globalFunctions.contains(functionObj)) {
			int offset = functionObj.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
			return;
		}
		// Virtual function

		// Load 'this' parameter for invokevirtual
		designator.traverseBottomUp(this);

		Code.put(Code.getfield);
		Code.put2(0);

		Code.put(Code.invokevirtual);
		for (char character : functionObj.getName().toCharArray()) {
			Code.put4(character);
		}
		Code.put4(-1);
	}

	public void visit(DesignatorStmtFunctionCall functionCall) {
		Obj obj = functionCall.getFunctionCall().getFunctionName().getDesignator().obj;
		if (!obj.getType().equals(Tab.noType)) {
			Code.put(Code.pop);
		}
	}

	/* Control statements */
	private Stack<Set<Integer>> addressesToFixAfterElseOrUnmatched = new Stack<>();
	private Stack<Set<Integer>> addressesToFixAfterIfMatched = new Stack<>();

	private Set<Integer> addressesToFixAfterOr = new HashSet<>();
	private Set<Integer> addressesToFixAfterControlCondition = new HashSet<>();

	private Stack<Integer> loopStartAddressStack = new Stack<>();
	private Stack<Set<Integer>> addressesToFixAfterLoop = new Stack<>();

	public void visit(IfKeyWord ifKeyWord) {
		if (ifKeyWord.getParent().getParent() instanceof IfElseStatement) {
			addressesToFixAfterIfMatched.push(new HashSet<>());
		}
		addressesToFixAfterElseOrUnmatched.push(new HashSet<>());
	}

	public void visit(ElseKeyWord elseKeyWord) {
		Code.putJump(0);
		addressesToFixAfterIfMatched.peek().add(Code.pc - 2);
		for (Integer address : addressesToFixAfterElseOrUnmatched.pop()) {
			Code.fixup(address);
		}
	}

	public void visit(IfElseStatement ifStatement) {
		for (Integer address : addressesToFixAfterIfMatched.pop()) {
			Code.fixup(address);
		}
	}

	public void visit(IfStatement ifStatement) {
		for (Integer address : addressesToFixAfterElseOrUnmatched.pop()) {
			Code.fixup(address);
		}
	}

	public void visit(WhileKeyWord whileKeyWord) {
		loopStartAddressStack.push(Code.pc);
		addressesToFixAfterLoop.push(new HashSet<>());
	}

	public void visit(WhileStatement whileStatement) {
		Code.putJump(loopStartAddressStack.pop());
		for (Integer address : addressesToFixAfterLoop.pop()) {
			Code.fixup(address);
		}
	}

	public void visit(ControlConditionValid condition) {
		for (Integer address : addressesToFixAfterControlCondition) {
			Code.fixup(address);
		}
		addressesToFixAfterControlCondition = new HashSet<>();
	}

	public void visit(BreakStatement breakStatement) {
		Code.putJump(0);
		addressesToFixAfterLoop.peek().add(Code.pc - 2);
	}

	public void visit(ContinueStatement continueStatement) {
		Code.put(Code.jmp);
		Code.put2(loopStartAddressStack.peek() - Code.pc + 1);
	}

	public void visit(ForeachKeyWord foreachKeyWord) {
		// Putting temp index variable on stack
		Code.put(Code.const_n);
		addressesToFixAfterLoop.push(new HashSet<>());
	}

	public void visit(ForIdent forIdent) {
		// Address that we are looping to
		loopStartAddressStack.push(Code.pc);

		// Stack: ..., adr, ind
		Code.put(Code.dup2); // ..., adr, ind, adr, ind
		Code.put(Code.dup_x2); // ..., adr, ind, ind, adr, ind
		Code.put(Code.pop); // ..., adr, ind, ind, adr
		Code.put(Code.arraylength); // ..., adr, ind, ind, len
		Code.putFalseJump(Code.lt, 0);
		addressesToFixAfterLoop.peek().add(Code.pc - 2);

		// Preparing for new iteration
		// ..., adr, ind
		Code.put(Code.dup2); // ..., adr, ind, adr, ind
		if (forIdent.obj.getType().equals(Tab.charType)) {
			Code.put(Code.baload);
		}
		else {			
			Code.put(Code.aload);
		}
		Code.store(forIdent.obj); // ..., adr, ind
	}

	public void visit(ForeachConstruct foreachConstruct) {
		Code.put(Code.const_1);
		Code.put(Code.add); // Stack: ...,adr, ind + 1

		Code.putJump(loopStartAddressStack.pop());
		for (Integer address : addressesToFixAfterLoop.pop()) {
			Code.fixup(address);
		}

		// Empty stack
		Code.put(Code.pop);
		Code.put(Code.pop);
	}

	public void visit(OrKeyWord orKeyWord) {
		for (Integer address : addressesToFixAfterOr) {
			Code.fixup(address);
		}

		addressesToFixAfterOr = new HashSet<>();
	}

	/* Conditions */
	public void visit(ConditionFactorSingle conditionFactor) {
		// Placeholder variable and operation
		Code.loadConst(1);
		int relOp = Code.eq;

		processConditionFactor(relOp, conditionFactor);
	}

	public void visit(ConditionFactorRel conditionFactor) {
		Relop relopNode = conditionFactor.getRelop();
		int relOp = determineRelOp(relopNode);

		processConditionFactor(relOp, conditionFactor);
	}

	// Condition's helpers
	private void processConditionFactor(int relOp, ConditionFactor conditionFactor) {
		ConditionTerm conditionTerm = getConditionTerm(conditionFactor);
		ControlConditionValid controlCondition = getControlCondition(conditionTerm);

		if (isLastConditionFactor(conditionFactor) && isLastConditionTerm(conditionTerm)) {
			// (x || Y)
			if (isIfCondition(controlCondition)) {
				Code.putFalseJump(relOp, 0);
				addressesToFixAfterElseOrUnmatched.peek().add(Code.pc - 2);
			} else if (isWhileCondition(controlCondition)) {
				Code.putFalseJump(relOp, 0);
				addressesToFixAfterLoop.peek().add(Code.pc - 2);
			}
		} else if (isLastConditionFactor(conditionFactor) && !isLastConditionTerm(conditionTerm)) {
			// (X || y)
			if (isIfCondition(controlCondition) || isWhileCondition(controlCondition)) {
				Code.put(Code.jcc + relOp);
				Code.put2(0);
				addressesToFixAfterControlCondition.add(Code.pc - 2);
			}
		} else if (!isLastConditionFactor(conditionFactor) && isLastConditionTerm(conditionTerm)) {
			// (x || Y && z);
			Code.putFalseJump(relOp, 0);

			if (isIfCondition(controlCondition)) {
				addressesToFixAfterElseOrUnmatched.peek().add(Code.pc - 2);
			} else if (isWhileCondition(controlCondition)) {
				addressesToFixAfterLoop.peek().add(Code.pc - 2);
			}
		} else {
			// (X && y || z)
			Code.putFalseJump(relOp, 0);
			addressesToFixAfterOr.add(Code.pc - 2);
		}
	}

	private int determineRelOp(Relop relationOperator) {
		if (relationOperator instanceof EqualsOp) {
			return Code.eq;
		} else if (relationOperator instanceof NotEqualsOp) {
			return Code.ne;
		} else if (relationOperator instanceof GreaterOp) {
			return Code.gt;
		} else if (relationOperator instanceof LessOp) {
			return Code.lt;
		} else if (relationOperator instanceof GreaterEqualOp) {
			return Code.ge;
		} else {
			return Code.le;
		}
	}

	private boolean isIfCondition(ControlConditionValid controlCondition) {
		return controlCondition.getParent() instanceof IfConstruct;
	}

	private boolean isWhileCondition(ControlConditionValid controlCondition) {
		return controlCondition.getParent() instanceof WhileConstruct;
	}

	private ControlConditionValid getControlCondition(ConditionTerm cond) {
		SyntaxNode node = cond.getParent();
		while (!(node instanceof ControlConditionValid)) {
			node = node.getParent();
		}
		return (ControlConditionValid) node;
	}

	private ConditionTerm getConditionTerm(ConditionFactor condFactor) {
		return (ConditionTerm) condFactor.getParent();
	}

	private boolean isLastConditionFactor(ConditionFactor condFactor) {
		return !(condFactor.getParent().getParent() instanceof ConditionTermAnd);

	}

	private boolean isLastConditionTerm(ConditionTerm condTerm) {
		return !(condTerm.getParent().getParent() instanceof ConditionOr);
	}

}
