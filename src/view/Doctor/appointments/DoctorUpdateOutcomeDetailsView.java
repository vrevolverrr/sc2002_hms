package view.Doctor.appointments;

import model.appointments.Appointment;
import view.View;
import view.widgets.Title;

public class DoctorUpdateOutcomeDetailsView extends View {
    private final Appointment appointment;

    public DoctorUpdateOutcomeDetailsView(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String getViewName() {
        return "Update Appointment Outcome";
    }

    @Override
    public void render() {
        new Title("Update Appointment Outcome").paint(context);

        
    }
}
