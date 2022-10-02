/**
 * Defines the fitness classes offered
 * @author Kennan Guan
 */
public class FitnessClass {
    private Time time;
    private String instructor;
    private MemberDatabase attendance;

    public FitnessClass(Time time, String instructor){
        this.time = time;
        this.instructor = instructor;
        attendance = new MemberDatabase();
    }

    public MemberDatabase getAttendance(){
        return attendance;
    }

    public void addMember(Member member){
        attendance.add(member);
    }

}
