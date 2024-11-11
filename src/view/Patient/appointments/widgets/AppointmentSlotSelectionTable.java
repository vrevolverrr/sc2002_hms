package view.Patient.appointments.widgets;

import java.util.List;

import model.appointments.AppointmentSlot;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;

public class AppointmentSlotSelectionTable extends Widget {
    private final List<AppointmentSlot> appointmentSlots;

    public AppointmentSlotSelectionTable(List<AppointmentSlot> appointmentSlots) {
        this.appointmentSlots = appointmentSlots;
    }

    @Override
    public String build(BuildContext context) {
        TableRow[] appoinmentSlotRows = appointmentSlots.stream().map((slot) -> new TableRow(
            slot.getTimeSlot().getFormattedDate(),
            slot.getTimeSlot().getFormattedTime(),
            slot.getDoctor().getName(), slot.getDoctor().getSpecialisation().toString())).toArray(TableRow[]::new);
        
      return new EnumeratedTable(appoinmentSlotRows).build(context);
    }
}
