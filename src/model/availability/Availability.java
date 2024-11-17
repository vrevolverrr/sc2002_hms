package model.availability;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.SerializableCopyable;
import model.appointments.TimeSlot;

public class Availability implements SerializableCopyable {
    static final long serialVersionUID = 42L;

    Map<DayOfWeek, TimePeriod> general = new HashMap<>();
    Map<LocalDate, TimePeriod> specific = new HashMap<>();

    public Availability() {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                general.put(day, TimePeriod.none());
                continue;
            }

            general.put(day, TimePeriod.defaultPeriod());
        }
    }

    public void setAvailability(DayOfWeek day, TimePeriod timePeriod) {
        general.put(day, timePeriod);
    }

    public void setAvailability(LocalDate date, TimePeriod timePeriod) {
        specific.put(date, timePeriod);
    }

    public TimePeriod getAvailability(DayOfWeek day) {
        return general.get(day);
    }

    public TimePeriod getAvailability(LocalDate date) {
        if (specific.containsKey(date)) {
            return specific.get(date);
        }

        return general.get(date.getDayOfWeek());
    }

    public Map<DayOfWeek, TimePeriod> getGeneralAvailability() {
        return new HashMap<DayOfWeek, TimePeriod>(general);
    }

    public Map<LocalDate, TimePeriod> getSpecificAvailability() {
        return new HashMap<LocalDate, TimePeriod>(specific);
    }

    public boolean isAvailable(TimeSlot slot) {
        TimePeriod period = getAvailability(slot.getDate());
        return period.contains(new TimePeriod(slot.getTime(), slot.getTime().plusMinutes(30)));
    }

    @Override
    public Availability copy() {
        Availability availability = new Availability();
        availability.general = new HashMap<DayOfWeek, TimePeriod>();
        availability.specific = new HashMap<LocalDate, TimePeriod>();

        for (DayOfWeek day : general.keySet()) {
            availability.general.put(day, general.get(day));
        }

        for (LocalDate date : specific.keySet()) {
            availability.specific.put(date, specific.get(date));
        }
    
        return availability;
    }
}
