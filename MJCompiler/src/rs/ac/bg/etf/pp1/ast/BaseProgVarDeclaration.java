// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class BaseProgVarDeclaration extends ProgVarList {

    private ProgVarDeclaration ProgVarDeclaration;

    public BaseProgVarDeclaration (ProgVarDeclaration ProgVarDeclaration) {
        this.ProgVarDeclaration=ProgVarDeclaration;
        if(ProgVarDeclaration!=null) ProgVarDeclaration.setParent(this);
    }

    public ProgVarDeclaration getProgVarDeclaration() {
        return ProgVarDeclaration;
    }

    public void setProgVarDeclaration(ProgVarDeclaration ProgVarDeclaration) {
        this.ProgVarDeclaration=ProgVarDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgVarDeclaration!=null) ProgVarDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgVarDeclaration!=null) ProgVarDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgVarDeclaration!=null) ProgVarDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BaseProgVarDeclaration(\n");

        if(ProgVarDeclaration!=null)
            buffer.append(ProgVarDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BaseProgVarDeclaration]");
        return buffer.toString();
    }
}
