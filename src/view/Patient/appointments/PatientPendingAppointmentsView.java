package view.Patient.appointments;

import controller.AppointmentManager;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.VSpacer;
import model.users.Patient;
import services.Navigator;
import view.View;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

public class PatientPendingAppointmentsView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final Patient patient;

    public PatientPendingAppointmentsView(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getViewName() {
        return "Pending Appointments";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Pending Appointments").paint(context);
        new AppointmentsTable(appointmentManager.getPendingAppointments(patient)).paint(context);

        new VSpacer(1).paint(context);

        new Pause("Press any key to go back.").pause(context);
        Navigator.pop();
    }
    
}
