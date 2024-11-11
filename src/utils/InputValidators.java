package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for validating user input.
 */
public class InputValidators {
    /**
     * Validates a date string to a given pattern.
     * @param dateString the date string to validate.
     * @param pattern the pattern to validate against.
     * @return whether the date is valid.
     */
    public static boolean validateDate(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            final LocalDate date = LocalDate.parse(dateString, formatter);
            if (date.isBefore(LocalDate.now())) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates a date string in the format dd/MM/yy.
     * @param dateString the date string to validate.
     * @return whether the date is valid.
     */
    public static boolean validateDate(String dateString) {
        return validateDate(dateString, "dd/MM/yy");
    }

    /**
     * Validates a range input, from 1 to rangeLength inclusive.
     * @param input the input to validate.
     * @param rangeLength the maximum value of the range.
     * @return whether the input is a valid range.
     */
    public static boolean validateRange(String input, int rangeLength) {
        try {
            int value = Integer.parseInt(input);
            return value >= 1 && value <= rangeLength;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates a yes/no input.
     * @param input the input to validate.
     * @return whether the input is a valid yes/no input.
     */
    public static boolean validateYesNo(String input) {
        if (input.isBlank()) {
            return false;
        }

        input = input.charAt(0) + "";
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n");
    }
}
