package Asphalt9;

import java.util.List;

public class MP1 extends MacroRun {

    private static final String MP1_D1 = "mp1/D1";
    private static final String MP1_D2 = "mp1/D2";
    private static final String OPEN = "MP1/opengame";

    public static void main(String[] args) {
        new MP1().combine();
    }

    @Override
    protected void buildMacro(List<String> macro) {
        combiner.addMacro(macro, MP1_D1, 3);
        combiner.addMacro(macro, OPEN, 1);
        combiner.addMacro(macro, MP1_D1, 3);
        combiner.addMacro(macro, OPEN, 1);
        combiner.addMacro(macro, MP1_D2, 3);
        combiner.addMacro(macro, OPEN, 1);
        combiner.addMacro(macro, MP1_D2, 3);

    }

    @Override
    protected String outputName() {
        return "MP1";
    }
}
