import Algorithm.Combinatorics;
import Asphalt9.DriverSyndicate;
import Asphalt9.MacroCombiner;
import FileHelper.TaxManGraphGenerator;
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

    private static final String c1 = "C1";
    private static final String c2 = "C2";
    private static final String c3 = "C3";
    private static final String c4 = "C4";
    private static final String c5 = "C5";


    public static void main(String[] args) {
        macroCombiner = new MacroCombiner();
        //macroCombiner.combineRuns(Arrays.asList(c5), Arrays.asList(5));
        //macroCombiner.combine(Arrays.asList(c1,c3,c5,c3,c4,c2,c4), Arrays.asList(10,10,1,9,10,1,9));
        macroCombiner.combine(Arrays.asList(c5,c3,c4,c1,c3,c1,c4), Arrays.asList(1,9,10,1,9,10,10));
    }
}

