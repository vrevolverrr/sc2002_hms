package view.Admin.appointments;
import java.util.List;

import controller.AppointmentManager;
import controller.UserManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.User;
import view.View;
import view.Admin.appointments.widgets.AdminAppointmentTable;
import view.widgets.Title;

public class AdminAppointmentView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final List<Appointment> appointments = appointmentManager.getAllAppointments();

    private String keyword = "";

    @Override
    public String getViewName() {
        return("Admin Appointment View");
    }

    @Override
    public void render() {
        // TODO add appointment outcome
        new Title("View All Appointments").paint(context);

        List<Appointment> filteredAppointments = filterByKeyword(keyword);
        new AdminAppointmentTable(filteredAppointments).paint(context);

        new VSpacer(1).paint(context);

        TextInputField searchField = new TextInputField("Search for appointments");
        new TextInput(searchField).read(context, input -> true);

        keyword = searchField.getValue().toLowerCase();
        clear();
        render();
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
        User user = userManager.getUser(id);
        return user == null ? "Unknown" : user.getName();
    }
}
