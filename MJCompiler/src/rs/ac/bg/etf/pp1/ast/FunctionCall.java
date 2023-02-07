// generated with ast extension for cup
// version 0.8
// 7/1/2023 14:26:37


package rs.ac.bg.etf.pp1.ast;

public class FunctionCall implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private FunctionName FunctionName;
    private OptionalMethodActualParams OptionalMethodActualParams;

    public FunctionCall (FunctionName FunctionName, OptionalMethodActualParams OptionalMethodActualParams) {
        this.FunctionName=FunctionName;
        if(FunctionName!=null) FunctionName.setParent(this);
        this.OptionalMethodActualParams=OptionalMethodActualParams;
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.setParent(this);
    }

    public FunctionName getFunctionName() {
        return FunctionName;
    }

    public void setFunctionName(FunctionName FunctionName) {
        this.FunctionName=FunctionName;
    }

    public OptionalMethodActualParams getOptionalMethodActualParams() {
        return OptionalMethodActualParams;
    }

    public void setOptionalMethodActualParams(OptionalMethodActualParams OptionalMethodActualParams) {
        this.OptionalMethodActualParams=OptionalMethodActualParams;
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
        if(FunctionName!=null) FunctionName.accept(visitor);
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FunctionName!=null) FunctionName.traverseTopDown(visitor);
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FunctionName!=null) FunctionName.traverseBottomUp(visitor);
        if(OptionalMethodActualParams!=null) OptionalMethodActualParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FunctionCall(\n");

        if(FunctionName!=null)
            buffer.append(FunctionName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalMethodActualParams!=null)
            buffer.append(OptionalMethodActualParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FunctionCall]");
        return buffer.toString();
    }
}
