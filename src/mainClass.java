import FileHelper.FileNameHandler;
import DataType.FileType;

public class mainClass {
    private static FileNameHandler fileNameHandler;
    private static final String FOLDER_LOCATION = "/Volumes/Seagate Expansion Drive/shuihu/";

    public static void main(String[] args) {
        fileNameHandler = new FileNameHandler(FOLDER_LOCATION);
        fileNameHandler.AddFileTypeToAllFiles(FileType.mkv);
    }
}
