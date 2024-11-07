package Asphalt9;

import FileHelper.FileContentHandler;
import IO.FilePrinter;

import java.util.*;

public class MacroCombiner {

    FileContentHandler fileContentHandler = new FileContentHandler();

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
    private static final String macroPath = "macroFiles/";
    private static final String macrosuffix = ".mcr";
    private static final String windowDesktop = "/Users/ybao0/OneDrive/Desktop/";

    @Deprecated
    public void combineBank(List<String> macroList, List<Integer> timesList) {
        List<String> finishedMacros = new ArrayList<>();
        String combinedRunName = combineRuns(macroList, timesList);
        finishedMacros.addAll(multiplySingleMacro("start", 1));
        finishedMacros.addAll(multiplySingleMacro("zl1_cpro", 1));
        finishedMacros.addAll(multiplySingleMacro(combinedRunName, 1));
        finishedMacros.addAll(multiplySingleMacro("600", 1));
        finishedMacros.addAll(multiplySingleMacro("close", 1));
        FilePrinter.overrideFile(finishedMacros, windowDesktop + "bank" + macrosuffix);
    }

    public void combineMultiplayer3() {
        List<String> finishedMacros = new ArrayList<>();
        String B2run = combineRuns(Arrays.asList(b2,B2,b2,B2,b2,B2), createTimes(6));
        String B1run = combineRuns(Arrays.asList(b1,B1,b1,B1,b1), createTimes(5));
        String B3run = combineRuns(Arrays.asList(B3,b3,B3,b3,B3), createTimes(5));
        String A1run = combineRuns(Arrays.asList(a1,A1,a1,A1,a1), createTimes(5));
        String A2run = combineRuns(Arrays.asList(A2,a2,A2,a2), createTimes(4));
        String A3run = combineRuns(Arrays.asList(A3,a3,A3), createTimes(3));
        finishedMacros.addAll(multiplySingleMacro(B2run, 1));
        finishedMacros.addAll(multiplySingleMacro(B1run, 1));
        finishedMacros.addAll(multiplySingleMacro(B3run, 1));
        finishedMacros.addAll(multiplySingleMacro(A1run, 1));
        finishedMacros.addAll(multiplySingleMacro(A2run, 1));
        finishedMacros.addAll(multiplySingleMacro(A3run, 1));

        FilePrinter.overrideFile(finishedMacros, windowDesktop + "MP3" + macrosuffix);
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
