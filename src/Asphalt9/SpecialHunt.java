package Asphalt9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class SpecialHunt {

    static MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) throws IOException {
        combine();
    }

    public static void combine() {
        String move = "SH/move";
        String run = "SH/1";
        String fuel = "SH/fuel";

        List<String> macro = new ArrayList<>();

        combiner.addMacro(macro, move, 1);
        combiner.addMacro(macro, run, 4);
        combiner.addMacro(macro, fuel, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 7);
        combiner.addMacro(macro, run, 4);
        combiner.addMacro(macro, fuel, 1);
        combiner.addMacro(macro, MacroCombiner.AD, 7);

        macro.addAll(combiner.resets());

        combiner.writeMacro("SpecialHunt", macro);
    }
}
