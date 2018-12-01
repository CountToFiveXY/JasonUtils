import Algorithm.Combinatorics;
import FileHelper.FileContentHandler;
import FileHelper.FileNameHandler;

public class mainClass {
    private static FileNameHandler fileNameHandler;
    private static FileContentHandler fileContentHandler;
    private static Combinatorics combinatorics;

    private static final String INPUT_FOLDER = "/Volumes/Seagate Expansion Drive/shuihu/";
    private static final String INPUT_FILE_PATH = "/Users/bayuqiao/Desktop/data.csv";
    private static final String OUTPUT_FILE_PATH = "/Users/bayuqiao/Desktop/result.csv";

    public static void main(String[] args) throws Exception{
        //-----------------//
//        fileNameHandler = new FileNameHandler(INPUT_FOLDER);

        //-----------------//
        fileContentHandler = new FileContentHandler(INPUT_FILE_PATH);
        fileContentHandler.handleFile(OUTPUT_FILE_PATH);

        //-----------------//
        combinatorics = new Combinatorics();
//        combinatorics.putBall(5,5).forEach(System.out::println);
    }
}

