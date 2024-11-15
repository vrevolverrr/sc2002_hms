package view.Patient.appointments;

import java.time.LocalDate;
import java.util.List;

import controller.AppointmentManager;
import controller.DoctorManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.AppointmentSlot;
import model.users.Doctor;
import model.users.Patient;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Patient.appointments.widgets.AppointmentScheduledStatus;
import view.Patient.appointments.widgets.AppointmentSlotSelectionTable;
import view.Patient.appointments.widgets.DoctorsSelectionTable;
import view.Patient.appointments.widgets.SelectedDoctorTable;
import view.widgets.Title;

public class PatientScheduleAppointmentView extends View {
    final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class); 
    final DoctorManager doctorManager = DoctorManager.getInstance(DoctorManager.class);
    
    private final Patient patient;

    public PatientScheduleAppointmentView(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getViewName() {
        return "Schedule Appointment";
    }
    
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Schedule Appointment").paint(context);
        new VSpacer(1).paint(context);

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

        // Schedule the appointment
        appointmentManager.scheduleAppointment(appointmentSlot, patient);

        /// Print the details of the newly scheduled appointment
        clear();
        new Breadcrumbs().paint(context);
        new Title("Schedule Appointment").paint(context);
        new VSpacer(1).paint(context);
        AppointmentScheduledStatus.scheduled(appointmentSlot).paint(context);

        new Pause().pause(context);
        Navigator.pop();
    }

    private Doctor promptChooseDoctor() {
        new Title("Available Doctors").paint(context);
        List<Doctor> doctors = doctorManager.getAllDoctors();
        new DoctorsSelectionTable(doctors).paint(context);
        new VSpacer(1).paint(context);

        /// Choose a doctor
        TextInputField doctorField = new TextInputField(String.format("Choose a doctor (1-%d)", doctors.size()));
        new TextInput(doctorField).read(context, "Choose a doctor from the list above.", (input) -> InputValidators.validateRange(input, doctors.size()));

        final Doctor selectedDoctor = doctors.get(doctorField.getOption());

        clearLines((doctors.size() + 1) * 2 + 1 + 3);
        new Title("Selected Doctor").paint(context);
        new SelectedDoctorTable(selectedDoctor).paint(context);

        return selectedDoctor;
    }

    private LocalDate promptChooseDate() {
        new Title("Choose Appointment Date").paint(context);
        new VSpacer(1).paint(context);

        TextInputField dateField = new TextInputField("Choose a date (dd/mm/yy)");
        new TextInput(dateField).read(context, "Enter a valid date starting from today.", 
            (input) -> InputValidators.validateDate(input));

        return dateField.getDate();
    }

    private AppointmentSlot promptChooseAppointmentSlot(LocalDate selectedDate, Doctor selectedDoctor) {
        new Title("Available Appointment Slots").paint(context);
        List<AppointmentSlot> appointmentSlots = appointmentManager.getAvailableSlotsByDoctor(selectedDate, selectedDoctor);
        new AppointmentSlotSelectionTable(appointmentSlots).paint(context);

        if (appointmentSlots.isEmpty()) {
            return null;
        }

        /// Choose an appointment slot
        new VSpacer(1).paint(context);
        TextInputField slotField = new TextInputField(String.format("Choose an appointment slot (1-%d)", appointmentSlots.size()));
        new TextInput(slotField).read(context, "Choose a slot from the list above.", (input) -> InputValidators.validateRange(input, appointmentSlots.size()));

        return appointmentSlots.get(slotField.getOption());
    }
}
