// generated with ast extension for cup
// version 0.8
// 6/1/2023 22:13:11


package rs.ac.bg.etf.pp1.ast;

public class MultipleAssignmentStatement extends DesignatorStatement {

    private DesignatorListStart DesignatorListStart;
    private Designator Designator;

    public MultipleAssignmentStatement (DesignatorListStart DesignatorListStart, Designator Designator) {
        this.DesignatorListStart=DesignatorListStart;
        if(DesignatorListStart!=null) DesignatorListStart.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignatorListStart getDesignatorListStart() {
        return DesignatorListStart;
    }

    public void setDesignatorListStart(DesignatorListStart DesignatorListStart) {
        this.DesignatorListStart=DesignatorListStart;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorListStart!=null) DesignatorListStart.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorListStart!=null) DesignatorListStart.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorListStart!=null) DesignatorListStart.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleAssignmentStatement(\n");

        if(DesignatorListStart!=null)
            buffer.append(DesignatorListStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleAssignmentStatement]");
        return buffer.toString();
    }
}
