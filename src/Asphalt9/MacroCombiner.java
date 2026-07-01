package Asphalt9;

import FileHelper.FileContentHandler;
import IO.FilePrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MacroCombiner {

    private final FileContentHandler fileContentHandler = new FileContentHandler();

    private static final String CLOSE = "close";
    private static final String RESET = "reset";
    private static final String LOADING = "load";
    private static final String WINDOWED = "window";
    private static final String AD = "ad";
    private static final String OPEN = "opengame";

    private static final String MP1_D1 = "mp1/D1";
    private static final String MP1_D2 = "mp1/D2";
    private static final String MP2_D1 = "mp2/D1";
    private static final String MP2_D2 = "mp2/D2";

    private static final String MACRO_PATH = "macroFiles/";
    private static final String MACRO_SUFFIX = ".mcr";

    private static final String windows = "/Users/ybao0/OneDrive/Desktop/Macro/";

    public MacroCombiner() {
        writeMacro("reset", resets());
    }

    public static void main(String[] args) throws IOException {
        MacroCombiner macroCombiner = new MacroCombiner();
        macroCombiner.carHunt();
    }

    public void SE() {
        String start = "SE/SE3";
        String move = "SE/SH/P1";
        String run = "SE/SH/1";

        List<String> macro = new ArrayList<>();

        addMacro(macro, start, 1);
        addMacro(macro, move, 1);
        addMacro(macro, run, 9);
        addMacro(macro, AD, 7);
        addMacro(macro, run, 9);
        addMacro(macro, AD, 7);
        addMacro(macro, run, 3);
        addMacro(macro, AD, 2);
        macro.addAll(resets());

        writeMacro("SE", macro);
    }

    public void spotlight() {
        String start = "SE/SE1";
        String move = "SE/SP/move";
        String run = "SE/SP/1";

        List<String> macro = new ArrayList<>();

        addMacro(macro, start, 1);
        addMacro(macro, move, 1);
        addMacro(macro, run, 9);
        addMacro(macro, AD, 7);
        addMacro(macro, run, 9);
        addMacro(macro, AD, 7);
        addMacro(macro, run, 3);
        addMacro(macro, AD, 2);
        macro.addAll(resets());

        writeMacro("SP", macro);
    }

    public void carHunt() {
        String move = "CH/P1";
        String run = "CH/1";

        String select = "CH/select";
        String fuel = "CH/fuelD";
        String base = "CH/base";

        List<String> macro = new ArrayList<>();

        addMacro(macro, move, 1);
        addMacro(macro, run, 5);

        addMacro(macro, select, 1);
        addMacro(macro, fuel, 1);
        addMacro(macro, base, 1);

        addMacro(macro, move, 1);
        addMacro(macro, AD, 9);
        addMacro(macro, run, 5);

        addMacro(macro, select, 1);
        addMacro(macro, fuel, 1);
        addMacro(macro, base, 1);

        addMacro(macro, move, 1);
        addMacro(macro, AD, 8);

        macro.addAll(resets());

        writeMacro("CarHunt", macro);
    }

    public void DS() {
        String ds = "DS/1";
        String dsAd = "DS/ad";

        List<String> macro = new ArrayList<>();

        addMacro(macro, ds, 3);
        addMacro(macro, dsAd, 6);

        writeMacro("DriverS", macro);
    }

    public void MP1() {
        List<String> macro = new ArrayList<>();

        addMacro(macro, MP1_D1, 6);
        addMacro(macro, OPEN, 1);
        addMacro(macro, MP1_D2, 6);
        addMacro(macro, OPEN, 1);

        writeMacro("MP1", macro);
    }

    public void MP2() {
        List<String> macro = new ArrayList<>();

        addMacro(macro, MP2_D1, 6);
        addMacro(macro, MP2_D2, 6);

        writeMacro("MP2", macro);
    }

    private List<String> resets() {
        List<String> macro = new ArrayList<>();

        addMacro(macro, CLOSE, 1);
        addMacro(macro, RESET, 1);
        addMacro(macro, OPEN, 1);
        addMacro(macro, LOADING, 1);
        addMacro(macro, WINDOWED, 1);

        return macro;
    }

    private void addMacro(List<String> macro, String macroName, int times) {
        macro.addAll(multiplySingleMacro(macroName, times));
    }

    private void writeMacro(String fileName, List<String> macro) {
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