package FileHelper;

import DataType.FileType;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.lang.StringBuilder;

public class FileNameHandler {
    private File folder;
    private static final String FILE_SEPARATOR = ".";

    public FileNameHandler(String folderPath) {
        this.folder = new File(folderPath);
    }

    public boolean isFolder() {
        return folder.isDirectory();
    }

    public boolean isFolderEmpty() {
        return folder.listFiles() == null;
    }

    public void AddFileTypeToAllFiles(FileType fileType) {
        if (!isFolder() || isFolderEmpty()) {
            return;
        }

        String type = FILE_SEPARATOR + fileType.toString();
        List<File> allFiles = Arrays.asList(folder.listFiles());
        System.out.println(allFiles.size());
        allFiles.forEach(
                file -> {
                    if(!file.getName().endsWith(".mkv")) {
                        System.out.println(file.getName()+"here");
                        file.renameTo(
                                new File(file.getAbsolutePath()+ type));
                    }
                }
        );
    }

    public void AddPrefixToAllFiles(String prefix) {
        if (!isFolder() || isFolderEmpty()) {
            return;
        }

        List<File> allFiles = Arrays.asList(folder.listFiles());
        allFiles.forEach(
                file -> file.renameTo(
                        new File(
                                new StringBuilder(prefix)
                                        .append(file.getName())
                                        .toString())
                        )
        );
    }

    public void AddSuffixToAllFiles(String suffix) {
        if (!isFolder() || isFolderEmpty()) {
            return;
        }

        List<File> allFiles = Arrays.asList(folder.listFiles());

    }

    public void checkFolder() {

    }
}
