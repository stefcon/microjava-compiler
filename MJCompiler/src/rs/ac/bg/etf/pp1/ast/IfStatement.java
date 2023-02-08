// generated with ast extension for cup
// version 0.8
// 8/1/2023 10:34:51


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private IfConstruct IfConstruct;

    public IfStatement (IfConstruct IfConstruct) {
        this.IfConstruct=IfConstruct;
        if(IfConstruct!=null) IfConstruct.setParent(this);
    }

    public IfConstruct getIfConstruct() {
        return IfConstruct;
    }

    public void setIfConstruct(IfConstruct IfConstruct) {
        this.IfConstruct=IfConstruct;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfConstruct!=null) IfConstruct.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfConstruct!=null) IfConstruct.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfConstruct!=null) IfConstruct.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(IfConstruct!=null)
            buffer.append(IfConstruct.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
