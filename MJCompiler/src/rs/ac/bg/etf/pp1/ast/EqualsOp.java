// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:3:2


package rs.ac.bg.etf.pp1.ast;

public class EqualsOp extends Relop {

    public EqualsOp () {
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
        buffer.append("EqualsOp(\n");

        buffer.append(tab);
        buffer.append(") [EqualsOp]");
        return buffer.toString();
    }
}
