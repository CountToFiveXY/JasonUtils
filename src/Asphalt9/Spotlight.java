package Asphalt9;

import java.util.ArrayList;
import java.util.List;

public class Spotlight {

    private static final String START = "SE/SE1";
    private static final String MOVE = "SE/SP/move";
    private static final String RUN = "SE/SP/1";

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

        combiner.writeMacro("SP", macro);
    }
}
