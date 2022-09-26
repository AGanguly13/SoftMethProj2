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
     * Constructs a date object with today's date
     * Today's date is define by a year, month, and day of month
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH);
        this.day = today.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Constructs a date object given a date in format "mm/dd/yyyy"
     * @param date a date in format "mm/dd/yyyy"
     */
    public Date(String date) {
        String[] split_date = date.split("/");
        this.month = Integer.parseInt(split_date[0]);
        this.day = Integer.parseInt(split_date[1]);
        this.year = Integer.parseInt(split_date[2]);
    }

    /**
     * Compares two dates for ordering and returns an integer value of -1, 0, or 1
     * -1 indicates less than, 0 indicates equality, 1 indicates greater than
     * @param date a date object with a day, month, and year
     * @return an integer value of -1, 0, or 1 when object is less than, equal to, or or greater than the passed object
     */
    @Override
    public int compareTo(Date date) {
        if(this.year > date.year) return 1;
        else if(this.year < date.year) return -1;
        else if(this.month > date.month) return 1;
        else if(this.month < date.month) return -1;
        else if(this.day > date.day) return 1;
        else if(this.day < date.day) return -1;

        return 0;
    }
    public boolean isValid() {
        return false;
    } //check if a date is a valid calendar date
}