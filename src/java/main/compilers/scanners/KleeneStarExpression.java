package compilers.scanners;

/**
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class KleeneStarExpression extends RegularExpression{

    public KleeneStarExpression(){
        super(RegularExpressionType.Alternation.KleeneStar);
    }

    @Override
    public <T> T Accept(RegularExpressionConverter<T> tRegularExpressionConverter){
        return tRegularExpressionConverter.convert(this);
    }
}
