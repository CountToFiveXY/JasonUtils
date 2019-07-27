package FileHelper;

import DataType.BeejakInputType;
import IO.FilePrinter;
import IO.InputFileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeejakInputGenerator {
    private final String GENERATE = "GenerateInvoice";
    private final String REGENERATE = "RegenerateInvoice";
    private final String CANCEL = "CancelInvoices";
    private final String TENANT_ID = "Digital_Forward";
    private final String ASSEMBLER_ID = "Digital_Forward_Unify";
    private final String CONTEXT = "tide";

    private final String GROUP_ID = "1eb5da59-73e4-2af8-e359-4781148d935d";
    private final String GROUP_ID_DOMAIN = "DigitalTransactionId";
    private final String TRANSACTION_ID = "888888888";
    private final String TRANSACTION_ID_DOMAIN = "CROW";

    Map<String, String> map = new HashMap<>();

    List<String> file;

    public BeejakInputGenerator(boolean isForward) {
        buildInputMap(isForward);
        file = InputFileReader.readFileToList("testFile/input/BeejakInputTemplate");
    }

    public void write(String outputFileName) {
        List<String> modifiedList = file.stream()
                .map(this::replace)
                .collect(Collectors.toList());
        FilePrinter.writeToFile(modifiedList, outputFileName);
    }

    private String replace(String line) {
        for (String key: map.keySet()) {
            if (line.contains(key)) {
                return FunctionHelper.replaceString(line, key, map.get(key));
            }
        }
        return line;
    }

    private void buildInputMap(boolean isForward) {
        map.put(BeejakInputType.BeejakOperation.toString(), GENERATE);
        map.put(BeejakInputType.mockTenant.toString(), TENANT_ID);
        map.put(BeejakInputType.mockConfigId.toString(), ASSEMBLER_ID);
        map.put(BeejakInputType.Rufus.toString(), CONTEXT);
        map.put(BeejakInputType.mockGroupId.toString(), GROUP_ID);
        map.put(BeejakInputType.mockGroupDomain.toString(), GROUP_ID_DOMAIN);

        if (isForward) {
            map.put(BeejakInputType.mockTransactionId.toString(), GROUP_ID);
            map.put(BeejakInputType.mockTransactionDomain.toString(), GROUP_ID_DOMAIN);
            map.put(BeejakInputType.mockDocument.toString(), "Invoice");
        } else {
            map.put(BeejakInputType.mockTransactionId.toString(), TRANSACTION_ID);
            map.put(BeejakInputType.mockTransactionDomain.toString(), TRANSACTION_ID_DOMAIN);
            map.put(BeejakInputType.mockDocument.toString(), "CreditNote");
        }
    }
}
