package view.Doctor.appointments;

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
import model.users.Doctor;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentsTable;
import view.widgets.Title;

/**
 * {@link DoctorAppointmentRequestsView} is a {@link View} that allows doctors to manage 
 * pending appointment requests. Doctors can view a list of pending appointments 
 * and choose to approve or decline them.
 * <p>
 * The view interacts with {@link AppointmentManager} to retrieve and update appointment statuses.
 * It displays the information using various widgets such as {@link Breadcrumbs}, 
 * {@link DoctorAppointmentsTable}, and {@link Text}.
 * </p>
 * 
 * <h3>Features:</h3>
 * <ul>
 *   <li>Displays pending appointment requests for a specific doctor.</li>
 *   <li>Enables approval or decline of selected appointments.</li>
 *   <li>Updates the appointment status in the system.</li>
 * </ul>
 * 
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 * 
 */

 public class DoctorAppointmentRequestsView extends View {
    /**
     * An instance of {@link AppointmentManager} used to manage appointments.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * The {@link Doctor} for whom the view is managing appointments.
     */
    private final Doctor doctor;

    /**
     * Constructs a new {@link DoctorAppointmentRequestsView} for a given doctor.
     *
     * @param doctor the {@link Doctor} whose appointments are being managed.
     */
    public DoctorAppointmentRequestsView(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Manage Appointment Requests".
     */
    @Override
    public String getViewName() {
        return "Manage Appointment Requests";
    }

    /**
     * Renders the view by displaying pending appointment requests and providing 
     * options to approve or decline them.
     * <p>
     * The rendering process includes:
     * <ol>
     *   <li>Displaying breadcrumbs and a title.</li>
     *   <li>Fetching pending appointments for the doctor using {@link AppointmentManager#getPendingAppointments(Doctor)}.</li>
     *   <li>Displaying the appointments using {@link DoctorAppointmentsTable}.</li>
     *   <li>Prompting the doctor to select and update the status of an appointment.</li>
     * </ol>
     * </p>
     * 
     */
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
            Pause.goBack().pause(context);
            Navigator.pop();
            return;
        }

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
