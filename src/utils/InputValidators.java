package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Utility class for validating user input.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public final class InputValidators {
    /**
     * Validates a date string to a given pattern.
     * @param dateString the date string to validate.
     * @param pattern the pattern to validate against.
     * @return whether the date is valid.
     */
    public static boolean validateFutureDate(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
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

    public static boolean validateTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(timeString, formatter);
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
    public static boolean validateFutureDate(String dateString) {
        return validateDate(dateString, "dd/MM/yy");
    }

    public static boolean validateDate(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(dateString, formatter);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

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

    public static boolean validateRange(String input, int start, int end) {
        try {
            int value = Integer.parseInt(input);
            return value >= start && value <= end;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates a range input, from 0 to rangeLength inclusive.
     * @param input the input to validate.
     * @param rangeLength the maximum value of the range.
     * @return whether the input is a valid range.
     */
    public static boolean validateRangeWithZero(String input, int rangeLength) {
        return validateRange(input, 0, rangeLength);
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

    /**
     * Validates an age input.
     * @param input the input to validate.
     * @return whether the input is a valid age.
     */
    public static boolean validateAge(String input) {
        try {
            int age = Integer.parseInt(input);
            return age >= 0 && age <= 150;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates a quantity input.
     * @param input the input to validate.
     * @return whether the input is a valid quantity.
     */
    public static boolean validateQuantity(String input) {
        try {
            int quantity = Integer.parseInt(input);
            return quantity > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates a price input.
     * @param input the input to validate.
     * @return whether the input is a valid price.
     */
    public static boolean validateEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    /**
     * Validates a phone number input.
     * @param phoneNumber the input to validate.
     * @return whether the input is a valid phone number.
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{8}$");
    }

    /**
     * Validates a password input.
     * @param password the input to validate.
     * @return whether the input is a valid password.
     */
    public static boolean validatePrevNext(String input) {
        return input.equals("1") || input.equals("2");
    }
}
