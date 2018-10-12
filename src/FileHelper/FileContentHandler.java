package FileHelper;

import java.io.*;
import java.util.HashSet;

public class FileContentHandler {

    File file;
    FileInputStream fis;
    BufferedReader bf;
    HashSet<String> set;

    public FileContentHandler(String inputFileName) {
        set = new HashSet<>();
        try {
            file = new File(inputFileName);
            fis = new FileInputStream(file);
            bf = new BufferedReader(new InputStreamReader(fis));
        } catch (FileNotFoundException e) {
            System.out.println("Can't find File" + inputFileName);
        }
    }

    public int getLineNumber() throws IOException{
        int lineNum = 0;
        while ((bf.readLine()) != null) { lineNum ++; }
        return lineNum;
    }

    public void handleFile(String outputFileName) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName));
            String line;

            while ((line = bf.readLine()) != null) {
                out.write(modifyEachContent(line));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private String modifyEachContent(String line) {
        return "https://tax-portal-eu.amazon.com/guitarTarViewer.cgi?action=getTars&displayDomain=prod&realm=EUECP&domain=ACBShipmentId&id="+line.split(":")[1];
    }
}
