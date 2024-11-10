package lib.uilib.widgets.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;

public class EnumeratedTable extends Table {
    public EnumeratedTable(TableRow... rows) {
        super(enumerateRows(rows));
    }

    private static TableRow[] enumerateRows(TableRow[] rows) {
        TableRow[] enumeratedRows = new TableRow[rows.length];
        
        for (int i = 0; i < rows.length; i++) {
            List<String> values = new ArrayList<String>(Arrays.asList(rows[i].getValues()));
            values.addFirst(String.valueOf(i + 1));
            enumeratedRows[i] = new TableRow(values.toArray(new String[rows.length]));
        }

        return enumeratedRows;
    }

    @Override
    protected void calculateWidths(BuildContext context) {
        columnWidths = new int[rows[0].getValues().length];
        
        for (TableRow row : rows) {
            String[] values = row.getValues();
            
            for (int i = 0; i < values.length; i++) {
                if (values.length > columnWidths[i]) {
                    columnWidths[i] = values.length;
                }
            }
        }

        int remainingSpace = Math.max(0, context.getWidth() - IntStream.of(columnWidths).sum()) - (columnWidths.length + 1);
        int extraSpacePerColumn = (int) Math.ceil(1.0 * remainingSpace / (columnWidths.length - 1))  ;

        for (int i = 1; i < columnWidths.length; i++) {
            columnWidths[i] += extraSpacePerColumn;
        }
    }
}
