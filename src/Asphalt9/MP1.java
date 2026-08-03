package Asphalt9;

import java.util.ArrayList;
import java.util.List;

public class MP1 {

    private static final String MP1_D1 = "mp1/D1";
    private static final String MP1_D2 = "mp1/D2";
    private static final String OPEN = "MP1/opengame";

    private static final MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) {
        combine();
    }

    public static void combine() {
        List<String> macro = new ArrayList<>();

        combiner.addMacro(macro, MP1_D1, 3);
        combiner.addMacro(macro, OPEN, 1);
        combiner.addMacro(macro, MP1_D1, 3);
        combiner.addMacro(macro, OPEN, 1);
        combiner.addMacro(macro, MP1_D2, 3);
        combiner.addMacro(macro, OPEN, 1);
        combiner.addMacro(macro, MP1_D2, 3);

        combiner.writeMacro("MP1", macro);
    }
}
