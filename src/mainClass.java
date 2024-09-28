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


    private static final String a1 = "a1";
    private static final String a2 = "a2";
    private static final String a3 = "a3";
    private static final String A1 = "1A";
    private static final String A2 = "2A";
    private static final String A3 = "3A";
    private static final String b1 = "b1";
    private static final String b2 = "b2";
    private static final String b3 = "b3";
    private static final String B1 = "1B";
    private static final String B2 = "2B";
    private static final String B3 = "3B";

    /*
    b1,B1,b1,B1,b1
    B2,b2,B2,b2,B2,b2,
    B3,b3,B3,b3,B3,
    a1,A1,a1,A1,a1
    a2,A2,a2,A2,
    a3,A3,a3,A3,
    */
    public static void main(String[] args) {
        macroCombiner = new MacroCombiner();
        macroCombiner.combineRuns(
                Arrays.asList(
                        //b1,B1,b1,B1,b1
                        //B2,b2,B2,b2,B2,b2,
                        //B3,b3,B3,
                         //b3,B3,
                        A1,a1,A1,a1
                        ,A2,a2,A2,a2
                        //a3,A3,a3,A3
                ),
                Arrays.asList(
                        //1,1,1,1,1,
                        1,1,1,1,
                        1,1,1,1
                        //1,1,1,1,1,
                        //1,1,1,1,
                        //1,1,1,1
                )
        );
    }
}

