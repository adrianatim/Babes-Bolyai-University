package table;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ProgramInternalForm {

    private final List<Pair<String, Integer>> programInternalForm;
    private final SymbolTable identifierSymbolTable;
    private final SymbolTable constantSymbolTable;

    public ProgramInternalForm(SymbolTable identifierSymbolTable, SymbolTable constantSymbolTable) {
        this.identifierSymbolTable = identifierSymbolTable;
        this.constantSymbolTable = constantSymbolTable;
        this.programInternalForm = new ArrayList<>();
    }

    public void add(String token) {
        if (identifierSymbolTable.getSymbolTable().containsKey(token) || constantSymbolTable.getSymbolTable().containsKey(token)) {
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