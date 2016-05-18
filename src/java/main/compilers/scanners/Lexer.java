package compilers.scanners;

import java.util.ArrayList;
import java.util.List;

/**
 * 词法解析器,包含词汇
 * @author yangqf
 * @version 1.0 2016/5/15
 */
public class Lexer{
    public List<TokenInfo> tokens;
    public Lexicon lexicon;//词汇
    public Lexer baseLexer;
    public int index;
    public int level;
    public List<Lexer> children;

    public Lexer(Lexicon lexicon, int index){
        this(lexicon, index, null);
    }

    Lexer(Lexicon lexicon, int index, Lexer baseLexer){
        children = new ArrayList<Lexer>();
        this.lexicon = lexicon;
        this.baseLexer = baseLexer;
        tokens = new ArrayList<TokenInfo>();
        this.index = index;

        if (baseLexer == null){
            level = 0;
        }else{
            level = baseLexer.level + 1;
            baseLexer.children.add(this);
        }
    }

    public Token DefineToken(RegularExpression regex, String description){

        int indexInState = tokens.size();

        TokenInfo token = lexicon.addToken(regex, this, indexInState, description);
        tokens.add(token);

        return token.tag;
    }

}
