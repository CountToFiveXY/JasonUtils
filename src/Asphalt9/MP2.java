package Asphalt9;

import java.util.List;

public class MP2 extends MacroRun {

    private static final String MP2_D1 = "mp2/D1";
    private static final String MP2_D2 = "mp2/D2";

    public static void main(String[] args) {
        new MP2().combine();
    }

    @Override
    protected void buildMacro(List<String> macro) {
        combiner.addMacro(macro, MP2_D1, 6);
        combiner.addMacro(macro, MP2_D2, 6);
    }

    @Override
    protected String outputName() {
        return "MP2";
    }
}
