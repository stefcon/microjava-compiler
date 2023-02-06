// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:3:2


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListStart implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private DesignatorListStartDummy DesignatorListStartDummy;
    private DesignatorListContent DesignatorListContent;

    public DesignatorListStart (DesignatorListStartDummy DesignatorListStartDummy, DesignatorListContent DesignatorListContent) {
        this.DesignatorListStartDummy=DesignatorListStartDummy;
        if(DesignatorListStartDummy!=null) DesignatorListStartDummy.setParent(this);
        this.DesignatorListContent=DesignatorListContent;
        if(DesignatorListContent!=null) DesignatorListContent.setParent(this);
    }

    public DesignatorListStartDummy getDesignatorListStartDummy() {
        return DesignatorListStartDummy;
    }

    public void setDesignatorListStartDummy(DesignatorListStartDummy DesignatorListStartDummy) {
        this.DesignatorListStartDummy=DesignatorListStartDummy;
    }

    public DesignatorListContent getDesignatorListContent() {
        return DesignatorListContent;
    }

    public void setDesignatorListContent(DesignatorListContent DesignatorListContent) {
        this.DesignatorListContent=DesignatorListContent;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorListStartDummy!=null) DesignatorListStartDummy.accept(visitor);
        if(DesignatorListContent!=null) DesignatorListContent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorListStartDummy!=null) DesignatorListStartDummy.traverseTopDown(visitor);
        if(DesignatorListContent!=null) DesignatorListContent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorListStartDummy!=null) DesignatorListStartDummy.traverseBottomUp(visitor);
        if(DesignatorListContent!=null) DesignatorListContent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListStart(\n");

        if(DesignatorListStartDummy!=null)
            buffer.append(DesignatorListStartDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorListContent!=null)
            buffer.append(DesignatorListContent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListStart]");
        return buffer.toString();
    }
}
