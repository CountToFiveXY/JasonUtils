package FileHelper;

import IO.FilePrinter;
import IO.InputFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileContentHandler {

    List<String> file;

    public FileContentHandler() { }

    public FileContentHandler(String inputFileName) {
        file = InputFileReader.readFileToList(inputFileName);
    }

    public void handleFile(String outputFileName) {
        List<String> modifiedList = file.stream()
                .map(this::modifyEachContent)
                .collect(Collectors.toList());
        FilePrinter.writeToFile(modifiedList, outputFileName);
    }

    public List<String> multiplySingleFile(String fileName, int times) {
        List<String> finishedMacros = new ArrayList<>();
        List<String> macroLines = InputFileReader.readFileToList(fileName);
        for (int i = 0; i < times; i++) {
            finishedMacros.addAll(macroLines);
        }
        return finishedMacros;
    }

    private String modifyEachContent(String line) {
        if (line.contains("LeftButtonDown")) {
            return "";
        }

        if (line.contains("LeftButtonUp")) {
            return line.replace("LeftButtonUp", "Click");
        }
        return line;
    }
}
