// generated with ast extension for cup
// version 0.8
// 6/1/2023 13:22:10


package rs.ac.bg.etf.pp1.ast;

public class ProgListClass extends ProgDeclList {

    private ProgDeclList ProgDeclList;
    private ProgClassDecl ProgClassDecl;

    public ProgListClass (ProgDeclList ProgDeclList, ProgClassDecl ProgClassDecl) {
        this.ProgDeclList=ProgDeclList;
        if(ProgDeclList!=null) ProgDeclList.setParent(this);
        this.ProgClassDecl=ProgClassDecl;
        if(ProgClassDecl!=null) ProgClassDecl.setParent(this);
    }

    public ProgDeclList getProgDeclList() {
        return ProgDeclList;
    }

    public void setProgDeclList(ProgDeclList ProgDeclList) {
        this.ProgDeclList=ProgDeclList;
    }

    public ProgClassDecl getProgClassDecl() {
        return ProgClassDecl;
    }

    public void setProgClassDecl(ProgClassDecl ProgClassDecl) {
        this.ProgClassDecl=ProgClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgDeclList!=null) ProgDeclList.accept(visitor);
        if(ProgClassDecl!=null) ProgClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgDeclList!=null) ProgDeclList.traverseTopDown(visitor);
        if(ProgClassDecl!=null) ProgClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgDeclList!=null) ProgDeclList.traverseBottomUp(visitor);
        if(ProgClassDecl!=null) ProgClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgListClass(\n");

        if(ProgDeclList!=null)
            buffer.append(ProgDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgClassDecl!=null)
            buffer.append(ProgClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgListClass]");
        return buffer.toString();
    }
}
