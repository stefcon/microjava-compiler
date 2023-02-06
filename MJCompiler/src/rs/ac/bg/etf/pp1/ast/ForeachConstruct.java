// generated with ast extension for cup
// version 0.8
// 6/1/2023 12:48:18


package rs.ac.bg.etf.pp1.ast;

public class ForeachConstruct implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ForeachDesignator ForeachDesignator;
    private ForeachKeyWord ForeachKeyWord;
    private ForIdent ForIdent;
    private Statement Statement;

    public ForeachConstruct (ForeachDesignator ForeachDesignator, ForeachKeyWord ForeachKeyWord, ForIdent ForIdent, Statement Statement) {
        this.ForeachDesignator=ForeachDesignator;
        if(ForeachDesignator!=null) ForeachDesignator.setParent(this);
        this.ForeachKeyWord=ForeachKeyWord;
        if(ForeachKeyWord!=null) ForeachKeyWord.setParent(this);
        this.ForIdent=ForIdent;
        if(ForIdent!=null) ForIdent.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForeachDesignator getForeachDesignator() {
        return ForeachDesignator;
    }

    public void setForeachDesignator(ForeachDesignator ForeachDesignator) {
        this.ForeachDesignator=ForeachDesignator;
    }

    public ForeachKeyWord getForeachKeyWord() {
        return ForeachKeyWord;
    }

    public void setForeachKeyWord(ForeachKeyWord ForeachKeyWord) {
        this.ForeachKeyWord=ForeachKeyWord;
    }

    public ForIdent getForIdent() {
        return ForIdent;
    }

    public void setForIdent(ForIdent ForIdent) {
        this.ForIdent=ForIdent;
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
        if(ForeachDesignator!=null) ForeachDesignator.accept(visitor);
        if(ForeachKeyWord!=null) ForeachKeyWord.accept(visitor);
        if(ForIdent!=null) ForIdent.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForeachDesignator!=null) ForeachDesignator.traverseTopDown(visitor);
        if(ForeachKeyWord!=null) ForeachKeyWord.traverseTopDown(visitor);
        if(ForIdent!=null) ForIdent.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForeachDesignator!=null) ForeachDesignator.traverseBottomUp(visitor);
        if(ForeachKeyWord!=null) ForeachKeyWord.traverseBottomUp(visitor);
        if(ForIdent!=null) ForIdent.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForeachConstruct(\n");

        if(ForeachDesignator!=null)
            buffer.append(ForeachDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachKeyWord!=null)
            buffer.append(ForeachKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForIdent!=null)
            buffer.append(ForIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForeachConstruct]");
        return buffer.toString();
    }
}
