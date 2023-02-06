// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:3:2


package rs.ac.bg.etf.pp1.ast;

public class BlockStatement extends Statement {

    private OptionalStatementList OptionalStatementList;

    public BlockStatement (OptionalStatementList OptionalStatementList) {
        this.OptionalStatementList=OptionalStatementList;
        if(OptionalStatementList!=null) OptionalStatementList.setParent(this);
    }

    public OptionalStatementList getOptionalStatementList() {
        return OptionalStatementList;
    }

    public void setOptionalStatementList(OptionalStatementList OptionalStatementList) {
        this.OptionalStatementList=OptionalStatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalStatementList!=null) OptionalStatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalStatementList!=null) OptionalStatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalStatementList!=null) OptionalStatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BlockStatement(\n");

        if(OptionalStatementList!=null)
            buffer.append(OptionalStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BlockStatement]");
        return buffer.toString();
    }
}
