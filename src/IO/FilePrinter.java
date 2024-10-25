package IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FilePrinter {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String SEPARATOR = "===================================================";
    private static BufferedWriter writer;

    public static void writeToFile(List<String> text, String fileName) {
        try {
            boolean isExisted = new File(fileName).exists();
            writer = new BufferedWriter(new FileWriter(fileName, true));

            if (isExisted) {
                writer.write(NEW_LINE );
                writeNewLine(SEPARATOR);
            }

            for (Iterator<String> iterator = text.iterator(); iterator.hasNext();) {
                String line = iterator.next();

                if (iterator.hasNext())
                    writeNewLine(line);
                else
                    writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error Printing File" + e.getMessage());
        }
    }

    public static void overrideFile(List<String> text, String fileName) {
        try {
            File file = new File(fileName);
            boolean isExisted = file.exists();
            if (isExisted){
                file.delete();
            }
            writer = new BufferedWriter(new FileWriter(fileName, true));

            for (Iterator<String> iterator = text.iterator(); iterator.hasNext();) {
                String line = iterator.next();

                if (iterator.hasNext())
                    writeNewLine(line);
                else
                    writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error Printing File" + e.getMessage());
        }
    }

    private static void writeNewLine(String line) {
        try {
            writer.write(line + NEW_LINE);
        } catch (IOException e) {
            System.out.println("Error Writing line:" + line);
        }
    }
}
