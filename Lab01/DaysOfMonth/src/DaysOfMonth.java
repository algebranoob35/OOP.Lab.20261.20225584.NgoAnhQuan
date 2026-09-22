// Exercise 6.4: DaysOfMonth.java
// Displays the number of days of a month in a year, both entered by the user.
// Accepted month inputs: full name (January), abbreviation (Jan.),
// 3 letters (Jan) or number (1). The year must be a non-negative integer
// written with all its digits (1999, not 99). Invalid input is asked again.
import java.util.Scanner;

public class DaysOfMonth {
    // One row per month: full name, abbreviation, 3 letters, number
    // (same as the table in the lab)
    private static final String[][] MONTHS = {
        {"January",   "Jan.",  "Jan", "1"},
        {"February",  "Feb.",  "Feb", "2"},
        {"March",     "Mar.",  "Mar", "3"},
        {"April",     "Apr.",  "Apr", "4"},
        {"May",       "May",   "May", "5"},
        {"June",      "June",  "Jun", "6"},
        {"July",      "July",  "Jul", "7"},
        {"August",    "Aug.",  "Aug", "8"},
        {"September", "Sept.", "Sep", "9"},
        {"October",   "Oct.",  "Oct", "10"},
        {"November",  "Nov.",  "Nov", "11"},
        {"December",  "Dec.",  "Dec", "12"}
    };

    // Number of days of each month in a common year (index 0 = January)
    private static final int[] DAYS_IN_COMMON_YEAR = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int month = -1;
        while (month == -1) {
            System.out.print("Enter a month (e.g. January, Jan., Jan or 1): ");
            month = parseMonth(keyboard.nextLine().trim());
            if (month == -1) {
                System.out.println("Invalid month. Please enter again.");
            }
        }

        int year = -1;
        while (year == -1) {
            System.out.print("Enter a year (e.g. 1999): ");
            year = parseYear(keyboard.nextLine().trim());
            if (year == -1) {
                System.out.println("Invalid year. Please enter again.");
            }
        }

        boolean leap = isLeapYear(year);
        int days = DAYS_IN_COMMON_YEAR[month];
        if (month == 1 && leap) {       // February of a leap year
            days = 29;
        }

        System.out.println(MONTHS[month][0] + " " + year + " has " + days + " days ("
                + year + " is a " + (leap ? "leap" : "common") + " year).");
        keyboard.close();
    }

    // Returns the month index (0 = January ... 11 = December),
    // or -1 if the input is not one of the accepted forms (case-insensitive)
    private static int parseMonth(String input) {
        for (int i = 0; i < MONTHS.length; i++) {
            for (String form : MONTHS[i]) {
                if (form.equalsIgnoreCase(input)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Returns the year, or -1 if the input is not made of digits only
    // (this rejects negative numbers, decimals, words and empty input)
    private static int parseYear(String input) {
        if (!input.matches("\\d+")) {
            return -1;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // too large for an int
        }
    }

    // Divisible by 4, except years divisible by 100 but not by 400
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
