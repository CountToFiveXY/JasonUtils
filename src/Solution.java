import IO.FilePrinter;

import java.util.*;


class Solution {
    private static final String hourFormat = "YYYY-MM-DDT";
    private static final String format = "YYYY-MM-DDThh:mm:ssZ";
    private static final String KEY_WORD = "blockchain validation error";

    private static final String FILE_NAME =  "blockchain_errors.txt";

    private FilePrinter printer = new FilePrinter();

    private static Map<String, Integer> map = new LinkedHashMap<>();
    public static synchronized List<String>  parse(List<String> logs) {
        List<String> filtered = new ArrayList<>();
        for (String log: logs) {
            String actualLogMessage = log.substring(format.length()+1);

            if (actualLogMessage.startsWith(KEY_WORD)) {
                String timeStampHour = log.substring(hourFormat.length(), hourFormat.length()+2);

                if (!map.containsKey(timeStampHour)) {
                    map.put(timeStampHour, 1);
                } else {
                    map.put(timeStampHour, map.get(timeStampHour)+1);
                }

                filtered.add(log);
            }
        }
        return filtered;
    }

    private static void writeToFile(List<String> logs) {
        FilePrinter.writeToFile(logs, FILE_NAME);
    }

    private static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            String hour = entry.getKey() + ":00: ";
            String suffix = entry.getValue() == 1? " error" : " errors";
            String count = entry.getValue() + suffix;
            System.out.println(hour + count);
        }
    }

    private static void printFiltered(List<String> list) {
        System.out.println("printing filtered list");
        for (String s : list) {
            System.out.println(String.format("%s", s));
        }
        System.out.println("=========");
    }


    public static void main(String[] args) {
        List<String> input = Arrays.asList(
                "2025-01-27T08:15:29Z transaction processed successfully",
                "2025-01-27T08:20:13Z blockchain validation error",
                "2025-01-27T08:45:00Z transaction processed successfully",
                "2025-01-27T09:10:45Z blockchain validation error",
                "2025-01-27T09:20:05Z blockchain validation error",
                "2025-01-27T10:00:00Z AI model prediction failed",
                "2025-01-27T10:30:25Z transaction processed successfully",
                "2025-01-27T11:00:45Z blockchain validation error"
        );
        //step1
        List<String> filtered = parse(input);
        printFiltered(filtered);

        //step2
        System.out.println(filtered.size());

        //step3
        writeToFile(filtered);

        //step4
        printMap(map);

    }
}