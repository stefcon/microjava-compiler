// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListCommaElem extends DesignatorListContent {

    private DesignatorDummy DesignatorDummy;
    private DesignatorListContent DesignatorListContent;

    public DesignatorListCommaElem (DesignatorDummy DesignatorDummy, DesignatorListContent DesignatorListContent) {
        this.DesignatorDummy=DesignatorDummy;
        if(DesignatorDummy!=null) DesignatorDummy.setParent(this);
        this.DesignatorListContent=DesignatorListContent;
        if(DesignatorListContent!=null) DesignatorListContent.setParent(this);
    }

    public DesignatorDummy getDesignatorDummy() {
        return DesignatorDummy;
    }

    public void setDesignatorDummy(DesignatorDummy DesignatorDummy) {
        this.DesignatorDummy=DesignatorDummy;
    }

    public DesignatorListContent getDesignatorListContent() {
        return DesignatorListContent;
    }

    public void setDesignatorListContent(DesignatorListContent DesignatorListContent) {
        this.DesignatorListContent=DesignatorListContent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorDummy!=null) DesignatorDummy.accept(visitor);
        if(DesignatorListContent!=null) DesignatorListContent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorDummy!=null) DesignatorDummy.traverseTopDown(visitor);
        if(DesignatorListContent!=null) DesignatorListContent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorDummy!=null) DesignatorDummy.traverseBottomUp(visitor);
        if(DesignatorListContent!=null) DesignatorListContent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListCommaElem(\n");

        if(DesignatorDummy!=null)
            buffer.append(DesignatorDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorListContent!=null)
            buffer.append(DesignatorListContent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListCommaElem]");
        return buffer.toString();
    }
}
