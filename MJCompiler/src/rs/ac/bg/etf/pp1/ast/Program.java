// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ProgName ProgName;
    private ProgDeclList ProgDeclList;
    private OptionalMethodDeclList OptionalMethodDeclList;

    public Program (ProgName ProgName, ProgDeclList ProgDeclList, OptionalMethodDeclList OptionalMethodDeclList) {
        this.ProgName=ProgName;
        if(ProgName!=null) ProgName.setParent(this);
        this.ProgDeclList=ProgDeclList;
        if(ProgDeclList!=null) ProgDeclList.setParent(this);
        this.OptionalMethodDeclList=OptionalMethodDeclList;
        if(OptionalMethodDeclList!=null) OptionalMethodDeclList.setParent(this);
    }

    public ProgName getProgName() {
        return ProgName;
    }

    public void setProgName(ProgName ProgName) {
        this.ProgName=ProgName;
    }

    public ProgDeclList getProgDeclList() {
        return ProgDeclList;
    }

    public void setProgDeclList(ProgDeclList ProgDeclList) {
        this.ProgDeclList=ProgDeclList;
    }

    public OptionalMethodDeclList getOptionalMethodDeclList() {
        return OptionalMethodDeclList;
    }

    public void setOptionalMethodDeclList(OptionalMethodDeclList OptionalMethodDeclList) {
        this.OptionalMethodDeclList=OptionalMethodDeclList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgName!=null) ProgName.accept(visitor);
        if(ProgDeclList!=null) ProgDeclList.accept(visitor);
        if(OptionalMethodDeclList!=null) OptionalMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgName!=null) ProgName.traverseTopDown(visitor);
        if(ProgDeclList!=null) ProgDeclList.traverseTopDown(visitor);
        if(OptionalMethodDeclList!=null) OptionalMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgName!=null) ProgName.traverseBottomUp(visitor);
        if(ProgDeclList!=null) ProgDeclList.traverseBottomUp(visitor);
        if(OptionalMethodDeclList!=null) OptionalMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        if(ProgName!=null)
            buffer.append(ProgName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgDeclList!=null)
            buffer.append(ProgDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalMethodDeclList!=null)
            buffer.append(OptionalMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
