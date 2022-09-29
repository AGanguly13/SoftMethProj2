/**
 * Defines the times of the set fitness classes
 * Format is hours:minutes
 * @author Kennan Guan
 */
public enum Time {
    PILATES("9:30", "Jennifer"),
    SPINNING("14:00", "Denise"),
    CARDIO("14:00", "Kim");

    private final String time;
    private final String instructor;

    Time(String time, String instructor){
        this.time = time;
        this.instructor = instructor;
    }

    public String getTime(){
        return time;
    }

    public String getInstructor(){
        return instructor;
    }
}
