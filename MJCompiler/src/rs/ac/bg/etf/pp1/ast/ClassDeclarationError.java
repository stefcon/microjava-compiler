// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:3:2


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarationError extends ProgClassDecl {

    private ClassName ClassName;
    private VarDeclList VarDeclList;
    private OptionalClassMethodDecl OptionalClassMethodDecl;

    public ClassDeclarationError (ClassName ClassName, VarDeclList VarDeclList, OptionalClassMethodDecl OptionalClassMethodDecl) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.OptionalClassMethodDecl=OptionalClassMethodDecl;
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public OptionalClassMethodDecl getOptionalClassMethodDecl() {
        return OptionalClassMethodDecl;
    }

    public void setOptionalClassMethodDecl(OptionalClassMethodDecl OptionalClassMethodDecl) {
        this.OptionalClassMethodDecl=OptionalClassMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassName!=null) ClassName.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclarationError(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalClassMethodDecl!=null)
            buffer.append(OptionalClassMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclarationError]");
        return buffer.toString();
    }
}
