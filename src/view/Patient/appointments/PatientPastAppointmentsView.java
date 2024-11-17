package view.Patient.appointments;

import java.util.List;

import controller.AppointmentManager;
import controller.interfaces.IAppointmentManager;
import controller.interfaces.IUserManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.DoctorViewAppointmentDetailsView;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

/**
 * View for displaying the patient's past appointments.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PatientPastAppointmentsView extends View {
    /**
     * Manager for handling appointment-related operations.
     */
    private final IAppointmentManager appointmentsManager = ServiceLocator.getService(AppointmentManager.class);

    /**
     * Manager for handling user-related operations.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * List of the patient's past appointments.
     */
    private final List<Appointment> pastAppointments;

    /**
     * Keyword for filtering appointments. This is stored as the state of the {@link View}
     * to allow for the user to search for appointments.
     */
    private String keyword = "";

    /**
     * Flag indicating whether search results are being displayed. This is stored as the 
     * state of the {@link View} to know whether the view is displaying search results 
     * or prompting to search.
     */
    private boolean showingResults = false;

    /**
     * Constructs a new view for displaying the patient's past appointments.
     * @param patient The patient whose past appointments are being displayed.
     */
    public PatientPastAppointmentsView(Patient patient) {
        pastAppointments = appointmentsManager.getPastAppointments(patient);
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
        return "Past Appointments";
    }

    /**
     * Renders the view to display the patient's past appointments.
     * The view will display the patient's past appointments in a table and prompt the 
     * user to search and select an appointment to view details.
     */
    @SuppressWarnings("unused")
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Past Appointments").paint(context);

        List<Appointment> filteredAppointments = filterAppointments(keyword);
        new AppointmentsTable(filteredAppointments).paint(context);

        if (filteredAppointments.isEmpty()) {
            new Pause("Press enter to try again.").pause(context);
            // Reset and repaint
            keyword = ""; showingResults = false;
            repaint();
            return;
        }

        new VSpacer(1).paint(context);

        if (showingResults) {
            TextInputField selectField = new TextInputField(String.format("Select an appointment to view details (1-%d)", filteredAppointments.size()));
            new TextInput(selectField).read(context, "Select an appointment from the list above.",
                (input) -> InputValidators.validateRange(input, filteredAppointments.size()));

            final Appointment selectedAppointment = filteredAppointments.get(selectField.getOption());
            /// Reuse the same view
            Navigator.navigateTo(new DoctorViewAppointmentDetailsView(selectedAppointment));
            return;
        }

        TextInputField searchField = new TextInputField("Search for appointments");
        new TextInput(searchField).read(context, input -> true);

        keyword = searchField.getValue().toLowerCase();
        showingResults = !keyword.isBlank();

        repaint();
    }

    /**
     * Filters the list of appointments based on the given keyword.
     * @param keyword The keyword to filter appointments.
     * @return The filtered list of appointments.
     */
    private List<Appointment> filterAppointments(String keyword) {
        if (keyword.isBlank()) {
            return pastAppointments;
        }

        return pastAppointments.stream().filter(appointment -> 
            String.format("%s %s %s %s %s",
                appointment.getId(), 
                appointment.getPatientId(),
                getNameById(appointment.getDoctorId()),
                appointment.getTimeSlot().getFormattedDateTime(),
                appointment.getStatus().toString()
            ).toLowerCase().contains(keyword)).toList();
    }

    /**
     * Retrieves the name of a user by their ID.
     * @param id The ID of the user.
     * @return The name of the user.
     */
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }   
}
