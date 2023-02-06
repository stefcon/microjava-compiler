// generated with ast extension for cup
// version 0.8
// 6/1/2023 12:48:17


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarations extends VarDeclList {

    private VarDeclList VarDeclList;
    private VarDeclStart VarDeclStart;

    public VarDeclarations (VarDeclList VarDeclList, VarDeclStart VarDeclStart) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.VarDeclStart=VarDeclStart;
        if(VarDeclStart!=null) VarDeclStart.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public VarDeclStart getVarDeclStart() {
        return VarDeclStart;
    }

    public void setVarDeclStart(VarDeclStart VarDeclStart) {
        this.VarDeclStart=VarDeclStart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(VarDeclStart!=null) VarDeclStart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(VarDeclStart!=null) VarDeclStart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(VarDeclStart!=null) VarDeclStart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarations(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclStart!=null)
            buffer.append(VarDeclStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarations]");
        return buffer.toString();
    }
}
