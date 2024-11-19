package view.Doctor.appointments;

import java.util.List;

import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Doctor;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentsTable;
import view.widgets.Title;
import services.ServiceLocator;
import controller.interfaces.IAppointmentManager;
import controller.interfaces.IUserManager;

/**
 * The {@link DoctorViewPastAppointmentsView} class provides a user interface for doctors
 * to view and search their past fulfilled or completed appointments.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorViewPastAppointmentsView extends View {
    /**
     * An instance of {@link AppointmentManager} used to manage appointments.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * An instance of {@link UserManager} used to manage users.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * The {@link List} of past fulfilled or completed appointments for the doctor.
     */
    private final List<Appointment> pastAppointments;

    /**
     * The search keyword used to filter appointments.
     */
    private String keyword = "";

    /**
     * A flag indicating whether search results are being displayed.
     */
    private boolean showingResults = false;

    /**
     * Constructs a new {@link DoctorViewPastAppointmentsView} for the given doctor.
     *
     * @param doctor the {@link Doctor} whose past appointments are being viewed.
     */
    public DoctorViewPastAppointmentsView(Doctor doctor) {
        pastAppointments = appointmentManager.getPastAppointments(doctor);
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Past Appointments".
     */
    @Override
    public String getViewName() {
        return "Past Appointments";
    }

    /**
     * Renders the user interface for viewing and searching past appointments.
     */
    @SuppressWarnings("unused")
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("View Past Appointments").paint(context);

        List<Appointment> filteredAppointments = filterAppointments(keyword);
        new DoctorAppointmentsTable("No past fulfilled or completed appointments", filteredAppointments).paint(context);

        new VSpacer(1).paint(context);

        if (showingResults) {
            TextInputField selectField = new TextInputField("Select an appointment to view details");
            new TextInput(selectField).read(context, "Select an appointment from the list above.",
                (input) -> InputValidators.validateRange(input, filteredAppointments.size()));

            final Appointment selectedAppointment = filteredAppointments.get(selectField.getOption());
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
     * Filters the list of past appointments based on a search keyword.
     * <p>
     * The search matches against the following fields:
     * <ul>
     *   <li>Appointment ID</li>
     *   <li>Patient ID</li>
     *   <li>Patient name</li>
     *   <li>Appointment date and time</li>
     *   <li>Appointment status</li>
     * </ul>
     * </p>
     *
     * @param keyword the search keyword to filter appointments.
     * @return a {@link List} of appointments matching the keyword.
     */
    private List<Appointment> filterAppointments(String keyword) {
        if (keyword.isBlank()) {
            return pastAppointments;
        }

        return pastAppointments.stream().filter(appointment -> 
            String.format("%s %s %s %s %s",
                appointment.getId(), 
                appointment.getPatientId(),
                getNameById(appointment.getPatientId()),
                appointment.getTimeSlot().getFormattedDateTime(),
                appointment.getStatus().toString()
            ).toLowerCase().contains(keyword)).toList();
    }

    /**
     * Retrieves the name of a user by their ID.
     *
     * @param id the ID of the user.
     * @return a {@link String} representing the user's name.
     */
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
