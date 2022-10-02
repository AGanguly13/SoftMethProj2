/**
 * Defines the times of the set fitness classes
 * Format is hours:minutes
 * @author Kennan Guan
 */
public enum Time {
    Morning(9, 30),
    Afternoon(14,0);

    private final int hour;
    private final int minute;

    Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }
}
