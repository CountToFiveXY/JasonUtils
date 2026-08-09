package Asphalt9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class SpecialHunt {

    private static final String MOVE = "SH/move";
    private static final String RUN = "SH/1";
    private static final String SELECT = "SH/select";
    private static final String FUEL = "SH/fuel";

    static MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) throws IOException {
        combine();
    }

    public static void combine() {

        List<String> macro = new ArrayList<>();

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

        combiner.writeMacro("SpecialHunt", macro);
    }
}
