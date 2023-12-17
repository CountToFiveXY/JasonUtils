import Algorithm.Combinatorics;
import Asphalt9.DriverSyndicate;
import FileHelper.BeejakInputGenerator;
import FileHelper.FileContentHandler;
import FileHelper.FileDiffHandler;
import FileHelper.FileNameHandler;
import FileHelper.Lottery;

import java.util.Arrays;
import java.util.List;

public class mainClass {
    private static FileNameHandler fileNameHandler;
    private static FileContentHandler fileContentHandler;
    private static FileDiffHandler fileDiffHandler;
    private static Combinatorics combinatorics;
    private static BeejakInputGenerator beejakInputGenerator;
    private static Lottery lottery;
    private static DriverSyndicate syndicate;

    //folder path
    private static final String TEST_IN = "testFile/input/";
    private static final String TEST_OUT = "testFile/output/";
    private static final String DESKTOP = "/Users/bayuqiao/Desktop/";
    private static final String DOWNLOAD = "/Users/bayuqiao/Downloads/";

    // input file
    private static final String INPUT_FILE_PATH = TEST_IN + "test";
    private static final String INPUT_FILE1_PATH = TEST_IN + "test1";
    private static final String INPUT_FILE2_PATH = TEST_IN + "test2";
    private static final List<String> INPUT_FILES_LIST = Arrays.asList(INPUT_FILE1_PATH, INPUT_FILE2_PATH);

    //output file
    private static final String OUTPUT_FILE_PATH = TEST_OUT + "out";
    private static final String BEEJAK_OUTPUT_FILE_PATH = TEST_OUT + "beejakInput";


    public static void main(String[] args) {
        syndicate = new DriverSyndicate();
        syndicate.getCoins();
    }
}

