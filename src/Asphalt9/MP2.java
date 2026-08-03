package Asphalt9;

import java.util.ArrayList;
import java.util.List;

public class MP2 {

    private static final String MP2_D1 = "mp2/D1";
    private static final String MP2_D2 = "mp2/D2";

    private static final MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) {
        combine();
    }

    public static void combine() {
        List<String> macro = new ArrayList<>();

        combiner.addMacro(macro, MP2_D1, 6);
        combiner.addMacro(macro, MP2_D2, 6);
        combiner.writeMacro("MP2", macro);
    }
}
