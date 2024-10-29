package lib.uilib.framework;

public class TableRecord {
    private String[] values;

    public TableRecord(String... values) {
        this.values = values;
    }    

    public String[] getValues() {
        return this.values;
    }
}
