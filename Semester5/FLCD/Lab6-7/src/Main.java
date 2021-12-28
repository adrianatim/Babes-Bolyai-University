import domain.GrammarModel;
import service.GrammarReader;
import service.Menu;
import test.TestFunctions;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> sampleGrammar() {
        List<String> grammar = new ArrayList<>();
        grammar.add("null");
        grammar.add("main");
        grammar.add("{");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("scrie");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("scrie");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("scrie");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("daca");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(">");
        grammar.add("identificator");
        grammar.add("{");
        grammar.add("identificator");
        grammar.add("=");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("afiseaza");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("}");

        return grammar;
    }

    public static void main(String[] args) throws Exception {
        final String GRAMMAR_FILE_PATH = "src/resources/g1.txt";

        GrammarReader grammarReader = new GrammarReader(GRAMMAR_FILE_PATH);
        GrammarModel grammarModel = grammarReader.readGrammar();

        TestFunctions testFunctions = new TestFunctions();
        testFunctions.test();
//
        Menu menu = new Menu(grammarModel);
        menu.run();
//        List<String> inputSequence = new ArrayList<>(Arrays.asList("a", "a", "c", "b", "c"));
//        List<String> inputSequence = new ArrayList<>(Arrays.asList("c"));
//        DescendentRecursiveParser descendentRecursiveParser = new DescendentRecursiveParser(grammarModel, inputSequence);
//        descendentRecursiveParser.runParser();

        System.out.println("~end of program~");
    }
}
