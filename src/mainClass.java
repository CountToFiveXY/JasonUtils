import Algorithm.Combinatorics;
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

    //folder path
    private static final String TEST = "testFile/";
    private static final String DESKTOP = "/Users/bayuqiao/Desktop/";
    private static final String DOWNLOAD = "/Users/bayuqiao/Downloads/";

    // input file
    private static final String INPUT_FILE_PATH = TEST + "test";
    private static final String INPUT_FILE1_PATH = TEST + "test1";
    private static final String INPUT_FILE2_PATH = TEST + "test2";
    private static final List<String> INPUT_FILES_LIST = Arrays.asList(INPUT_FILE1_PATH, INPUT_FILE2_PATH);

    //output file
    private static final String OUTPUT_FILE_PATH = TEST + "out";


    public static void main(String[] args) {
        //-----------------//
        fileNameHandler = new FileNameHandler(TEST);

        //-----------------//
        fileContentHandler = new FileContentHandler(INPUT_FILE_PATH);
//        fileContentHandler.handleFile(OUTPUT_FILE_PATH);

        //-----------------//
        fileDiffHandler = new FileDiffHandler(INPUT_FILES_LIST);
        fileDiffHandler.filterTwoFiles(OUTPUT_FILE_PATH);

        //-----------------//
//        combinatorics = new Combinatorics();
//        combinatorics.putBall(5,5).forEach(System.out::println);
    }
}

