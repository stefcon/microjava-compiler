// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class WhileStatement extends Statement {

    private WhileConstruct WhileConstruct;

    public WhileStatement (WhileConstruct WhileConstruct) {
        this.WhileConstruct=WhileConstruct;
        if(WhileConstruct!=null) WhileConstruct.setParent(this);
    }

    public WhileConstruct getWhileConstruct() {
        return WhileConstruct;
    }

    public void setWhileConstruct(WhileConstruct WhileConstruct) {
        this.WhileConstruct=WhileConstruct;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WhileConstruct!=null) WhileConstruct.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileConstruct!=null) WhileConstruct.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileConstruct!=null) WhileConstruct.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileStatement(\n");

        if(WhileConstruct!=null)
            buffer.append(WhileConstruct.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileStatement]");
        return buffer.toString();
    }
}
