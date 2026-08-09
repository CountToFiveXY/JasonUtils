package Asphalt9;

import FileHelper.FileContentHandler;
import IO.FilePrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MacroCombiner {

    private final FileContentHandler fileContentHandler = new FileContentHandler();

    static final String CLOSE = "close";
    static final String RESET = "reset";
    static final String LOADING = "load";
    static final String WINDOWED = "window";
    static final String AD = "ad";
    static final String BASE = "base";
    static final String OPEN = "opengame";
    static final Map<String, Integer> classFuels = new HashMap<>();

    private static final String MACRO_PATH = "macroFiles/";
    private static final String MACRO_SUFFIX = ".mcr";

    private static final String windows = "/Users/ybao0/OneDrive/Desktop/Macro/";

    public MacroCombiner() {
        writeMacro("reset", resets());
        classFuels.put("D4", 2);
        classFuels.put("C4", 5);
        classFuels.put("C5", 6);
        classFuels.put("B5", 7);
        classFuels.put("B6", 8);
    }

    List<String> resets() {
        List<String> macro = new ArrayList<>();

        addMacro(macro, CLOSE, 1);
        addMacro(macro, RESET, 1);
        addMacro(macro, OPEN, 1);
        addMacro(macro, LOADING, 1);
        addMacro(macro, WINDOWED, 1);

        return macro;
    }

    void addMacro(List<String> macro, String macroName, int times) {
        macro.addAll(multiplySingleMacro(macroName, times));
    }

    void writeMacro(String fileName, List<String> macro) {
        FilePrinter.overrideFile(
                macro,
                windows + fileName + MACRO_SUFFIX
        );
    }

    private List<String> multiplySingleMacro(String macroName, int times) {
        return fileContentHandler.multiplySingleFile(
                MACRO_PATH + macroName + MACRO_SUFFIX,
                times
        );
    }
}
