package csv;

import org.simpleflatmapper.csv.CsvParser;
import org.simpleflatmapper.util.CloseableIterator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Illia Krysenko on 02.06.2017.
 */
public class CsvSequencesGenerator {
    String[] currentRow;

    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\admin\\Downloads\\extract_account2.csv");
        File outputFile = new File("C:\\Users\\admin\\Downloads\\account10000_output.csv");
        List<List<String>> outputData = new ArrayList<List<String>>();
/*
        try (Stream<String[]> stream = CsvParser.stream(inputFile)) {
            stream.forEach(row -> System.out.println(Arrays.toString(row)));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        try (CloseableIterator<String[]> it = CsvParser.iterator(inputFile)) {
            int trNumHeaderIndex = -1;
            int nextTrNumber = -1;
            String[] nextRow = new String[0];
            // HEADER
            if (it.hasNext()) {
                nextRow = it.next();
                outputData.add(Arrays.asList(nextRow));
                trNumHeaderIndex = findTrackingNumHeaderIndex(nextRow);
            }
            if (trNumHeaderIndex != -1) {
                // GET FIRST DATA ROW
                if (it.hasNext()) {
                    nextRow = it.next();
                    outputData.add(new ArrayList<String>(Arrays.asList(nextRow)));
                    nextTrNumber = Integer.parseInt(nextRow[trNumHeaderIndex]);
                }
                // GENERATE OTHER DATA ROWS
                for (int i=0;i<10001;i++) {
                    nextRow[trNumHeaderIndex] = Integer.toString(++nextTrNumber);
                    outputData.add(new ArrayList<String>(Arrays.asList(nextRow)));
                }
            }
            saveData(outputData, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        try {
            CsvParser.forEach(inputFile, row -> saveData(row));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    private static void saveData(List<List<String>> outputData, File outputFile) {
        try {
            FileWriter writer = new FileWriter(outputFile);
            for (List<String> row : outputData) {
                for (int i=0; i<row.size();i++) {
                    writer.append("\"");
                    writer.append(row.get(i));
                    writer.append("\"");
                    if (i != row.size()-1) {
                        writer.append(",");
                    }
                }
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findTrackingNumHeaderIndex(String[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].toLowerCase().contains("SELECTICA_ID".toLowerCase()))
                return i;
        }
        return -1;
    }

}
