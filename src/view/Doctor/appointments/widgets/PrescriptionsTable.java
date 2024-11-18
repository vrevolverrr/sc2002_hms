package view.Doctor.appointments.widgets;

import java.util.List;

import controller.interfaces.IInventoryManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.prescriptions.MedicineDosage;
import model.prescriptions.Prescription;
import services.ServiceLocator;

/**
 * A {@link Widget} that displays a table of prescriptions.
 * This widget is designed to provide doctors with a clear view of all prescriptions for a specific appointment.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class PrescriptionsTable extends Widget {
    /**
     * The {@link InventoryManager} instance used to retrieve inventory item names by their IDs.
     */
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);

    /**
     * A list of {@link Prescription} objects representing the prescriptions to be displayed.
     */
    private final List<Prescription> prescriptions;

    /**
     * Constructs a {@code PrescriptionsTable} with the given list of prescriptions.
     *
     * @param prescriptions a list of {@link Prescription} objects that represent the prescriptions to be displayed.
     */
    public PrescriptionsTable(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Builds the UI for displaying the prescriptions in a table format. The table contains:
     * <ul>
     *   <li>Medicine</li>
     *   <li>Dosage</li>
     *   <li>Frequency</li>
     *   <li>Quantity</li>
     *   <li>Status</li>
     * </ul>
     * If no prescriptions are available, a single row indicating "No prescriptions found" is displayed.
     * Otherwise, the table is constructed with a header and rows representing each prescription.
     *
     * @param context the {@link BuildContext} used for rendering the widget.
     * @return a {@link String} representing the built table UI.
     */
    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Medicine", "Dosage", "Frequency", "Quantity", "Status");

        if (prescriptions.isEmpty()) {
            return new Table(new TableRow("No prescriptions found"))
                .build(context);
        }

        List<TableRow> rows = prescriptions.stream()
            .map(prescription -> new TableRow(
                getDrugName(prescription.getDrugId()), 
                parseDosage(prescription.getDosage()), 
                prescription.getFrequency().toString(),
                String.valueOf(prescription.getQuantity()),
                prescription.getStatus().toString()
            ))
            .toList();

        return EnumeratedTable.withHeader(header, rows.toArray(TableRow[]::new)).build(context);
    }

    /**
     * Parses the {@link MedicineDosage} object into a formatted string.
     *
     * @param dosage the {@link MedicineDosage} to parse.
     * @return a {@link String} representing the {@link DosageUnit}.
     */
    private String parseDosage(MedicineDosage dosage) {
        return dosage.getQuantity() + " " + dosage.getUnit();
    }

    /**
     * Retrieves the name of the drug based on the given drug ID.
     *
     * @param drugId the ID of the drug whose name is to be retrieved.
     * @return the name of the drug as a {@link String}.
     */
    private String getDrugName(String drugId) {
        return inventoryManager.getItem(drugId).getItemName();
    }
}
