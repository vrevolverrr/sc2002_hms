package view.Pharmacist.prescription;

import java.util.List;

import controller.AppointmentManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Pharmacist.prescription.widgets.PharmacistAppointmentsTable;
import view.widgets.Title;

public class PharmacistPrescriptionView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);

    @Override
    public String getViewName() {
        return "Update Prescription Status";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Update Prescription Status").paint(context);

        new VSpacer(1).paint(context);

        List<Appointment> undispensedAppointments = appointmentManager.getUndispensedAppointments();
        new PharmacistAppointmentsTable(undispensedAppointments).paint(context);

        new VSpacer(1).paint(context);

        if (undispensedAppointments.isEmpty()) {
            new Pause("No undispensed appointments found. Press any key to go back.").paint(context);
            Navigator.pop();
            return;
        }

        TextInputField selectField = new TextInputField("Select an appointment to prescribe medication");
        new TextInput(selectField).read(context, "Select an appointment from the list above.",
            (input) -> InputValidators.validateRange(input, undispensedAppointments.size()));

        final Appointment selectedAppointment = undispensedAppointments.get(selectField.getOption());

        Navigator.navigateTo(new PharmacistPrescribeMedicineView(selectedAppointment));
    }
    
}
