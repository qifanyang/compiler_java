package simple.ast;

import java.util.LinkedList;
import java.util.List;

/**
 * ExprAST列表 , 可作为函数body存在
 * Created by yangqifan on 2017/8/20.
 */
public class MultipleExprAST extends ExprAST {
    private List<ExprAST> exprASTList = new LinkedList<>();

    public void add(ExprAST exprAST){
        exprASTList.add(exprAST);
    }
}
