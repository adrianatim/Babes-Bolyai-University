package scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitText {
    public static final String SPACE = " ";
    public static final String SQUARE_BRACKETS = "[\\[\\]]";

    public static List<String> splitLine(String line) {
        List<String> finalListOfTokens = new ArrayList<>();
        String[] newList = line.split(SPACE);
        for (String value : newList) {
            String[] tokensSplitBySquareBrackets = value.split(SQUARE_BRACKETS);
            finalListOfTokens.addAll(Arrays.asList(tokensSplitBySquareBrackets));
        }
        return eraseEmptyValuesFromList(finalListOfTokens);
    }

    private static List<String> eraseEmptyValuesFromList(List<String> tokens) {
        tokens.removeIf(String::isEmpty);
        return tokens;
    }
}