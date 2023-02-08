// generated with ast extension for cup
// version 0.8
// 8/1/2023 10:34:51


package rs.ac.bg.etf.pp1.ast;

public class AssignmentStatement extends DesignatorStatement {

    private AssignmentStatementExpr AssignmentStatementExpr;

    public AssignmentStatement (AssignmentStatementExpr AssignmentStatementExpr) {
        this.AssignmentStatementExpr=AssignmentStatementExpr;
        if(AssignmentStatementExpr!=null) AssignmentStatementExpr.setParent(this);
    }

    public AssignmentStatementExpr getAssignmentStatementExpr() {
        return AssignmentStatementExpr;
    }

    public void setAssignmentStatementExpr(AssignmentStatementExpr AssignmentStatementExpr) {
        this.AssignmentStatementExpr=AssignmentStatementExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssignmentStatementExpr!=null) AssignmentStatementExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignmentStatementExpr!=null) AssignmentStatementExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignmentStatementExpr!=null) AssignmentStatementExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignmentStatement(\n");

        if(AssignmentStatementExpr!=null)
            buffer.append(AssignmentStatementExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignmentStatement]");
        return buffer.toString();
    }
}
