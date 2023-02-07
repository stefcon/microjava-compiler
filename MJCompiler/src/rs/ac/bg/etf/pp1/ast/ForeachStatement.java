// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class ForeachStatement extends Statement {

    private ForeachConstruct ForeachConstruct;

    public ForeachStatement (ForeachConstruct ForeachConstruct) {
        this.ForeachConstruct=ForeachConstruct;
        if(ForeachConstruct!=null) ForeachConstruct.setParent(this);
    }

    public ForeachConstruct getForeachConstruct() {
        return ForeachConstruct;
    }

    public void setForeachConstruct(ForeachConstruct ForeachConstruct) {
        this.ForeachConstruct=ForeachConstruct;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForeachConstruct!=null) ForeachConstruct.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForeachConstruct!=null) ForeachConstruct.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForeachConstruct!=null) ForeachConstruct.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForeachStatement(\n");

        if(ForeachConstruct!=null)
            buffer.append(ForeachConstruct.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForeachStatement]");
        return buffer.toString();
    }
}
