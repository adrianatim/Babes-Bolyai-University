import java.util.Scanner;

public class Main {
    private static final String FILENAME1 = "src/g1.txt";

    public static void main(String[] args) {
        String menu = "0.Exit\n" +
                "1.Display nonTerminals\n" +
                "2.Display terminals\n" +
                "3.Display starting symbol\n" +
                "4.Display productions\n" +
                "5.Display for specific production\n" +
                "Input>>";
        System.out.println(menu);
        Scanner input = new Scanner(System.in);
        Grammar grammar = new Grammar(FILENAME1);
        try {
            grammar.validateStartingSymbol();
            grammar.validateProductions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int menuNo = input.nextInt();
        while (menuNo != 0) {
            switch (menuNo) {
                case 1:
                    System.out.println("The nonTerminals are: ");
                    System.out.println(grammar.getNonTerminals());
                    break;
                case 2:
                    System.out.println("The terminals are: ");
                    System.out.println(grammar.getTerminals());
                    break;
                case 3:
                    System.out.println("The starting symbol is: ");
                    System.out.println(grammar.getStartingSymbol());
                    break;
                case 4:
                    System.out.println("The productions are: ");
                    System.out.println(grammar.getProductions());
                    break;
                case 5:
                    System.out.println("Give the nonTerminal >>");
                    String nt = input.next();
                    while (!nt.equals("break\n")) {
                        System.out.println(grammar.getForASpecificNonTerminal(nt));
                        nt = input.next();
                    }
                    break;
                default:
                    break;
            }
            menuNo = input.nextInt();
        }
    }
}
