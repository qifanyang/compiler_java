package compilers.scanners;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqf
 * @version 1.0 2016/5/15
 */
public class Lexicon{
    public Lexer defaultState;
    public List<Lexer> lexerStates;
    public List<TokenInfo> tokenList;

    public Lexicon(){
        tokenList = new ArrayList<>();
        lexerStates = new ArrayList<>();
        defaultState = new Lexer(this, 0);

        lexerStates.add(defaultState);
    }

    public TokenInfo addToken(RegularExpression regex, Lexer lexer, int indexInState, String description){
        int index = tokenList.size();
        Token tag = new Token(index, description, lexer.index);
        TokenInfo token = new TokenInfo(regex, this, lexer, tag);
        tokenList.add(token);
        return token;
    }

    public List<TokenInfo> getTokens(){
        return tokenList;
    }
}
