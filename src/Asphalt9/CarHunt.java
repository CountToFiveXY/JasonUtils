package Asphalt9;

import java.util.ArrayList;
import java.util.List;

public class CarHunt {

    private static final String MOVE = "CH/P1";
    private static final String RUN = "CH/1";
    private static final String SELECT = "CH/select";
    private static final String FUEL = "CH/fuelD";
    private static final String BASE = "CH/base";

    private static final MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) {
        combine();
    }

    public static void combine() {
        List<String> macro = new ArrayList<>();

        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, RUN, 5);

        combiner.addMacro(macro, SELECT, 1);
        combiner.addMacro(macro, FUEL, 1);
        combiner.addMacro(macro, BASE, 1);

        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 9);
        combiner.addMacro(macro, RUN, 5);

        combiner.addMacro(macro, SELECT, 1);
        combiner.addMacro(macro, FUEL, 1);
        combiner.addMacro(macro, BASE, 1);

        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 8);

        macro.addAll(combiner.resets());

        combiner.writeMacro("CarHunt", macro);
    }
}
