// generated with ast extension for cup
// version 0.8
// 8/1/2023 10:34:51


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl extends MethodDeclaration {

    private MethodTypeAndName MethodTypeAndName;
    private FormalParams FormalParams;
    private VarDeclList VarDeclList;
    private OptionalStatementList OptionalStatementList;

    public MethodDecl (MethodTypeAndName MethodTypeAndName, FormalParams FormalParams, VarDeclList VarDeclList, OptionalStatementList OptionalStatementList) {
        this.MethodTypeAndName=MethodTypeAndName;
        if(MethodTypeAndName!=null) MethodTypeAndName.setParent(this);
        this.FormalParams=FormalParams;
        if(FormalParams!=null) FormalParams.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.OptionalStatementList=OptionalStatementList;
        if(OptionalStatementList!=null) OptionalStatementList.setParent(this);
    }

    public MethodTypeAndName getMethodTypeAndName() {
        return MethodTypeAndName;
    }

    public void setMethodTypeAndName(MethodTypeAndName MethodTypeAndName) {
        this.MethodTypeAndName=MethodTypeAndName;
    }

    public FormalParams getFormalParams() {
        return FormalParams;
    }

    public void setFormalParams(FormalParams FormalParams) {
        this.FormalParams=FormalParams;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public OptionalStatementList getOptionalStatementList() {
        return OptionalStatementList;
    }

    public void setOptionalStatementList(OptionalStatementList OptionalStatementList) {
        this.OptionalStatementList=OptionalStatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodTypeAndName!=null) MethodTypeAndName.accept(visitor);
        if(FormalParams!=null) FormalParams.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(OptionalStatementList!=null) OptionalStatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodTypeAndName!=null) MethodTypeAndName.traverseTopDown(visitor);
        if(FormalParams!=null) FormalParams.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(OptionalStatementList!=null) OptionalStatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodTypeAndName!=null) MethodTypeAndName.traverseBottomUp(visitor);
        if(FormalParams!=null) FormalParams.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(OptionalStatementList!=null) OptionalStatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodTypeAndName!=null)
            buffer.append(MethodTypeAndName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParams!=null)
            buffer.append(FormalParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalStatementList!=null)
            buffer.append(OptionalStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
