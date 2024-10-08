package Asphalt9;

import FileHelper.FileContentHandler;
import IO.FilePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MacroCombiner {

    FileContentHandler fileContentHandler = new FileContentHandler();

    private static String macroPath = "macroFiles/";
    private static String macrosuffix = ".mcr";
    private static String windowDesktop = "/Users/ybao0/OneDrive/Desktop/";

    public void combine(List<String> macroList, List<Integer> timesList) {
        List<String> finishedMacros = new ArrayList<>();
        String combinedRunName = combineRuns(macroList, timesList);
        finishedMacros.addAll(multiplySingleMacro("start", 1));
        finishedMacros.addAll(multiplySingleMacro(combinedRunName, 1));
        finishedMacros.addAll(multiplySingleMacro("600", 1));
        FilePrinter.writeToFile(finishedMacros, windowDesktop + "output" + macrosuffix);
    }

    public String combineRuns(List<String> macroList, List<Integer> timesList) {
        StringJoiner sb = new StringJoiner("_");
        List<String> finishedMacros = new ArrayList<>();
        for (int i = 0 ; i < macroList.size() ; i++) {
            String macro = macroList.get(i);
            int times = timesList.get(i);
            finishedMacros.addAll(multiplySingleMacro(macroList.get(i), timesList.get(i)));
            finishedMacros.add(System.getProperty("line.separator"));
            sb.add(macro + "x" + times);
        }
        String combinedRunName = sb.toString();
        FilePrinter.writeToFile(finishedMacros, macroPath + combinedRunName + macrosuffix);
        return sb.toString();
    }

    private List<String> multiplySingleMacro(String macroName, int times) {
        return fileContentHandler.multiplySingleFile(macroPath + macroName + macrosuffix, times);
    }
}
