package FileHelper;

import IO.FilePrinter;
import IO.InputFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileContentHandler {

    List<String> file;

    public FileContentHandler() {
        file = InputFileReader.readFileToList("testFile/input/test");
    }

    public FileContentHandler(String inputFileName) {
        file = InputFileReader.readFileToList(inputFileName);
    }

    public void quickHandle() {
        List<String> modifiedList = file.stream()
                .map(this::modifyEachContent)
                .collect(Collectors.toList());
        FilePrinter.writeToFile(modifiedList, "testFile/output/out");
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
        return String.format("https://odin.amazon.com/#remove-host-entity/materialSetsPage=0&searchInactive=true&hostEntityType=HostClass&hostEntityName=%s", line);
    }
}
