import Algorithm.Combinatorics;
import Asphalt9.DriverSyndicate;
import Asphalt9.MacroCombiner;
import FileHelper.TaxManGraphGenerator;
import FileHelper.FileContentHandler;
import FileHelper.FileDiffHandler;
import FileHelper.FileNameHandler;
import FileHelper.Lottery;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mainClass {
    private static FileNameHandler fileNameHandler;
    private static FileContentHandler fileContentHandler;
    private static FileDiffHandler fileDiffHandler;
    private static Combinatorics combinatorics;
    private static TaxManGraphGenerator taxManGraphGenerator;
    private static Lottery lottery;
    private static DriverSyndicate syndicate;
    private static MacroCombiner macroCombiner;

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

    public static void main(String[] args) {
        macroCombiner = new MacroCombiner();
        macroCombiner.combineSE();
    }
}