package compilers.scanners;

/**
 * 语言是字符串的集合,字符串是字符的有限长度序列<br>
 * 组成语言的字符串很可能是无限多个,需要使用一种形式来描述这类无限的语言(变量名任意命名,无限多个),就是正则表达式
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public abstract class RegularExpression{

    private RegularExpressionType expressionType;

    public RegularExpression(RegularExpressionType expressionType){
        this.expressionType = expressionType;
    }

    /**
     * 不同对象
     * @param convert
     * @param <T>
     * @return
     */
    public abstract <T> T Accept(RegularExpressionConverter<T> convert);
}
