/**
 * Defines a member object to be a part of a gym.
 * Implements compareTo(), toString(), and equals() methods for comparing and representing members.
 * @author Adwait Ganguly, Kennan Guan
 */
public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    /**
     * Constructs a member object based on String parsed from the command line and then split.
     * @param newMember is the string from the command line detailing the member information.
     */
    public Member (String newMember) {
        String[] splitMember = newMember.split(" ");
        this.fname = splitMember[0];
        this.lname = splitMember[1];
        this.dob = new Date(splitMember[2]);
        this.expire = new Date(splitMember[3]);
        this.location = Location.valueOf(splitMember[4].toUpperCase());
    }

    /**
     * This is the constructor used to create a Member object that will be removed from the
     * database.
     * @param firstName is the first name of the member.
     * @param lastName is the last name of the member.
     * @param DOB is the date of birth of the member.
     */
    public Member (String firstName, String lastName, String DOB) {
        this.fname = firstName;
        this.lname = lastName;
        this.dob = new Date(DOB);
        this.expire = null;
    }

    /**
     * Getter method for Member first name.
     * @return a String representing the first name of the Member.
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * Getter method for Member last name.
     * @return a String representing the last name of the Member.
     */
    public String getLname() {
        return this.lname;
    }

    /**
     * Getter method for Member date of birth.
     * @return a Date object representing the member's date of birth.
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * Getter method for Member's gym membership expiration date.
     * @return a Date object representing the member's date of membership expiration.
     */
    public Date getExpire() {
        return this.expire;
    }

    /**
     * Getter method for Member's gym location.
     * @return a Location object representing the member's gym location.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Helper method for the compareTo method that compares the lengths of either the first or last
     * names of a member.
     * @param name1 is either the first or last name of this member object.
     * @param name2 is either the first or last name of the member object being compared.
     * @return the integer length of the longest name out of the two being compared.
     */
    private int getLongerName (String name1, String name2) {
        if (name2.length() > name1.length()) {
            return name2.length();
        }
        else {
            return name1.length();
        }
    }

    /**
     * Constructs a textual representation of a Member object.
     * @return a String in the format: "firstName lastname, DOB: mm/dd/yyyy, Membership expires
     * mm/dd/yyyy, Location: City, Zip Code, County".
     */
    @Override
    public String toString() {
        return(fname + " " + lname + ", DOB: " + dob.toString() + ", Membership expires "
                + expire.toString() + ", Location: " + location);
    }

    /**
     * Checks if two objects are of the same class or if either is null, then checks equality.
     * @param obj an object that is not necessarily a Member object, and is used for comparison.
     * @return true if first name, last name, and DOB of objects are all equal,
     * otherwise returns false.
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
        if (this.fname.equalsIgnoreCase(mem.fname.toLowerCase()) && this.lname.equalsIgnoreCase(mem.lname.toLowerCase())
                && this.dob.compareTo(mem.dob) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Compares two members based on last name alphabetically, and then first name alphabetically.
     * @param member is the member object whose last and first name will be compared to this member
     * object.
     * @return positive number, 0, or negative number based on if this object's name is
     * alphabetically greater than, equal to, or less than the member parameters name, respectively.
     */
    @Override
    public int compareTo(Member member) {
        if (member == null) {
            return 1;
        }

        int maxLength = getLongerName(this.lname, member.lname); // variable that will be assigned the longer last or first name
        char[] thisLastName = this.lname.toCharArray();
        char[] memLastName = member.lname.toCharArray();
        for (int x = 0; x < maxLength; x++) {
            if (Character.toLowerCase(thisLastName[x]) != Character.toLowerCase(memLastName[x])) {
                return thisLastName[x] - memLastName[x];
            }
        }

        maxLength = getLongerName(this.fname, member.fname);
        char[] thisFirstName = this.fname.toCharArray();
        char[] memFirstName = member.fname.toCharArray();
        for (int y = 0; y < maxLength; y++) {
            if (Character.toLowerCase(thisFirstName[y]) != Character.toLowerCase(memFirstName[y])) {
                return thisFirstName[y] - memFirstName[y];
            }
        }
        return 0;
    }
}