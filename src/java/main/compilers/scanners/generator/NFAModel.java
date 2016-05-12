package compilers.scanners.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有正则表达式都可以转化为一个有一条输入边，以及一个接受状态的的正则表达式
 * @author yangqf
 * @version 1.0 2016/5/11
 */
public class NFAModel{

    private List<NFAState> states;//中间状态
    public NFAState TailState;//终止状态
    /**进入初始状态的输入边*/
    public NFAEdge EntryEdge;
    public NFAModel(){
        states = new ArrayList<>();
    }

    /**
     * 添加顺序很重要
     * @param state
     */
    public void AddState(NFAState state){
        states.add(state);
        state.setIndex(states.size() - 1);
    }

    public void AddStates(List<NFAState> states){
        for(NFAState state : states){
            AddState(state);
        }
    }

    public List<NFAState> getStates(){
        return states;
    }

    public void setStates(List<NFAState> states){
        this.states = states;
    }

    public NFAState getTailState(){
        return TailState;
    }

    public void setTailState(NFAState tailState){
        TailState = tailState;
    }

    public NFAEdge getEntryEdge(){
        return EntryEdge;
    }

    public void setEntryEdge(NFAEdge entryEdge){
        EntryEdge = entryEdge;
    }
}
