package compilers.scanners.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * 有穷自动机,包含有限状态集(必须有一个初始状态和至少一个接受状态)+从一个状态到另一个状态转换的<B>边</B> <br>
 *     初始状态图形为有根外来的边,接受状态有个黑圈<br>
 *<br>
 *   有穷自动机处理输入字符串<br>
 *  1.一开始，自动机处于初始状态<br>
 *  2.输入字符串的第一个字符，这时自动机会查询当前状态上与输入字符相匹配的边，并沿这条边转换到下一个状态。<br>
 *  3.继续输入下一个字符，重复第二步，查询当前状态上的边并进行状态转换<br>
 *  4.当字符串全部输入后，如果自动机正好处于接受状态上，就说该自动机接受了这一字符串。<br>
 *  有穷自动机可以用于聊天时敏感字过滤,java正则表达式采用的是NFA,使用正则来作为敏感字过滤<br>
 *<br>
 * 非确定性有限状态机中的状态对象<br>
 * 和DFAState区别是,一个状态可以发出具有相同符号的边,甚至epsilon(空)符号,NFA可以不输入任何字符就可以从一个状态转换到下一状态<br>
 *
 *     DFA、NFA和正则表达式是等价的<br>
 *     一般采用正则-->NFA-->DFA 转换, 也可以正则-->DFA 直接转换<br>
 *     转换实现是先将每个单词的正则转换为NFA 然后alternation运算, 就得到词法分析的最终NFA
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class NFAState{

    private List<NFAEdge> outEdges;
    private int index;//在NFAModel state list中的索引位置
    public int tokenIndex;

    public NFAState(){
        outEdges = new ArrayList<>();
        tokenIndex = -1;//default value -1 means no token is bound with this state
    }

    //添加一个epsilon 符号的边并指向到下一状态
    public void AddEmptyEdgeTo(NFAState targetState){
        outEdges.add(new NFAEdge(targetState));
    }

    public void AddEdge(NFAEdge edge){
        outEdges.add(edge);
    }

    public List<NFAEdge> getOutEdges(){
        return outEdges;
    }

    public void setOutEdges(List<NFAEdge> outEdges){
        this.outEdges = outEdges;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getTokenIndex(){
        return tokenIndex;
    }

    public void setTokenIndex(int tokenIndex){
        this.tokenIndex = tokenIndex;
    }
}
