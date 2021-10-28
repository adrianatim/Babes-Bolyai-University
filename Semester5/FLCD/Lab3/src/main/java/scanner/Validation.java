package scanner;

import file.ReadWrite;

import java.util.List;
import java.util.regex.Pattern;

public class Validation {

    private final static List<String> RESERVED_WORDS_AND_CHARACTERS = ReadWrite.getFileContent("src/main/resources/reserved/token.in");
    private static final List<String> BOOLEAN_CONSTANTS = List.of("True", "False");

    public int verifyItsIdentifier(String token) {
        if (!RESERVED_WORDS_AND_CHARACTERS.contains(token) && verifyIfIdentifierIsCorrectFormatted(token)) {
            return getPosition(token);
        }
        return -1;
    }

    public int verifyItsConstant(String token) {
        if (!RESERVED_WORDS_AND_CHARACTERS.contains(token) && BOOLEAN_CONSTANTS.contains(token) || verifyIfItsStringType(token)
                || verifyIfItsNumericType(token) || verifyIfItsCharType(token)) {
            return getPosition(token);
        }
        return -1;
    }

    public boolean verifyIfItsReservedWord(String token) {
        return RESERVED_WORDS_AND_CHARACTERS.contains(token);
    }

    private boolean verifyIfIdentifierIsCorrectFormatted(String identifier) {
        String regex = "^([a-zA-Z][a-zA-Z0-9]*$)";
        return Pattern.matches(regex, identifier);
    }

    private boolean verifyIfItsNumericType(String constant) {
        String regex = "^([0-9]*)$";
        return Pattern.matches(regex, constant);
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
}