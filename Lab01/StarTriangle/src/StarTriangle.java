// Exercise 6.3: StarTriangle.java
// Displays a triangle of stars (*) with a height of n rows; n is entered by the user.
// Row i (i = 1..n) has (n - i) leading spaces followed by (2i - 1) stars.
import java.util.Scanner;

public class StarTriangle {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = readPositiveInt(keyboard, "Enter the height of the triangle n: ");

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
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
}
