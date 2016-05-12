package compilers.scanners;

/**
 *
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class StringLiteralExpression extends RegularExpression{
    public String literal;
    public StringLiteralExpression(String literal){
        super(RegularExpressionType.Alternation.StringLiteral);
        this.literal = literal;
    }

    @Override
    public <T> T Accept(RegularExpressionConverter<T> converter){
        return converter.convertStringLiteral(this);
    }
}
