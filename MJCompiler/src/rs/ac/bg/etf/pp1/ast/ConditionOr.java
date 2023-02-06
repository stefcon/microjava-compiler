// generated with ast extension for cup
// version 0.8
// 6/1/2023 13:22:10


package rs.ac.bg.etf.pp1.ast;

public class ConditionOr extends Condition {

    private Condition Condition;
    private OrKeyWord OrKeyWord;
    private ConditionTerm ConditionTerm;

    public ConditionOr (Condition Condition, OrKeyWord OrKeyWord, ConditionTerm ConditionTerm) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.OrKeyWord=OrKeyWord;
        if(OrKeyWord!=null) OrKeyWord.setParent(this);
        this.ConditionTerm=ConditionTerm;
        if(ConditionTerm!=null) ConditionTerm.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public OrKeyWord getOrKeyWord() {
        return OrKeyWord;
    }

    public void setOrKeyWord(OrKeyWord OrKeyWord) {
        this.OrKeyWord=OrKeyWord;
    }

    public ConditionTerm getConditionTerm() {
        return ConditionTerm;
    }

    public void setConditionTerm(ConditionTerm ConditionTerm) {
        this.ConditionTerm=ConditionTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(OrKeyWord!=null) OrKeyWord.accept(visitor);
        if(ConditionTerm!=null) ConditionTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(OrKeyWord!=null) OrKeyWord.traverseTopDown(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(OrKeyWord!=null) OrKeyWord.traverseBottomUp(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionOr(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrKeyWord!=null)
            buffer.append(OrKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionTerm!=null)
            buffer.append(ConditionTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionOr]");
        return buffer.toString();
    }
}
