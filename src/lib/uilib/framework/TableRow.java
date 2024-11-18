package lib.uilib.framework;

/**
 * Represents a row in a table.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class TableRow {
    /**
     * The values of the row.
     */
    private String[] values;

    /**
     * The constructor of {@link TableRow}.
     * 
     * @param values the values of the row.
     */
    public TableRow(String... values) {
        this.values = values;
    }    

    /**
     * Gets the values of the row.
     * 
     * @return the values of the row.
     */
    public String[] getValues() {
        return this.values;
    }
}
