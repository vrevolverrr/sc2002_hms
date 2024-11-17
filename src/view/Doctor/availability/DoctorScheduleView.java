package view.Doctor.availability;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.interfaces.IAppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.TimeSlot;
import model.availability.TimePeriod;
import model.users.Doctor;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Doctor.availability.widgets.DoctorScheduleTable;
import view.widgets.Title;

public class DoctorScheduleView extends View {
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);
    
    private final Doctor doctor;

    private final BuildContext context = BuildContext.unboundedVertical(110);

    /**
     * Date to view schedule for.
     */
    private LocalDate date;

    public DoctorScheduleView(Doctor doctor) {
        this.doctor = doctor;
        date = LocalDate.now();
    }

    @Override
    public String getViewName() {
        return "Doctor Schedule";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Doctor Schedule").paint(context);

        final List<LocalDate> weekDates = datesOfTheWeek();

        final List<TimeSlot> apptTimeSlots = 
            appointmentManager.getAppointments(doctor).stream()
            .filter(appointment -> weekDates.stream().anyMatch(d -> d.equals(appointment.getTimeSlot().getDate())))
            .map(appointment -> new TimeSlot(appointment.getTimeSlot().getDateTime()))
            .toList();

        final Map<DayOfWeek, TimePeriod> availableTimes = doctor.getAvailability().getGeneralAvailability();
        final Map<LocalDate, TimePeriod> specificAvailability = doctor.getAvailability().getSpecificAvailability();

        for (LocalDate d : weekDates) {
            if (specificAvailability.containsKey(d)) {
                availableTimes.put(d.getDayOfWeek(), specificAvailability.get(d));
            }
        }

        new DoctorScheduleTable(date, availableTimes, apptTimeSlots).paint(context);
        new VSpacer(1).paint(context);

        TextInputField navigateDates = new TextInputField("Enter 1 for previous week, 2 for next week, or a date (dd/mm/yy): ");
        new TextInput(navigateDates).read(context, input -> InputValidators.validatePrevNext(input) || InputValidators.validateDate(input));

        if (InputValidators.validatePrevNext(navigateDates.getValue())) {
            date = navigateDates.getValue().equals("1") ? date.minusWeeks(1) : date.plusWeeks(1);
        } else {
            date = navigateDates.getDate();
        }

        repaint();
    }

    private List<LocalDate> datesOfTheWeek() {
        List<LocalDate> weekDates = new ArrayList<LocalDate>();
        LocalDate startOfWeek = date.minusDays(date.getDayOfWeek().getValue());

        for (int i = 0; i < 7; i++) {
            weekDates.add(startOfWeek.plusDays(i));
        }

        return weekDates;
    }
    
}