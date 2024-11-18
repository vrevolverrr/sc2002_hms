package lib.uilib.widgets.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;


/**
 * A table that enumerates the rows.
 * The table is displayed with an index column on the left side.
 * The index column is used to enumerate the rows.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class EnumeratedTable extends Table {

    /**
     * Constructs a new enumerated table.
     * 
     * @param rows The rows of the table.
     */
    private EnumeratedTable(TableRow... rows) {
        super(rows);
    }

    /**
     * Creates a new enumerated table without a header.
     * 
     * @param rows The rows of the table.
     * @return The enumerated table.
     */
    public static EnumeratedTable headerless(TableRow... rows) {
        return new EnumeratedTable(enumerateRows(rows));
    }

    /**
     * Creates a new enumerated table with a header.
     * 
     * @param header The header of the table.
     * @param rows The rows of the table.
     * @return The enumerated table.
     */
    public static EnumeratedTable withHeader(TableRow header, TableRow... rows) {
        TableRow[] enumeratedRows = enumerateRows(rows);
        TableRow enumeratedHeader = new TableRow(Stream.concat(Stream.of(""), Arrays.stream(header.getValues())).toArray(String[]::new));
        
        TableRow[] newRows = Stream.concat(Stream.of(enumeratedHeader), Arrays.stream(enumeratedRows)).toArray(TableRow[]::new);
        
        return new EnumeratedTable(newRows);
    }
    
    /**
     * Enumerates the rows of the table.
     * 
     * @param rows The rows of the table.
     * @return The enumerated rows.
     */
    private static TableRow[] enumerateRows(TableRow[] rows) {
        TableRow[] enumeratedRows = new TableRow[rows.length];
        
        for (int i = 0; i < rows.length; i++) {
            List<String> values = new ArrayList<String>(Arrays.asList(rows[i].getValues()));
            values.addFirst(String.valueOf(i + 1));
            enumeratedRows[i] = new TableRow(values.toArray(new String[rows[i].getValues().length]));
        }

        return enumeratedRows;
    }

    /**
     * Calculate the widths of the columns in the table.
     * 
     * @param context The build context.
     */
    @Override
    protected void calculateWidths(BuildContext context) {
        columnWidths = new int[rows[0].getValues().length];
        
        for (TableRow row : rows) {
            String[] values = row.getValues();
            
            // For each column in row, find the maximum length of the column
            for (int i = 0; i < values.length; i++) {
                if (values[i] == null) {
                    continue;
                }

                if (values[i].length() > columnWidths[i]) {
                    columnWidths[i] = values[i].length();
                }
            }
        }

        int numCellDividers = columnWidths.length + 1;
        int minCellPadding = 2;

        // If needed, iteratively reduce the length of the longest column until the table fits the context
        while (IntStream.of(columnWidths).sum() + numCellDividers + (minCellPadding * columnWidths.length) > context.getWidth()) {
            int maxColumnIndex = IntStream.range(0, columnWidths.length)
                .reduce((i, j) -> columnWidths[i] > columnWidths[j] ? i : j)
                .getAsInt();
            columnWidths[maxColumnIndex]--;
        }

        int indexColumnPadding = 2;
        int remainingSpace = Math.max(0, context.getWidth() - 
            IntStream.of(columnWidths).sum() - numCellDividers - indexColumnPadding);
        
        int extraSpacePerColumn = (int) Math.ceil(1.0 * remainingSpace / (columnWidths.length - 1));

        columnWidths[0] += indexColumnPadding;

        for (int i = 1; i < columnWidths.length; i++) {
            columnWidths[i] += extraSpacePerColumn;
        }
    }
}
