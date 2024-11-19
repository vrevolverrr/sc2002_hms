package model.availability;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.SerializableCopyable;

/**
 * An immutable representation of a time period.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class TimePeriod implements SerializableCopyable {
    /**
     * The serializable class version number to verify whether the serialized object have loaded classes 
     * for that object that are compatible with respect to serialization. 
     * @see https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html.
     */
    private static final long serialVersionUID = 42L;

    /**
     * The start time of the time period.
     */
    private final LocalTime start;

    /**
     * The end time of the time period.
     */
    private final LocalTime end;

    /**
     * Constructor for the {@link TimePeriod} class.
     * @param start the start time of the time period.
     * @param end the end time of the time period.
     */
    public TimePeriod(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a {@link TimePeriod} object representing no time period.
     * @return a {@link TimePeriod} object representing no time period.
     */
    public static TimePeriod none() {
        return new TimePeriod(LocalTime.of(0, 0), LocalTime.of(0, 0));
    }

    /**
     * Returns a {@link TimePeriod} object representing the default time period.
     * @return a {@link TimePeriod} object representing the default time period.
     */
    public static TimePeriod defaultPeriod() {
        return new TimePeriod(LocalTime.of(8, 0), LocalTime.of(18, 0));
    }

    /**
     * Gets the start time of the time period.
     * @return the start time of the time period.
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Gets the end time of the time period.
     * @return the end time of the time period.
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Gets the formatted period.
     * @return the formatted period.
     */
    public String getFormattedPeriod() {
        if (this.equals(TimePeriod.none())) {
            return "N/A";
        }

        return start.format(DateTimeFormatter.ofPattern("HH:mm")) + 
        "-" + end.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Checks if the time period contains a given time.
     * @param time the time to check.
     * @return {@code true} if the time period contains the given time, {@code false} otherwise.
     */
    public boolean contains(LocalTime time) {
        if (start.equals(LocalTime.of(0, 0)) && end.equals(LocalTime.of(0, 0))) {
            return false;
        }

        return !time.isBefore(start) && !time.isAfter(end);
    }

    /**
     * Checks if the time period contains another time period.
     * @param period the time period to check.
     * @return {@code true} if the time period contains the given time period, {@code false} otherwise.
     */
    public boolean contains(TimePeriod period) {
        return contains(period.start) && contains(period.end);
    }

    /**
     * Checks if the time period overlaps with another time period.
     * @param obj the time period to check.
     * @return {@code true} if the time period overlaps with the given time period, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimePeriod)) {
            return false;
        }

        TimePeriod other = (TimePeriod) obj;
        return start.equals(other.start) && end.equals(other.end);
    }

    /**
     * Creates a copy of the time period.
     * @return a copy of the time period.
     */
    @Override
    public TimePeriod copy() {
        return new TimePeriod(getStart(), getEnd());
    }
}
