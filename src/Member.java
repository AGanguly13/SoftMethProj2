/**
 * Defines a member object to be a part of a gym
 * Implements compareTo(), toString(), and equals() methods for comparing and representing members
 * @author Adwait Ganguly
 */
public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public int getLongerName (String name1, String name2) {
        if (name2.length() > name1.length()) {
            return name2.length();
        }
        else {
            return name1.length();
        }
    }

    /**
     * Constructs a textual representation of a Member object
     * @return a String in the format: "firstName lastname, DOB: mm/dd/yyyy, Membership expires
     * mm/dd/yyyy, Location: City, Zip Code, County"
     */
    @Override
    public String toString() {
        return(fname + " " + lname + ", DOB: " + dob + ", Membership expires "
                + expire + ", Location: " + location);
    }

    /**
     * Checks if two objects are of the same class or if either is null, then checks equality
     * @param obj an object that is not necessarily a Member object, used for comparison
     * @return true if first name, last name, and DOB of objects are all equal,
     * otherwise returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Member mem = (Member) obj;
        if (this.fname == mem.fname && this.lname == mem.lname && this.dob == mem.dob) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param member is the member object whose last and first name will be compared to this object
     * @return positive number, 0, or negative number based on if this object's name is
     * alphabetically greater than, equal to, or less than the member parameters name, respectively
     */
    @Override
    public int compareTo(Member member) {
        if (member == null) {
            return 1;
        }

        // variable that will be assigned the longer last or first name
        int maxLength = getLongerName(this.lname, member.lname);
        char[] thisLastName = this.lname.toCharArray();
        char[] memberLastName = member.lname.toCharArray();
        for (int x = 0; x < maxLength; x++) {
            if (thisLastName[x] != memberLastName[x]) {
                return thisLastName[x] - memberLastName[x];
            }
        }

        maxLength = getLongerName(this.fname, member.fname);
        char[] thisFirstName = this.fname.toCharArray();
        char[] memberFirstName = member.fname.toCharArray();
        for (int y = 0; y < maxLength; y++) {
            if (thisFirstName[y] != memberFirstName[y]) {
                return thisFirstName[y] - memberFirstName[y];
            }
        }
        return 0;
    }
}