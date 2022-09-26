import java.util.Calendar;

/**
 * Defines the day, month, and year for the date object
 * @author Kennan
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Creates a date object with today's date
     */
    public Date() {
    } //create an object with today’s date (see Calendar class)

    /**
     * Constructs a date object given a date in format "mm/dd/yyyy"
     * @param date a date in format "mm/dd/yyyy"
     */
    public Date(String date) {
        String[] split_date = date.split("/");
        this.month = Integer.parseInt(split_date[0]);
        this.day = Integer.parseInt(split_date[1]);
        this.year = Integer.parseInt(split_date[2]);
    } //take “mm/dd/yyyy” and create a Date object

    @Override
    public int compareTo(Date date) {
        return 0;
    }
    public boolean isValid() {
        return false;
    } //check if a date is a valid calendar date
}