package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixUtils {

    public static List readMatrixFromFile(String filePath) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        Scanner input = new Scanner(new File(filePath));
        while (input.hasNextLine()) {
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList col = new ArrayList();
            while (colReader.hasNextInt()) {
                col.add(colReader.nextInt());
            }
            a.add(col);
        }
        return a;
    }

    public static boolean checkForSquare(List<List<Integer>> matrix) {
        int y = matrix.size();
        return matrix.stream().noneMatch(line -> line.size() != y);
    }
}
