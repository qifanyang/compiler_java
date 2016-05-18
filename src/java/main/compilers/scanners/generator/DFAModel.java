package compilers.scanners.generator;

import compilers.scanners.Lexicon;
import compilers.scanners.Token;
import compilers.scanners.TokenInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqf
 * @version 1.0 2016/5/15
 */
public class DFAModel{
    public List<Integer>[] acceptTables;
    public List<DFAState> dfaStates;
    public Lexicon lexicon;
    public NFAModel nfa;

    public DFAModel(Lexicon lexicon){
        this.lexicon = lexicon;
        this.dfaStates = new ArrayList<>();
        //initialize accept table
//        int stateCount = lexicon.LexerCount;
        int stateCount = 5;
        acceptTables = new ArrayList[stateCount];
        for (int i = 0; i < stateCount; i++){
            acceptTables[i] = new ArrayList<Integer>();
        }

    }

    public void ConvertLexiconToNFA()
    {
        //Compact transition char set
        NFAConverter converter = new NFAConverter();

        NFAState entryState = new NFAState();
        NFAModel lexerNFA = new NFAModel();

        lexerNFA.AddState(entryState);
        for(TokenInfo token : lexicon.getTokens())
        {
            NFAModel tokenNFA = token.CreateFiniteAutomatonModel(converter);

            entryState.AddEdge(tokenNFA.EntryEdge);
            lexerNFA.AddStates(tokenNFA.getStates());
        }

        lexerNFA.EntryEdge = new NFAEdge(entryState);

        nfa = lexerNFA;
    }

}
