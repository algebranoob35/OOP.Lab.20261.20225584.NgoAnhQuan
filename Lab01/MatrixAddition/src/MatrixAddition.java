// Exercise 6.6: MatrixAddition.java
// Adds two matrices of the same size entered by the user.
import java.util.Scanner;

public class MatrixAddition {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        // The size is entered once and used for both matrices,
        // so A and B always have the same size and can be added.
        int rows = readPositiveInt(keyboard, "Enter the number of rows: ");
        int cols = readPositiveInt(keyboard, "Enter the number of columns: ");

        double[][] a = readMatrix(keyboard, "A", rows, cols);
        double[][] b = readMatrix(keyboard, "B", rows, cols);

        double[][] sum = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i][j] = a[i][j] + b[i][j];
            }
        }

        System.out.println("Matrix A:");
        printMatrix(a);
        System.out.println("Matrix B:");
        printMatrix(b);
        System.out.println("A + B:");
        printMatrix(sum);
        keyboard.close();
    }

    private static double[][] readMatrix(Scanner keyboard, String name, int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        System.out.println("Enter the elements of matrix " + name + " (" + rows + " x " + cols + "):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = readDouble(keyboard, name + "[" + (i + 1) + "][" + (j + 1) + "] = ");
            }
        }
        return matrix;
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%10s", format(value));
            }
            System.out.println();
        }
    }

    // Whole numbers are printed without ".0" (3.0 -> "3"); -0.0 is printed as "0"
    private static String format(double value) {
        if (value == Math.rint(value) && Math.abs(value) < 1e15) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    // Keeps asking until the user enters an integer greater than 0
    private static int readPositiveInt(Scanner keyboard, String prompt) {
        while (true) {
            System.out.print(prompt);
            String token = keyboard.next();
            try {
                int value = Integer.parseInt(token);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Not an integer: fall through to the error message below
            }
            System.out.println("\"" + token + "\" is not a positive integer. Please enter again.");
        }
    }

    // Keeps asking until the user enters a valid number ('.' is the decimal separator)
    private static double readDouble(Scanner keyboard, String prompt) {
        while (true) {
            System.out.print(prompt);
            String token = keyboard.next();
            try {
                double value = Double.parseDouble(token);
                if (Double.isFinite(value)) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Not a number: fall through to the error message below
            }
            System.out.println("\"" + token + "\" is not a valid number. Please enter again.");
        }
    }
}
