package simple;

import simple.token.Token;

import java.io.IOException;

/**
 * Created by yangqifan on 2017/8/18.
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        testLexer();
//        testParser();
        testParserInteractive();
    }

    static void testLexer(){
        String source = "a+b";
        Lexer lexer = new Lexer(source);
        Token token =  lexer.nextToken();
        while (token != null){
            System.out.println(token.getLiterals());
            token = lexer.nextToken();
        }
    }

    static void testParser(){
//        String source = "a+b+c";
        String source = "a+b+(c+d)*e*f+g";
        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer);
        parser.parse();
    }

    static void testParserInteractive() throws IOException {
        //s=4; y=9; def ffff(c){}
        //s=4; y=9; ffff(c);
        //x=3;y=4;z=add(x,y);print(z);def add(x y){return x+y;}
        Parser parser = new Parser(null);
        parser.consoleInteractive();
    }
}
