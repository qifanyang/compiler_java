package compilers.scanners.generator;

/**
 * NFA 边,
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class NFAEdge{
    private Integer symbol;//为null 表示epsilon
    private NFAState targetState;

    /**
     * 有符号的边
     * @param symbol
     * @param targetState
     */
    public NFAEdge(int symbol, NFAState targetState){
        this.symbol = symbol;
        this.targetState = targetState;
    }

    /**
     * epsilon 符号边
     * @param targetState
     */
    public NFAEdge(NFAState targetState){
        this.targetState = targetState;
    }

    public Integer getSymbol(){
        return symbol;
    }

    public void setSymbol(int symbol){
        this.symbol = symbol;
    }

    public NFAState getTargetState(){
        return targetState;
    }

    public void setTargetState(NFAState targetState){
        this.targetState = targetState;
    }
}
