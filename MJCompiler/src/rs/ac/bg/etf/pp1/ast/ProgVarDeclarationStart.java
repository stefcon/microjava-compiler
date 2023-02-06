// generated with ast extension for cup
// version 0.8
// 6/1/2023 13:22:10


package rs.ac.bg.etf.pp1.ast;

public class ProgVarDeclarationStart extends ProgVarDeclStart {

    private ProgVarType ProgVarType;
    private ProgVarList ProgVarList;

    public ProgVarDeclarationStart (ProgVarType ProgVarType, ProgVarList ProgVarList) {
        this.ProgVarType=ProgVarType;
        if(ProgVarType!=null) ProgVarType.setParent(this);
        this.ProgVarList=ProgVarList;
        if(ProgVarList!=null) ProgVarList.setParent(this);
    }

    public ProgVarType getProgVarType() {
        return ProgVarType;
    }

    public void setProgVarType(ProgVarType ProgVarType) {
        this.ProgVarType=ProgVarType;
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
        if(ProgVarType!=null) ProgVarType.accept(visitor);
        if(ProgVarList!=null) ProgVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgVarType!=null) ProgVarType.traverseTopDown(visitor);
        if(ProgVarList!=null) ProgVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgVarType!=null) ProgVarType.traverseBottomUp(visitor);
        if(ProgVarList!=null) ProgVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgVarDeclarationStart(\n");

        if(ProgVarType!=null)
            buffer.append(ProgVarType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgVarList!=null)
            buffer.append(ProgVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgVarDeclarationStart]");
        return buffer.toString();
    }
}
