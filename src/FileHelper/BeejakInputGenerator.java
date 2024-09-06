package FileHelper;

import DataType.BeejakInputType;
import IO.FilePrinter;
import IO.InputFileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeejakInputGenerator {

    private enum ASSEMBLER_CONFIG_ID {
        EU_Retail_Forward,
        PRIMENOW_Forward,
        JP_Retail_Forward,
        Digital_Forward,
        Digital_Forward_Unify
    }

    private enum TENANT {
        EU_Retail_Forward,
        PRIMENOW_Forward,
        Digital_Forward
    }

    private enum DOMAIN {
        ACBShipmentId,
        DigitalTransactionId,
        CROW
    }

    private enum BEEJAK_OPERATION {
        GenerateInvoice,
        RegenerateInvoice,
        CancelInvoices
    }

    private final String BEEJAK_API = BEEJAK_OPERATION.GenerateInvoice.name() ;;
    private final String TENANT_ID = TENANT.Digital_Forward.name();
    private final String CONFIG_ID = ASSEMBLER_CONFIG_ID.Digital_Forward_Unify.name();

    private final String GROUP_ID = "6ab62768-bf7e-522a-f84a-7a97f1ac1c16";
    private final String GROUP_ID_DOMAIN = DOMAIN.DigitalTransactionId.name();
    private final String TRANSACTION_ID = "888888888";
    private final String TRANSACTION_ID_DOMAIN = DOMAIN.CROW.name();
    private final String CONTEXT = "bayuqiao";

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
                return LineModifier.replaceString(line, key, map.get(key));
            }
        }
        return line;
    }

    private void buildInputMap(boolean isForward) {
        map.put(BeejakInputType.BeejakOperation.toString(), BEEJAK_API);
        map.put(BeejakInputType.mockTenant.toString(), TENANT_ID);
        map.put(BeejakInputType.mockConfigId.toString(), CONFIG_ID);
        map.put(BeejakInputType.Rufus.toString(), CONTEXT);
        map.put(BeejakInputType.mockGroupId.toString(), GROUP_ID);
        map.put(BeejakInputType.mockGroupDomain.toString(), GROUP_ID_DOMAIN);

        if (isForward) {
            map.put(BeejakInputType.mockTransactionId.toString(), GROUP_ID);
            map.put(BeejakInputType.indexTransactionDomain.toString(), getIndexTransactionDomain(GROUP_ID_DOMAIN));
            map.put(BeejakInputType.mockTransactionDomain.toString(), GROUP_ID_DOMAIN);
            map.put(BeejakInputType.mockDocument.toString(), "Invoice");
        } else {
            map.put(BeejakInputType.mockTransactionId.toString(), TRANSACTION_ID);
            map.put(BeejakInputType.indexTransactionDomain.toString(), TRANSACTION_ID_DOMAIN);
            map.put(BeejakInputType.mockTransactionDomain.toString(), TRANSACTION_ID_DOMAIN);
            map.put(BeejakInputType.mockDocument.toString(), "CreditNote");
        }
    }

    private String getIndexTransactionDomain(String groupIdDomain) {
        if (groupIdDomain.startsWith("ACB")) {
            return LineModifier.getSubStringAfterIndex(groupIdDomain, "ACB");
        }
        return groupIdDomain;
    }
}
