/**
 * @author Adwait Ganguly, Kennan Guan
 */
public class Family extends Member {
    private int guestPasses;

    private static final double FAMILYMONTHLYFEE = 59.99; //static final constant for family monthly fee

    private static final int EXPIREINTHREEMONTHS = 4;

    /**
     * Constructor for a new family member
     * @param newFamilyMem
     */
    public Family (String newFamilyMem) {
        super(newFamilyMem.split(" ")[0], newFamilyMem.split(" ")[1], newFamilyMem.split(" ")[2], newFamilyMem.split(" ")[3], EXPIREINTHREEMONTHS);
        this.guestPasses = 1;
    }

    /**
     * Constructor for a new premium member
     * @param newFamMem
     * @param premiumPasses
     * @param expireMonthsLater
     */
    public Family (String newFamMem, int premiumPasses, int expireMonthsLater) {
        super(newFamMem.split(" ")[0], newFamMem.split(" ")[1], newFamMem.split(" ")[2], newFamMem.split(" ")[3], expireMonthsLater);
        this.guestPasses = premiumPasses;
    }


    /**
     * Method that represents a guest pass being used, thus decreasing the objects guest pass count by one
     * I want this to be inherited by the premium class which will be done automatically
     */
    public void useGuestPass() {
        this.guestPasses--;
    }

    /**
     * Getter for guest passes
     * @return
     */
    public int getGuestPasses() {
        return this.guestPasses;
    }

    /**
     * Gets and returns private family monthly fee constant variable
     * @return
     */
    public double getFamilyMonthlyFee() {
        return FAMILYMONTHLYFEE;
    }

    /**
     * Overridden membership fee method
     * @return
     */
    @Override
    public double membershipFee() {
        return getOneTimeFee() + (getQuarterly() * FAMILYMONTHLYFEE);
    }

    /**
     * Overridden member toString method to include guestPasses printing
     * @return
     */
    @Override
    public String toString() {
        return(super.toString() + " (Family) guest-pass remaining: " + guestPasses);
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
