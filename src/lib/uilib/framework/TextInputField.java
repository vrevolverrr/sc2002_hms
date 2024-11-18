package lib.uilib.framework;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a text input field in a form.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class TextInputField {
    /**
     * The label of the text input field.
     */
    final private String label;

    /**
     * The value of the text input field.
     */
    private String value = null;

    /**
     * The constructor of {@link TextInputField}.
     * 
     * @param label the label of the text input field.
     */
    public TextInputField(String label) {
        this.label = label;
    }

    /**
     * Gets the label of the text input field.
     * 
     * @return the label of the text input field.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the value of the text input field.
     * 
     * @return the value of the text input field.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the text input field.
     * 
     * @param value the value of the text input field.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the integer value of the input field. This method assumes that the input is a valid number.
     * 
     * @return the integer value of the input field.
     */
    public int getInt() {
        return Integer.parseInt(this.value);
    }

    /**
     * Get the option value of the input field. This method assumes that the input is a valid number.
     * 
     * @return the option value of the input field.
     */
    public int getOption() {
        return Integer.parseInt(this.value) - 1;
    }

    /**
     * Get the age value of the input field. This method assumes that the input is a valid number.
     * 
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
     * 
     * @param pattern the pattern of the date string
     * @return the date value of the input field.
     */
    public LocalDate getDate(String pattern) {
        return LocalDate.parse(this.value,  DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Get the date value of the input field. This method assumes that the input is a valid date string.
     * 
     * @return the date value of the input field.
     */
    public LocalDate getDate() {
        return getDate("dd/MM/yy");
    }

    /**
     * Get the time value of the input field. This method assumes that the input is a valid time string.
     * 
     * @param pattern the pattern of the time string.
     * @return the time value of the input field.
     */
    public LocalTime getTime(String pattern) {
        return LocalTime.parse(this.value,  DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Get the time value of the input field.
     * 
     * @return the time value of the input field.
     */
    public LocalTime getTime() {
        return LocalTime.parse(this.value);
    }

    /**
     * Get the string value of the input field.
     * 
     * @return the string value of the input field.
     */
    @Override
    public String toString() {
        return this.value.toString();
    }
}
