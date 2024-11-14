package model.availability;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Availability implements Serializable {
    static final long serialVersionUID = 42L;

    Map<DayOfWeek, TimePeriod> general = new HashMap<>();
    Map<LocalDate, TimePeriod> specific = new HashMap<>();

    public Availability() {
        for (DayOfWeek day : DayOfWeek.values()) {
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
        return Map.copyOf(general);
    }

    public Map<LocalDate, TimePeriod> getSpecificAvailability() {
        return Map.copyOf(specific);
    }

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
