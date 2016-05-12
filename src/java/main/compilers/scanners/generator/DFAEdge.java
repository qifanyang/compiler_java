package compilers.scanners.generator;

/**
 * DFA 边
 * DFA 状态从一个状态转到另一个状态的 边
 * @author yangqf
 * @version 1.0 2016/5/6
 */
public class DFAEdge{
    private int symbol;//从一个状态转换到另一个状态 的边上的符号
    private DFAState targetState;
    public DFAEdge(int symbol, DFAState targetState){
        this.symbol = symbol;
        this.targetState = targetState;
    }

    public int getSymbol(){
        return symbol;
    }

    public void setSymbol(int symbol){
        this.symbol = symbol;
    }

    public DFAState getTargetState(){
        return targetState;
    }

    public void setTargetState(DFAState targetState){
        this.targetState = targetState;
    }
}
