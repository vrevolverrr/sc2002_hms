package view.Patient.appointments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import controller.AppointmentManager;
import controller.DoctorManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.appointments.AppointmentSlot;
import model.users.Doctor;
import model.users.Patient;
import services.InputValidators;
import services.Navigator;
import view.View;
import view.Patient.appointments.widgets.AppointmentScheduledStatus;
import view.Patient.appointments.widgets.AppointmentSlotSelectionTable;
import view.Patient.appointments.widgets.DoctorsSelectionTable;

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
        new Align(Alignment.CENTER, new Text("[ Schedule Appointment ]", TextStyle.BOLD)).paint(context);
        new VSpacer(1).paint(context);

        /// Print table of doctors to choose from
        new Align(Alignment.CENTER, new Text("[ Available Doctors ]", TextStyle.BOLD)).paint(context);
        List<Doctor> doctors = doctorManager.getAllDoctors();
        new DoctorsSelectionTable(doctors).paint(context);

        /// Choose a doctor
        TextInputField doctorField = new TextInputField("Choose a doctor");
        new TextInput(doctorField).read(context, "Choose a doctor from the list above.", (input) -> InputValidators.validateRange(input, doctors.size()));

        final Doctor selectedDoctor = doctors.get(Integer.parseInt(doctorField.getValue()) - 1);
        new VSpacer(1).paint(context);

        /// Choose a date
        new Align(Alignment.CENTER, new Text("[ Choose Appointment Date ]", TextStyle.BOLD)).paint(context);
        new VSpacer(1).paint(context);

        TextInputField dateField = new TextInputField("Choose a date (dd/mm/yy)");
        new TextInput(dateField).read(context, "Enter a valid date starting from today.", 
            (input) -> InputValidators.validateDate(input));

        final LocalDate selectedDate = LocalDate.parse(dateField.getValue(), DateTimeFormatter.ofPattern("dd/MM/yy"));

        /// Print table of available appointment slots
        new Align(Alignment.CENTER, new Text("[ Available Appointment Slots ]", TextStyle.BOLD)).paint(context);
        List<AppointmentSlot> appointmentSlots = appointmentManager.getAvailableSlotsByDoctor(selectedDate, selectedDoctor);
        new AppointmentSlotSelectionTable(appointmentSlots).paint(context);

        /// Choose an appointment slot
        TextInputField slotField = new TextInputField("Choose an appointment slot");
        new TextInput(slotField).read(context, "Choose a slot from the list above.", (input) -> InputValidators.validateRange(input, appointmentSlots.size()));

        final AppointmentSlot appointmentSlot = appointmentSlots.get(slotField.getOption());
        
        // Schedule the appointment
        appointmentManager.scheduleAppointment(appointmentSlot, patient);

        /// Print the details of the newly scheduled appointment
        AppointmentScheduledStatus.scheduled(appointmentSlot).paint(context);

        new Pause().pause(context);
        Navigator.pop();
    }
}
