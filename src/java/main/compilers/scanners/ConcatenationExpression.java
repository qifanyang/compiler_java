package compilers.scanners;

/**
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class ConcatenationExpression extends RegularExpression{
    public RegularExpression Left;
    public RegularExpression Right;
    public ConcatenationExpression(RegularExpression left, RegularExpression right){
        super(RegularExpressionType.Concatenation);
        this.Left = left;
        this.Right = right;
    }

    @Override
    public <T> T Accept(RegularExpressionConverter<T> converter){
        return converter.convertConcatenation(this);
    }
}
