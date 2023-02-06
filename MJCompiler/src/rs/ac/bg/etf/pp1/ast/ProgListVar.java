// generated with ast extension for cup
// version 0.8
// 6/1/2023 12:48:17


package rs.ac.bg.etf.pp1.ast;

public class ProgListVar extends ProgDeclList {

    private ProgDeclList ProgDeclList;
    private ProgVarDeclStart ProgVarDeclStart;

    public ProgListVar (ProgDeclList ProgDeclList, ProgVarDeclStart ProgVarDeclStart) {
        this.ProgDeclList=ProgDeclList;
        if(ProgDeclList!=null) ProgDeclList.setParent(this);
        this.ProgVarDeclStart=ProgVarDeclStart;
        if(ProgVarDeclStart!=null) ProgVarDeclStart.setParent(this);
    }

    public ProgDeclList getProgDeclList() {
        return ProgDeclList;
    }

    public void setProgDeclList(ProgDeclList ProgDeclList) {
        this.ProgDeclList=ProgDeclList;
    }

    public ProgVarDeclStart getProgVarDeclStart() {
        return ProgVarDeclStart;
    }

    public void setProgVarDeclStart(ProgVarDeclStart ProgVarDeclStart) {
        this.ProgVarDeclStart=ProgVarDeclStart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgDeclList!=null) ProgDeclList.accept(visitor);
        if(ProgVarDeclStart!=null) ProgVarDeclStart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgDeclList!=null) ProgDeclList.traverseTopDown(visitor);
        if(ProgVarDeclStart!=null) ProgVarDeclStart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgDeclList!=null) ProgDeclList.traverseBottomUp(visitor);
        if(ProgVarDeclStart!=null) ProgVarDeclStart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgListVar(\n");

        if(ProgDeclList!=null)
            buffer.append(ProgDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgVarDeclStart!=null)
            buffer.append(ProgVarDeclStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgListVar]");
        return buffer.toString();
    }
}
