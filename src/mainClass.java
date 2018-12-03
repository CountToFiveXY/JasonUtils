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

    private static final String INPUT_FOLDER = "/Volumes/Seagate Expansion Drive/shuihu/";
    private static final String INPUT_FILE_PATH = "/Users/bayuqiao/Desktop/data.csv";
    private static final String INPUT_FILE1_PATH = "testFile/test1";
    private static final String INPUT_FILE2_PATH = "testFile/test2";
    private static final List<String> INPUT_FILES_LIST = Arrays.asList(INPUT_FILE1_PATH, INPUT_FILE2_PATH);
    private static final String OUTPUT_FILE_PATH = "/Users/bayuqiao/Desktop/result.csv";

    public static void main(String[] args) {
        //-----------------//
        fileNameHandler = new FileNameHandler(INPUT_FOLDER);

        //-----------------//
        fileContentHandler = new FileContentHandler(INPUT_FILE_PATH);
//        fileContentHandler.handleFile(OUTPUT_FILE_PATH);

        //-----------------//
        fileDiffHandler = new FileDiffHandler(INPUT_FILES_LIST);
        fileDiffHandler.filterTwoFiles("testFile/out");

        //-----------------//
        combinatorics = new Combinatorics();
//        combinatorics.putBall(5,5).forEach(System.out::println);
    }
}

