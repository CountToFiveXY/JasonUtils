package Asphalt9;

import java.util.ArrayList;
import java.util.List;

public abstract class MacroRun {

    protected final MacroCombiner combiner;

    protected MacroRun() {
        combiner = new MacroCombiner();
    }

    public final void combine() {
        List<String> macro = new ArrayList<>();

        combiner.reset();
        buildMacro(macro);
        combiner.writeMacro(outputName(), macro);
    }

    protected abstract void buildMacro(List<String> macro);

    protected abstract String outputName();
}
