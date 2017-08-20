package simple;

import simple.ast.*;
import simple.token.Keyword;
import simple.token.Literals;
import simple.token.Symbol;
import simple.token.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 1.每个parse*ExprAST完成解析后,都需要读取下一个token,调用parse*ExprAST方法后直接处理currentToken
 * Created by yangqifan on 2017/8/19.
 */
public class Parser {
    private Lexer lexer;
    private Token curToken;

    private List<Object> programBody = new LinkedList<>();

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    /**
     * 读取下一个token作为当前token, parse*方法解析后都会调用该方法读取下一个token
     */
    Token nextToken(){
        curToken = lexer.nextToken();
        return curToken;
    }


    public void consoleInteractive() throws IOException {
        System.out.print("ready>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        lexer = new Lexer(reader.readLine());
        nextToken();
        while (true){
            if(null == curToken){
                System.out.println("解析完毕");
                return;
            }

            if(curToken.getLiterals().equalsIgnoreCase(Keyword.DEF.name())){
                handleFnDefinition();
            }else if(curToken.getTokenType() == Symbol.SEMI){
                nextToken();
            }else{
                handleTopLevelExpression();
            }
        }

    }

    public ExprAST parse(String input){
        lexer = new Lexer(input);
        nextToken();
        return parseExpression();
    }

    public void parse(){

        curToken = lexer.nextToken();

        while (null != curToken){

            ExprAST exprAST = parseExpression();

//            if(curToken.getTokenType() == Literals.INT){
//                parseIntExpr();
//            }
//
//            if(curToken.getTokenType() == Literals.IDENTIFIER){
//                parseIdentifierExpr();
//            }
//
//            if(curToken.getTokenType() == Symbol.LEFT_PAREN){
//                parseParenExpr();
//            }

            System.out.println();

            curToken = lexer.nextToken();
        }

    }

    /**
     * 解析数字
     * @return
     */
    ExprAST parseIntExpr(){
        NumberExprAST exprAST = new NumberExprAST(Double.valueOf(curToken.getLiterals()));
        nextToken();
        return exprAST;
    }


    /**
     * parenExpression '(' expression ')'
     * 解析括号表达式
     * @return
     */
    ExprAST parseParenExpr(){

        nextToken();//eat (
        ExprAST exprAST = parseExpression();
        if(null==exprAST)return null;

        nextToken();

        return exprAST;
    }

    /**
     * 解析标识符,可能只是一个普通变量,也可能是函数名
     * @return
     */
    ExprAST parseIdentifierExpr(){

        //为了判断identifier是普通变量还是函数名,读取下一个token
        String identifierName = curToken.getLiterals();

        //遇到结束符,等于null,直接返回
        Token nextToken = lexer.nextToken();// eat current token
        if(null == nextToken || nextToken.getTokenType() != Symbol.LEFT_PAREN){
            //是变量标志,检查是否初始化
            if(nextToken.getTokenType() != Symbol.EQ){
                VariableExprAST identifierExprAST = new VariableExprAST(identifierName, null);
                curToken = nextToken;
                return identifierExprAST;
            }
            //初始化变量
            nextToken();//eat =
            ExprAST initExprAST = parseExpression();
            VariableExprAST identifierExprAST = new VariableExprAST(identifierName, initExprAST);
            return identifierExprAST;
        }


        //函数调用
        nextToken();// eat (
        List<ExprAST> args = null;
        if(curToken.getTokenType() != Symbol.RIGHT_PAREN){//有参数
            args = new ArrayList<>();
            while(true){
                ExprAST arg = parseExpression();
                if(null == arg)return null;
                args.add(arg);
                if(curToken.getTokenType() == Symbol.RIGHT_PAREN)break;
                if(curToken.getTokenType() != Symbol.COMMA){
                    throw new RuntimeException("函数调用,形参没有用逗号隔开");
                }
                nextToken();
            }
        }
        nextToken();//eat )



        return new CallExprAST(identifierName, args);
    }

    /**
     * 解析表达式, 可能是单个声明的表示, 也可能是x+y+z*m等复杂表达式
     * 以第一个操作符+为分隔符,可以看作左右两部分,所以变成了解析二元表达式
     *
     * 然后又递归解析右边,最终都变成解析二元表达式
     * @return
     */
    ExprAST parseExpression(){
        ExprAST lhs = parsePrimary();
        if(null == lhs)return null;//没有解析出了表达式
        return parseBinOpRHS(0, lhs);
    }

    /**
     * 解析表达式的主入口
     * 1.标识符
     * 2.数字
     * 3.(expression)
     * 4.{} 函数体
     * @return
     */
    ExprAST parsePrimary(){
        if(curToken.getTokenType() == Literals.IDENTIFIER){
            return parseIdentifierExpr();
        }else if(curToken.getTokenType() == Literals.INT){
            return parseIntExpr();
        }else if(curToken.getTokenType() == Symbol.LEFT_PAREN){
            return parseParenExpr();
        }else if(curToken.getTokenType() == Symbol.RIGHT_PAREN){
            //() 括号中表达式为空, )会作为primary解析,直接返回空
            return null;
        }else if(curToken.getTokenType() == Symbol.LEFT_BRACE){
            nextToken();//eat {
            MultipleExprAST multipleExprAST = new MultipleExprAST();
            while (curToken.getTokenType() != Symbol.RIGHT_BRACE){
                ExprAST exprAST = parseExpression();
                if(null != exprAST){
                    multipleExprAST.add(exprAST);
                }
            }
            if(curToken.getTokenType() != Symbol.RIGHT_BRACE){
                throw new RuntimeException("函数体解析, 函数体 '}' 不存在");
            }
            nextToken();//eat }
            return multipleExprAST;
        }else if(curToken.getTokenType() == Symbol.RIGHT_BRACE){
            //{} 空函数体, }会作为primary解析,直接返回空
            return null;
        }else if(curToken.getTokenType() == Symbol.SEMI){
            nextToken();
            return null;
        }else {
            System.out.println(curToken);
            throw new RuntimeException("unknown token when expecting an expression");
        }

    }

    /**
     * 解析二元表达式右边
     * e.g. a+b 解析b  预读取b右边的操作符时,如果没有token,优先级返回-1,
     * e.g. a+b+c 解析b+c
     *
     * @param exprPrecedence 最低优先级,如果右边操作符的优先级小于该值,直接返回已经解析的表达式
     *                       对于a+b+c,可以直接返回a+b,但是两个操作符优先级一样,解析+c时
     * @param lhs
     * @return
     */
    ExprAST parseBinOpRHS(int exprPrecedence, ExprAST lhs){

        //为什么要while(true)
        //e.g. a+b+c, 当解析完a+b, a+b作为lhs继续和[+,c]构建ast,所以需要循环
        while (true){

            int tokenPrecedence = getTokenPrecedence();//当前操作符的优先级

            if(tokenPrecedence < exprPrecedence){
                //如果下一个表达式操作符优先级小于当前优先级, 直接返回
                //a+b, a为lhs, [+,b]为rhs,  +优先级大于0所以不返回
                //a 没有二元操作符, 优先级-1, 小于0 所以直接返回a
                return lhs;
            }

            //获取操作符op
            Symbol op = Symbol.literalsOf(curToken.getLiterals());
            nextToken();//eat binop 跳过二元操作符,读入下一个token

            //解析二元操作符右边的token,也就是有序对中二元操作符后的主表达式
            ExprAST rhs = parsePrimary();
            if(rhs == null)return null;

            //调用parse*方法后会读取下一个token
            //也就是获取下一个Token操作符优先级,
            //如果不是二元运算符(可能为空,分号,等等)那么返回的是-1
            int nextTokenPrecedence = getTokenPrecedence();

            //比较当前操作符优先级和右边标识符后的操作符优先级
            //基于运算符优先级算法,先拆分二元表达式为有序组([操作符,主表达式]),
            //这里根据每个有序组的操作符优先级(算术结合律)来构建二元表达式AST
            //如果有序组的优先级更高,先解析更高优先级的表达式,最后作为表达式的rhs返回
            //e.g. a+b+c 比较first + and second +
            //e.g. a+b*c compare + and *
            if(tokenPrecedence < nextTokenPrecedence){
                //right hand side binary operation precedence higher than the left
                //so parse the right hand side
                //why the argument exprPrecedence is tokenPrecedence+1

                //+1的目的是遇到tokenPrecedence同优先级时停止递归
                //nextTokenPrecedence优先级大于tokenPrecedence才会进入这里
                //比如m+a*b*c+d, 遇到+d时返回a*b*c
                rhs = parseBinOpRHS(tokenPrecedence+1, rhs);
                //a+b不会进入这里
                if(rhs == null)return null;
            }

            //merge lhs/rhs
            lhs = new BinaryExprAST(op, lhs, rhs);
        }

    }


    //解析函数, 1.函数声明, 2.函数定义

    /**
     * 解析函数原型,函数名+形参
     * @return
     */
    PrototypeAST parsePrototype(){
        if(curToken.getTokenType() != Literals.IDENTIFIER){
            throw new RuntimeException("解析函数原型,函数名字错误");
        }

        String fnName = curToken.getLiterals();
        nextToken();//读取(

        if(curToken.getTokenType() != Symbol.LEFT_PAREN){
            throw new RuntimeException("解析函数原型,函数名字后不是 '(' ");
        }

        //读取函数参数名列表
        List<String> argNames = new ArrayList<>();
        while (nextToken().getTokenType() == Literals.IDENTIFIER){
            argNames.add(curToken.getLiterals());
        }

        if(curToken.getTokenType() != Symbol.RIGHT_PAREN){
            throw new RuntimeException("解析函数原型,参数列表后不是')' ");
        }

        nextToken();//eat ), 读取下一个token
        return new PrototypeAST(fnName, argNames);
    }


    /**
     * 解析函数定义,函数定义:函数声明+body expression
     * @return
     */
    FunctionAST parseFnDefinition(){
        nextToken();//eat def, 进入对应parse说明已经读取并且识别,进入后可以直接跳过

        //解析定义函数时的函数声明
        PrototypeAST prototypeAST = parsePrototype();
        if(null == prototypeAST)return null;


        //函数体就是一个表达式, llvm tutorial函数体没有{}, 这里加上{}检查
        if(curToken.getTokenType() != Symbol.LEFT_BRACE){
            throw new RuntimeException("函数体解析, 函数体 '{' 不存在");
        }
        ExprAST exprAST = parseExpression();


        return new FunctionAST(prototypeAST, exprAST);
    }

    void handleFnDefinition(){
        FunctionAST functionAST = parseFnDefinition();
        if(null != functionAST){
            System.out.println("解析函数成功");
            programBody.add(functionAST);
        }else {
            //跳过解析异常
            nextToken();
        }
    }

    void handleTopLevelExpression(){
        FunctionAST functionAST = parseTopLevelExpr();
        if(null != functionAST){
            System.out.println("解析表达式成功");
            programBody.add(functionAST);
        }else {
            nextToken();
        }
    }

    /**
     * 顶层表达式,源代码中定义在函数外面的代码,创建一个匿名函数将表达式包装在函数内部
     * @return
     */
    FunctionAST parseTopLevelExpr(){
        ExprAST exprAST = parseExpression();
        if(null == exprAST)return null;

        return new FunctionAST(new PrototypeAST("", Collections.emptyList()), exprAST);
    }


    int getTokenPrecedence(){
        // 1 is lowest precedence.
        //Precedence['<'] = 10;
        //Precedence['+'] = 20;
        //Precedence['-'] = 20;
        //Precedence['*'] = 40;  // highest.
        if(null == curToken){
            return -1;
        }else if(curToken.getTokenType() == Symbol.LT){
            return 10;
        }else if(curToken.getTokenType() == Symbol.PLUS){
            return 20;
        }else if(curToken.getTokenType() == Symbol.SUB){
            return 20;
        }else if(curToken.getTokenType() == Symbol.STAR){
            return 40;
        }else {
            return -1;
        }
    }




}
