// generated with ast extension for cup
// version 0.8
// 6/1/2023 12:48:18


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(ConstructorDecl ConstructorDecl);
    public void visit(Relop Relop);
    public void visit(ConstructorDeclStart ConstructorDeclStart);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(ProgDeclList ProgDeclList);
    public void visit(VarName VarName);
    public void visit(ProgVarDeclStart ProgVarDeclStart);
    public void visit(StatementList StatementList);
    public void visit(ProgClassDecl ProgClassDecl);
    public void visit(ConditionTerm ConditionTerm);
    public void visit(Addop Addop);
    public void visit(ProgVarList ProgVarList);
    public void visit(Factor Factor);
    public void visit(ConstDeclStart ConstDeclStart);
    public void visit(VarList VarList);
    public void visit(ConstList ConstList);
    public void visit(MethodTypeAndName MethodTypeAndName);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(OptionalClassExtends OptionalClassExtends);
    public void visit(Condition Condition);
    public void visit(VarDeclStart VarDeclStart);
    public void visit(OptionalClassMethodDecl OptionalClassMethodDecl);
    public void visit(OptionalStatementList OptionalStatementList);
    public void visit(ActualParamList ActualParamList);
    public void visit(ProgVarDeclaration ProgVarDeclaration);
    public void visit(ConstructorDeclList ConstructorDeclList);
    public void visit(VarDeclList VarDeclList);
    public void visit(FormalParamList FormalParamList);
    public void visit(Expr Expr);
    public void visit(FormalParams FormalParams);
    public void visit(AssignmentStatementExpr AssignmentStatementExpr);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Statement Statement);
    public void visit(DesignatorListContent DesignatorListContent);
    public void visit(OptionalMethodActualParams OptionalMethodActualParams);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(ConditionFactor ConditionFactor);
    public void visit(ConstType ConstType);
    public void visit(SuperClass SuperClass);
    public void visit(OptionalMethodDeclList OptionalMethodDeclList);
    public void visit(ModuloOp ModuloOp);
    public void visit(DivisionOp DivisionOp);
    public void visit(MultiplyOp MultiplyOp);
    public void visit(LessEqualOp LessEqualOp);
    public void visit(GreaterEqualOp GreaterEqualOp);
    public void visit(LessOp LessOp);
    public void visit(GreaterOp GreaterOp);
    public void visit(NotEqualsOp NotEqualsOp);
    public void visit(EqualsOp EqualsOp);
    public void visit(MinusOp MinusOp);
    public void visit(PlusOp PlusOp);
    public void visit(ClassNewType ClassNewType);
    public void visit(ExprFactor ExprFactor);
    public void visit(FactorFuncCall FactorFuncCall);
    public void visit(OperatorNewArr OperatorNewArr);
    public void visit(OperatorNewClass OperatorNewClass);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(ConstFactor ConstFactor);
    public void visit(FactorTerm FactorTerm);
    public void visit(MulTerm MulTerm);
    public void visit(TermExpr TermExpr);
    public void visit(AddExpr AddExpr);
    public void visit(NegExpr NegExpr);
    public void visit(Type Type);
    public void visit(ActualParameter ActualParameter);
    public void visit(ActualParamSingle ActualParamSingle);
    public void visit(ActualParamListRec ActualParamListRec);
    public void visit(NoMethodActualParams NoMethodActualParams);
    public void visit(OptMethodActualParams OptMethodActualParams);
    public void visit(FunctionName FunctionName);
    public void visit(FunctionCall FunctionCall);
    public void visit(DesignatorField DesignatorField);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(DesignatorSingle DesignatorSingle);
    public void visit(DesignatorReal DesignatorReal);
    public void visit(DesignatorDummy DesignatorDummy);
    public void visit(DesignatorListDummy DesignatorListDummy);
    public void visit(DesignatorListCommaElem DesignatorListCommaElem);
    public void visit(DesignatorListStartDummy DesignatorListStartDummy);
    public void visit(DesignatorListStart DesignatorListStart);
    public void visit(AssignmentExpr AssignmentExpr);
    public void visit(DesignatorStatementDerived1 DesignatorStatementDerived1);
    public void visit(DecrementDesignator DecrementDesignator);
    public void visit(IncrementDesignator IncrementDesignator);
    public void visit(DesignatorStmtFunctionCall DesignatorStmtFunctionCall);
    public void visit(MultipleAssignmentStatement MultipleAssignmentStatement);
    public void visit(AssignmentStatement AssignmentStatement);
    public void visit(AndKeyWord AndKeyWord);
    public void visit(OrKeyWord OrKeyWord);
    public void visit(ConditionFactorSingle ConditionFactorSingle);
    public void visit(ConditionFactorRel ConditionFactorRel);
    public void visit(ConditionTermSingle ConditionTermSingle);
    public void visit(ConditionTermAnd ConditionTermAnd);
    public void visit(ConditionSingle ConditionSingle);
    public void visit(ConditionOr ConditionOr);
    public void visit(ControlCondition ControlCondition);
    public void visit(ForeachDesignator ForeachDesignator);
    public void visit(ForIdent ForIdent);
    public void visit(ForeachKeyWord ForeachKeyWord);
    public void visit(ForeachConstruct ForeachConstruct);
    public void visit(WhileKeyWord WhileKeyWord);
    public void visit(WhileConstruct WhileConstruct);
    public void visit(ElseKeyWord ElseKeyWord);
    public void visit(IfKeyWord IfKeyWord);
    public void visit(IfConstruct IfConstruct);
    public void visit(BlockStatement BlockStatement);
    public void visit(IfElseStatement IfElseStatement);
    public void visit(IfStatement IfStatement);
    public void visit(ReturnExprStatement ReturnExprStatement);
    public void visit(ReturnStatement ReturnStatement);
    public void visit(ContinueStatement ContinueStatement);
    public void visit(BreakStatement BreakStatement);
    public void visit(ReadStatement ReadStatement);
    public void visit(PrintWidthStatement PrintWidthStatement);
    public void visit(PrintStatement PrintStatement);
    public void visit(ForeachStatement ForeachStatement);
    public void visit(WhileStatement WhileStatement);
    public void visit(DesignatorStatementStmt DesignatorStatementStmt);
    public void visit(StatementSingle StatementSingle);
    public void visit(Statements Statements);
    public void visit(NoStatementList NoStatementList);
    public void visit(OptStatementList OptStatementList);
    public void visit(FormalParamDeclDerived1 FormalParamDeclDerived1);
    public void visit(FormalParamDeclArray FormalParamDeclArray);
    public void visit(FormalParamDeclSingle FormalParamDeclSingle);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormalParam NoFormalParam);
    public void visit(OptFormalParams OptFormalParams);
    public void visit(MethodVoidName MethodVoidName);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(MethodDecl MethodDecl);
    public void visit(MethodDeclSingle MethodDeclSingle);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(NoMethodDeclList NoMethodDeclList);
    public void visit(MethodDeclListExists MethodDeclListExists);
    public void visit(ConstructorDeclarationStart ConstructorDeclarationStart);
    public void visit(ConstructorDeclarationInst ConstructorDeclarationInst);
    public void visit(ConstructorDeclarationEmpty ConstructorDeclarationEmpty);
    public void visit(ConstructorDeclarationList ConstructorDeclarationList);
    public void visit(NoClassMethodDecl NoClassMethodDecl);
    public void visit(ClassMethodDecl ClassMethodDecl);
    public void visit(SuperClassErr SuperClassErr);
    public void visit(SuperClassIdent SuperClassIdent);
    public void visit(NoClassExtends NoClassExtends);
    public void visit(ClassExtends ClassExtends);
    public void visit(ClassName ClassName);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(ProgVar ProgVar);
    public void visit(BaseProgVarDeclaration BaseProgVarDeclaration);
    public void visit(ProgVarListRec ProgVarListRec);
    public void visit(ProgVarType ProgVarType);
    public void visit(ProgVarDeclarationStart ProgVarDeclarationStart);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(VarNameArray VarNameArray);
    public void visit(VarNameSingle VarNameSingle);
    public void visit(BaseVarDeclarationList BaseVarDeclarationList);
    public void visit(VarDeclarationList VarDeclarationList);
    public void visit(VarDeclarationStart VarDeclarationStart);
    public void visit(ConstBoolType ConstBoolType);
    public void visit(ConstCharType ConstCharType);
    public void visit(ConstIntType ConstIntType);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(BaseConstDeclarationList BaseConstDeclarationList);
    public void visit(ConstDeclarationList ConstDeclarationList);
    public void visit(ConstDeclarationStart ConstDeclarationStart);
    public void visit(NoProgDeclList NoProgDeclList);
    public void visit(ProgListClass ProgListClass);
    public void visit(ProgListVar ProgListVar);
    public void visit(ProgListConst ProgListConst);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
