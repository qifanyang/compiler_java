package compilers.scanners;

/**
 * 将一个正则表达式转换为一个值<br>
 * 采用visitor模式,应为正则表达式类型数量是固定的,但是正则表达式是无穷的,<br>
 *     该converter就是visitor, 遍历各种正则表达式并调用表达式的accept方法,都会回调visitor方法处理对应的对象<br>
 *     这里已经定义好了处理各种正则表达式的各种方法,visitor模式使用:被访问对象提供遍历方法,被遍历的对象会回调visitor.visit()
 *
 *     访问者模式的基本想法如下：首先我们拥有一个由许多对象构成的对象结构，这些对象的类都拥有一个accept方法用来接受访问者对象；
 *     访问者是一个接口，它拥有一个visit方法，这个方法对访问到的对象结构中不同类型的元素作出不同的反应；在对象结构的一次访问过程中，
 *     我们遍历整个对象结构，对每一个元素都实施accept方法，在每一个元素的accept方法中回调访问者的visit方法，
 *     从而使访问者得以处理对象结构的每一个元素。我们可以针对对象结构设计不同的实在的访问者类来完成不同的操作。
 *
 *     对于同一对象结构,不同的访问者完成不同的功能,扩展性好
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public abstract class RegularExpressionConverter<T>{

    public T convert(RegularExpression expression){
        return expression.Accept(this);
    }

    public abstract T convertAlternation(AlternationExpression exp);

    public abstract T convertSymbol(SymbolExpression exp);

    public abstract T convertEmpty(EmptyExpression exp);

    public abstract T convertConcatenation(ConcatenationExpression exp);

    public abstract T convertAlternationCharSet(AlternationCharSetExpression exp);

    public abstract T convertStringLiteral(StringLiteralExpression exp);

    public abstract T convertKleeneStar(KleeneStarExpression exp);
}
