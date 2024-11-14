package view.Admin.appointments;
import java.util.List;

import controller.AppointmentManager;
import controller.UserManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Admin.appointments.widgets.AdminAppointmentTable;
import view.Doctor.appointments.DoctorViewAppointmentDetailsView;
import view.widgets.Title;

public class AdminAppointmentView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final List<Appointment> appointments = appointmentManager.getAllAppointments();

    private String keyword = "";
    private boolean showingResults = false;

    @Override
    public String getViewName() {
        return("Admin Appointment View");
    }

    @SuppressWarnings("unused")
    @Override
    public void render() {
        new Title("View All Appointments").paint(context);

        List<Appointment> filteredAppointments = filterByKeyword(keyword);
        new AdminAppointmentTable(filteredAppointments).paint(context);

        new VSpacer(1).paint(context);

        if (showingResults) {
            TextInputField selectField = new TextInputField("Select an appointment to view");
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

    public List<Appointment> filterByKeyword(String keyword) {
        if (keyword.isBlank()) {
            return appointments;
        }

        return appointments.stream().filter(appointment -> 
            String.format("%s %s %s %s %s",
                appointment.getId(), 
                getNameById(appointment.getPatientId()),
                getNameById(appointment.getDoctorId()),
                appointment.getDateTime().getFormattedDateTime(),
                appointment.getStatus().toString()
            ).toLowerCase().contains(keyword)).toList();
    }

    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
