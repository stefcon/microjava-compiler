// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:13:11


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListCommaElem extends DesignatorListContent {

    private DesignatorListContent DesignatorListContent;
    private DesignatorDummy DesignatorDummy;

    public DesignatorListCommaElem (DesignatorListContent DesignatorListContent, DesignatorDummy DesignatorDummy) {
        this.DesignatorListContent=DesignatorListContent;
        if(DesignatorListContent!=null) DesignatorListContent.setParent(this);
        this.DesignatorDummy=DesignatorDummy;
        if(DesignatorDummy!=null) DesignatorDummy.setParent(this);
    }

    public DesignatorListContent getDesignatorListContent() {
        return DesignatorListContent;
    }

    public void setDesignatorListContent(DesignatorListContent DesignatorListContent) {
        this.DesignatorListContent=DesignatorListContent;
    }

    public DesignatorDummy getDesignatorDummy() {
        return DesignatorDummy;
    }

    public void setDesignatorDummy(DesignatorDummy DesignatorDummy) {
        this.DesignatorDummy=DesignatorDummy;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorListContent!=null) DesignatorListContent.accept(visitor);
        if(DesignatorDummy!=null) DesignatorDummy.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorListContent!=null) DesignatorListContent.traverseTopDown(visitor);
        if(DesignatorDummy!=null) DesignatorDummy.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorListContent!=null) DesignatorListContent.traverseBottomUp(visitor);
        if(DesignatorDummy!=null) DesignatorDummy.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListCommaElem(\n");

        if(DesignatorListContent!=null)
            buffer.append(DesignatorListContent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorDummy!=null)
            buffer.append(DesignatorDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListCommaElem]");
        return buffer.toString();
    }
}
