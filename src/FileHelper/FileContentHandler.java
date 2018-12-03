package FileHelper;

import IO.FilePrinter;
import IO.InputFileReader;

import java.util.List;
import java.util.stream.Collectors;

public class FileContentHandler {

    List<String> file;

    public FileContentHandler() { }

    public FileContentHandler(String inputFileName) {
        file = InputFileReader.readFileToList(inputFileName);
    }

    public int getLineNumber() {
        return file.size();
    }

    public void handleFile(String outputFileName) {
        List<String> modifiedList = file.stream()
                .map(this::modifyEachContent)
                .collect(Collectors.toList());
        FilePrinter.writeToFile(modifiedList, outputFileName);
    }

    private String modifyEachContent(String line) {
        return line + "," + line;
    }
}
