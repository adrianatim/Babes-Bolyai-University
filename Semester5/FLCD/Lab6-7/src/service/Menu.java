package service;

import domain.GrammarModel;
import domain.Production;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private final GrammarModel grammarModel;

    public Menu(GrammarModel grammarModel) {
        this.grammarModel = grammarModel;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            System.out.println("\n");

            switch (choice) {
                case 1:
                    grammarModel.getNonTerminals().forEach(nonTerminal -> System.out.print(nonTerminal + " "));
                    break;
                case 2:
                    grammarModel.getTerminals().forEach(terminal -> System.out.print(terminal + " "));
                    break;
                case 3:
                    grammarModel.getProductions().forEach(System.out::println);
                    break;
                case 4:
                    try {
                        System.out.println("~Insert non-terminal: ");
                        String nonTerminal = scanner.next();

                        List<Production> productionList = grammarModel.getProductionsForNonTerminal(nonTerminal);
                        System.out.println("--The productions for the " + nonTerminal + " non terminal are:");
                        productionList.forEach(production -> System.out.println("\t" + production));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("~Insert sequence: ");
                        String sequence = scanner.next();

                        List<String> inputSequence;
                        if (sequence.length() > 1) {
                            inputSequence = Arrays.stream(sequence.split("")).collect(Collectors.toList());
                        } else {
                            inputSequence = List.of(sequence);
                        }

                        DescendentRecursiveParser descendentRecursiveParser = new DescendentRecursiveParser(grammarModel, inputSequence);
                        descendentRecursiveParser.runParser();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case 0:
                    System.exit(0);
                default:
                    System.out.println("~Invalid choice!~\n");
                    break;
            }
        }
    }

    private void printMenu() {
        String printMessage = "";
        printMessage += "1. Set of non terminals\n";
        printMessage += "2. Set of terminals \n";
        printMessage += "3. Set of productions \n";
        printMessage += "4. Productions for a given non terminal\n";
        printMessage += "5. Verify if a sequence is accepted or not\n";
        printMessage += "0 -> Close the app\n";

        printMessage += "\n\n";

        System.out.println(printMessage);
    }
}