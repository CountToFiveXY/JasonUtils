package Asphalt9;

import java.util.List;

public class Spotlight extends MacroRun {

    private static final String START = "SE/SE1";
    private static final String MOVE = "SE/SP/move";
    private static final String RUN = "SE/SP/1";

    public static void main(String[] args) {
        new Spotlight().combine();
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

        macro.addAll(combiner.resets());
    }

    @Override
    protected String outputName() {
        return "SP";
    }
}
