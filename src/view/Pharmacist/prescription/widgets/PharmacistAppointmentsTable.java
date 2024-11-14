package view.Pharmacist.prescription.widgets;

import java.util.List;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;

public class PharmacistAppointmentsTable extends Widget {
    private UserManager userManager = UserManager.getInstance(UserManager.class);
    private final List<Appointment> undispensedAppointments;

    public PharmacistAppointmentsTable(List<Appointment> undispensedAppointments) {
        this.undispensedAppointments = undispensedAppointments;
    }
    
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
    
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }

    private PrescriptionStatus getOverallPrescriptionStatus(List<Prescription> prescriptions) {
        return prescriptions.stream()
            .anyMatch(prescription -> prescription.getStatus() == PrescriptionStatus.PENDING) 
            
            ? PrescriptionStatus.PENDING : PrescriptionStatus.DISPENSED;
    }
}
