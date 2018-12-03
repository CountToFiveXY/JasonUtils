package IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilePrinter {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String SEPARATOR = "=============";

    public static void writeToFile(List<String> text, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            writeNewLine(writer, SEPARATOR);

            for (String line : text) {
                writeNewLine(writer, line);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error Printing");
        }
    }

    private static void writeNewLine(BufferedWriter writer, String line) {
        try {
            writer.write(line + NEW_LINE);
        } catch (IOException e) {
            System.out.println("Error Writing line:" + line);
        }
    }
}
