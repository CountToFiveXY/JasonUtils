package FileHelper;
import IO.FilePrinter;
import IO.InputFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDiffHandler {

    List<List<String>> files = new ArrayList<>();

    public FileDiffHandler(List<String> inputFileNames) {
        for (String fileName : inputFileNames) {
            files.add(InputFileReader.readFileToList(fileName));
        }
    }

    public void filterTwoFiles(String outputFileName) {
        List<String> keySet = files.get(1)
                .stream()
                .map(this::getKeyFromLine)
                .collect(Collectors.toList());

        List<String> filtererFile = files.get(0)
                .stream()
                .filter(line -> keySet.contains(getKeyFromLine(line)))
                .collect(Collectors.toList());
        FilePrinter.writeToFile(filtererFile, outputFileName);
    }

    private String getKeyFromLine(String line) {
        return line;
    }
}
