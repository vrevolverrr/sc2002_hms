package view.Patient.appointments;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import controller.AppointmentManager;
import controller.AppointmentSlot;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.layout.Align;
import lib.uilib.widgets.layout.Column;
import services.Navigator;
import view.View;

public class PatientScheduleAppointmentView extends View {
    final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class); 
    @Override
    public String getViewName() {
        return "Schedule Appointment";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 1000);
        
        new Align(Alignment.CENTER, new Text("[ Schedule Appointment ]", TextStyle.BOLD)).paint(context);
        new VSpacer(1).paint(context);

        new Align(Alignment.CENTER, new Text("[ Available Time Slots ]", TextStyle.BOLD)).paint(context);
        
        List<AppointmentSlot> appointmentSlots = appointmentManager.getAvailableSlots();

        TableRow[] appoinmentSlotRows = appointmentSlots.stream().map((slot) -> new TableRow(
            slot.getTimeSlot().format(DateTimeFormatter.ofPattern("dd/MM/yy")),
            slot.getTimeSlot().format(DateTimeFormatter.ofPattern("h:mma")),
            slot.getDoctor().getName(), slot.getDoctor().getSpecialisation().toString())).toArray(TableRow[]::new);
        
            new EnumeratedTable(appoinmentSlotRows).paint(context);
        
        TextInputField slotField = new TextInputField("Choose an appointment slot");
        new TextInput(slotField).read(context, (input) -> validateInput(input, appointmentSlots.size()));

        new VSpacer(1).paint(context);
        
        // TODO schedule appointment
        new Text("Appointment scheduled successfully.", TextStyle.BOLD).paint(context);
        new Column(
            new Text("Date and Time: " + appointmentSlots.get(Integer.parseInt(slotField.getValue()) - 1).getTimeSlot().format(DateTimeFormatter.ofPattern("dd/MM/yy h:mma"))),
            new Text("Doctor: " + appointmentSlots.get(Integer.parseInt(slotField.getValue()) - 1).getDoctor().getName())
        ).paint(context);

        new Pause().pause(context);
        Navigator.pop();

    }
}
