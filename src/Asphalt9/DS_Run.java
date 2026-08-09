package Asphalt9;

import java.util.List;

public class DS_Run extends MacroRun {

    private static final String DS = "DS/1";
    private static final String DS_AD = "DS/ad";

    public static void main(String[] args) {
        new DS_Run().combine();
    }

    @Override
    protected void buildMacro(List<String> macro) {
        combiner.addMacro(macro, DS, 3);
        combiner.addMacro(macro, DS_AD, 6);
    }

    @Override
    protected String outputName() {
        return "DriverS";
    }
}
