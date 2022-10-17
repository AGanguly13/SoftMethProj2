/**
 * @author Adwait Ganguly, Kennan Guan
 */
public class Premium extends Family {

    private static final int ANNUALFEEMONTHS = 11;

    private static final int PREMIUMPASSES = 3;

    private static final int SETEXPIREYEARLATER = 13;

    /**
     * Creates a new premium member object
     * @param newPremiumMember
     */
    public Premium (String newPremiumMember) {
        super(newPremiumMember, PREMIUMPASSES, SETEXPIREYEARLATER);
    }

    /**
     * Overrides membershipFee for premium member
     * @return
     */
    @Override
    public double membershipFee() {
        return getFamilyMonthlyFee() * ANNUALFEEMONTHS;
    }

    /**
     * Overrides toString method in family class
     * @return
     */
    @Override
    public String toString() {
        return(this.getFname() + " " + this.getLname() + ", DOB: " + this.getDob().toString() + ", Membership expires "
                + this.getExpire().toString() + ", Location: " + this.getLocation() +", (Premium) Guess-pass remaining: " + this.getGuestPasses());
    }

    /**
     * Super call to the equals method
     * @param obj an object that is not necessarily a Member object, and is used for comparison.
     * @return
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Super call to the compareTo method
     * @param member the object to be compared.
     * @return
     */
    public int compareTo(Member member) {
        return super.compareTo(member);
    }

}
