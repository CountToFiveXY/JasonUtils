package FileHelper;

import IO.FilePrinter;
import IO.InputFileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaxManGraphGenerator {

    String realm;
    String stack;
    String timePeriod;
    String hostPrefix;

    Map<String, String> map = new HashMap<>();

    List<String> file;

    public TaxManGraphGenerator() {
        buildInputMap();
        file = InputFileReader.readFileToList("testFile/input/TaxManGraphTemplate");
    }

    public void write() {
        List<String> modifiedList = file.stream()
                .map(this::replace)
                .collect(Collectors.toList());
        FilePrinter.writeToFile(modifiedList, "testFile/output/" + realm + "-" + stack);
    }

    private String replace(String line) {
        for (String key: map.keySet()) {
            if (line.contains(key)) {
                line = LineModifier.replaceString(line, key, map.get(key));
            }
        }
        return line;
    }

    private void buildInputMap() {
        realm = "NAECP-AWS";
        stack = "SPT";
        timePeriod = "StartTime1=2024-09-10T07:39:00Z&EndTime1=2024-09-10T09:05:00Z";
        hostPrefix = "tea-taxmanv9-awsna-spt";

        map.put("STACK", stack);
        map.put("MARKETPLACE", realm);
        map.put("TIMEPERIOD", timePeriod);
        map.put("HOSTPREFIX", hostPrefix);
    }
}
