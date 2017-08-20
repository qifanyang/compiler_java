package simple;

import simple.token.Keyword;
import simple.token.Literals;
import simple.token.Symbol;
import simple.token.Token;

/**
 * Tokenizer 分词器, 可以将构建Token的任务放到Tokenizer中
 * 简单处理就在Lexer中完成
 * Created by yangqifan on 2017/8/18.
 */
public class Lexer {
    private String input;

    private int offset = 0;

    public Lexer(String input){
        this.input = input;
    }


    public Token nextToken(){
        Token nextToken = doNextToken();
        if(null != nextToken){
            offset = nextToken.getEndPosition();
        }
        return nextToken;
    }
    /**
     * 解析输入流,返回下一个Token
     * @return
     */
    public Token doNextToken(){

        //token可以采用正则定义,解析token时需要实现一个状态机,采用硬编码实现状态机
        char ch = getCurrentChar(0);

        while (Character.isSpaceChar(ch)){
            ch = nextChar();//跳过空白, offset自增
        }

        //数字
        if(Character.isDigit(ch) || ch == '.'){
            int length = 0;
            StringBuilder sb = new StringBuilder();
            do{
                length++;
                sb.append(ch);
                ch = getCurrentChar(length);
            } while (Character.isDigit(ch) || ch == '.');

            return new Token(Literals.INT, sb.toString(), offset+length);
        }

        //字符串(变量名或关键字)
        if(Character.isAlphabetic(ch) || '$'==ch || '_'==ch){
            int length = 0;
            StringBuilder sb = new StringBuilder();
            do {
                length++;
                sb.append(ch);
                ch = getCurrentChar(length);
            } while (Character.isAlphabetic(ch));

            //判断关键字
            String identity = sb.toString();
            if("int".equalsIgnoreCase(identity)){
                return new Token(Keyword.INT, identity, offset+length);
            }

            return new Token(Literals.IDENTIFIER, identity, offset+length);
        }


        //符号, + - * / ( ) ...
        Symbol symbol = Symbol.literalsOf(String.valueOf(ch));
        if(symbol != null){
            return new Token(symbol, symbol.getLiterals(), offset+1);
        }




        return null;
    }


    protected final char nextChar(){
        ++offset;
        return getCurrentChar(0);
    }

    /**
     * 返回当前input的offset字符
     * @param offset
     * @return
     */
    protected final char getCurrentChar(final int offset) {
        return this.offset + offset >= input.length() ? (char) -1 : input.charAt(this.offset + offset);
    }
}
