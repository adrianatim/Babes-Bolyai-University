import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Grammar {

    private static final String SPACE = " ";
    private List<String> nonTerminals;
    private List<String> terminals;
    private String startingSymbol;
    private Map<String, List<String>> productions;

    public Grammar(String filename) {
        this.nonTerminals = new ArrayList<>();
        this.terminals = new ArrayList<>();
        this.productions = new HashMap<>();
        List<String> readLines = readFromFile(filename);
        initializeFields(readLines);
    }

    public static List<String> readFromFile(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<String> getForASpecificNonTerminal(String nonTerminal) {
        if (nonTerminals.contains(nonTerminal)) {
            return productions.get(nonTerminal);
        }
        return List.of();
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public Map<String, List<String>> getProductions() {
        return productions;
    }

    public void validateStartingSymbol() throws Exception {
        if (!nonTerminals.contains(startingSymbol)) {
            throw new Exception("Invalid starting symbol! " + startingSymbol + " was not found in the nonTerminals list!");
        }
    }

    public void validateProductions() throws Exception {
        for (int i = 0; i < productions.size(); i++) {
            for (String nonTerminal : nonTerminals) {
                if (!productions.containsKey(nonTerminal)) {
                    throw new Exception("Invalid production! " + nonTerminal + " is not a nonTerminal!");
                }
            }
        }
    }

    private void initializeFields(List<String> readLines) {
        nonTerminals = Arrays.asList(readLines.get(0).split(SPACE));
        terminals = Arrays.asList(readLines.get(1).split(SPACE));
        startingSymbol = readLines.get(2);
        for (int i = 3; i < readLines.size(); i++) {
            String line = readLines.get(i);
            List<String> values = Arrays.asList(line.substring(4).split(" "));
            List<String> finalValues = eraseDelimiter(values);
            productions.put("" + line.charAt(0), finalValues);
        }
    }

    private List<String> eraseDelimiter(List<String> values) {
        List<String> finalList = new ArrayList<>();
        for (String value : values) {
            if (!value.equals("|")) {
                finalList.add(value);
            }
        }
        return finalList;
    }
}
