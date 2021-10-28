package table;

import java.util.Hashtable;
import java.util.Map;

public class SymbolTable {
    private final Map<String, Integer> symbolTable;

    public SymbolTable() {
        this.symbolTable = new Hashtable<>();
    }

    public void add(String symbol, int position) {
        this.symbolTable.put(symbol, position);
    }

    public Map<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "symbolTable=" + symbolTable +
                '}';
    }
}