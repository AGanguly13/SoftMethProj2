/**
 * Defines the times of the set fitness classes
 * Format is hours:minutes
 * @author Kennan Guan
 */
public enum Time {
    PILATES(9,30),
    SPINNING(14,00),
    CARDIO(14,00);

    private final int hour;
    private final int minutes;

    Time(int hour, int minutes){
        this.hour = hour;
        this.minutes = minutes;
    }
}
