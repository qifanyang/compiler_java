package simple.ast;

/**
 * Created by yangqifan on 2017/8/18.
 */
public class NumberExprAST extends ExprAST {
    double val;
    public NumberExprAST(double val){
        this.val = val;
    }


}
