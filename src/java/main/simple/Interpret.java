package simple;

import simple.ast.ExprAST;
import simple.ast.VariableExprAST;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangqifan on 2017/8/20.
 */
public class Interpret {
//    x=3;
//    y=4;
//    z=add(x,y);
//    print(z);
//    def add(x,y){
//        return x+y;
//    }


    private Map<String, Object> globalContext = new HashMap<>();

    public static void main(String[] args) {
        Interpret interpret = new Interpret();
        Parser parser = new Parser(null);
        ExprAST exprAST = parser.parse("x=3");
        interpret.interpret(exprAST);
    }

    private void interpret(ExprAST exprAST){
        if(exprAST instanceof VariableExprAST){
            VariableExprAST variableExprAST = (VariableExprAST) exprAST;
            globalContext.put(variableExprAST.getId(), variableExprAST);
        }

    }
}
