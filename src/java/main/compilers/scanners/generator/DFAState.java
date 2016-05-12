package compilers.scanners.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DFA state 通过一条edge 转换到 next state,  一个state不能够拥有符号相同的边, 每个状态只能发出具有一条某一符号的边<br>
 *     符号只是一种条件,还可以是其它条件,比如值大于多少切换到哪个状态
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class DFAState{
    private List<DFAState> outEdges = new ArrayList<>();//状态的转换边
    private Set<Integer> nfaStateSet = new HashSet<>();//NFA的状态集

    public List<DFAState> getOutEdges(){
        return outEdges;
    }

    public void setOutEdges(List<DFAState> outEdges){
        this.outEdges = outEdges;
    }

    public Set<Integer> getNfaStateSet(){
        return nfaStateSet;
    }

    public void setNfaStateSet(Set<Integer> nfaStateSet){
        this.nfaStateSet = nfaStateSet;
    }
}
