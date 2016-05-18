package compilers.scanners;

/**
 * @author yangqf
 * @version 1.0 2016/5/15
 */
public class FiniteAutomationEngine{

    public char[] charClassTable;
    public int currentState;
    public int[][] transitionTable;

    public FiniteAutomationEngine(int[][] transitionTable, char[] charClassTable){
        this.transitionTable = transitionTable;
        this.charClassTable = charClassTable;
        currentState = 1;
    }

    public void reset(){
        currentState = 1;//转换表每次重置
    }

    //转换表处理输入字符
    public void input(char c){
        //下一状态数组
        int[] transitions = transitionTable[currentState];
        //find out which is the next state
        int nextStateIndex = -1;
        for(int index = 0; index < charClassTable.length; index++){
            if(charClassTable[index] == c){
                nextStateIndex = index;
                break;
            }
        }
        int nextState = transitions[nextStateIndex];
        //go to next state
        currentState = nextState;
    }

    public void inputString(String str){
        char[] chars = str.toCharArray();
        for(char c : chars){
            input(c);
        }
    }

}
