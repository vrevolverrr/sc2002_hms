package view.Pharmacist.prescription.widgets;

import java.util.List;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;
import services.ServiceLocator;

/**
 * The {@link PharmacistAppointmentsTable} widget displays a table of appointments that have undispensed prescriptions.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class PharmacistAppointmentsTable extends Widget {
    /**
     * An instance of User Manager used to manage users.
     */
    private IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * The {@link List} of {@link Appointment} with undispensed prescriptions.
     */
    private final List<Appointment> undispensedAppointments;

    /**
     * Constructs a new {@link PharmacistAppointmentsTable} with a given {@link List} of undispensed appointments.
     * 
     * @param undispensedAppointments the list of undispensed appointments.
     */
    public PharmacistAppointmentsTable(List<Appointment> undispensedAppointments) {
        this.undispensedAppointments = undispensedAppointments;
    }
    
    /**
     * Builds the table of appointments with undispensed prescriptions.
     * 
     * @param context the {@link BuildContext} used to build the widget.
     * @return a {@link String} representing the table of appointments with undispensed prescriptions.
     */
    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Date", "Time", "Patient", "Doctor", "Status", "Prescription");

        if (undispensedAppointments.isEmpty()) {
            return new Table(new TableRow("No appointments with undispensed prescriptions")).build(context);
        }
        
        TableRow[] rows = undispensedAppointments.stream().map(appointment -> new TableRow(
            appointment.getTimeSlot().getFormattedDate(),
            appointment.getTimeSlot().getFormattedTime(),
            getNameById(appointment.getPatientId()),
            getNameById(appointment.getDoctorId()),
            appointment.getStatus().toString(),
            getOverallPrescriptionStatus(appointment.getOutcomeRecord().getPrescriptions()).toString()
        )).toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, rows).build(context);
    }
    
    /**
    * Retrieves the name of a user by their unique ID.
    * 
    * @param id The ID of the user.
    * @return The name of the user associated with the given ID.
    */
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }

    /**
     * Determines the overall prescription status of a list of prescriptions.
     * 
     * @param prescriptions the {@link List} of {@link Prescription} to check.
     * @return the overall prescription status of the {@link List} of {@link Prescription}.
     */
    private PrescriptionStatus getOverallPrescriptionStatus(List<Prescription> prescriptions) {
        return prescriptions.stream()
            .anyMatch(prescription -> prescription.getStatus() == PrescriptionStatus.PENDING) 
            
            ? PrescriptionStatus.PENDING : PrescriptionStatus.DISPENSED;
    }
}
