package lib.uilib.framework;

public class TableRow {
    private String[] values;

    public TableRow(String... values) {
        this.values = values;
    }    

    public String[] getValues() {
        return this.values;
    }
}