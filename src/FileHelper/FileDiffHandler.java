package FileHelper;
import IO.FilePrinter;
import IO.InputFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDiffHandler {

    private List<List<String>> files = new ArrayList<>();

    public FileDiffHandler(List<String> inputFileNames) {
        for (String fileName : inputFileNames) {
            files.add(InputFileReader.readFileToList(fileName));
        }
    }

    public void filterTwoFiles(String outputFileName) {
        List<String> keySet = files.get(1)
                .stream()
                .map(this::getKeyFromIndexFile)
                .collect(Collectors.toList());

        List<String> filteredFile = files.get(0)
                .stream()
                .filter(line -> keySet.contains(getKeyFromOriginalFile(line)))
                .collect(Collectors.toList());
        FilePrinter.writeToFile(filteredFile, outputFileName);
    }

    private String getKeyFromIndexFile(String line) {
        return line;
    }

    private String getKeyFromOriginalFile(String line) {
        return line.split(",")[1];
    }
}
