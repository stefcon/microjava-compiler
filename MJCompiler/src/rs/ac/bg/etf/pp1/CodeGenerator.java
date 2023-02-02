package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int varCount;
	private int paramCnt;

	private int mainPc;

	private List<Obj> classList;
	private Set<Obj> globalFunctions;

	private boolean insideClass = false;

	private ArrayList<DesignatorReal> designatorLeft = new ArrayList<>();
	private boolean inDesignatorList = false;

	public int getMainPc() {
		return mainPc;
	}

	CodeGenerator(List<Obj> classList, Set<Obj> globalFunctions) {
		this.classList = classList;
		this.globalFunctions = globalFunctions;
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

	/* Class scopes */
	// TODO:

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
	public void visit(OperatorNewArr newArr) {
		// 0 - char, 1 - int, bool
		int arrayType = 0;
		if (!newArr.getType().struct.equals(Tab.charType)) {
			arrayType = 1;
		}
		Code.put(Code.newarray);
		Code.put(arrayType);
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

		// TODO: Poseban slucaj za klase
	}

	// TODO: multiple assignment for designators...
//	public void visit(MultipleAssignmentStatement assignment) {
//		Code.store()
//	}
//	
	private boolean isClassFieldOrMethod(Obj obj) {
		Obj first_param = null;
		Iterator<Obj> it = obj.getLocalSymbols().iterator();
		if (it.hasNext()) {
			first_param = it.next();
		}

		if (insideClass && (obj.getKind() == Obj.Fld
				|| obj.getKind() == Obj.Meth && first_param != null && first_param.getName().equals("this"))) {
			return true;
		} else {
			return false;
		}
	}

	// TODO: Probaby not needed
	private int isPartOfDesignatorListStmt(Designator designator) {
		if (designator.getParent() instanceof DesignatorReal) {
			// Left side of assignemnt
			return 1;
		} else if (designator.getParent() instanceof MultipleAssignmentStatement) {
			// Right side, array
			return 2;
		}
		return 0;
	}

	private boolean shouldLoadDesignator(Designator designator) {
		int designatorListElemRole = isPartOfDesignatorListStmt(designator);
		if (inDesignatorList) {
			// TODO: After simple designators!
//			if (isPartOfDesignatorListStmt(designator) == 1) {
//				// Left side, save node in a separate list for later visit
//				designatorLeft.add(designator);
//			}

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
		for (DesignatorReal node : designatorLeft) {
			++i;
			// Won't detect if there are more elements 
			// if exception happens on the 'dummy' element!
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
		if (!(isPartOfDesignatorListStmt(designator) == 0) && isClassFieldOrMethod(designator.obj)) {
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

	@Override
	public void visit(MethodTypeName methodTypeName) {
		if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			Code.mainPc = mainPc = Code.pc;

			// TODO: generating vtf
		}
		methodTypeName.obj.setAdr(Code.pc);

		// Collect arguments and local variables.
		SyntaxNode methodNode = methodTypeName.getParent();
		VariableDeclarationCounter varCnt = new VariableDeclarationCounter();
		methodNode.traverseTopDown(varCnt);
		FormalParamCounter fpCnt = new FormalParamCounter();
		methodNode.traverseTopDown(fpCnt);

		// Generate the entry.
		Code.put(Code.enter);
		if (globalFunctions.contains(methodTypeName.obj)) {
			Code.put(fpCnt.getCounter());
			Code.put(varCnt.getCounter() + fpCnt.getCounter());
		} else {
			Code.put(fpCnt.getCounter() + 1);
			Code.put(varCnt.getCounter() + fpCnt.getCounter() + 1);
		}
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
		Obj functionObj = functionCall.getFunctionName().getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		if (globalFunctions.contains(functionObj)) {
			Code.put(Code.call);
			Code.put2(offset);
			return;
		}
		// Virtual function
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

	@Override
	public void visit(VarNameSingle VarDecl) {
		varCount++;
	}

	@Override
	public void visit(VarNameArray VarDecl) {
		varCount++;
	}

	@Override
	public void visit(FormalParamDecl FormalParam) {
		paramCnt++;
	}

}
