package lib.uilib.widgets.base;

import java.util.*;
import java.util.stream.*;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRecord;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.layout.Align;

public class Table extends Widget {
    private final TableRecord[] records;
    private int[] columnWidths;

    public Table(TableRecord... records) {
        this.records = records;
    }

    private void calculateWidths(BuildContext context) {
        columnWidths = new int[records[0].getValues().length];
        
        for (TableRecord record : records) {
            String[] values = record.getValues();
            
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
        if (this.records.length == 0) {
            return "";
        }

        calculateWidths(context);
        List<String> renderLines = new ArrayList<String>();

        for (int i = 0; i < this.records.length; i++) {
            StringBuilder topBorder = new StringBuilder(Border.THIN.topLeft());
            StringBuilder valueLine = new StringBuilder(Border.THIN.vertical());
            StringBuilder bottomBorder = new StringBuilder((i < this.records.length - 1) ? "\u251C" : Border.THIN.bottomLeft());

            String[] columnValues = this.records[i].getValues();

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
                if (i == this.records.length - 1) {
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
