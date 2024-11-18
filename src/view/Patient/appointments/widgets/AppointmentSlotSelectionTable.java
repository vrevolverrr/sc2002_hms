package view.Patient.appointments.widgets;

import java.util.List;

import model.appointments.AppointmentSlot;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;

/**
 * The {@link AppointmentSlotSelectionTable} is a {@link Widget} to display a table of available appointment slots for selection.
 * 
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class AppointmentSlotSelectionTable extends Widget {
    /**
     * The {@link List} of available {@AppointmentSlot}.
     */
    private final List<AppointmentSlot> appointmentSlots;

    /**
     * Constructs a new {@link AppointmentSlotSelectionTable} widget.
     * 
     * @param appointmentSlots The list of available appointment slots.
     */
    public AppointmentSlotSelectionTable(List<AppointmentSlot> appointmentSlots) {
        this.appointmentSlots = appointmentSlots;
    }

    /**
     * Builds the UI representation of the appointment slot selection table.
     * <p>
     * If no appointment slots are available, the table displays a single row stating "No appointment slots available."
     * Otherwise, the table includes one row for each appointment slot, displaying the details of the slot.
     * </p>
     * 
     * @param context The build context in which the UI elements are rendered.
     * @return The UI representation of the appointment slot selection table.
     */
    @Override
    public String build(BuildContext context) {
        if (appointmentSlots.isEmpty()) {
            return new Table(new TableRow("No appointment slots available")).build(context);
        }

        TableRow[] appoinmentSlotRows = appointmentSlots.stream().map((slot) -> new TableRow(
            slot.getTimeSlot().getFormattedDate(),
            slot.getTimeSlot().getFormattedTime(),
            slot.getDoctor().getName(), slot.getDoctor().getSpecialisation().toString())).toArray(TableRow[]::new);
        
      return EnumeratedTable.headerless(appoinmentSlotRows).build(context);
    }
}
