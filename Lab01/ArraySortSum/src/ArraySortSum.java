// Exercise 6.5: ArraySortSum.java
// Sorts a numeric array entered by the user (ascending order)
// and calculates the sum and the average value of its elements.
import java.util.Arrays;
import java.util.Scanner;

public class ArraySortSum {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int n = readPositiveInt(keyboard, "Enter the number of elements: ");
        double[] numbers = new double[n];
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = readDouble(keyboard, "Element " + (i + 1) + ": ");
        }

        System.out.println("Original array: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));

        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }
        double average = sum / n;

        System.out.println("Sum:     " + sum);
        System.out.println("Average: " + average);
        keyboard.close();
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
