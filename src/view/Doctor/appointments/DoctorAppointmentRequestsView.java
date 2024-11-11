package view.Doctor.appointments;

import controller.AppointmentManager;
import model.users.Doctor;
import view.View;

public class DoctorAppointmentRequestsView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final Doctor doctor;

    public DoctorAppointmentRequestsView(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String getViewName() {
        return "Manage Appointment Requests";
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
}
