package view.Doctor.appointments.widgets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Column;
import model.enums.MedicalService;
import model.prescriptions.Prescription;
import view.widgets.Title;

public class AppointmentUpdateOutcomeTable extends Widget {
    private final String consultationNotes;
    private final List<Prescription> prescriptions;
    private final List<MedicalService> services;
    
    public AppointmentUpdateOutcomeTable(String consultationNotes, List<Prescription> prescriptions, List<MedicalService> services) {
        this.consultationNotes = consultationNotes == null ? "" : consultationNotes;
        this.prescriptions = prescriptions == null ? List.of() : prescriptions;
        this.services = services == null ? List.of() : services;
    }

    @Override
    public String build(BuildContext context) {
        return new Column(
            new Table(
                new TableRow("Date Recorded", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
                new TableRow("Consultation Notes", consultationNotes),
                new TableRow("Medical Services", 
                    String.join(", ", services.stream().map(service -> service.toString()).toArray(String[]::new)))
            ),
            new VSpacer(1),
            new Title("Prescriptions"),
            new PrescriptionsTable(prescriptions)
        ).build(context);
    }
    
}
