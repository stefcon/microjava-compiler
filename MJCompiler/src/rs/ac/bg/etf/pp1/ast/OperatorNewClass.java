// generated with ast extension for cup
// version 0.8
// 6/1/2023 13:58:23


package rs.ac.bg.etf.pp1.ast;

public class OperatorNewClass extends Factor {

    private ClassNewType ClassNewType;
    private OptionalMethodActualParams OptionalMethodActualParams;

    public OperatorNewClass (ClassNewType ClassNewType, OptionalMethodActualParams OptionalMethodActualParams) {
        this.ClassNewType=ClassNewType;
        if(ClassNewType!=null) ClassNewType.setParent(this);
        this.OptionalMethodActualParams=OptionalMethodActualParams;
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.setParent(this);
    }

    public ClassNewType getClassNewType() {
        return ClassNewType;
    }

    public void setClassNewType(ClassNewType ClassNewType) {
        this.ClassNewType=ClassNewType;
    }

    public OptionalMethodActualParams getOptionalMethodActualParams() {
        return OptionalMethodActualParams;
    }

    public void setOptionalMethodActualParams(OptionalMethodActualParams OptionalMethodActualParams) {
        this.OptionalMethodActualParams=OptionalMethodActualParams;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassNewType!=null) ClassNewType.accept(visitor);
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassNewType!=null) ClassNewType.traverseTopDown(visitor);
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassNewType!=null) ClassNewType.traverseBottomUp(visitor);
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OperatorNewClass(\n");

        if(ClassNewType!=null)
            buffer.append(ClassNewType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalMethodActualParams!=null)
            buffer.append(OptionalMethodActualParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OperatorNewClass]");
        return buffer.toString();
    }
}
