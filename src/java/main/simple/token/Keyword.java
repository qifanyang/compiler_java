package simple.token;

/**
 * Created by yangqifan on 2017/8/18.
 */
public enum Keyword implements TokenType {
    INT,DEF;


    public static void main(String[] args) {
        System.out.println(DEF.name());
    }
}
