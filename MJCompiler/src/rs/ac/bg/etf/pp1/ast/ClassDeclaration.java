// generated with ast extension for cup
// version 0.8
// 8/1/2023 10:34:51


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ProgClassDecl {

    private ClassName ClassName;
    private OptionalClassExtends OptionalClassExtends;
    private FieldDeclList FieldDeclList;
    private OptionalClassMethodDecl OptionalClassMethodDecl;

    public ClassDeclaration (ClassName ClassName, OptionalClassExtends OptionalClassExtends, FieldDeclList FieldDeclList, OptionalClassMethodDecl OptionalClassMethodDecl) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.OptionalClassExtends=OptionalClassExtends;
        if(OptionalClassExtends!=null) OptionalClassExtends.setParent(this);
        this.FieldDeclList=FieldDeclList;
        if(FieldDeclList!=null) FieldDeclList.setParent(this);
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

    public FieldDeclList getFieldDeclList() {
        return FieldDeclList;
    }

    public void setFieldDeclList(FieldDeclList FieldDeclList) {
        this.FieldDeclList=FieldDeclList;
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
        if(FieldDeclList!=null) FieldDeclList.accept(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(OptionalClassExtends!=null) OptionalClassExtends.traverseTopDown(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseTopDown(visitor);
        if(OptionalClassMethodDecl!=null) OptionalClassMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(OptionalClassExtends!=null) OptionalClassExtends.traverseBottomUp(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseBottomUp(visitor);
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

        if(FieldDeclList!=null)
            buffer.append(FieldDeclList.toString("  "+tab));
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
