/**
 * Defines the fitness classes offered
 * @author Kennan Guan
 */
public class FitnessClass {
    private Time timeslot;
    private MemberDatabase attendance;

    public MemberDatabase getAttendance(){
        return attendance;
    }

    public void addMember(Member member){
        attendance.add(member);
    }

}
