package compilers.scanners;

/**
 * 空,epsilon
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class EmptyExpression extends RegularExpression{

    public EmptyExpression(){
        super(RegularExpressionType.Empty);
    }

    @Override
    public String toString(){
        return "ε";
    }

    @Override
    public <T> T Accept(RegularExpressionConverter<T> converter){
        return converter.convertEmpty(this);
    }
}
