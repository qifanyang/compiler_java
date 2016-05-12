package compilers.scanners;

/**
 * 单一字符的正则表达式
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class SymbolExpression extends RegularExpression{
    private char symbol;
    public SymbolExpression(char symbol){
        super(RegularExpressionType.Alternation.Symbol);
        this.symbol = symbol;
    }

    @Override
    public <T> T Accept(RegularExpressionConverter<T> converter){
        return converter.convertSymbol(this);
    }

    public char getSymbol(){
        return symbol;
    }
}
