import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderUtil {

    public int[][] getTwoDimensionalArrayOfData(String fileName) throws FileNotFoundException {

        List<String> linesData = readFromFile(fileName);
        return arrayListToTwoDimensionalArray(linesData);
    }

    private List<String> readFromFile(String fileName) throws FileNotFoundException {

        List<String> linesData = new ArrayList();
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextLine()) {
            String lineStr = sc.nextLine();
            linesData.add(lineStr);
        }
        sc.close();
        return linesData;
    }

    private int[][] arrayListToTwoDimensionalArray(List<String> linesData) {

        int inputArray[][] = {};
        inputArray = new int[linesData.size()][];
        for (int i = 0; i < linesData.size(); i++) {
            String[] values = linesData.get(i).split(" ");
            int rowArray[] = new int[values.length];
            for (int j = 0; j < values.length; j++) {
                rowArray[j] = Integer.parseInt(values[j]);
            }
            inputArray[i] = rowArray;
        }
        return inputArray;
    }

}
