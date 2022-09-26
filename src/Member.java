public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    @Override
    public String toString() {
        return(fname + " " + lname + ", DOB: " + dob + ", Membership expires "
                + expire + ", Location: " + location);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return true;
        }

        Member mem = (Member) obj;
        if (this.fname == mem.fname && this.lname == mem.lname && this.dob == mem.dob) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Member member) {

        if (member == null) {
            return 1;
        }

        if (this.lname.toLowerCase().compareTo(member.lname.toLowerCase()) == 0){
            return (this.fname.toLowerCase().compareTo(member.fname.toLowerCase()));
        }

        return this.lname.toLowerCase().compareTo(member.lname.toLowerCase());
    }
}