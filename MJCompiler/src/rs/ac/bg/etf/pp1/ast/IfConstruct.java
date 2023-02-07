// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class IfConstruct implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private IfKeyWord IfKeyWord;
    private ControlCondition ControlCondition;
    private Statement Statement;

    public IfConstruct (IfKeyWord IfKeyWord, ControlCondition ControlCondition, Statement Statement) {
        this.IfKeyWord=IfKeyWord;
        if(IfKeyWord!=null) IfKeyWord.setParent(this);
        this.ControlCondition=ControlCondition;
        if(ControlCondition!=null) ControlCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public IfKeyWord getIfKeyWord() {
        return IfKeyWord;
    }

    public void setIfKeyWord(IfKeyWord IfKeyWord) {
        this.IfKeyWord=IfKeyWord;
    }

    public ControlCondition getControlCondition() {
        return ControlCondition;
    }

    public void setControlCondition(ControlCondition ControlCondition) {
        this.ControlCondition=ControlCondition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
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
        if(IfKeyWord!=null) IfKeyWord.accept(visitor);
        if(ControlCondition!=null) ControlCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfKeyWord!=null) IfKeyWord.traverseTopDown(visitor);
        if(ControlCondition!=null) ControlCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfKeyWord!=null) IfKeyWord.traverseBottomUp(visitor);
        if(ControlCondition!=null) ControlCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfConstruct(\n");

        if(IfKeyWord!=null)
            buffer.append(IfKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ControlCondition!=null)
            buffer.append(ControlCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfConstruct]");
        return buffer.toString();
    }
}
