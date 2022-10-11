import java.text.DecimalFormat;
/**
 * Defines the times of the set fitness classes as morning and afternoon fitness class sessions.
 * Time is defined by hour and minutes and also a textual representation in a clock format.
 * This class also provides getter methods for each attribute assigned to the sessions.
 * Format is hours:minutes
 * @author Kennan Guan, Adwait Ganguly
 */
public enum Time {
    MORNING(9, 30),
    AFTERNOON(14,00);

    private final int hour;
    private final int minute;

    /**
     * Constructs the Time object with hours, minutes, and a string representation.
     * @param hour the hour of day a class takes place.
     * @param minute the minutes into an hour a class takes place.
     */
    Time (int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
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
        DecimalFormat formatted = new DecimalFormat("#00");
        return hour + formatted.format(minute);
    }
}
