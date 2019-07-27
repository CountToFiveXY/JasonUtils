import Algorithm.Combinatorics;
import FileHelper.BeejakInputGenerator;
import FileHelper.FileContentHandler;
import FileHelper.FileDiffHandler;
import FileHelper.FileNameHandler;

import java.util.Arrays;
import java.util.List;

public class mainClass {
    private static FileNameHandler fileNameHandler;
    private static FileContentHandler fileContentHandler;
    private static FileDiffHandler fileDiffHandler;
    private static Combinatorics combinatorics;
    private static BeejakInputGenerator beejakInputGenerator;

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
        //-----------------//
        fileNameHandler = new FileNameHandler(TEST_IN);

        //-----------------//
        fileContentHandler = new FileContentHandler(INPUT_FILE_PATH);
//        fileContentHandler.handleFile(OUTPUT_FILE_PATH);

        //-----------------//
       fileDiffHandler = new FileDiffHandler(INPUT_FILES_LIST);
//        fileDiffHandler.filterTwoFiles(OUTPUT_FILE_PATH);

        //-----------------//
        combinatorics = new Combinatorics();
//        combinatorics.putBall(5,5).forEach(System.out::println);

        //-----------------//
        boolean isForward = true;
        beejakInputGenerator = new BeejakInputGenerator(isForward);
        beejakInputGenerator.write(BEEJAK_OUTPUT_FILE_PATH);
    }
}

