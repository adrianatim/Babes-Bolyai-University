package fa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiniteAutomata {

    private static final String SPACE = " ";
    private final List<String> states;
    private final List<String> alphabet;
    private String initialState;
    private final List<String> finalStates;
    private final Map<List<String>, String> transitions;

    public FiniteAutomata(List<String> fileContent) {
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.transitions = new HashMap<>();
        assignValuesFromFileToTheParameters(fileContent);
    }

    public boolean verifyIfTheFAIsAccepted(String sequence) {
        if (isDFA()) {
            String currentState = initialState;
            for (int i = 0; i < sequence.length(); i++) {
                char constant = sequence.charAt(i);
                List<String> list = new ArrayList<>();
                list.add(currentState);
                list.add(String.valueOf(constant));

                if (transitions.containsKey(list)) {
                    currentState = transitions.get(list);
                } else {
                    return false;
                }
            }
            return isFinalState(currentState);
        }
        return false;
    }

    public List<String> getStates() {
        return states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public Map<List<String>, String> getTransitions() {
        return transitions;
    }

    private void assignValuesFromFileToTheParameters(List<String> fileContent) {
        String[] auxStates = fileContent.get(0).split(SPACE);
        initializeLists(auxStates, states);
        String[] auxAlphabet = fileContent.get(1).split(SPACE);
        initializeLists(auxAlphabet, alphabet);
        initialState = String.valueOf(fileContent.get(2).charAt(5));
        String[] auxFinalStates = fileContent.get(3).split(SPACE);
        initializeLists(auxFinalStates, finalStates);
        for (int i = 5; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            String chr1 = String.valueOf(line.charAt(1));
            String chr2 = String.valueOf(line.charAt(4));
            String chr3 = String.valueOf(line.charAt(9));
            transitions.put(List.of(chr1, chr2), chr3);
        }
    }

    private boolean isDFA() {
        for (List<String> key : transitions.keySet()) {
            if (transitions.get(key) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isFinalState(String string) {
        for (String finalState : finalStates) {
            if (finalState.equals(string)) {
                return true;
            }
        }
        return false;
    }

    private void initializeLists(String[] aux, List<String> list) {
        for (String a : aux) {
            if (a.length() == 1) {
                list.add(a);
            }
        }
    }
}
