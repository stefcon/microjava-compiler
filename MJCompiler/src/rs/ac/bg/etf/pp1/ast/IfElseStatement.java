// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends Statement {

    private IfConstruct IfConstruct;
    private ElseKeyWord ElseKeyWord;
    private Statement Statement;

    public IfElseStatement (IfConstruct IfConstruct, ElseKeyWord ElseKeyWord, Statement Statement) {
        this.IfConstruct=IfConstruct;
        if(IfConstruct!=null) IfConstruct.setParent(this);
        this.ElseKeyWord=ElseKeyWord;
        if(ElseKeyWord!=null) ElseKeyWord.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public IfConstruct getIfConstruct() {
        return IfConstruct;
    }

    public void setIfConstruct(IfConstruct IfConstruct) {
        this.IfConstruct=IfConstruct;
    }

    public ElseKeyWord getElseKeyWord() {
        return ElseKeyWord;
    }

    public void setElseKeyWord(ElseKeyWord ElseKeyWord) {
        this.ElseKeyWord=ElseKeyWord;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfConstruct!=null) IfConstruct.accept(visitor);
        if(ElseKeyWord!=null) ElseKeyWord.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfConstruct!=null) IfConstruct.traverseTopDown(visitor);
        if(ElseKeyWord!=null) ElseKeyWord.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfConstruct!=null) IfConstruct.traverseBottomUp(visitor);
        if(ElseKeyWord!=null) ElseKeyWord.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

        if(IfConstruct!=null)
            buffer.append(IfConstruct.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseKeyWord!=null)
            buffer.append(ElseKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}
