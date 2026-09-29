// Exercise 2.2.6: EquationSolver.java
// Solves the following equations (real roots only):
//   1. First-degree equation with one variable:  ax + b = 0
//   2. System of first-degree equations with two variables:
//          a11*x1 + a12*x2 = b1
//          a21*x1 + a22*x2 = b2
//   3. Second-degree equation with one variable: ax^2 + bx + c = 0
import javax.swing.JOptionPane;

public class EquationSolver {
    // Relative tolerance used to decide whether a computed difference is 0.
    // Floating-point numbers have rounding errors: 0.1 * 3 - 0.3 gives 5.55E-17, not 0.
    private static final double EPSILON = 1e-9;

    public static void main(String[] args) {
        String[] options = {"ax + b = 0", "Linear system", "ax^2 + bx + c = 0", "Exit"};
        String menu = "Choose the type of equation to solve:\n"
                + "1. First-degree equation with one variable: ax + b = 0\n"
                + "2. System of first-degree equations with two variables\n"
                + "3. Second-degree equation with one variable: ax^2 + bx + c = 0";

        while (true) {
            int choice = JOptionPane.showOptionDialog(null, menu, "Equation Solver",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) {
                String form = "ax + b = 0\n";
                double a = readDouble(form + "Enter a:");
                double b = readDouble(form + "Enter b:");
                showResult("First-degree equation", solveLinearEquation(a, b));
            } else if (choice == 1) {
                String form = "a11*x1 + a12*x2 = b1\na21*x1 + a22*x2 = b2\n";
                double a11 = readDouble(form + "Enter a11:");
                double a12 = readDouble(form + "Enter a12:");
                double b1 = readDouble(form + "Enter b1:");
                double a21 = readDouble(form + "Enter a21:");
                double a22 = readDouble(form + "Enter a22:");
                double b2 = readDouble(form + "Enter b2:");
                showResult("System of first-degree equations",
                        solveLinearSystem(a11, a12, b1, a21, a22, b2));
            } else if (choice == 2) {
                String form = "ax^2 + bx + c = 0\n";
                double a = readDouble(form + "Enter a:");
                double b = readDouble(form + "Enter b:");
                double c = readDouble(form + "Enter c:");
                showResult("Second-degree equation", solveQuadraticEquation(a, b, c));
            } else {
                // "Exit" button, or the menu was closed with X / Esc
                System.exit(0);
            }
        }
    }

    // 1. ax + b = 0
    private static String solveLinearEquation(double a, double b) {
        if (a == 0) {
            if (b == 0) {
                return "The coefficient of x and the constant term are both 0:\n"
                        + "every real number x is a solution (infinitely many solutions).";
            }
            return "The coefficient of x is 0 but the constant term is not 0:\n"
                    + "the equation has no solution.";
        }
        return "The equation has one solution: x = " + format(-b / a);
    }

    // 2. Linear system, solved with determinants:
    //    D  = a11*a22 - a21*a12
    //    D1 = b1*a22  - b2*a12
    //    D2 = a11*b2  - a21*b1
    private static String solveLinearSystem(double a11, double a12, double b1,
                                            double a21, double a22, double b2) {
        if (!differenceIsZero(a11 * a22, a21 * a12)) {          // D != 0
            double d = a11 * a22 - a21 * a12;
            double d1 = b1 * a22 - b2 * a12;
            double d2 = a11 * b2 - a21 * b1;
            return "D = " + format(d) + " != 0: the system has a unique solution\n"
                    + "x1 = D1 / D = " + format(d1 / d) + "\n"
                    + "x2 = D2 / D = " + format(d2 / d);
        }
        if (!differenceIsZero(b1 * a22, b2 * a12)              // D1 != 0
                || !differenceIsZero(a11 * b2, a21 * b1)) {    // D2 != 0
            return "D = 0 and (D1 != 0 or D2 != 0): the system has no solution.";
        }
        // D = D1 = D2 = 0
        if (a11 == 0 && a12 == 0 && a21 == 0 && a22 == 0) {
            // Special case: both equations have the form 0*x1 + 0*x2 = b
            if (b1 == 0 && b2 == 0) {
                return "All coefficients and constants are 0:\n"
                        + "every pair (x1, x2) is a solution (infinitely many solutions).";
            }
            return "All coefficients are 0 but b1 or b2 is not 0: the system has no solution.";
        }
        return "D = D1 = D2 = 0: the system has infinitely many solutions.";
    }

    // 3. ax^2 + bx + c = 0
    private static String solveQuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            return "a = 0, so the equation becomes bx + c = 0.\n"
                    + solveLinearEquation(b, c);
        }
        double delta = b * b - 4 * a * c;
        if (differenceIsZero(b * b, 4 * a * c)) {              // delta = 0
            return "Delta = 0: the equation has a double root\n"
                    + "x1 = x2 = " + format(-b / (2 * a));
        }
        if (delta < 0) {
            return "Delta = " + format(delta) + " < 0: the equation has no real root.";
        }
        double sqrtDelta = Math.sqrt(delta);
        return "Delta = " + format(delta) + " > 0: the equation has two distinct real roots\n"
                + "x1 = " + format((-b + sqrtDelta) / (2 * a)) + "\n"
                + "x2 = " + format((-b - sqrtDelta) / (2 * a));
    }

    // Returns true if x - y should be treated as 0, i.e. the difference is tiny
    // compared with x and y and only comes from rounding errors.
    // When x = y = 0 the difference is exactly 0 and the method returns true.
    private static boolean differenceIsZero(double x, double y) {
        return Math.abs(x - y) <= EPSILON * Math.max(Math.abs(x), Math.abs(y));
    }

    // Shows an input dialog until the user enters a valid number.
    // Pressing Cancel (or closing the dialog) ends the program.
    private static double readDouble(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message, "Input",
                    JOptionPane.QUESTION_MESSAGE);
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

    private static void showResult(String title, String result) {
        JOptionPane.showMessageDialog(null, result, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Prints 0.0 instead of -0.0 (e.g. x = -b / a with b = 0 gives -0.0)
    private static String format(double value) {
        if (value == 0) {
            value = 0; // -0.0 == 0 is true, so -0.0 is replaced by +0.0
        }
        return String.valueOf(value);
    }
}
