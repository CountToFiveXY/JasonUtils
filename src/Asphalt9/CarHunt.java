package Asphalt9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarHunt {

    private static final String MOVE = "CH/P1";
    private static final String RUN = "CH/1";
    private static final String SELECT = "CH/select";
    private static final String FUEL = "CH/fuel";

    private static final MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) {
        combine();
    }

    public static void combine() {
        List<String> macro = new ArrayList<>();
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

        combiner.writeMacro("CarHunt", macro);
    }
}
