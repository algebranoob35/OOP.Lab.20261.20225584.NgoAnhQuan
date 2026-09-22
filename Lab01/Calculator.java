// Exercise 2.2.5: Calculator.java
// Calculates the sum, difference, product and quotient of two double numbers
// entered by the user.
import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {
        double num1 = readDouble("Please input the first number:", "Input the first number");
        double num2 = readDouble("Please input the second number:", "Input the second number");

        String result = "First number: " + format(num1) + "\n"
                + "Second number: " + format(num2) + "\n\n"
                + "Sum: " + format(num1 + num2) + "\n"
                + "Difference: " + format(num1 - num2) + "\n"
                + "Product: " + format(num1 * num2) + "\n";

        // Dividing a double by 0 does not throw an exception in Java
        // (the result is Infinity or NaN), so the divisor must be checked first.
        if (num2 == 0) {
            result += "Quotient: cannot be calculated because the divisor is 0";
        } else {
            result += "Quotient: " + format(num1 / num2);
        }

        JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // Shows an input dialog until the user enters a valid number.
    // Pressing Cancel (or closing the dialog) ends the program.
    private static double readDouble(String message, String title) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message, title,
                    JOptionPane.INFORMATION_MESSAGE);
            if (input == null) {
                System.exit(0);
            } else {
                try {
                    double value = Double.parseDouble(input.trim());
                    if (Double.isFinite(value)) {
                        return value;
                    }
                } catch (NumberFormatException e) {
                    // Not a number: fall through to the error message below
                }
                JOptionPane.showMessageDialog(null,
                        "\"" + input + "\" is not a valid number.\n"
                        + "Use '.' as the decimal separator, e.g. 3.14",
                        "Invalid input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Prints 0.0 instead of -0.0 (in Java, 0 * -5 gives -0.0)
    private static String format(double value) {
        if (value == 0) {
            value = 0; // -0.0 == 0 is true, so -0.0 is replaced by +0.0
        }
        return String.valueOf(value);
    }
}
