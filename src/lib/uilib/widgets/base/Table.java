package lib.uilib.widgets.base;

import java.util.*;
import java.util.stream.*;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.framework.exceptions.InvalidTableDimensionsException;
import lib.uilib.widgets.layout.Align;

/**
 * A widget that draws a table from a list of {@link TableRow}.
 * <pre>
 * 
 * new Table(
 *     new TableRow("Patient ID", "Name", "Date of Birth", "Gender", "Blood Type"),
 *     new TableRow("P1001", "Bryan Soong", "05/02/2003", "Male", "O+")
 * ).paint(context);
 * </pre>
 */
public class Table extends Widget {
    private final TableRow[] rows;
    private int[] columnWidths;

    public Table(TableRow... rows) {
        this.rows = rows;
        assertRowDimensions(rows);
    }

    private static void assertRowDimensions(TableRow[] rows) {
        if (rows.length == 0) {
            return;
        }
        
        int numColumns = rows[0].getValues().length;
        for (TableRow row : rows) {
            if (row.getValues().length != numColumns ) {
                throw new InvalidTableDimensionsException(
                    "One or more of the provided rows have different number of columns.", new Exception());
            }
        }
    }
 
    private void calculateWidths(BuildContext context) {
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
        int extraSpacePerColumn = (int) Math.ceil(1.0 * remainingSpace / columnWidths.length)  ;

        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] += extraSpacePerColumn;
        }

        
    }

    @Override
    public String build(BuildContext context) {
        if (this.rows.length == 0) {
            return "";
        }

        calculateWidths(context);
        List<String> renderLines = new ArrayList<String>();

        for (int i = 0; i < this.rows.length; i++) {
            StringBuilder topBorder = new StringBuilder(Border.THIN.topLeft());
            StringBuilder valueLine = new StringBuilder(Border.THIN.vertical());
            StringBuilder bottomBorder = new StringBuilder((i < this.rows.length - 1) ? "\u251C" : Border.THIN.bottomLeft());

            String[] columnValues = this.rows[i].getValues();

            // For each column
            for (int j = 0; j < columnValues.length; j++) {
                // Set the size (context) to match the size of the column
                BuildContext columnContext = new BuildContext(columnWidths[j], 1);
                
                // Draw the top border of the column
                if (i == 0) {
                    topBorder.append(Border.THIN.horizontal().repeat(columnWidths[j]) + ((j == columnValues.length - 1) ? Border.THIN.topRight() : "\u252C"));
                }
                
                valueLine.append(new Align(Alignment.CENTER, new Text(columnValues[j], (j == 0) ? TextStyle.BOLD : TextStyle.NORMAL))
                    .build(columnContext) + Border.THIN.vertical());

                // Check if its the last row in the table
                if (i == this.rows.length - 1) {
                    bottomBorder.append(Border.THIN.horizontal().repeat(columnWidths[j]) + ((j == columnValues.length - 1) ? Border.THIN.bottomRight() : "\u2534"));
                } else {
                    bottomBorder.append(Border.THIN.horizontal().repeat(columnWidths[j]) + ((j == columnValues.length - 1) ? "\u2524" : "\u253C"));
                }

            }
            
            if (i == 0) {
                renderLines.add(topBorder.toString());
            }

            renderLines.add(valueLine.toString());
            renderLines.add(bottomBorder.toString());
        }

        return String.join("\n", renderLines);
    }
    
}
