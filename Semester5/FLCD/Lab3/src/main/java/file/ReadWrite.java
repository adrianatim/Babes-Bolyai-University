package file;

import table.ProgramInternalForm;
import table.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadWrite {

    public static List<String> getFileContent(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static void writeSTToFile(String filename, SymbolTable symbolTable1, SymbolTable symbolTable2) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(symbolTable1.toString());
        writer.write("\n");
        writer.write(symbolTable2.toString());
        writer.close();
    }

    public static void writePIFToFile(String filename, ProgramInternalForm programInternalForm) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(programInternalForm.toString());
        writer.close();
    }
}