package compilers.scanners.generator;

import compilers.scanners.*;

/**
 * 访问者模式算法实现,将正则表达式转换为
 * @author yangqf
 * @version 1.0 2016/5/11
 */
public class NFAConverter extends RegularExpressionConverter<NFAModel>{
    @Override
    public NFAModel convertAlternation(AlternationExpression exp){
        // a|b  对应NFA图形, 这里的代码是对图形的翻译
        RegularExpression expr1 = exp.getExpr1();
        RegularExpression expr2 = exp.getExpr2();

        NFAModel nfa1 = convert(expr1);
        NFAModel nfa2 = convert(expr2);

        //开始构建alternation
        NFAState head = new NFAState();//图形最左边的状态
        NFAState tail = new NFAState();

        //nfa1的输入边是head的输出边,通过list连接起来
        head.AddEdge(nfa1.EntryEdge);//省略了epsilon边
        head.AddEdge(nfa2.EntryEdge);

        //nfa1指向到tail
        nfa1.TailState.AddEmptyEdgeTo(tail);//a和b添加epsilon边到终止状态
        nfa2.TailState.AddEmptyEdgeTo(tail);

        //a|b的数据模型
        NFAModel alternationNfa = new NFAModel();

        alternationNfa.AddState(head);
        alternationNfa.AddStates(nfa1.getStates());
        alternationNfa.AddStates(nfa2.getStates());
        alternationNfa.AddState(tail);

        //add an empty entry edge, 任何正则都可以转为一条输入边和一个终止状态
        alternationNfa.EntryEdge = new NFAEdge(head);
        alternationNfa.TailState = tail;

        return alternationNfa;
    }

    @Override
    public NFAModel convertSymbol(SymbolExpression exp){
        //单一字符表达式转换为NFAModel
        NFAState tail = new NFAState();
        NFAEdge entryEdge = new NFAEdge(exp.getSymbol(), tail);

        NFAModel symbolNfa = new NFAModel();

        symbolNfa.AddState(tail);
        symbolNfa.TailState = tail;
        symbolNfa.EntryEdge = entryEdge;

        return symbolNfa;
    }

    @Override
    public NFAModel convertEmpty(EmptyExpression exp){
        //epsilon 空集
        NFAState tail = new NFAState();
        NFAEdge entryEdge = new NFAEdge(tail);//指向终止状态的边

        NFAModel emptyNfa = new NFAModel();

        emptyNfa.AddState(tail);
        emptyNfa.TailState = tail;
        emptyNfa.EntryEdge = entryEdge;

        return emptyNfa;
    }

    @Override
    public NFAModel convertConcatenation(ConcatenationExpression exp){
        NFAModel leftNFA = convert(exp.Left);
        NFAModel rightNFA = convert(exp.Right);

        //connect left with right, left的尾巴和right输入边连接
        leftNFA.TailState.AddEdge(rightNFA.EntryEdge);

        NFAModel concatenationNfa = new NFAModel();

        concatenationNfa.AddStates(leftNFA.getStates());
        concatenationNfa.AddStates(rightNFA.getStates());
        concatenationNfa.EntryEdge = leftNFA.EntryEdge;
        concatenationNfa.TailState = rightNFA.TailState;

        return concatenationNfa;
    }

    @Override
    public NFAModel convertAlternationCharSet(AlternationCharSetExpression exp){

        return null;
    }

    @Override
    public NFAModel convertStringLiteral(StringLiteralExpression exp){
        //字符串正则
        NFAModel literalNfa = new NFAModel();
        char[] chars = exp.literal.toCharArray();

        NFAState lastState = null;
        for(char c : chars){
            NFAState symbolState = new NFAState();
            NFAEdge  symbolEdge = new NFAEdge(c, symbolState);

            if (lastState != null){
                lastState.AddEdge(symbolEdge);
            }else{
                literalNfa.EntryEdge = symbolEdge;
            }
            lastState = symbolState;
            literalNfa.AddState(symbolState);//状态串联
        }
        return literalNfa;
    }

    @Override
    public NFAModel convertKleeneStar(KleeneStarExpression exp){
        NFAModel innerNFA = convert(exp.InnerExpression);

        NFAState tail = new NFAState();
        NFAEdge entryEdge = new NFAEdge(tail);//epsilon 到终止状态的边

        innerNFA.TailState.AddEmptyEdgeTo(tail);
        tail.AddEdge(innerNFA.EntryEdge);

        NFAModel kleenStarNFA = new NFAModel();

        kleenStarNFA.AddStates(innerNFA.getStates());
        kleenStarNFA.AddState(tail);
        kleenStarNFA.EntryEdge = entryEdge;
        kleenStarNFA.TailState = tail;

        return kleenStarNFA;
    }
}
