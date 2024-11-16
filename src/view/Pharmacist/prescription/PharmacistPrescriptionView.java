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

/**
 * This view allows the pharmacist to update the prescription status.
 * It displays a list of undispensed appointments and allows the pharmacist to select one to prescribe medicine.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public final class PharmacistPrescriptionView extends View {
    /**
     * An instance of the {@link AppointmentManager} class. Used to retrieve appointments.
     */
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Update Prescription Status";
    }

    /**
     * Renders the view.
     */
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

        TextInputField selectField = new TextInputField(String.format("Select an appointment to prescribe medicine (1-%d)", undispensedAppointments.size()));
        new TextInput(selectField).read(context, "Select an appointment from the list above.",
            (input) -> InputValidators.validateRange(input, undispensedAppointments.size()));

        final Appointment selectedAppointment = undispensedAppointments.get(selectField.getOption());

        Navigator.navigateTo(new PharmacistPrescribeMedicineView(selectedAppointment));
    }
    
}
