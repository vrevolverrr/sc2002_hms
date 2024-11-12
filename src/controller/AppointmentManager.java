package controller;

import model.Appointment;
import model.Patient;
import model.Doctor;
import model.Admin;

/* Appointment Manager implements an access-control logic (using encapsulation)
1. Which appointments do they have access to? 
    a. Patient - can only view and manage their own appointments
    b. Doctor - can only view and manage appointments associated with them
    c. Administrator - has full access to all appointments 
2. Which pieces of information within their appointment can they edit?
    a. Patient - 
    b. Doctor - can view all information
    c. Administrator - has full access to edit all information in each appointment
*/
public class AppointmentManager {
    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentManager(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments(User user) {
        switch (user.getRole()) {
            case PATIENT:
                // Patients can only view their own appointments
                return appointments.stream()
                        .filter(a -> a.getPatientId().equals(user.getUserId()))
                        .collect(Collectors.toList());

            case DOCTOR:
                // Doctors can view only their own patients' appointments
                return appointments.stream()
                        .filter(a -> a.getDoctorId().equals(user.getUserId()))
                        .collect(Collectors.toList());

            case ADMIN:
                // Administrators have full access to all appointments
                return appointments;

            default:
                throw new UnsupportedOperationException("Unknown user role");
        }
    }

    public void updateAppointmentStatus(User user, String appointmentId, AppointmentStatus newStatus) {
        Appointment appointment = appointments.stream()
                .filter(a -> a.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        switch (user.getRole()) {
            case PATIENT:
                if (!appointment.getPatientId().equals(user.getUserId())) {
                    throw new SecurityException("Patients can only update their own appointments");
                }
                // Patients may have limited status update permissions (e.g., only to cancel)
                if (newStatus == AppointmentStatus.CANCELLED) {
                    appointment.setStatus(newStatus);
                } else {
                    throw new UnsupportedOperationException("Patients can only cancel appointments");
                }
                break;

            case DOCTOR:
                if (!appointment.getDoctorId().equals(user.getUserId())) {
                    throw new SecurityException("Doctors can only update their own appointments");
                }
                // Allow doctors to update certain statuses
                appointment.setStatus(newStatus);
                break;

            case ADMIN:
                // Admins can update any appointment status
                appointment.setStatus(newStatus);
                break;

            default:
                throw new UnsupportedOperationException("Unknown user role");
        }
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
