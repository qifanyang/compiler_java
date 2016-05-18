package compilers.scanners;

import compilers.scanners.generator.NFAConverter;
import compilers.scanners.generator.NFAModel;

/**
 * @author yangqf
 * @version 1.0 2016/5/15
 */
public class TokenInfo{

    public Token tag;
    public Lexicon lexicon;
    public Lexer state;
    public RegularExpression definition;

    public TokenInfo(RegularExpression definition, Lexicon lexicon, Lexer state, Token tag){
        this.lexicon = lexicon;
        this.definition = definition;
        this.state = state;
        this.tag = tag;
    }

    public NFAModel CreateFiniteAutomatonModel(NFAConverter converter)
    {
        NFAModel nfa = converter.convert(definition);


        nfa.TailState.tokenIndex = tag.Index;

        return nfa;
    }
}
