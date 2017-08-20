package simple.ast;

import java.util.List;

/**
 * Created by yangqifan on 2017/8/20.
 */
public class PrototypeAST{
    private String fnName;
    private List<String> argNames;
    public PrototypeAST(String fnName, List<String> argNames){
        this.fnName = fnName;
        this.argNames = argNames;
    }

    public String getFnName() {
        return fnName;
    }

    public void setFnName(String fnName) {
        this.fnName = fnName;
    }

    public List<String> getArgNames() {
        return argNames;
    }

    public void setArgNames(List<String> argNames) {
        this.argNames = argNames;
    }
}
