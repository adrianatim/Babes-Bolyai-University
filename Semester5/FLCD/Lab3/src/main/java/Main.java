import file.ReadWrite;
import scanner.Scanner;
import table.SymbolTable;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final String FILE_PATH = "src/main/resources/miniProgram";

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            SymbolTable identifierSymbolTable = new SymbolTable();
            SymbolTable constantSymbolTable = new SymbolTable();
            Scanner scanner = new Scanner(identifierSymbolTable, constantSymbolTable);
            i++;
            List<String> miniProgram = ReadWrite.getFileContent(FILE_PATH + "/p" + i);
            System.out.println("Problem " + i + " is: " + scanner.verifyAndAddTokensInSTAndPIF(miniProgram, "ST-" + i, "PIF-" + i));
            i--;
        }
    }
}
