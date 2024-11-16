package view.Admin.appointments;
import java.util.List;

import controller.AppointmentManager;
import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Admin.appointments.widgets.AdminAppointmentTable;
import view.Doctor.appointments.DoctorViewAppointmentDetailsView;
import view.widgets.Title;

/**
 * This view allows the admin to view all appointments.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminAppointmentView extends View {
    /**
     * An instance of the {@link UserManager} class. Used to manage user operations.
     */
    private final UserManager userManager = UserManager.getInstance(UserManager.class);

    /**
     * An instance of the {@link AppointmentManager} class. Used to manage appointment operations.
     */
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);

    /**
     * The list of all appointments.
     */
    private final List<Appointment> appointments = appointmentManager.getAllAppointments();

    /**
     * The build context for rendering the view.
     */
    private final BuildContext context = BuildContext.unboundedVertical(110);

    /**
     * The search keyword entered by the admin.
     */
    private String keyword = "";

    /**
     * Indicates whether the view is showing search results.
     */
    private boolean showingResults = false;

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "View All Appointments";
    }

    @SuppressWarnings("unused")
    /**
     * Renders the view, allowing the admin to view all appointments.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("View All Appointments").paint(context);

        List<Appointment> filteredAppointments = filterByKeyword(keyword);
        new AdminAppointmentTable(filteredAppointments).paint(context);

        new VSpacer(1).paint(context);

        if (showingResults) {
            TextInputField selectField = new TextInputField(String.format("Select an appointment to view details (1-%d)", filteredAppointments.size()));
            new TextInput(selectField).read(context, "Select an item from the list above.",
                (input) -> InputValidators.validateRange(input, filteredAppointments.size()));

            final Appointment selectedItem = filteredAppointments.get(selectField.getOption());
            Navigator.navigateTo(new DoctorViewAppointmentDetailsView(selectedItem));
            return;
        }

        TextInputField searchField = new TextInputField("Search for appointments");
        new TextInput(searchField).read(context, input -> true);

        keyword = searchField.getValue().toLowerCase();
        showingResults = !keyword.isBlank();
        repaint();
    }

    /**
     * Filters the appointments by the specified keyword.
     * @param keyword the keyword to filter by.
     * @return the list of filtered appointments.
     */
    public List<Appointment> filterByKeyword(String keyword) {
        if (keyword.isBlank()) {
            return appointments;
        }

        return appointments.stream().filter(appointment -> 
            String.format("%s %s %s %s %s",
                appointment.getId(), 
                getNameById(appointment.getPatientId()),
                getNameById(appointment.getDoctorId()),
                appointment.getTimeSlot().getFormattedDateTime(),
                appointment.getStatus().toString()
            ).toLowerCase().contains(keyword)).toList();
    }

    /**
     * Gets the name of the user by their ID.
     * @param id the ID of the user.
     * @return the name of the user.
     */
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
