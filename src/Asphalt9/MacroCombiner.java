package Asphalt9;

import FileHelper.FileContentHandler;
import IO.FilePrinter;

import java.util.*;

public class MacroCombiner {

    FileContentHandler fileContentHandler = new FileContentHandler();

    private static final String r1 = "mp1/run1";
    private static final String r2 = "mp1/run2";
    private static final String d1 = "mp1/D1";
    private static final String d2 = "mp1/D2";
    private static final String d3 = "mp2/D1";
    private static final String d4 = "mp2/D2";

    private static final String a1 = "mp3/a1";
    private static final String a2 = "mp3/a2";
    private static final String a3 = "mp3/a3";
    private static final String A1 = "mp3/1A";
    private static final String A2 = "mp3/2A";
    private static final String A3 = "mp3/3A";
    private static final String b1 = "mp3/b1";
    private static final String b2 = "mp3/b2";
    private static final String b3 = "mp3/b3";
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

    public void combineMultiplayer1() {
        List<String> dropMacro = new ArrayList<>();
        int time = 7;
        dropMacro.addAll(multiplySingleMacro(r1, time));
        dropMacro.addAll(multiplySingleMacro(r2, time));
        FilePrinter.overrideFile(dropMacro, windows + "drop" + macrosuffix);

        List<String> MP1 = new ArrayList<>();
        MP1.addAll(multiplySingleMacro(d1, time));
        MP1.addAll(multiplySingleMacro(d2, time));
        FilePrinter.overrideFile(MP1, windows + "MP1" + macrosuffix);
    }

    public void combineMultiplayer2() {
        int time = 6;
        List<String> MP2 = new ArrayList<>();
        MP2.addAll(multiplySingleMacro(d3, time));
        MP2.addAll(multiplySingleMacro(d4, time));
        FilePrinter.overrideFile(MP2, windows + "MP2" + macrosuffix);
    }

    public void combineMultiplayer3() {
        List<String> farmMilestones = new ArrayList<>();

        /*
        String B2run = combineRuns(Arrays.asList(B2,B2,B2,B2,B2,B2,B2,B2), createTimes(8));
        String B1run = combineRuns(Arrays.asList(B1,B1,B1,B1,B1,B1,B1), createTimes(7));
        String B3run = combineRuns(Arrays.asList(B3,B3,B3,B3,B3,B3,B3,B3), createTimes(8));
        String A1run = combineRuns(Arrays.asList(A1,A1,A1,A1,A1,A1,A1,A1), createTimes(8));
        String A2run = combineRuns(Arrays.asList(A2,A2,A2,A2,A2,A2,A2,A2), createTimes(8));
        String A3run = combineRuns(Arrays.asList(A3,A3,A3,A3,A3,A3,A3,A3), createTimes(8));
        */
        farmMilestones.addAll(multiplySingleMacro(B1, 9));
        farmMilestones.addAll(multiplySingleMacro(B2, 9));
        farmMilestones.addAll(multiplySingleMacro(B3, 9));
        farmMilestones.addAll(multiplySingleMacro(B4, 9));
        farmMilestones.addAll(multiplySingleMacro(B5, 9));
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
