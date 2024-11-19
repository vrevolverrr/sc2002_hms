package view.Doctor.appointments;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import services.Navigator;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentDetailsTable;
import view.Doctor.appointments.widgets.PatientDetailsTable;
import view.Doctor.appointments.widgets.PrescriptionsTable;
import view.widgets.Title;
import services.ServiceLocator;
import controller.interfaces.IPatientManager;

/**
 * The {@link DoctorViewAppointmentDetailsView} class is a view for doctors to view the details of an appointment,
 * including the patient details, appointment details, and the recorded outcome (if any).
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorViewAppointmentDetailsView extends View {
    /**
     * The {@link Appointment} object representing the appointment details to be viewed.
     */
    private final Appointment appointment;

    /**
     * The patient manager instance used to retrieve patient details.
     */
    private final IPatientManager patientManager = ServiceLocator.getService(IPatientManager.class);

    /**
     * The {@link BuildContext} used for rendering the view.
     */
    private final BuildContext context = BuildContext.unboundedVertical(110);

    /**
     * Constructs a new {@link DoctorViewAppointmentDetailsView} to view the details of the given appointment.
     *
     * @param appointment the {@link Appointment} object that contains the appointment details to be viewed.
     */
    public DoctorViewAppointmentDetailsView(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "View Appointment Outcome".
     */
    @Override
    public String getViewName() {
        return "View Appointment Outcome";
    }

    /**
     * Renders the user interface for viewing appointment details.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("View Appointment Details").paint(context);

        new VSpacer(1).paint(context);

        new Title("Patient Details").paint(context);
        new PatientDetailsTable(patientManager.getPatient(appointment.getPatientId())).paint(context);

        new VSpacer(1).paint(context);

        new Title("Appointment Details").paint(context);
        new DoctorAppointmentDetailsTable(appointment).paint(context);

        new VSpacer(1).paint(context);

        new Title("Appointment Outcome").paint(context);

        if (appointment.getOutcomeRecord() == null) {
            new Table(new TableRow("No outcome recorded")).paint(context);
            Pause.goBack().pause(context);
            Navigator.pop();
            return;
        }
        
        new Table(
            new TableRow("Date Recorded", appointment.getOutcomeRecord().getFormattedRecordedDate()),
            new TableRow("Consultation Notes", appointment.getOutcomeRecord().getConsultationNotes()),
            new TableRow("Medical Services", 
                String.join(", ", appointment.getOutcomeRecord()
                    .getServices().stream().map(service -> service.toString()).toArray(String[]::new)))
        ).paint(context);

        new VSpacer(1).paint(context);

        new Title("Prescriptions").paint(context);
        new PrescriptionsTable(appointment.getOutcomeRecord().getPrescriptions()).paint(context);

        new VSpacer(1).paint(context);

        new Pause("Press any key to go back.").pause(context);
        Navigator.pop();
    }
}
