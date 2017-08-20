package simple.ast;

import simple.token.Symbol;

/**
 * 二元表达式,
 * Created by yangqifan on 2017/8/19.
 */
public class BinaryExprAST extends ExprAST {
    Symbol op;
    ExprAST lhs;// left hand side
    ExprAST rhs;//right hand side
    public BinaryExprAST(Symbol op, ExprAST lhs, ExprAST rhs){
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Symbol getOp() {
        return op;
    }

    public void setOp(Symbol op) {
        this.op = op;
    }

    public ExprAST getLhs() {
        return lhs;
    }

    public void setLhs(ExprAST lhs) {
        this.lhs = lhs;
    }

    public ExprAST getRhs() {
        return rhs;
    }

    public void setRhs(ExprAST rhs) {
        this.rhs = rhs;
    }


}
