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

public class PrescriptionsTable extends Widget {
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);
    private final List<Prescription> prescriptions;

    public PrescriptionsTable(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

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

    private String parseDosage(MedicineDosage dosage) {
        return dosage.getQuantity() + " " + dosage.getUnit();
    }

    private String getDrugName(String drugId) {
        return inventoryManager.getInventoryItem(drugId).getItemName();
    }
}
