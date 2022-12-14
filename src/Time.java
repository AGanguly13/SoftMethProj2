/**
 * Defines the times of the set fitness classes as morning and afternoon fitness class sessions.
 * Time is defined by hour and minutes and also a textual representation in a clock format.
 * This class also provides getter methods for each attribute assigned to the sessions.
 * Format is hours:minutes
 * @author Kennan Guan, Adwait Ganguly
 */
public enum Time {
    MORNING(9, 30, "9:30"),
    AFTERNOON(14,00, "14:00");

    private final int hour;
    private final int minute;
    private final String clock;

    /**
     * Constructs the Time object with hours, minutes, and a string representation.
     * @param hour the hour of day a class takes place.
     * @param minute the minutes into an hour a class takes place.
     * @param clock is a string representation of time instance
     */
    Time (int hour, int minute, String clock) {
        this.hour = hour;
        this.minute = minute;
        this.clock = clock;
    }

    /**
     * Getter method for hour.
     * @return the hour of day.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Getter method for minute.
     * @return the minute of the hour.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Getter method for the clock representation of time.
     * @return the clock representation.
     */
    public String getClock() {
        return clock;
    }
}
