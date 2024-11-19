package model.appointments;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
* An immutable representation of a time slot for appointments, encapsulating a specific {@link LocalDateTime}.
*
* @author Bryan Soong, Joyce Lee
* @version 1.0
* @since 2024-11-16
*/
public class TimeSlot implements Serializable, Comparable<TimeSlot> {
    /**
     * The serializable class version number to verify whether the serialized object have loaded classes 
     * for that object that are compatible with respect to serialization. 
     * @see https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html.
     */
    private static final long serialVersionUID = 42L;

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
     * Checks if this {@link TimeSlot} is before another.
     * 
     * @param slot the other {@link TimeSlot} to compare.
     * @return true if this {@link TimeSlot} is before the other, false otherwise.
     */
    public boolean isBefore(TimeSlot slot) {
        return this.dateTime.isBefore(slot.getDateTime());
    }

    /**
     * Checks if this {@link TimeSlot} is before a given {@link LocalDateTime}.
     * 
     * @param dateTime the {@link LocalDateTime} to compare.
     * @return true if this {@link TimeSlot} is before the given {@link LocalDateTime}, false otherwise.
     */
    public boolean isBefore(LocalDateTime dateTime) {
        return this.dateTime.isBefore(dateTime);
    }

    /**
     * Checks if this {@link TimeSlot} is after another.
     * 
     * @param slot the other {@link TimeSlot} to compare.
     * @return true if this {@link TimeSlot} is after the other, false otherwise.
     */
    public boolean isAfter(TimeSlot slot) {
        return this.dateTime.isAfter(slot.getDateTime());
    }

    /**
     * Checks if this {@link TimeSlot} is after a given {@link LocalDateTime}.
     * 
     * @param dateTime the {@link LocalDateTime} to compare.
     * @return true if this {@link TimeSlot} is after the given {@link LocalDateTime}, false otherwise.
     */
    public boolean isAfter(LocalDateTime dateTime) {
        return this.dateTime.isAfter(dateTime);
    }

    /**
     * Compares this {@link TimeSlot} with another based on the {@link LocalDateTime}.
     * 
     * @param o the other {@link TimeSlot} to compare.
     * @return a negative integer, zero, or a positive integer as this {@link TimeSlot} is less than, equal to, or greater than the other.
     */
    @Override
    public int compareTo(TimeSlot o) {
        return this.dateTime.compareTo(o.getDateTime());
    }
}
