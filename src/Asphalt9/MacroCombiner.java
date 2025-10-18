package Asphalt9;

import FileHelper.FileContentHandler;
import IO.FilePrinter;

import java.util.*;

public class MacroCombiner {

    FileContentHandler fileContentHandler = new FileContentHandler();

    private static final String reset = "SE/reset";
    private static final String ad = "SE/ad";
    private static final String close = "SE/close";

    private static final String mp1_d1 = "mp1/D1";
    private static final String mp1_d2 = "mp1/D2";

    private static final String mp2_d1 = "mp2/D1";
    private static final String mp2_d2 = "mp2/D2";

    private static final String B1 = "mp3/1B";
    private static final String B2 = "mp3/2B";
    private static final String B3 = "mp3/3B";
    private static final String B4 = "mp3/4B";
    private static final String B5 = "mp3/5B";
    private static final String macroPath = "macroFiles/";
    private static final String macrosuffix = ".mcr";
    private static final String windows = "/Users/ybao0/OneDrive/Desktop/Macro/";

    @Deprecated
    public void combineBank(List<String> macroList, List<Integer> timesList) {
        List<String> finishedMacros = new ArrayList<>();
        String combinedRunName = combineRuns(macroList, timesList);
        finishedMacros.addAll(multiplySingleMacro("start", 1));
        finishedMacros.addAll(multiplySingleMacro("zl1_cpro", 1));
        finishedMacros.addAll(multiplySingleMacro(combinedRunName, 1));
        finishedMacros.addAll(multiplySingleMacro("600", 1));
        finishedMacros.addAll(multiplySingleMacro("close", 1));
        FilePrinter.overrideFile(finishedMacros, windows + "bank" + macrosuffix);
    }

    public void SE() {
        String move = "SE/C2/move";
        String run = "SE/C2/1";
        List<String> SE = new ArrayList<>();
        SE.addAll(multiplySingleMacro(move, 1));
        SE.addAll(multiplySingleMacro(run, 9));
        SE.addAll(multiplySingleMacro(ad, 6));
        SE.addAll(multiplySingleMacro(run, 9));
        SE.addAll(multiplySingleMacro(ad, 7));
        SE.addAll(multiplySingleMacro(run, 5));
        SE.addAll(multiplySingleMacro(ad, 4));
        SE.addAll(multiplySingleMacro(close, 2));
        SE.addAll(multiplySingleMacro(reset, 1));
        FilePrinter.overrideFile(SE, windows + "SE" + macrosuffix);
    }

    public void carHunt() {
        String move = "SE/CH/move";
        String run = "SE/CH/1";
        String fuel = "SE/CH/fuel1";
        List<String> SE = new ArrayList<>();
        SE.addAll(multiplySingleMacro(move, 1));
        SE.addAll(multiplySingleMacro(run, 5));
        SE.addAll(multiplySingleMacro(fuel, 1));
        SE.addAll(multiplySingleMacro(ad, 8));
        SE.addAll(multiplySingleMacro(run, 5));
        SE.addAll(multiplySingleMacro(fuel, 1));
        SE.addAll(multiplySingleMacro(ad, 8));
        SE.addAll(multiplySingleMacro(reset, 1));
        FilePrinter.overrideFile(SE, windows + "CarHunt" + macrosuffix);
    }

    public void DS() {
        String move = "DS/1";
        String dsAd = "DS/ad";
        List<String> SE = new ArrayList<>();
        SE.addAll(multiplySingleMacro(move, 5));
        SE.addAll(multiplySingleMacro(dsAd, 5));
        FilePrinter.overrideFile(SE, windows + "DriverS" + macrosuffix);
    }

    public void MP1() {
        int time = 6;
        List<String> MP1 = new ArrayList<>();
        MP1.addAll(multiplySingleMacro(mp1_d1, time));
        MP1.addAll(multiplySingleMacro(mp1_d2, time));
        FilePrinter.overrideFile(MP1, windows + "MP1" + macrosuffix);
    }

    public void MP2() {
        int time = 6;
        List<String> MP2 = new ArrayList<>();
        MP2.addAll(multiplySingleMacro(mp2_d1, time));
        MP2.addAll(multiplySingleMacro(mp2_d2, time));
        FilePrinter.overrideFile(MP2, windows + "MP2" + macrosuffix);
    }

    public void MP3() {
        List<String> farmMilestones = new ArrayList<>();
        farmMilestones.addAll(multiplySingleMacro(B1, 4));
        farmMilestones.addAll(multiplySingleMacro(B2, 4));
        farmMilestones.addAll(multiplySingleMacro(B3, 4));
        farmMilestones.addAll(multiplySingleMacro(B4, 4));
        farmMilestones.addAll(multiplySingleMacro(B5, 3));
        FilePrinter.overrideFile(farmMilestones, windows + "MP3" + macrosuffix);
    }

    public String combineRuns(List<String> macroList, List<Integer> timesList) {
        StringJoiner sb = new StringJoiner("_");
        List<String> finishedMacros = new ArrayList<>();
        for (int i = 0 ; i < macroList.size() ; i++) {
            String macro = macroList.get(i);
            int times = timesList.get(i);
            finishedMacros.addAll(multiplySingleMacro(macro, times));
            finishedMacros.add(System.getProperty("line.separator"));
            sb.add(macro.split("/")[1] + "x" + times);
        }
        String combinedRunName = sb.toString();
        FilePrinter.overrideFile(finishedMacros, macroPath + combinedRunName + macrosuffix);
        return combinedRunName;
    }

    private List<String> multiplySingleMacro(String macroName, int times) {
        return fileContentHandler.multiplySingleFile(macroPath + macroName + macrosuffix, times);
    }

    private List<Integer> createTimes(int gas) {
        return Collections.nCopies(gas, 1);
    }
}
