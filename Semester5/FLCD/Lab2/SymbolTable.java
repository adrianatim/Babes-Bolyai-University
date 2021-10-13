import java.util.*;

public class SymbolTable {

    private final static List<String> RESERVED_WORDS = List.of("declare", "int", "string", "char", "boolean", "div", "mod", "print", "read",
            "if", "elif", "else", "True", "False", "for", "while", "increase", "by", "start", "end", "break");

    private final Map<String, Integer> symbolTable;

    public SymbolTable() {
        this.symbolTable = new Hashtable<>();
    }

    public int add(String symbol) {
        int position = getPosition(symbol);
        if (!symbolTable.containsKey(symbol) && !RESERVED_WORDS.contains(symbol)) {
            symbolTable.put(symbol, position);
        }
        return position;
    }

    public int getPosition(String symbol) {
        return symbol.length() % 10;
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
