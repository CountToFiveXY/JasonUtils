package Asphalt9;

import java.util.List;

public class SE extends MacroRun {

    private static final String START = "SE/SE4";
    private static final String MOVE = "SE/SH/P2";
    private static final String RUN = "SE/SH/1";

    public static void main(String[] args) {
        new SE().combine();
    }

    @Override
    protected void buildMacro(List<String> macro) {
        combiner.addMacro(macro, START, 1);
        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, RUN, 9);
        combiner.addMacro(macro, MacroCombiner.AD, 7);
        combiner.addMacro(macro, RUN, 9);
        combiner.addMacro(macro, MacroCombiner.AD, 7);
        combiner.addMacro(macro, RUN, 3);
        combiner.addMacro(macro, MacroCombiner.AD, 2);
        //reset in the end
        macro.addAll(combiner.resets());
    }

    @Override
    protected String outputName() {
        return "SE";
    }
}
