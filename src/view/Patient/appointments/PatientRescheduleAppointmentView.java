package view.Patient.appointments;

import java.time.LocalDate;
import java.util.List;

import controller.interfaces.IAppointmentManager;
import controller.interfaces.IDoctorManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.appointments.AppointmentSlot;
import model.users.Doctor;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Patient.appointments.widgets.AppointmentScheduledStatus;
import view.Patient.appointments.widgets.AppointmentSlotSelectionTable;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.Patient.appointments.widgets.DoctorsSelectionTable;
import view.Patient.appointments.widgets.PatientAppointmentDetailsTable;
import view.Patient.appointments.widgets.SelectedDoctorTable;
import view.widgets.Title;

/**
 * View for rescheduling a patient's appointment.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public final class PatientRescheduleAppointmentView extends View {
    /**
     * Manager for handling appointment-related operations.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * Manager for handling doctor-related operations.
     */
    private final IDoctorManager doctorManager = ServiceLocator.getService(IDoctorManager.class);

    /**
     * The patient rescheduling the appointment.
     */
    private Patient patient;

    /**
     * Constructs a new view for rescheduling an appointment for the patient.
     * 
     * @param patient the patient rescheduling the appointment.
     */
    public PatientRescheduleAppointmentView(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * 
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Reschedule Appointment";
    }

    /**
     * Renders the view to reschedule a patient's appointment. The patient selects an
     * appointment to reschedule, then selects a doctor, date, and appointment slot to
     * reschedule the appointment.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Reschedule Appointment").paint(context);
        new VSpacer(1).paint(context);

        final Appointment selectedAppointment = promptChooseAppointment();
        new VSpacer(1).paint(context);

        /// Reschedule Appointment
        final Doctor selectedDoctor = promptChooseDoctor();
        new VSpacer(1).paint(context);

        final LocalDate selectedDate = promptChooseDate();
        new VSpacer(1).paint(context);

        final AppointmentSlot appointmentSlot = promptChooseAppointmentSlot(selectedDate, selectedDoctor);

        if (appointmentSlot == null) {
            new Pause("Press enter to try again.").pause(context);
            repaint();
            return;
        }

        new VSpacer(1).paint(context);

        // Reschedule the appointment
        appointmentManager.rescheduleAppointment(selectedAppointment, appointmentSlot);

        clear();
        new Breadcrumbs().paint(context);
        new Title("Reschedule Appointment").paint(context);
        new VSpacer(1).paint(context);

        AppointmentScheduledStatus.rescheduled(appointmentSlot).paint(context);

        new Pause().pause(context);
        Navigator.pop();
    }

    /**
     * Prompts the patient to choose an appointment to reschedule.
     * 
     * @return the selected appointment to reschedule.
     */
    private Appointment promptChooseAppointment() {
        new Title("Upcoming Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getScheduledAppointments(patient);
        new AppointmentsTable(appointments).paint(context);

        new VSpacer(1).paint(context);

        TextInputField appointmentField = new TextInputField(
                String.format("Choose an appointment to reschedule (1-%d)", appointments.size()));
        new TextInput(appointmentField).read(context, (input) -> InputValidators.validateRange(input, appointments.size()));

        final Appointment selectedAppointment = appointments.get(appointmentField.getOption());

        clearLines((appointments.size() + 1) * 2 + 1 + 3);
        new Title("Appointment To Reschedule").paint(context);
        new PatientAppointmentDetailsTable(selectedAppointment).paint(context);

        return selectedAppointment;
    }

    /**
     * Prompts the patient to choose a doctor for the appointment.
     * 
     * @return the selected doctor.
     */
    private Doctor promptChooseDoctor() {
        new Title("Available Doctors").paint(context);
        List<Doctor> doctors = doctorManager.getAllDoctors();
        new DoctorsSelectionTable(doctors).paint(context);
        new VSpacer(1).paint(context);

        /// Choose a doctor
        TextInputField doctorField = new TextInputField(String.format("Choose a doctor (1-%d)", doctors.size()));
        new TextInput(doctorField).read(context, "Choose a doctor from the list above.",
                (input) -> InputValidators.validateRange(input, doctors.size()));

        final Doctor selectedDoctor = doctors.get(doctorField.getOption());

        clearLines((doctors.size() + 1) * 2 + 1 + 3);
        new Title("Selected Doctor").paint(context);
        new SelectedDoctorTable(selectedDoctor).paint(context);

        return selectedDoctor;
    }

    /**
     * Prompts the patient to choose a date for the appointment.
     * 
     * @return the selected date.
     */
    private LocalDate promptChooseDate() {
        new Title("Choose Appointment Date").paint(context);
        new VSpacer(1).paint(context);

        TextInputField dateField = new TextInputField("Choose a date (dd/mm/yy)");
        new TextInput(dateField).read(context, "Enter a valid date starting from today.",
                (input) -> InputValidators.validateFutureDate(input));

        return dateField.getDate();
    }

    /**
     * Prompts the patient to choose an appointment slot.
     * 
     * @param selectedDate  the selected date for the appointment.
     * @param selectedDoctor the selected doctor for the appointment.
     * @return the selected appointment slot.
     */
    private AppointmentSlot promptChooseAppointmentSlot(LocalDate selectedDate, Doctor selectedDoctor) {
        new Title("Available Appointment Slots").paint(context);
        List<AppointmentSlot> appointmentSlots = appointmentManager.getAvailableSlotsByDoctor(selectedDate,
                selectedDoctor);
        new AppointmentSlotSelectionTable(appointmentSlots).paint(context);

        if (appointmentSlots.isEmpty()) {
            return null;
        }

        /// Choose an appointment slot
        new VSpacer(1).paint(context);
        TextInputField slotField = new TextInputField(
                String.format("Choose an appointment slot (1-%d)", appointmentSlots.size()));
        new TextInput(slotField).read(context, "Choose a slot from the list above.",
                (input) -> InputValidators.validateRange(input, appointmentSlots.size()));

        return appointmentSlots.get(slotField.getOption());
    }
}
