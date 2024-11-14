package view.Doctor.appointments;

import java.util.List;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Doctor;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentsTable;
import view.widgets.Title;

public class DoctorAppointmentRequestsView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final Doctor doctor;

    public DoctorAppointmentRequestsView(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String getViewName() {
        return "Manage Appointment Requests";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Manage Appointment Requests").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Pending Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getPendingAppointments(doctor);
        
        new DoctorAppointmentsTable(appointments).paint(context);

        new VSpacer(1).paint(context);

        if (appointments.isEmpty()) {
            new Pause("Press any key to go back.").pause(context);
            Navigator.pop();
            return;
        }

        /// Choose an appointment to update
        TextInputField apptField = new TextInputField("Choose an appointment to approve/decline");
        new TextInput(apptField).read(context, "Choose an appointment from the list above.",
            (input) -> InputValidators.validateRange(input, appointments.size()));

        final Appointment selectedAppointment = appointments.get(apptField.getOption());
        
        new VSpacer(1).paint(context);
        
        TextInputField statusField = new TextInputField("Do you accept this appointment? (Y/N)");
        new TextInput(statusField).read(context, "Specify \"Y\" to accept and \"N\" to decline.", 
            (input) -> InputValidators.validateYesNo(input));

        final boolean accept = statusField.getYesNo();

        if (accept) {
            appointmentManager.acceptAppointment(selectedAppointment);
        } else {
            appointmentManager.declineAppointment(selectedAppointment);
        }

        new Text("Appointment status updated successfully.", TextStyle.BOLD).paint(context);
        new Pause().pause(context);

        clear();
        render();
    }
}
