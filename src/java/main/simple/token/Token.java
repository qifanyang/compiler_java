package simple.token;

/**
 * 词法分析器解析出token
 * Created by yangqifan on 2017/8/18.
 */
public class Token {
    private TokenType tokenType;
    private String literals;
    private int endPosition;

    public Token(TokenType tokenType, String literals, int endPosition){
        this.tokenType = tokenType;
        this.literals = literals;
        this.endPosition = endPosition;
    }
    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getLiterals() {
        return literals;
    }

    public void setLiterals(String literals) {
        this.literals = literals;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", literals='" + literals + '\'' +
                ", endPosition=" + endPosition +
                '}';
    }
}
