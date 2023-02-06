// generated with ast extension for cup
// version 0.8
// 6/1/2023 13:58:23


package rs.ac.bg.etf.pp1.ast;

public class ActualParamListRec extends ActualParamList {

    private ActualParamList ActualParamList;
    private ActualParameter ActualParameter;

    public ActualParamListRec (ActualParamList ActualParamList, ActualParameter ActualParameter) {
        this.ActualParamList=ActualParamList;
        if(ActualParamList!=null) ActualParamList.setParent(this);
        this.ActualParameter=ActualParameter;
        if(ActualParameter!=null) ActualParameter.setParent(this);
    }

    public ActualParamList getActualParamList() {
        return ActualParamList;
    }

    public void setActualParamList(ActualParamList ActualParamList) {
        this.ActualParamList=ActualParamList;
    }

    public ActualParameter getActualParameter() {
        return ActualParameter;
    }

    public void setActualParameter(ActualParameter ActualParameter) {
        this.ActualParameter=ActualParameter;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParamList!=null) ActualParamList.accept(visitor);
        if(ActualParameter!=null) ActualParameter.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParamList!=null) ActualParamList.traverseTopDown(visitor);
        if(ActualParameter!=null) ActualParameter.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParamList!=null) ActualParamList.traverseBottomUp(visitor);
        if(ActualParameter!=null) ActualParameter.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParamListRec(\n");

        if(ActualParamList!=null)
            buffer.append(ActualParamList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParameter!=null)
            buffer.append(ActualParameter.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParamListRec]");
        return buffer.toString();
    }
}
