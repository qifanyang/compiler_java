package compilers.scanners;

/**
 *
 * @author yangqf
 * @version 1.0 2016/5/15
 */
public class Token implements Comparable<Token>{

    public int Index;
    public int LexerIndex;
    public String Description;

    public Token(int index, String description, int lexerIndex){
        Index = index;
        Description = description;
        LexerIndex = lexerIndex;
    }

    @Override
    public int compareTo(Token o){
        return 0;
    }

    @Override
    public String toString(){
        return Description;
    }
}
