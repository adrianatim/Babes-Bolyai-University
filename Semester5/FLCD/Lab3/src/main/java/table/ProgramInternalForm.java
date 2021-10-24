package table;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ProgramInternalForm {

    private final List<Pair<String, Integer>> programInternalForm;
    private final SymbolTable symbolTable;

    public ProgramInternalForm(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.programInternalForm = new ArrayList<>();
    }

    public void add(String token) {
        if (symbolTable.getIdentifierSymbolTable().containsKey(token) || symbolTable.getConstantSymbolTable().containsKey(token)) {
            programInternalForm.add(Pair.of(token, token.hashCode()));
        } else {
            programInternalForm.add(Pair.of(token, -1));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pair<String, Integer> stringIntegerPair : programInternalForm) {
            stringBuilder.append(stringIntegerPair.getLeft())
                    .append(" -> ")
                    .append(stringIntegerPair.getRight())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
