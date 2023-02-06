package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {

	protected int counter = 0;

	public int getCounter() {
		return counter;
	}

	public static class FormalParamCounter extends CounterVisitor {
		public void visit(FormalParamDeclSingle formParam) {
			counter++;
		}

		public void visit(FormalParamDeclArray formParam) {
			counter++;
		}
	}

	public static class VariableDeclarationCounter extends CounterVisitor {
		public void visit(VarNameSingle var) {
			counter++;
		}

		public void visit(VarNameArray var) {
			counter++;
		}
	}

}