import file.ReadWrite;
import table.ProgramInternalForm;
import table.SymbolTable;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final String FILE_PATH = "src/main/resources/miniProgram";
    public static final String PROBLEM1 = FILE_PATH + "/p1";
    public static final String PROBLEM2 = FILE_PATH + "/p2";
    public static final String PROBLEM3 = FILE_PATH + "/p3";
    public static final String PROBLEM2_WITH_ERROR = FILE_PATH + "/p2-err";

    public static void main(String[] args) throws IOException {
        List<String> miniProgram1 = ReadWrite.getFileContent(PROBLEM1);
        System.out.println("Problem 1 is: " + verifyAndAddTokensInST(miniProgram1, "ST-1", "PIF-1"));
        List<String> miniProgram2 = ReadWrite.getFileContent(PROBLEM2);
        System.out.println("Problem 2 is: " + verifyAndAddTokensInST(miniProgram2, "ST-2", "PIF-2"));
        List<String> miniProgram3 = ReadWrite.getFileContent(PROBLEM3);
        System.out.println("Problem 3 is: " + verifyAndAddTokensInST(miniProgram3, "ST-3", "PIF-3"));
        List<String> miniProgram2Err = ReadWrite.getFileContent(PROBLEM2_WITH_ERROR);
        System.out.println("Problem 2-err is: " + verifyAndAddTokensInST(miniProgram2Err, "ST-2_ERR", "PIF-2_ERR"));
    }

    public static String verifyAndAddTokensInST(List<String> miniProgram, String ST_filename, String PIF_filename) throws IOException {
        SymbolTable symbolTable = new SymbolTable();
        ProgramInternalForm programInternalForm = new ProgramInternalForm(symbolTable);
        boolean semaphore = true;
        for (int i = 0; i < miniProgram.size(); i++) {
            List<String> tokens = SplitText.splitLine(miniProgram.get(i));
            int j = 0;
            try {
                for (String token : tokens) {
                    symbolTable.add(token);
                    programInternalForm.add(token);
                    j += 1;
                }
            } catch (RuntimeException e) {
                semaphore = false;
                int counterLine = i + 1;
                System.err.println("Error at line " + counterLine + " -" + tokens.get(j));
            }
        }
        ReadWrite.writeSTToFile(ST_filename, symbolTable);
        ReadWrite.writePIFToFile(PIF_filename, programInternalForm);
        if (semaphore) {
            return "Lexical correct!";
        }
        return "Lexical incorrect!";
    }
}
