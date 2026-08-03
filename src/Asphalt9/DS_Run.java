package Asphalt9;

import java.util.ArrayList;
import java.util.List;

public class DS_Run {

    private static final String DS = "DS/1";
    private static final String DS_AD = "DS/ad";

    private static final MacroCombiner combiner = new MacroCombiner();

    public static void main(String[] args) {
        combine();
    }

    public static void combine() {
        List<String> macro = new ArrayList<>();

        combiner.addMacro(macro, DS, 3);
        combiner.addMacro(macro, DS_AD, 6);

        combiner.writeMacro("DriverS", macro);
    }
}
