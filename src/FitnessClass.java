/**
 * Defines the fitness classes offered
 * @author Kennan Guan
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

    public String getName(){
        return name;
    }

    public String getInstructor(){
        return instructor;
    }

    public Time getTime(){
        return time;
    }

    public MemberDatabase getAttendance(){
        return attendance;
    }

    public boolean isEmpty(){
        return attendance.isEmpty();
    }

    public boolean addMember(Member member) {
        return attendance.add(member);
    }

    public boolean removeMember(Member member){
        return attendance.remove(member);
    }
}
