// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class ProgVarListRec extends ProgVarList {

    private ProgVarDeclaration ProgVarDeclaration;
    private ProgVarList ProgVarList;

    public ProgVarListRec (ProgVarDeclaration ProgVarDeclaration, ProgVarList ProgVarList) {
        this.ProgVarDeclaration=ProgVarDeclaration;
        if(ProgVarDeclaration!=null) ProgVarDeclaration.setParent(this);
        this.ProgVarList=ProgVarList;
        if(ProgVarList!=null) ProgVarList.setParent(this);
    }

    public ProgVarDeclaration getProgVarDeclaration() {
        return ProgVarDeclaration;
    }

    public void setProgVarDeclaration(ProgVarDeclaration ProgVarDeclaration) {
        this.ProgVarDeclaration=ProgVarDeclaration;
    }

    public ProgVarList getProgVarList() {
        return ProgVarList;
    }

    public void setProgVarList(ProgVarList ProgVarList) {
        this.ProgVarList=ProgVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgVarDeclaration!=null) ProgVarDeclaration.accept(visitor);
        if(ProgVarList!=null) ProgVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgVarDeclaration!=null) ProgVarDeclaration.traverseTopDown(visitor);
        if(ProgVarList!=null) ProgVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgVarDeclaration!=null) ProgVarDeclaration.traverseBottomUp(visitor);
        if(ProgVarList!=null) ProgVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgVarListRec(\n");

        if(ProgVarDeclaration!=null)
            buffer.append(ProgVarDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgVarList!=null)
            buffer.append(ProgVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgVarListRec]");
        return buffer.toString();
    }
}
