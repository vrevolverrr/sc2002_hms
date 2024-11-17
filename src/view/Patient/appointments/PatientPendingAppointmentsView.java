package view.Patient.appointments;

import controller.interfaces.IAppointmentManager;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.VSpacer;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

/**
 * View for displaying the patient's pending appointments.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PatientPendingAppointmentsView extends View {
    /**
     * Manager for handling appointment-related operations.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * The patient whose pending appointments are being displayed.
     */
    private final Patient patient;

    /**
     * Constructs a new view for displaying the patient's pending appointments.
     * @param patient The patient whose pending appointments are being displayed.
     */
    public PatientPendingAppointmentsView(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the name of the view.
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
        return "Pending Appointments";
    }

    /**
     * Renders the view to display the patient's pending appointments.
     * The view will display the patient's pending appointments in a table.
     */
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
