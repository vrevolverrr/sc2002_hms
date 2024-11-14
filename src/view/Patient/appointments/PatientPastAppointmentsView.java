package view.Patient.appointments;

import java.util.List;

import controller.AppointmentManager;
import controller.UserManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Patient;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.DoctorViewAppointmentDetailsView;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

public class PatientPastAppointmentsView extends View {
    private final AppointmentManager appointmentsManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final List<Appointment> pastAppointments;

    private String keyword = "";
    private boolean showingResults = false;

    public PatientPastAppointmentsView(Patient patient) {
        pastAppointments = appointmentsManager.getPastAppointments(patient);
    }

    @Override
    public String getViewName() {
        return "Past Appointments";
    }

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
            TextInputField selectField = new TextInputField("Select an appointment to view details");
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

    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }   
}
