package view.Doctor.appointments;

import java.util.List;

import controller.interfaces.IAppointmentManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Doctor;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentsTable;
import view.widgets.Title;

/**
 * {@link DoctorRecordAppointmentOutcomeView} is a {@link View} that allows doctors to record
 * the outcomes of fulfilled appointments. Doctors can view a list of fulfilled appointments
 * and choose to update the outcomes of the appointments.
 * 
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorRecordAppointmentOutcomeView extends View {
    /**
     * An instance of {@link AppointmentManager} used to manage appointments.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * The {@link Doctor} for whom the view is managing appointments.
     */
    private final Doctor doctor;

    /**
     * Constructs a new {@link DoctorRecordAppointmentOutcomeView} for a given doctor.
     *
     * @param doctor the {@link Doctor} whose appointments are being managed.
     */
    public DoctorRecordAppointmentOutcomeView(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Record Appointment Outcome".
     */
    @Override
    public String getViewName() {
        return "Record Appointment Outcome";
    }

    /**
     * Renders the "Record Appointment Outcome" view for the doctor.
     * <p>
     * The rendering process includes:
     * <ol>
     *   <li>A breadcrumb navigation</li>
     *   <li>A title of "Record Appointment Outcomes"</li>
     *   <li>A title of "Fulfilled Appointments"</li>
     *   <li>A table displaying all fulfilled appointments</li>
     *   <li>A prompt to select an appointment to update the outcome</li>
     * </ol>
     * </p>
     * If there are no fulfilled appointments, the view will display a message informing the doctor and navigate back.
     * If there are fulfilled appointments, the doctor can select one to update the outcome.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Record Appointment Outcomes").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Fulfilled Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getFulfilledAppointments(doctor);
        
        new DoctorAppointmentsTable("No appointments pending outcome",  appointments).paint(context);

        new VSpacer(1).paint(context);

        if (appointments.isEmpty()) {
            Pause.goBack().pause(context);
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
