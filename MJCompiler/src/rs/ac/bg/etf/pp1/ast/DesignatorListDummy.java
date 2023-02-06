// generated with ast extension for cup
// version 0.8
// 6/1/2023 13:22:10


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListDummy extends DesignatorListContent {

    private DesignatorDummy DesignatorDummy;

    public DesignatorListDummy (DesignatorDummy DesignatorDummy) {
        this.DesignatorDummy=DesignatorDummy;
        if(DesignatorDummy!=null) DesignatorDummy.setParent(this);
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
        if(DesignatorDummy!=null) DesignatorDummy.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorDummy!=null) DesignatorDummy.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorDummy!=null) DesignatorDummy.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListDummy(\n");

        if(DesignatorDummy!=null)
            buffer.append(DesignatorDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListDummy]");
        return buffer.toString();
    }
}
