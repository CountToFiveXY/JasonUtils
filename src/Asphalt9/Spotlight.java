package Asphalt9;

import java.util.List;

public class Spotlight extends MacroRun {

    private static final String START = "SE/SE1";
    private static final String MOVE = "SE/SP/move";
    private static final String RUN = "SE/SP/1";
    private static final String SELECT = "SE/SP/select";
    private static final String FUEL = "SE/SP/fuel";

    public static void main(String[] args) {
        new Spotlight().combine();
    }

    @Override
    protected void buildMacro(List<String> macro) {
        String carClass = "A6";
        combiner.addMacro(macro, START, 1);
        combiner.addMacro(macro, MOVE, 1);

        combiner.addMacro(macro, RUN, 4);

        combiner.addMacro(macro, SELECT, 1);
        combiner.addMacro(macro, FUEL, MacroCombiner.classFuels.get(carClass));

        combiner.addMacro(macro, MacroCombiner.BASE, 1);
        combiner.addMacro(macro, START, 1);
        combiner.addMacro(macro, MOVE, 1);

        combiner.addMacro(macro, MacroCombiner.AD, 6);

        combiner.addMacro(macro, RUN, 4);

        combiner.addMacro(macro, MacroCombiner.AD, 4);

        combiner.addMacro(macro, SELECT, 1);
        combiner.addMacro(macro, FUEL, MacroCombiner.classFuels.get(carClass));

        macro.addAll(combiner.resets());
    }

    @Override
    protected String outputName() {
        return "SP";
    }
}
