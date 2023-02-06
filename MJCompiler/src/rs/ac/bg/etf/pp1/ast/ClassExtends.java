// generated with ast extension for cup
// version 0.8
// 6/1/2023 13:58:22


package rs.ac.bg.etf.pp1.ast;

public class ClassExtends extends OptionalClassExtends {

    private SuperClass SuperClass;

    public ClassExtends (SuperClass SuperClass) {
        this.SuperClass=SuperClass;
        if(SuperClass!=null) SuperClass.setParent(this);
    }

    public SuperClass getSuperClass() {
        return SuperClass;
    }

    public void setSuperClass(SuperClass SuperClass) {
        this.SuperClass=SuperClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SuperClass!=null) SuperClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SuperClass!=null) SuperClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SuperClass!=null) SuperClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassExtends(\n");

        if(SuperClass!=null)
            buffer.append(SuperClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassExtends]");
        return buffer.toString();
    }
}
