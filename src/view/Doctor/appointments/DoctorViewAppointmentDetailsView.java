package view.Doctor.appointments;

import controller.PatientManager;
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

public class DoctorViewAppointmentDetailsView extends View {
    private final Appointment appointment;
    private final PatientManager patientManager = PatientManager.getInstance(PatientManager.class);

    private final BuildContext context = BuildContext.unboundedVertical(110);

    public DoctorViewAppointmentDetailsView(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String getViewName() {
        return "View Appointment Outcome";
    }

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
