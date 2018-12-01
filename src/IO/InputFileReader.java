package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputFileReader {

    public static List<String> readFileToList(String inputFileName) {

        File file;
        BufferedReader br;
        String line;
        List<String> lines = new ArrayList<>();
        Set<String> checkSet = new HashSet<>();

        try {
            file = new File(inputFileName);
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Can't Find file: " + inputFileName);
        }

        return lines;
    }

    //add this method before line.add(line) if necessary
    private boolean isDuplicate(Set<String> checkSet, String line) {
        if (checkSet.contains(line)) {
            return false;
        }
        checkSet.add(line);
        return true;
    }
}
