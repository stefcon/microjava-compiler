// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:13:11


package rs.ac.bg.etf.pp1.ast;

public class NoClassExtends extends OptionalClassExtends {

    public NoClassExtends () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoClassExtends(\n");

        buffer.append(tab);
        buffer.append(") [NoClassExtends]");
        return buffer.toString();
    }
}