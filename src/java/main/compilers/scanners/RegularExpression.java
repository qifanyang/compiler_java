package compilers.scanners;

/**
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public abstract class RegularExpression{

    private RegularExpressionType expressionType;

    public RegularExpression(RegularExpressionType expressionType){
        this.expressionType = expressionType;
    }

    public abstract <T> T Accept(RegularExpressionConverter<T> tRegularExpressionConverter);
}
