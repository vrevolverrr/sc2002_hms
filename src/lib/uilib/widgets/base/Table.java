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
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class Table extends Widget {

    /**
     * The rows of the table.
     */
    protected final TableRow[] rows;

    /**
     * The widths of the columns.
     */
    protected int[] columnWidths;

    /**
     * Constructs a new table widget.
     * 
     * @param rows The rows of the table.
     */
    public Table(TableRow... rows) {
        this.rows = rows;
        assertRowDimensions(rows);
    }

    /**
     * Asserts that all rows have the same number of columns.
     * 
     * @param rows The rows to check.
     */
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
 
    /**
     * Calculates the widths of the columns.
     * 
     * @param context The build context.
     */
    protected void calculateWidths(BuildContext context) {
        columnWidths = new int[rows[0].getValues().length];
        
        for (TableRow row : rows) {
            String[] values = row.getValues();
            
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

        int remainingSpace = Math.max(0, context.getWidth() - IntStream.of(columnWidths).sum() - numCellDividers);
        int extraSpacePerColumn = (int) Math.ceil(1.0 * remainingSpace / columnWidths.length)  ;

        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] += extraSpacePerColumn;
        }
    }

    /**
     * Builds the table widget.
     * 
     * @param context The build context.
     * @return The table widget.
     */
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

                if (columnValues[j] == null) {
                    columnValues[j] = "";
                }
                
                valueLine.append(new Align(Alignment.CENTER, new Text(columnValues[j], (i == 0) ? TextStyle.BOLD : TextStyle.NORMAL))
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
