package view.Doctor.availability;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.TimeSlot;
import model.availability.TimePeriod;
import model.users.Doctor;
import utils.InputValidators;
import view.View;
import view.Doctor.availability.widgets.DoctorScheduleTable;
import view.widgets.Title;

/**
 * {@link DoctorScheduleView} is a {@link View} that allows doctors to view their weekly schedule.
 * Doctors can view their availability and scheduled appointments for each day of the week.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorScheduleView extends View {
    /**
     * An instance of {@link AppointmentManager} used to manage appointments.
     */
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    
    /**
     * The {@link Doctor} for whom the view is displaying the schedule.
     */
    private final Doctor doctor;

    /**
     * The {@link BuildContext} used to render the view.
     */
    private final BuildContext context = BuildContext.unboundedVertical(110);

    /**
     * Date to view schedule for.
     */
    private LocalDate date;

    /**
     * Constructs a new {@link DoctorScheduleView} for the given doctor.
     *
     * @param doctor the {@link Doctor} whose schedule is being viewed.
     */
    public DoctorScheduleView(Doctor doctor) {
        this.doctor = doctor;
        date = LocalDate.now();
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Doctor Schedule".
     */
    @Override
    public String getViewName() {
        return "Doctor Schedule";
    }

    /**
     * Renders the "Doctor Schedule" view for the doctor.
     * <p>
     * The rendering process includes:
     * <ol>
     * <li>Displaying the breadcrumbs and title of the view.</li>
     * <li>Displaying the weekly schedule of the doctor.</li>
     * <li>Providing options to navigate to the previous or next week.</li>
     * </ol>
     */
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

    /**
     * Returns a {@link List} of {@link LocalDate} representing the dates of the week.
     *
     * @return a {@link List} of {@link LocalDate} representing the dates of the week.
     */
    private List<LocalDate> datesOfTheWeek() {
        List<LocalDate> weekDates = new ArrayList<LocalDate>();
        LocalDate startOfWeek = date.minusDays(date.getDayOfWeek().getValue());

        for (int i = 0; i < 7; i++) {
            weekDates.add(startOfWeek.plusDays(i));
        }

        return weekDates;
    }
    
}