package com.hummingbee.utils;

import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.LocalTime;

public class TimeInterval {
    private static final Instant CONSTANT = new Instant(0);
    private final LocalTime from;
    private final LocalTime to;

    public TimeInterval(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }
    
    public TimeInterval(Interval i) {
        this.from = new LocalTime(i.getStart().getHourOfDay() + ":" + i.getStart().getMinuteOfHour() + ":00");
        this.to = new LocalTime(i.getEnd().getHourOfDay() + ":" + i.getEnd().getMinuteOfHour() + ":00");
    }

    public boolean isValid() {
        try { return toInterval() != null; } 
        catch (IllegalArgumentException e) { return false;}
    }

    public boolean overlapsWith(TimeInterval timeInterval) {
        return this.toInterval().overlaps(timeInterval.toInterval());
    }

    /**
     * @return this represented as a proper Interval
     * @throws IllegalArgumentException if invalid (to is before from)
     */
    public Interval toInterval() throws IllegalArgumentException {
        return new Interval(from.toDateTime(CONSTANT), to.toDateTime(CONSTANT));
    }
}