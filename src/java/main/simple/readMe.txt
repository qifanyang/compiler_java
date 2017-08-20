Lexer 词法分析器,也叫Scanner

Tokenizer 分词器,读取输入流,返回一个Token

Token 根据词法规则,将输入流转换成一连串的Token, 比如按空格分割,每个Token需要有类型标志,内容,坐标等
解析Token时,需要先识别Token类型然后再解析,比如字符串,数字,关键字等
一般使用正则表达式来定义Token


Parser 语法解析器

语法解析器调用Lexer.nextToken(),根据Token类型做出对应解析方式


语言:
数据类型 int
四则运算 + - * /
函数 func(){}

约定:
变量没有初始化默认undefine
函数没有返回值默认undefine
标准输出print()

例子:
x=3;
y=4;
z=add(x,y);
print(z);
def add(x,y){
return x+y;
}


