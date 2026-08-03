package Asphalt9;

import java.util.ArrayList;
import java.util.List;

public class SE {

    private static final String START = "SE/SE3";
    private static final String MOVE = "SE/SH/P1";
    private static final String RUN = "SE/SH/1";

    private static final MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) {
        combine();
    }

    public static void combine() {
        List<String> macro = new ArrayList<>();

        combiner.addMacro(macro, START, 1);
        combiner.addMacro(macro, MOVE, 1);
        combiner.addMacro(macro, RUN, 9);
        combiner.addMacro(macro, MacroCombiner.AD, 7);
        combiner.addMacro(macro, RUN, 9);
        combiner.addMacro(macro, MacroCombiner.AD, 7);
        combiner.addMacro(macro, RUN, 3);
        combiner.addMacro(macro, MacroCombiner.AD, 2);
        macro.addAll(combiner.resets());

        combiner.writeMacro("SE", macro);
    }
}
