/**
 * Defines the Member Database for the entire fitness chain.
 * The database is implemented as a resizable array, and the class implements methods to increase
 * the size of the array, add or remove members from the array, and print the array as is or sorted
 * by location, expiration date, or name.
 *
 * @author Adwait Ganguly
 */
public class MemberDatabase {
    private Member [] mlist;
    private int size;

    private static final int NOT_FOUND = -1; //constant used when Member is not found in Database

    /**
     * Default constructor that creates a Member Database as an array of length 4 with the size
     * variable set to 0, given that there are initially no members in the database.
     */
    public MemberDatabase() {
        this.mlist = new Member[4];
        this.size = 0;
    }

    /**
     * Searches for the parameter Member object in the database array.
     * @param member is the member that is being searched for in the database.
     * @return the index of the member if found, otherwise return constant NOT_FOUND.
     */
    private int find(Member member) {
        for (int x = 0; x < size; x++) {
            if (this.mlist[x].equals(member)) { //this .equals needs to be changed it's currently the Object superclass .equals()
                return x;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Increases the size of the array by 4. Does so by copying the elements of the Member Database
     * array to a new array that has an increased length.
     */
    private void grow() {
        Member[] copy = new Member[this.mlist.length + 4];
        for (int x = 0; x < this.size; x++) {
            copy[x] = this.mlist[x];
        }
        this.mlist = copy;
    }

    /**
     *
     * @param member is the member that is intended to be added to the database.
     * @return true if the member was not initially in the database and was then successfully added,
     * otherwise this method returns false.
     */
    public boolean add(Member member) {
        boolean wasAdded = false;
        if (find(member) < 0) {
            this.mlist[this.size] = member;
            this.size++;
            wasAdded = true;
        }

        if (this.mlist.length == this.size) {
            grow();
        }

        if (wasAdded) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param member is the member that is a candidate to be removed from the database.
     * @return true if the member is in the database and is successfully removed, false otherwise.
     */
    public boolean remove(Member member) {
        boolean wasRemoved = false;
        int memberToRemoveIndex = find(member);

        if (memberToRemoveIndex >= 0) {
            this.mlist[memberToRemoveIndex] = null;
            wasRemoved = true;
            for (int c = memberToRemoveIndex; c < this.size - 1; c++) {
                this.mlist[c] = this.mlist[c + 1];
            }
            this.size--;
        }

        if (wasRemoved) {
            return true;
        }
        return false;
    }

    /**
     * Prints the member database as is, with each Member object being printed out on a new line.
     * Uses the Member class toString() method to print out all necessary Member object information.
     */
    public void print () {
        for (int x = 0; x < this.size; x++){
            System.out.println(this.mlist[x].toString());//this needs to implement the member toString, currently implements Object superclass
        }
    } //print the array contents as is
    public void printByCounty() { } //sort by county and then zipcode
    public void printByExpirationDate() { } //sort by the expiration date
    public void printByName() { } //sort by last name and then first name
}
