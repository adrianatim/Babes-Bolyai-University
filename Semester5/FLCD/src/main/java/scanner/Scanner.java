package scanner;

import file.ReadWrite;
import table.ProgramInternalForm;
import table.SymbolTable;

import java.io.IOException;
import java.util.List;

public class Scanner {

    private final SymbolTable identifierSymbolTable;
    private final SymbolTable constantSymbolTable;
    private final Validation validation;

    public Scanner(SymbolTable identifierSymbolTable, SymbolTable constantSymbolTable) {
        this.identifierSymbolTable = identifierSymbolTable;
        this.constantSymbolTable = constantSymbolTable;
        this.validation = new Validation();
    }

    public String verifyAndAddTokensInSTAndPIF(List<String> miniProgram, String ST_filename, String PIF_filename) throws IOException {
        ProgramInternalForm programInternalForm = new ProgramInternalForm(identifierSymbolTable, constantSymbolTable);
        boolean semaphore = true;
        for (int i = 0; i < miniProgram.size(); i++) {
            List<String> tokens = SplitText.splitLine(miniProgram.get(i));
            int j = 0;
            try {
                for (String token : tokens) {
                    if (validation.verifyItsConstant(token) != -1) {
                        constantSymbolTable.add(token, validation.verifyItsConstant(token));
                    } else if (validation.verifyItsIdentifier(token) != -1) {
                        identifierSymbolTable.add(token, validation.verifyItsIdentifier(token));
                    } else if (!validation.verifyIfItsReservedWord(token)) {
                        throw new RuntimeException("Error");
                    }
                    programInternalForm.add(token);
                    j += 1;
                }
            } catch (RuntimeException e) {
                semaphore = false;
                int counterLine = i + 1;
                System.err.println("Error at line " + counterLine + " -" + tokens.get(j));
            }
        }
        ReadWrite.writeSTToFile(ST_filename, identifierSymbolTable, constantSymbolTable);
        ReadWrite.writePIFToFile(PIF_filename, programInternalForm);
        if (semaphore) {
            return "Lexical correct!";
        }
        return "Lexical incorrect!";
    }

}
