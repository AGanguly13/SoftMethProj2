/**
 * Defines the fitness classes offered
 * @author Kennan Guan, Adwait Ganguly
 */
public class FitnessClass {
    private Time time;
    private String instructor;
    private String name;
    private MemberDatabase attendance;

    /**
     * Constructs a fitness class given a time of day, instrucotr name, and class name
     * @param time A Time object which specifies morning or afternnon timeslot
     * @param instructor The name of the instructor for the class
     * @param name The type of class being offered
     */
    public FitnessClass(Time time, String instructor, String name) {
        this.time = time;
        this.instructor = instructor;
        this.name = name;
        attendance = new MemberDatabase();
    }

    /**
     * Getter method for the name of the class
     * @return the name of the class
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the instructor of the class
     * @return the name of the instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Getter method for the Time object of the class
     * @return the Time object
     */
    public Time getTime() {
        return time;
    }

    /**
     * Getter method for the member datatbase of the class
     * @return the member database
     */
    public MemberDatabase getAttendance() {
        return attendance;
    }

    /**
     * Returns whether the member database for the class is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return attendance.isEmpty();
    }

    /**
     * Adds a member into the member database and returns if the member was added
     * Will return false if the member already is in the database
     * @param member the member object to be added
     * @return true if the member was successfully added, false otherwise
     */
    public boolean addMember(Member member) {
        return attendance.add(member);
    }

    /**
     * Remove a member from the member datatbase and returns if the member was removed
     * Will return false if the member does not exist
     * @param member the member object to be removed
     * @return true if the member was successfully removed, false otherwise
     */
    public boolean removeMember(Member member) {
        return attendance.remove(member);
    }

}
