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
     * This method checks if a database object does or does not have any members in it.
     * @return true if the database has no members, false if it does have members.
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if a member is currently in the array structure representing a Member
     * Database.
     * @param member is the member that is being checked for if they are in the array structure.
     * @return true if the member is in the array, false otherwise.
     */
    public boolean isInArray(Member member) {
        for (int x = 0; x < this.size; x++) {
            if (this.mlist[x].equals(member)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a member if it is in the database.
     * @param member is the member that is being checked for if they are in the database.
     * @return the member if that member is found in the database, null otherwise
     */
    public Member isMemberInArray(Member member) {
        for (int x = 0; x < this.size; x++) {
            if (this.mlist[x].equals(member)) {
                return this.mlist[x];
            }
        }
        return null;
    }

    /**
     * Searches for the parameter Member object in the database array.
     * @param member is the member that is being searched for in the database.
     * @return the index of the member if found, otherwise return constant NOT_FOUND.
     */
    private int find(Member member) {
        for (int x = 0; x < this.size; x++) {
            if (this.mlist[x].equals(member)) {
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

        return wasAdded;
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

        return wasRemoved;
    }

    /**
     * Prints the member database as is, with each Member object being printed out on a new line.
     * Uses the Member class toString() method to print out all necessary Member object information.
     */
    public void print () {
        if (this.size == 0) {
            System.out.println("Member database is empty!");
        }
        for (int x = 0; x < this.size; x++){
            System.out.println(this.mlist[x].toString());
        }
    } //print the array contents as is

    /**
     * Implements bubble sort to sort the database by Date of birth from earliest DOB to latest DOB.
     * Calls print() method once the sorting is complete.
     */
    public void printByCounty() {
        if (this.size == 0) {
            System.out.println("Member database is empty!");
            return;
        }
        for (int x = 0; x < this.size - 1; x++) {
            for (int y = 0; y < this.size - x - 1; y++) {
                if (this.mlist[y].getLocation().getCounty().compareTo(this.mlist[y + 1].getLocation().getCounty()) > 0) { //needs to implement a String compareTo method
                    Member temp = this.mlist[y];
                    this.mlist[y] = this.mlist[y + 1];
                    this.mlist[y + 1] = temp;
                }
            }
        }
        int trackSwitch = 0; //variable that tracks when the county changes
        for (int x = trackSwitch; trackSwitch < this.size - 1; trackSwitch++) {
            for (int y = 0; y < this.size - x - 1; y++) {
                if (this.mlist[y + 1].getLocation().getCounty().equals("MIDDLESEX")) {
                    if (this.mlist[y].getLocation().getZip() > this.mlist[y + 1].getLocation().getZip()) {
                        Member temp = this.mlist[y];
                        this.mlist[y] = this.mlist[y + 1];
                        this.mlist[y + 1] = temp;
                    }
                }
            }
        }
        for (int x = trackSwitch; trackSwitch < this.size - 1; trackSwitch++) {
            for (int y = trackSwitch; y < this.size - x - 1; y++) {
                    if (this.mlist[y].getLocation().getZip() > this.mlist[y + 1].getLocation().getZip()) {
                        Member temp = this.mlist[y];
                        this.mlist[y] = this.mlist[y + 1];
                        this.mlist[y + 1] = temp;
                    }
            }
        }
        System.out.println("-list of members sorted by county and zipcode-");
        for (int x = 0; x < this.size; x++){
            System.out.println(this.mlist[x].toString());
        }
        System.out.println("-end of list-");
    } //sort by county and then zipcode

    /**
     * Implements bubble sort to sort the database by expiration date, from the earliest expiration
     * date to the latest expiration date. Calls print() method once sorting is complete.
     */
    public void printByExpirationDate() {
        if (this.size == 0) {
            System.out.println("Member database is empty!");
            return;
        }

        for (int x = 0; x < this.size - 1; x++) {
            for (int y = 0; y < this.size - x - 1; y++) {
                if (this.mlist[y].getExpire().compareTo(this.mlist[y + 1].getExpire()) > 0) { //needs to implement a Date class compareTo method
                    Member temp = this.mlist[y];
                    this.mlist[y] = this.mlist[y + 1];
                    this.mlist[y + 1] = temp;
                }
            }
        }

        System.out.println("-list of members sorted by membership expiration date-");
        for (int x = 0; x < this.size; x++){
            System.out.println(this.mlist[x].toString());
        }
        System.out.println("-end of list-");
    } //sort by the expiration date

    /**
     * Implement bubble sort to alphabetically sort the database by last name, and then first name
     * if last names are the same. Calls print() method once sorting is complete.
     */
    public void printByName() {
        if (this.size == 0) {
            System.out.println("Member database is empty!");
            return;
        }

        for (int x = 0; x < this.size - 1; x++) {
            for (int y = 0; y < this.size - x - 1; y++) {
                if (this.mlist[y].compareTo(this.mlist[y + 1]) > 0) {
                    Member temp = this.mlist[y];
                    this.mlist[y] = this.mlist[y + 1];
                    this.mlist[y + 1] = temp;
                }
            }
        }

        System.out.println("-list of members sorted by last name, and first name-");
        for (int x = 0; x < this.size; x++){
            System.out.println(this.mlist[x].toString());
        }
        System.out.println("-end of list-");
    } //sort by last name and then first name
}
