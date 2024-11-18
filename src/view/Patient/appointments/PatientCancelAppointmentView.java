package view.Patient.appointments;

import java.util.List;

import controller.interfaces.IAppointmentManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

/**
 * View for canceling a patient's appointment.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PatientCancelAppointmentView extends View {
    /**
     * Manager for handling appointment-related operations.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * The patient canceling the appointment.
     */
    private Patient patient;

    /**
     * Constructs a new view for canceling a patient's appointment.
     * 
     * @param patient The patient canceling the appointment.
     */
    public PatientCancelAppointmentView(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * 
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
       return "Cancel Appointment";
    }

    /**
     * Renders the view to cancel a patient's appointment.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Cancel Appointment").paint(context);
        new VSpacer(1).paint(context);

        new Title("Upcoming Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getUpcomingAppointments(patient);
        
        new AppointmentsTable(appointments).paint(context);

        new VSpacer(1).paint(context);
        
        if (appointments.isEmpty()) {
            Pause.goBack().pause(context);
            Navigator.pop();
            return;
        }

        /// Choose the appointment to cancel by index on the table, the input is automatically
        /// validated.
        TextInputField timeslotField = new TextInputField(String.format("Choose an appointment to cancel (1-%d)", appointments.size()));
        new TextInput(timeslotField).read(context, "Choose an appointment to cancel.", 
            (input) -> InputValidators.validateRange(input, appointments.size()));

        final Appointment selectedAppointment = appointments.get(timeslotField.getOption());

        /// Cancel the appointment
        appointmentManager.cancelAppointment(selectedAppointment);

        /// Rebuild the screen with updated table
        clear();
        new Title("Cancel Appointment").paint(context);
        new VSpacer(1).paint(context);

        new AppointmentsTable(appointmentManager.getUpcomingAppointments(patient)).paint(context);

        new VSpacer(1).paint(context);
        new Text("Appointment cancelled successfully", TextStyle.BOLD).paint(context);

        new Pause().pause(context);
        Navigator.pop();

    }
}
