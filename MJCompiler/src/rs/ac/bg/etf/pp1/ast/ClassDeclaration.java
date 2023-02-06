// generated with ast extension for cup
// version 0.8
// 6/1/2023 12:48:17


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ProgClassDecl {

    private ClassName ClassName;
    private OptionalClassExtends OptionalClassExtends;
    private VarDeclList VarDeclList;
    private OptionalClassMethodDecl OptionalClassMethodDecl;

    public ClassDeclaration (ClassName ClassName, OptionalClassExtends OptionalClassExtends, VarDeclList VarDeclList, OptionalClassMethodDecl OptionalClassMethodDecl) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.OptionalClassExtends=OptionalClassExtends;
        if(OptionalClassExtends!=null) OptionalClassExtends.setParent(this);
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

    public OptionalClassExtends getOptionalClassExtends() {
        return OptionalClassExtends;
    }

    public void setOptionalClassExtends(OptionalClassExtends OptionalClassExtends) {
        this.OptionalClassExtends=OptionalClassExtends;
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
        if(OptionalClassExtends!=null) OptionalClassExtends.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(OptionalClassExtends!=null) OptionalClassExtends.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(OptionalClassExtends!=null) OptionalClassExtends.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclaration(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalClassExtends!=null)
            buffer.append(OptionalClassExtends.toString("  "+tab));
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
        buffer.append(") [ClassDeclaration]");
        return buffer.toString();
    }
}
