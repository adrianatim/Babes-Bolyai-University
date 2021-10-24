package table;

import file.ReadWrite;
import org.apache.commons.lang3.StringUtils;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SymbolTable {
    private final static List<String> RESERVED_WORDS = ReadWrite.getFileContent("src/main/resources/reserved/reservedWords");
    private final static List<String> RESERVED_CHARACTERS = ReadWrite.getFileContent("src/main/resources/reserved/reservedCharacters");
    private static final List<String> BOOLEAN_CONSTANTS = List.of("True", "False");
    private final Map<String, Integer> identifierSymbolTable;
    private final Map<String, Integer> constantSymbolTable;

    public SymbolTable() {
        this.identifierSymbolTable = new Hashtable<>();
        this.constantSymbolTable = new Hashtable<>();
    }

    public void add(String symbol) {
        int position = getPosition(symbol);
        if (!identifierSymbolTable.containsKey(symbol) && !constantSymbolTable.containsKey(symbol)
                && !RESERVED_WORDS.contains(symbol) && !RESERVED_CHARACTERS.contains(symbol)) {
            if (BOOLEAN_CONSTANTS.contains(symbol) || verifyIfItsStringType(symbol) || StringUtils.isNumeric(symbol) ||
                    verifyIfItsCharType(symbol)) {
                constantSymbolTable.put(symbol, position);
            } else if (verifyIfIdentifierIsCorrectFormatted(symbol)) {
                identifierSymbolTable.put(symbol, position);
            } else throw new RuntimeException("Error!");
        }
    }

    public Map<String, Integer> getIdentifierSymbolTable() {
        return identifierSymbolTable;
    }

    public Map<String, Integer> getConstantSymbolTable() {
        return constantSymbolTable;
    }

    private boolean verifyIfIdentifierIsCorrectFormatted(String identifier) {
        String regex = "^([a-zA-Z][a-zA-Z0-9]*$)";
        return Pattern.matches(regex, identifier);
    }

    private boolean verifyIfItsStringType(String token) {
        char firstCharacter = token.charAt(0);
        char lastCharacter = token.charAt(token.length() - 1);

        if (token.length() >= 3) {
            String finalToken = token.substring(1, token.length() - 1);
            return firstCharacter == '"' && lastCharacter == '"' && !finalToken.contains("\"");
        }
        return false;
    }

    private boolean verifyIfItsCharType(String token) {
        if (token.length() == 3) {
            String firstCharacter = String.valueOf(token.charAt(0));
            String middleCharacter = String.valueOf(token.charAt(1));
            String lastCharacter = String.valueOf(token.charAt(2));
            return firstCharacter.equals("'") && lastCharacter.equals("'") && !middleCharacter.contains("'");
        }
        return false;
    }

    private int getPosition(String token) {
        return token.hashCode();
    }

    @Override
    public String toString() {
        return "IdentifierSymbolTable=" + identifierSymbolTable +
                "\nConstantSymbolTable=" + constantSymbolTable;
    }
}
