package view.Doctor.availability.widgets;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.appointments.TimeSlot;
import model.availability.TimePeriod;

/**
 * The {@code DoctorScheduleTable} class is a {@link Widget} that generates a weekly schedule table 
 * for a doctor. The table displays the availability and scheduled appointments for each day of the week.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorScheduleTable extends Widget {

    /**
     * The date of the schedule table.
     */
    private final LocalDate date;

    /**
     * A {@link Map} of {@link DayOfWeek} to {@link TimePeriod} representing the available times for each day of the week.
     */
    private final Map<DayOfWeek, TimePeriod> availableTimes;

    /**
     * A {@link List} of {@link TimeSlot} representing the scheduled appointment slots.
     */
    private final List<TimeSlot> scheduledAppointmentSlots;

    /**
     * Constructs a new {@code DoctorScheduleTable} with the given date, available times, and scheduled appointment slots.
     * 
     * @param date the date of the schedule table.
     * @param availableTimes a {@link Map} of {@link DayOfWeek} to {@link TimePeriod} representing the available times for each day of the week.
     * @param scheduledAppointmentSlots a {@link List} of {@link TimeSlot} representing the scheduled appointment slots.
     */
    public DoctorScheduleTable(LocalDate date, Map<DayOfWeek, TimePeriod> availableTimes, List<TimeSlot> scheduledAppointmentSlots) {
        this.date = date;
        this.availableTimes = availableTimes;
        this.scheduledAppointmentSlots = scheduledAppointmentSlots;
    }

    /**
     * Builds the UI for displaying the doctor's weekly schedule in a table format. The table contains:
     * <ul>
     *   <li>Time slots</li>
     *   <li>Availability status for each time slot on each day of the week</li>
     * </ul>
     * 
     * @param context the {@link BuildContext} used for rendering the widget.
     * @return a {@link String} representing the built table UI.
     */
    @Override
    public String build(BuildContext context) {
        // TableRow header = new TableRow("Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        
        List<String> headerRow = new ArrayList<String>();
        headerRow.add("Time");

        // Start at 1st day of the week (Monday)
        final LocalDate[] current = {date.minusDays(date.getDayOfWeek().getValue() - 1)};

        for (DayOfWeek day : DayOfWeek.values()) {
            headerRow.add(mapDayToString(day)
                + " (" + current[0].plusDays(day.getValue() - 1).format(DateTimeFormatter.ofPattern("dd/MM")) + ")");
        }

        TableRow header = new TableRow(headerRow.toArray(String[]::new));

        int START_HOUR = AppointmentManager.START_HOUR;
        int INTERVAL = AppointmentManager.SLOT_DURATION;

        final DayOfWeek[] daysOfWeek = DayOfWeek.values();

        List<String[]> rows = new ArrayList<String[]>();

        // For each day of the week
        for (int i = 0; i < 7; i++) {
            // For each time slot hour of the day
            final LocalTime[] time = {LocalTime.of(START_HOUR, 0)};
            for (int j = 0; j < 20; j++) {
                TimePeriod timePeriod = 
                    new TimePeriod(time[0], time[0].plusMinutes(INTERVAL));
                
                boolean isAvailable = availableTimes.get(daysOfWeek[i]).contains(timePeriod);
                boolean hasAppt = scheduledAppointmentSlots.stream()
                    .anyMatch(slot -> slot.getDateTime().equals(LocalDateTime.of(current[0], time[0])));

                if (rows.size() <= j) {
                    rows.add(new String[8]);
                    rows.get(j)[0] = time[0].format(DateTimeFormatter.ofPattern("HH:mm"));
                }

                if (hasAppt) {
                    rows.get(j)[i + 1] = "Booked";
                } else if (!isAvailable) {
                    rows.get(j)[i + 1] = "N/A";
                } else {
                    rows.get(j)[i + 1] = "";
                }

                time[0] = time[0].plusMinutes(INTERVAL);
            }

            current[0] = current[0].plusDays(1);
        }

        TableRow[] tableRows = new TableRow[rows.size() + 1];
        tableRows[0] = header;

        for (int k = 0; k < rows.size(); k++) {
            tableRows[k + 1] = new TableRow(rows.get(k));
        }

        return new Table(tableRows).build(context);
    }
    
    /**
     * Maps a {@link DayOfWeek} to a {@link String} representation.
     * 
     * @param day the day of the week.
     * @return a {@link String} representing the day of the week.
     */
    private String mapDayToString(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return "Mon";
            case TUESDAY:
                return "Tue";
            case WEDNESDAY:
                return "Wed";
            case THURSDAY:
                return "Thu";
            case FRIDAY:
                return "Fri";
            case SATURDAY:
                return "Sat";
            case SUNDAY:
                return "Sun";
            default:
                return "";
        }
    }
}
