package simple.ast;

/**
 *
 * Created by yangqifan on 2017/8/20.
 */
public class FunctionAST{
    private PrototypeAST prototypeAST;
    private ExprAST exprAST;
    public FunctionAST(PrototypeAST prototypeAST, ExprAST exprAST){
        this.prototypeAST = prototypeAST;
        this.exprAST = exprAST;
    }

    public PrototypeAST getPrototypeAST() {
        return prototypeAST;
    }

    public void setPrototypeAST(PrototypeAST prototypeAST) {
        this.prototypeAST = prototypeAST;
    }

    public ExprAST getExprAST() {
        return exprAST;
    }

    public void setExprAST(ExprAST exprAST) {
        this.exprAST = exprAST;
    }
}
