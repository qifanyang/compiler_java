package simple.ast;

/**
 * Created by yangqifan on 2017/8/18.
 */
public class VariableExprAST extends ExprAST {
    String id;
    ExprAST init;
    public VariableExprAST(String id, ExprAST init){
        this.id = id;
        this.init = init;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExprAST getInit() {
        return init;
    }

    public void setInit(ExprAST init) {
        this.init = init;
    }
}
