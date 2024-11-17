package model.appointments;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a time slot for appointments, encapsulating a specific {@link LocalDateTime}.
*
* @author Bryan Soong, Joyce Lee
* @version 1.0
* @since 2024-11-16
*/
public class TimeSlot implements Serializable, Comparable<TimeSlot> {
    static final long serialVersionUID = 42L;

    /**
     * The {@link LocalDateTime} representing the time slot.
     */
    private final LocalDateTime dateTime;

     /**
     * Constructs an instance of {@link TimeSlot}.
     * 
     * @param dateTime the {@link LocalDateTime} for this time slot.
     */
    public TimeSlot(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the {@link LocalDateTime} of the time slot.
     * 
     * @return the {@link LocalDateTime} representing the time slot.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Gets the {@link LocalDate} of the time slot.
     * 
     * @return the {@link LocalDate} extracted from the time slot.
     */
    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    /**
     * Gets the {@link LocalTime} of the time slot.
     * 
     * @return the {@link LocalTime} extracted from the time slot.
     */
    public LocalTime getTime() {
        return this.dateTime.toLocalTime();
    }

    /**
     * Gets the formatted date of the time slot.
     * 
     * @return a {@link String} representing the date in the format "dd/MM/yy".
     */
    public String getFormattedDate() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
    }

    /**
     * Gets the formatted time of the time slot.
     * 
     * @return a {@link String} representing the time in the format "hh:mma".
     */
    public String getFormattedTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("hh:mma"));
    }

    /**
     * Gets the formatted date and time of the time slot.
     * 
     * @return a {@link String} representing the date and time in the format "dd/MM/yy hh:mma".
     */
    public String getFormattedDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy hh:mma"));
    }

    /**
     * Compares this {@link TimeSlot} with another to determine if they are equal.
     * 
     * @param slot the other {@link TimeSlot} to compare.
     * @return true if the {@link LocalDateTime} values are the same, false otherwise.
     */
    public boolean equals(TimeSlot slot) {
        return this.dateTime.equals(slot.getDateTime());
    }

    /**
     * Compares this {@link TimeSlot} with another for ordering.
     * 
     * @param o the other {@link TimeSlot} to compare.
     * @return a negative integer, zero, or a positive integer if this {@link TimeSlot} is 
     *         earlier than, equal to, or later than the specified {@link TimeSlot}.
     */
    @Override
    public int compareTo(TimeSlot o) {
        return this.dateTime.compareTo(o.getDateTime());
    }
}
