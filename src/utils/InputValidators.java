package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputValidators {
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

    public static boolean validateDate(String dateString) {
        return validateDate(dateString, "dd/MM/yy");
    }
}
