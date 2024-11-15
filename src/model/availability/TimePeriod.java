package model.availability;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.SerializableCopyable;

public class TimePeriod implements SerializableCopyable {
    static final long serialVersionUID = 42L;

    private final LocalTime start;
    private final LocalTime end;

    public TimePeriod(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public static TimePeriod none() {
        return new TimePeriod(LocalTime.of(0, 0), LocalTime.of(0, 0));
    }

    public static TimePeriod defaultPeriod() {
        return new TimePeriod(LocalTime.of(8, 0), LocalTime.of(18, 0));
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getFormattedPeriod() {
        if (this.equals(TimePeriod.none())) {
            return "N/A";
        }

        return start.format(DateTimeFormatter.ofPattern("HH:mm")) + 
        "-" + end.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public boolean contains(LocalTime time) {
        if (start.equals(LocalTime.of(0, 0)) && end.equals(LocalTime.of(0, 0))) {
            return false;
        }

        return !time.isBefore(start) && !time.isAfter(end);
    }

    public boolean contains(TimePeriod period) {
        return contains(period.start) && contains(period.end);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimePeriod)) {
            return false;
        }

        TimePeriod other = (TimePeriod) obj;
        return start.equals(other.start) && end.equals(other.end);
    }

    @Override
    public TimePeriod copy() {
        return new TimePeriod(getStart(), getEnd());
    }
}
