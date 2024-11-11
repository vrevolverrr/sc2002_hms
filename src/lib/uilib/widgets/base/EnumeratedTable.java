package lib.uilib.widgets.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;

public class EnumeratedTable extends Table {
    private EnumeratedTable(TableRow... rows) {
        super(rows);
    }

    public static EnumeratedTable headerless(TableRow... rows) {
        return new EnumeratedTable(enumerateRows(rows));
    }

    public static EnumeratedTable withHeader(TableRow header, TableRow... rows) {
        TableRow[] enumeratedRows = enumerateRows(rows);
        TableRow enumeratedHeader = new TableRow(Stream.concat(Stream.of(""), Arrays.stream(header.getValues())).toArray(String[]::new));
        
        TableRow[] newRows = Stream.concat(Stream.of(enumeratedHeader), Arrays.stream(enumeratedRows)).toArray(TableRow[]::new);
        
        return new EnumeratedTable(newRows);
    }
    
    private static TableRow[] enumerateRows(TableRow[] rows) {
        TableRow[] enumeratedRows = new TableRow[rows.length];
        
        for (int i = 0; i < rows.length; i++) {
            List<String> values = new ArrayList<String>(Arrays.asList(rows[i].getValues()));
            values.addFirst(String.valueOf(i + 1));
            enumeratedRows[i] = new TableRow(values.toArray(new String[rows[i].getValues().length]));
        }

        return enumeratedRows;
    }

    @Override
    protected void calculateWidths(BuildContext context) {
        columnWidths = new int[rows[0].getValues().length];
        
        for (TableRow row : rows) {
            String[] values = row.getValues();
            
            // For each column in row, find the maximum length of the column
            for (int i = 0; i < values.length; i++) {
                if (values[i].length() > columnWidths[i]) {
                    columnWidths[i] = values[i].length();
                }
            }
        }

        int indexColumnPadding = 2;
        int remainingSpace = Math.max(0, context.getWidth() - 
            IntStream.of(columnWidths).sum() - (columnWidths.length + 1) - indexColumnPadding);
        
        int extraSpacePerColumn = (int) Math.ceil(1.0 * remainingSpace / (columnWidths.length - 1));

        columnWidths[0] += indexColumnPadding;

        for (int i = 1; i < columnWidths.length; i++) {
            columnWidths[i] += extraSpacePerColumn;
        }
    }
}
