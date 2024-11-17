package model.availability;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.SerializableCopyable;

/**
 * Represents the availability of a user.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 * 
 */
public class Availability implements SerializableCopyable {
    static final long serialVersionUID = 42L;

    /**
     * The general availability of the user.
     */
    Map<DayOfWeek, TimePeriod> general = new HashMap<>();

    /**
     * The specific availability of the user.
     */
    Map<LocalDate, TimePeriod> specific = new HashMap<>();

    /**
     * Constructor for the {@link Availability} class.
     */
    public Availability() {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                general.put(day, TimePeriod.none());
                continue;
            }

            general.put(day, TimePeriod.defaultPeriod());
        }
    }

    /**
     * Sets the availability of the user on a specific day.
     * @param day the day of the week.
     * @param timePeriod the time period.
     */
    public void setAvailability(DayOfWeek day, TimePeriod timePeriod) {
        general.put(day, timePeriod);
    }

    /**
     * Sets the availability of the user on a specific date.
     * @param date the date.
     * @param timePeriod the time period.
     */
    public void setAvailability(LocalDate date, TimePeriod timePeriod) {
        specific.put(date, timePeriod);
    }

    /**
     * Retrieves the general availability for a given day of the week.
     * 
     * @param day the {@link DayOfWeek} for which the general availability is being fetched.
     * @return the {@link TimePeriod} representing the availability for the given day.
     */
    public TimePeriod getAvailability(DayOfWeek day) {
        return general.get(day);
    }

    /**
     * Retrieves the availability for a specific date. If a specific availability for the date exists,
     * it will be returned. Otherwise, the general availability for the corresponding day of the week is returned.
     * 
     * @param date the {@link LocalDate} for which the availability is being fetched.
     * @return the {@link TimePeriod} representing the availability for the given date.
     */
    public TimePeriod getAvailability(LocalDate date) {
        if (specific.containsKey(date)) {
            return specific.get(date);
        }

        return general.get(date.getDayOfWeek());
    }

    /**
     * Retrieves the general availability for a given day of the week.
     * 
     * @param day the {@link DayOfWeek} for which the general availability is being fetched.
     * @return the {@link TimePeriod} representing the availability for the given day.
     */
    public Map<DayOfWeek, TimePeriod> getGeneralAvailability() {
        return new HashMap<DayOfWeek, TimePeriod>(general);
    }

    /**
     * Retrieves the specific availability for a given date.
     * 
     * @param date the {@link LocalDate} for which the specific availability is being fetched.
     * @return the {@link TimePeriod} representing the availability for the given date.
     */
    public Map<LocalDate, TimePeriod> getSpecificAvailability() {
        return new HashMap<LocalDate, TimePeriod>(specific);
    }

    /**
     * Creates a copy of the current {@link Availability} instance.
     * 
     * @return the exact copy (shallow) of the {@link Availability}.
     */
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
