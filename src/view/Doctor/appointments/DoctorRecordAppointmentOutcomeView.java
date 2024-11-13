package view.Doctor.appointments;

import java.util.List;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Doctor;
import services.InputValidators;
import services.Navigator;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentsTable;
import view.widgets.Title;

public class DoctorRecordAppointmentOutcomeView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final Doctor doctor;

    public DoctorRecordAppointmentOutcomeView(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String getViewName() {
        return "Record Appointment Outcome";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 1000);

        new Title("Record Appointment Outcomes").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Fulfilled Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getFulfilledAppointments(doctor);
        
        new DoctorAppointmentsTable("No appointments pending outcome",  appointments).paint(context);

        new VSpacer(1).paint(context);

        if (appointments.isEmpty()) {
            new Pause("Press any key to go back.").pause(context);
            Navigator.pop();
            return;
        }

        /// Choose an appointment to update
        TextInputField apptField = new TextInputField("Choose an appointment to update outcome");
        new TextInput(apptField).read(context, "Choose an appointment from the list above.",
            (input) -> InputValidators.validateRange(input, appointments.size()));

        final Appointment selectedAppointment = appointments.get(apptField.getOption());
        Navigator.navigateTo(new DoctorUpdateOutcomeDetailsView(selectedAppointment));       
    }
    
}
