import fa.FileReader;
import fa.FiniteAutomata;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String menu = "0.Exit\n" +
                "1.Display initial state\n" +
                "2.Display FA States\n" +
                "3.Display FA Alphabet\n" +
                "4.Display FA transitions\n" +
                "5.Display FA final states\n" +
                "6.Check if sequence is accepted by the FA\n" +
                "Input>>";
        System.out.println(menu);
        Scanner input = new Scanner(System.in);
        List<String> fileContent = FileReader.readFromFile("src\\fa.in");
        FiniteAutomata finiteAutomata = new FiniteAutomata(fileContent);
        int menuNo = input.nextInt();
        while (menuNo != 0) {
            switch (menuNo) {
                case 1:
                    System.out.println("The initial state is: ");
                    System.out.println(finiteAutomata.getInitialState());
                    break;
                case 2:
                    System.out.println("The FA states are: ");
                    System.out.println(finiteAutomata.getStates());
                    break;
                case 3:
                    System.out.println("The FA alphabet is: ");
                    System.out.println(finiteAutomata.getAlphabet());
                    break;
                case 4:
                    System.out.println("The FA transitions is: ");
                    System.out.println(finiteAutomata.getTransitions());
                    break;
                case 5:
                    System.out.println("The FA final states are: ");
                    System.out.println(finiteAutomata.getFinalStates());
                    break;
                case 6:
                    System.out.println("Give the Finite Automata >>");
                    String fa = input.next();
                    while (!fa.equals("break\n")) {
                        if (finiteAutomata.verifyIfTheFAIsAccepted(fa)) {
                            System.out.println("The Fa is accepted!");
                        } else {
                            System.out.println("The Fa is not accepted!");
                        }
                        fa = input.next();
                    }
                    break;
                default:
                    break;
            }
            menuNo = input.nextInt();
        }
    }
}
