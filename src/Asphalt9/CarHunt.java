package Asphalt9;

import java.util.List;

public class CarHunt extends MacroRun {

    private static final String MOVE = "CH/P1";
    private static final String RUN = "CH/1";
    private static final String SELECT = "CH/select";
    private static final String FUEL = "CH/fuel";

    public static void main(String[] args) {
        new CarHunt().combine();
    }

    @Override
    protected void buildMacro(List<String> macro) {
        String carClass = "B5";

        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, RUN, 5);

        combiner.addMacro(macro, SELECT, 1);
        combiner.addMacro(macro, FUEL, MacroCombiner.classFuels.get(carClass));

        combiner.addMacro(macro, MacroCombiner.BASE, 1);
        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 9);
        combiner.addMacro(macro, RUN, 5);

        combiner.addMacro(macro, SELECT, 1);
        combiner.addMacro(macro, FUEL, MacroCombiner.classFuels.get(carClass));

        combiner.addMacro(macro, MacroCombiner.BASE, 1);
        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 8);

        macro.addAll(combiner.resets());
    }

    @Override
    protected String outputName() {
        return "CarHunt";
    }
}
