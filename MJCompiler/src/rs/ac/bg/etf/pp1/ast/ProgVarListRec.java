// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:3:1


package rs.ac.bg.etf.pp1.ast;

public class ProgVarListRec extends ProgVarList {

    private ProgVarList ProgVarList;
    private ProgVarDeclaration ProgVarDeclaration;

    public ProgVarListRec (ProgVarList ProgVarList, ProgVarDeclaration ProgVarDeclaration) {
        this.ProgVarList=ProgVarList;
        if(ProgVarList!=null) ProgVarList.setParent(this);
        this.ProgVarDeclaration=ProgVarDeclaration;
        if(ProgVarDeclaration!=null) ProgVarDeclaration.setParent(this);
    }

    public ProgVarList getProgVarList() {
        return ProgVarList;
    }

    public void setProgVarList(ProgVarList ProgVarList) {
        this.ProgVarList=ProgVarList;
    }

    public ProgVarDeclaration getProgVarDeclaration() {
        return ProgVarDeclaration;
    }

    public void setProgVarDeclaration(ProgVarDeclaration ProgVarDeclaration) {
        this.ProgVarDeclaration=ProgVarDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgVarList!=null) ProgVarList.accept(visitor);
        if(ProgVarDeclaration!=null) ProgVarDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgVarList!=null) ProgVarList.traverseTopDown(visitor);
        if(ProgVarDeclaration!=null) ProgVarDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgVarList!=null) ProgVarList.traverseBottomUp(visitor);
        if(ProgVarDeclaration!=null) ProgVarDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgVarListRec(\n");

        if(ProgVarList!=null)
            buffer.append(ProgVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgVarDeclaration!=null)
            buffer.append(ProgVarDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgVarListRec]");
        return buffer.toString();
    }
}
