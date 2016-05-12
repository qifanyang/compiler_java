package compilers.scanners;

/**
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public enum  RegularExpressionType{
                Empty,//Represents a regular expression accepts an empty set
                Symbol,//Represents a regular expression accepts a literal character
                Alternation,//交替   X|Y
                Concatenation,//连接 X*Y 或 XY
                KleeneStar,
                AlternationCharSet,
                StringLiteral
}
