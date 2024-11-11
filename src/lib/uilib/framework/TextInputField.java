package lib.uilib.framework;

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
     * Get the integer value of the input field. This method assumes that the input is a number.
     * @return The integer value of the input field.
     */
    public int getInt() {
        return Integer.parseInt(this.value) - 1;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
