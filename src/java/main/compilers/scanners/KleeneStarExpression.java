package compilers.scanners;

/**
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class KleeneStarExpression extends RegularExpression{

    public RegularExpression InnerExpression;//对一个表达式做克林运算
    public KleeneStarExpression(RegularExpression expr){
        super(RegularExpressionType.Alternation.KleeneStar);
        this.InnerExpression = expr;
    }

    @Override
    public <T> T Accept(RegularExpressionConverter<T> tRegularExpressionConverter){
        return tRegularExpressionConverter.convert(this);
    }
}
