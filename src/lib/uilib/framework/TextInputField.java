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

    @Override
    public String toString() {
        return this.value.toString();
    }
}
