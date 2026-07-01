package Asphalt9;

import FileHelper.FileContentHandler;
import IO.FilePrinter;

import java.io.IOException;
import java.util.*;

public class MacroCombiner {

    FileContentHandler fileContentHandler = new FileContentHandler();

    private static final String close = "close";
    private static final String reset = "reset";
    private static final String loading = "load";
    private static final String window = "window";
    private static final String ad = "ad";
    private static final String open = "opengame";

    private static final String mp1_d1 = "mp1/D1";
    private static final String mp1_d2 = "mp1/D2";

    private static final String mp2_d1 = "mp2/D1";
    private static final String mp2_d2 = "mp2/D2";
    private static final String macroPath = "macroFiles/";
    private static final String macrosuffix = ".mcr";
    private static final String windows = "/Users/ybao0/OneDrive/Desktop/Macro/";

    public MacroCombiner() {
        FilePrinter.overrideFile(resets(), windows + "reset" + macrosuffix);
    }

    public static void main(String[] args) throws IOException {
        MacroCombiner macroCombiner = new MacroCombiner();
        macroCombiner.carHunt();
    }

    public void SE() {
        String se = "SE/SE3";
        List<String> macro = new ArrayList<>(multiplySingleMacro(se, 1));
        String move = "SE/SH/P1";
        String run = "SE/SH/1";
        macro.addAll(multiplySingleMacro(move, 1));
        macro.addAll(multiplySingleMacro(run, 9));
        macro.addAll(multiplySingleMacro(ad, 7));
        macro.addAll(multiplySingleMacro(run, 9));
        macro.addAll(multiplySingleMacro(ad, 7));
        macro.addAll(multiplySingleMacro(run, 3));
        macro.addAll(multiplySingleMacro(ad, 2));
        macro.addAll(resets());
        FilePrinter.overrideFile(macro, windows + "SE" + macrosuffix);
    }

    public void spotlight() {
        String se = "SE/SE1";
        List<String> macro = new ArrayList<>(multiplySingleMacro(se, 1));
        String move = "SE/SP/move";
        String run = "SE/SP/1";
        macro.addAll(multiplySingleMacro(move, 1));
        macro.addAll(multiplySingleMacro(run, 9));
        macro.addAll(multiplySingleMacro(ad, 7));
        macro.addAll(multiplySingleMacro(run, 9));
        macro.addAll(multiplySingleMacro(ad, 7));
        macro.addAll(multiplySingleMacro(run, 3));
        macro.addAll(multiplySingleMacro(ad, 2));
        macro.addAll(resets());
        FilePrinter.overrideFile(macro, windows + "SP" + macrosuffix);
    }

    public void carHunt() {
        String move = "CH/P1";
        String run = "CH/1";
        String select = "CH/select";
        String fuel = "CH/fuel1";
        List<String> SE = new ArrayList<>();
        SE.addAll(multiplySingleMacro(move, 1));
        SE.addAll(multiplySingleMacro(run, 5));
        SE.addAll(multiplySingleMacro(fuel, 1));
        SE.addAll(multiplySingleMacro(move, 1));
        SE.addAll(multiplySingleMacro(ad, 9));
        SE.addAll(multiplySingleMacro(run, 5));
        SE.addAll(multiplySingleMacro(fuel, 1));
        SE.addAll(multiplySingleMacro(move, 1));
        SE.addAll(multiplySingleMacro(ad, 8));
        SE.addAll(resets());
        FilePrinter.overrideFile(SE, windows + "CarHunt" + macrosuffix);
    }

    private List<String> resets() {
        List<String> macro = new ArrayList<>();
        macro.addAll(multiplySingleMacro(close, 1));
        macro.addAll(multiplySingleMacro(reset, 1));
        macro.addAll(multiplySingleMacro(open, 1));
        macro.addAll(multiplySingleMacro(loading, 1));
        macro.addAll(multiplySingleMacro(window, 1));
        return macro;
    }

    public void DS() {
        String ds = "DS/1";
        String dsAd = "DS/ad";
        List<String> SE = new ArrayList<>();
        SE.addAll(multiplySingleMacro(ds, 3));
        SE.addAll(multiplySingleMacro(dsAd, 6));
        FilePrinter.overrideFile(SE, windows + "DriverS" + macrosuffix);
    }

    public void MP1() {
        List<String> MP1 = new ArrayList<>();
        MP1.addAll(multiplySingleMacro(mp1_d1, 6));
        MP1.addAll(multiplySingleMacro(open, 1));
        MP1.addAll(multiplySingleMacro(mp1_d2, 6));
        MP1.addAll(multiplySingleMacro(open, 1));
        FilePrinter.overrideFile(MP1, windows + "MP1" + macrosuffix);
    }

    public void MP2() {
        int time = 6;
        List<String> MP2 = new ArrayList<>();
        MP2.addAll(multiplySingleMacro(mp2_d1, time));
        MP2.addAll(multiplySingleMacro(mp2_d2, time));
        FilePrinter.overrideFile(MP2, windows + "MP2" + macrosuffix);
    }

    private List<String> multiplySingleMacro(String macroName, int times) {
        return fileContentHandler.multiplySingleFile(macroPath + macroName + macrosuffix, times);
    }

    private List<String> repeat(List<String> macro, int times) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            res.addAll(macro);
        }
        return res;
    }
}
