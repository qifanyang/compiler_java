package compilers.scanners;

/**X|Y 交替 , 需要两个正则进行alternation运算
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class AlternationExpression extends RegularExpression{
    private RegularExpression expr1;
    private RegularExpression expr2;

    public AlternationExpression(RegularExpression expr1, RegularExpression expr2){
        super(RegularExpressionType.Alternation);
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public <T> T Accept(RegularExpressionConverter<T> converter){
        return converter.convertAlternation(this);
    }

    public RegularExpression getExpr1(){
        return expr1;
    }

    public void setExpr1(RegularExpression expr1){
        this.expr1 = expr1;
    }

    public RegularExpression getExpr2(){
        return expr2;
    }

    public void setExpr2(RegularExpression expr2){
        this.expr2 = expr2;
    }
}
