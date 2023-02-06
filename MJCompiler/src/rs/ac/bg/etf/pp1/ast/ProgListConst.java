// generated with ast extension for cup
// version 0.8
// 6/1/2023 12:48:17


package rs.ac.bg.etf.pp1.ast;

public class ProgListConst extends ProgDeclList {

    private ProgDeclList ProgDeclList;
    private ConstDeclStart ConstDeclStart;

    public ProgListConst (ProgDeclList ProgDeclList, ConstDeclStart ConstDeclStart) {
        this.ProgDeclList=ProgDeclList;
        if(ProgDeclList!=null) ProgDeclList.setParent(this);
        this.ConstDeclStart=ConstDeclStart;
        if(ConstDeclStart!=null) ConstDeclStart.setParent(this);
    }

    public ProgDeclList getProgDeclList() {
        return ProgDeclList;
    }

    public void setProgDeclList(ProgDeclList ProgDeclList) {
        this.ProgDeclList=ProgDeclList;
    }

    public ConstDeclStart getConstDeclStart() {
        return ConstDeclStart;
    }

    public void setConstDeclStart(ConstDeclStart ConstDeclStart) {
        this.ConstDeclStart=ConstDeclStart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgDeclList!=null) ProgDeclList.accept(visitor);
        if(ConstDeclStart!=null) ConstDeclStart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgDeclList!=null) ProgDeclList.traverseTopDown(visitor);
        if(ConstDeclStart!=null) ConstDeclStart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgDeclList!=null) ProgDeclList.traverseBottomUp(visitor);
        if(ConstDeclStart!=null) ConstDeclStart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgListConst(\n");

        if(ProgDeclList!=null)
            buffer.append(ProgDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclStart!=null)
            buffer.append(ConstDeclStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgListConst]");
        return buffer.toString();
    }
}
