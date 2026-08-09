package Asphalt9;

import java.util.List;

public class SpecialHunt extends MacroRun {

    private static final String MOVE = "SH/move";
    private static final String RUN = "SH/1";
    private static final String SELECT = "SH/select";
    private static final String FUEL = "SH/fuel";

    public static void main(String[] args) {
        new SpecialHunt().combine();
    }

    @Override
    protected void buildMacro(List<String> macro) {
        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, RUN, 3);
        combiner.addMacro(macro, FUEL, 1);

        combiner.addMacro(macro, MacroCombiner.BASE, 1);
        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 8);

        combiner.addMacro(macro, RUN, 3);
        combiner.addMacro(macro, FUEL, 1);

        combiner.addMacro(macro, MacroCombiner.BASE, 1);
        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 8);

        macro.addAll(combiner.resets());
    }

    @Override
    protected String outputName() {
        return "SpecialHunt";
    }
}
