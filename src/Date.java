/**
 * Defines the date object as day, month, and year
 * @author Kennan Guan
 */
import java.util.Calendar;
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    private int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Constructs a date object with today's date
     * Today's date is define by a year, month, and day of month
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Constructs a date object given a date in format "mm/dd/yyyy"
     * @param date a date in format "mm/dd/yyyy"
     */
    public Date(String date) {
        String[] split_date = date.split("/");
        month = Integer.parseInt(split_date[0]);
        day = Integer.parseInt(split_date[1]);
        year = Integer.parseInt(split_date[2]);
    }

    /**
     * Compares two dates for ordering and returns an integer value of -1, 0, or 1
     * -1 indicates less than, 0 indicates equality, 1 indicates greater than
     * @param date a date object with a day, month, and year
     * @return an integer value of -1, 0, or 1 when object is less than, equal to, or or greater than the passed object
     */
    @Override
    public int compareTo(Date date) {

        if(this.year > date.year)
            return 1;
        else if(this.year < date.year)
            return -1;
        else if(this.month > date.month)
            return 1;
        else if(this.month < date.month)
            return -1;
        else if(this.day > date.day)
            return 1;
        else if(this.day < date.day)
            return -1;

        return 0;
    }

    /**
     * Determines if the date object is valid
     * @return true is valid, false otherwise
     */
    public boolean isValid() {
        if(month < 1 || month > 12){
            return false;
        }

        boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

        if(month == 2 && isLeapYear){
            return days[1]+1 == day;
        }else{
            return day == days[month-1];
        }
    }
}