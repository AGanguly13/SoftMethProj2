import java.sql.Array;
import java.util.ArrayList;
/**
 * Defines the fitness classes offered.
 * Each fitness class consists of a Time enum instance, an instructor String instance variable,
 * a name String instance variable, and a MemberDatabase instance.
 * Methods that occur in the FitnessClass class include getter methods, a method to check if a fitness
 * class has no participants, and methods to add or remove participants from a fitness class.
 * @author Kennan Guan, Adwait Ganguly
 */
public class FitnessClass {
    private Time time;
    private String instructor;
    private String name;
    private Location location;
    private ArrayList<Member> attendance;
    private int guests = 0;

    /**
     * Constructs a fitness class given a time of day, instructor name, and class name.
     * @param time A Time object which specifies morning or afternoon timeslot.
     * @param instructor The name of the instructor for the class.
     * @param name The type of class being offered.
     */
    public FitnessClass(Time time, String instructor, String name, Location location) {
        this.time = time;
        this.instructor = instructor;
        this.name = name;
        this.location = location;
        attendance = new ArrayList<>();
    }

    /**
     * Getter method for the number of guests in this fitness class.
     * @return the number of guests
     */
    public int getGuests() {
        return guests;
    }

    /**
     * Adds one guest into this fitness class.
     */
    public void addGuest() {
        guests++;
    }

    /**
     * Removes one guest from this fitness class.
     */
    public void removeGuest(){
        guests--;
    }

    /**
     * Getter method for the name of the class.
     * @return the name of the class.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the instructor of the class.
     * @return the name of the instructor.
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Getter method for the Time object of the class.
     * @return the Time object.
     */
    public Time getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Getter method for the member database of the class.
     * @return the member database.
     */
    public ArrayList<Member> getAttendance() {
        return attendance;
    }

    /**
     * Returns whether the member database for the class is empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return attendance.isEmpty();
    }

    /**
     * Adds a member into the member database and returns if the member was added.
     * If member is a duplicate, false is returned
     * @param member the member object to be added.
     * @return true if the member was successfully added and is not a duplicate, false otherwise.
     */
    public boolean addMember(Member member) {
        if (attendance.contains(member)){
            return false;
        }
        return attendance.add(member);
    }

    /**
     * Remove a member from the member database and returns if the member was removed.
     * @param member the member object to be removed.
     * @return true if the member was successfully removed, false otherwise.
     */
    public boolean removeMember(Member member) {
        return attendance.remove(member);
    }

    /**
     * Overrides the toString method to print a textual representation of fitness class.
     * Prints name, instructor, time, and location.
     * @return a textual representation of fitness class as "name - instructor, hh:mm, location"
     */
    @Override
    public String toString() {
        return name + " - " + instructor + ", " + time.getClock( ) + ", " + location;
    }

}
