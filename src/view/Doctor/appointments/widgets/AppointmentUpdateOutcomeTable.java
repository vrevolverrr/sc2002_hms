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

/**
 * {@code AppointmentUpdateOutcomeTable} is a {@link Widget} that displays the details of an updated
 * appointment outcome for a doctor. It includes the consultation notes, medical services provided,
 * and any prescriptions issued during the consultation.
 * <p>
 * The table displays:
 * <ul>
 *   <li>Date of the appointment</li>
 *   <li>Consultation notes</li>
 *   <li>Medical services provided</li>
 * </ul>
 * Additionally, the table includes a list of prescriptions issued during the appointment.
 * </p>
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */

public class AppointmentUpdateOutcomeTable extends Widget {
    /**
     * The consultation notes recorded during the appointment.
     */
    private final String consultationNotes;
    
    /**
     * A list of {@link Prescription} objects representing the prescriptions issued during the appointment.
     */
    private final List<Prescription> prescriptions;

    /**
     * A list of {@link MedicalService} objects representing the medical services provided during the appointment.
     */
    private final List<MedicalService> services;
    
    /**
     * Constructs an {@code AppointmentUpdateOutcomeTable} with the given consultation notes, prescriptions, and medical services.
     *
     * @param consultationNotes the notes recorded during the consultation (can be null).
     * @param prescriptions the list of {@link Prescription} objects issued during the appointment (can be null).
     * @param services the list of {@link MedicalService} objects provided during the appointment (can be null).
     */
    public AppointmentUpdateOutcomeTable(String consultationNotes, List<Prescription> prescriptions, List<MedicalService> services) {
        this.consultationNotes = consultationNotes == null ? "" : consultationNotes;
        this.prescriptions = prescriptions == null ? List.of() : prescriptions;
        this.services = services == null ? List.of() : services;
    }

    /**
     * Builds the UI layout displaying the appointment outcome details.
     * @param context the {@link BuildContext} for rendering the widget.
     * @return the built UI as a {@link String}.
     */
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
