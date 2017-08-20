package simple.ast;

import java.util.List;

/**
 * Created by yangqifan on 2017/8/20.
 */
public class CallExprAST extends ExprAST {
    private String callee;
    private List<ExprAST> args;

    public CallExprAST(String callee, List<ExprAST> args){
        this.callee=callee;
        this.args=args;
    }



    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee;
    }

    public List<ExprAST> getArgs() {
        return args;
    }

    public void setArgs(List<ExprAST> args) {
        this.args = args;
    }
}
