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

public class DoctorScheduleTable extends Widget {
    private final LocalDate date;
    private final Map<DayOfWeek, TimePeriod> availableTimes;
    private final List<TimeSlot> scheduledAppointmentSlots;

    public DoctorScheduleTable(LocalDate date, Map<DayOfWeek, TimePeriod> availableTimes, List<TimeSlot> scheduledAppointmentSlots) {
        this.date = date;
        this.availableTimes = availableTimes;
        this.scheduledAppointmentSlots = scheduledAppointmentSlots;
    }

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
    
    private String mapDayToString(DayOfWeek day) {
        switch (day) {
            case DayOfWeek.MONDAY:
                return "Mon";
            case DayOfWeek.TUESDAY:
                return "Tue";
            case DayOfWeek.WEDNESDAY:
                return "Wed";
            case DayOfWeek.THURSDAY:
                return "Thu";
            case DayOfWeek.FRIDAY:
                return "Fri";
            case DayOfWeek.SATURDAY:
                return "Sat";
            case DayOfWeek.SUNDAY:
                return "Sun";
            default:
                return "";
        }
    }
}
