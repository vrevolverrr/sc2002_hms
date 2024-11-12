package lib.uilib.framework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TextInputField {
    final private String label;
    private String value = null;

    public TextInputField(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the integer value of the input field. This method assumes that the input is a valid number.
     * @return the integer value of the input field.
     */
    public int getInt() {
        return Integer.parseInt(this.value);
    }

    /**
     * Get the option value of the input field. This method assumes that the input is a valid number.
     * @return the option value of the input field.
     */
    public int getOption() {
        return Integer.parseInt(this.value) - 1;
    }

    /**
     * Get the age value of the input field. This method assumes that the input is a valid number.
     * @return the age value of the input field.
     */
    public int getAge() {
        return Integer.parseInt(this.value);
    }

    /**
     * Get the boolean value of the input field. This method assumes that the input is a y/n.
     * @return the boolean value of the input field.
     */
    public boolean getYesNo() {
        String value = this.value.charAt(0) + "";
        return value.equalsIgnoreCase("y");
    }

    /**
     * Get the date value of the input field. This method assumes that the input is a valid date string.
     * @param pattern the pattern of the date string
     * @return the date value of the input field.
     */
    public LocalDate getDate(String pattern) {
        return LocalDate.parse(this.value,  DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
