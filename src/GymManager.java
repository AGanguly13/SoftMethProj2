/**
 * User Interface class that processes command line inputs from user
 * Accepts input as single command line or batch
 * Terminates only when "Q" is typed
 * @author Kennan Guan, Adwait Ganguly
 */
import java.util.Scanner;
import java.util.Calendar;

public class GymManager {
    private MemberDatabase database = new MemberDatabase();
    private FitnessClass pilates = new FitnessClass(Time.MORNING, "JENNIFER", "Pilates");
    private FitnessClass spinning = new FitnessClass(Time.AFTERNOON, "DENISE", "Spinning");
    private FitnessClass cardio = new FitnessClass(Time.AFTERNOON, "KIM", "Cardio");
    private FitnessClass[] listOfClasses = {pilates, spinning, cardio};

    /**
     * Runs the Gym Manager and accepts input from command line
     * Only terminates upon typing "Q"
     */
    public void run() {
        System.out.println("Gym Manager running...");
        Scanner keyboard = new Scanner(System.in);

        while (keyboard.hasNextLine()) {
            String inputLine = keyboard.nextLine();
            String[] inputs = inputLine.split(" ", 2);
            if (inputs[0].equals("Q")) {
                break;
            } else if (inputs[0].equals("A")) {
                addMember(inputs[1]);
            } else if (inputs[0].equals("R")) {
                cancelMembership(inputs[1]);
            } else if (inputs[0].equals("P")) {
                database.print();
            } else if (inputs[0].equals("PC")) {
                database.printByCounty();
            } else if (inputs[0].equals("PN")) {
                database.printByName();
            } else if (inputs[0].equals("PD")) {
                database.printByExpirationDate();
            } else if (inputs[0].equals("S")) {
                printClasses();
            } else if (inputs[0].equals("C")) {
                checkIn(inputs[1]);
            } else if (inputs[0].equals("D")) {
                dropMember(inputs[1]);
            } else {
                System.out.println(inputs[0] + " is an invalid command!");
            }
        }
        System.out.println("Gym Manager Terminated");
    }

    /**
     * Adds a new member into the gym database
     * Will also check for valid date of birth and expiration date
     * @param input the customer data: first name, last name, date of birth, membership expiration date, and gym location
     */
    public void addMember(String input) {
        Date dateOfBirth = new Date(input.split(" ")[2]);
        Date expirationDate = new Date(input.split(" ")[3]);
        String city = input.split(" ")[4].toUpperCase();
        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.name().equals(city.toUpperCase())) {
                validCity = true;
            }
        }
        if (!validCity) {
            System.out.println(city + ": invalid location!");
        }else if(!dateOfBirth.isValid()) {
            System.out.println("DOB " + input.split(" ")[2] + ": invalid calendar date!");
        }else if(!dateOfBirth.isFuture(dateOfBirth)) {
            System.out.println("DOB " + input.split(" ")[2] + ": cannot be today or future date!");
        }else if(!dateOfBirth.isEighteen(dateOfBirth)) {
            System.out.println("DOB " + input.split(" ")[2] + ": must be 18 or older to join!");
        }else if(!expirationDate.isValid()) {
            System.out.println("Expiration Date " + input.split(" ")[3] + ": invalid calendar date!");
        }else{
            Member newEntry = new Member(input);
            if(database.add(newEntry)) System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            else System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " already in database.");
        }
    }

    /**
     * Cancels and removes a member from the member database
     * @param input the command line inputs: first name, last name, date of birth, membership expiration date, location
     */
    public void cancelMembership(String input) {
        String[] inputs = input.split(" ");
        Member entry = new Member(inputs[0], inputs[1], inputs[2]);
        if(database.remove(entry)) {
            System.out.println(inputs[0] + " " + inputs[1] + " removed.");
        }else {
            System.out.println(inputs[0] + " " + inputs[1] + " is not in the database.");
        }
    }

    /**
     * Adds a new member into a specified fitness class
     * @param input the command line inputs: fitness class name, first name, last name, date of birth
     */
    public void checkIn(String input) {
        String[] split = input.split(" ");
        Member entry = new Member(split[1], split[2], split[3]);
        String session = split[0];
        Date today = new Date();
        Date DOB = new Date(split[3]);

        if(DOB.isValid()) {
            if(today.compareTo(entry.getExpire()) <= 0) {
                if(session.equals("Pilates") || session.equals("Spinning") || session.equals("Cardio")){
                    boolean inConflictSpinning = spinning.getAttendance().isInArray(entry);
                    boolean inConflictCardio = cardio.getAttendance().isInArray(entry);
                    for(int i = 0 ; i < listOfClasses.length; i++) {
                        if (session.equals(listOfClasses[i].getName()) && listOfClasses[i].addMember(entry)) {
                            if(i != 0 && inConflictSpinning){
                                System.out.println(listOfClasses[i].getName() + " time conflict -- " + split[1] + " " + split[2] + " has already checked into Spinning");
                            }else if(i != 0 && inConflictCardio) {
                                System.out.println(listOfClasses[i].getName() + " time conflict -- " + split[1] + " " + split[2] + " has already checked into Cardio");
                            }else {
                                System.out.println(split[1] + " " + split[2] + " check in " + listOfClasses[i].getName());
                            }
                        } else if (session.equals(listOfClasses[i].getName()) && !listOfClasses[i].addMember(entry)){
                            System.out.println(split[1] + " " + split[2] + " has already checked in " + listOfClasses[i].getName());
                        }
                    }
                } else {
                    System.out.println(session + " class does not exist");
                }
            } else {
                System.out.println(split[1] + " " + split[2] + " " + split[3] + " membership expired");
            }
        } else {
            System.out.println("DOB " + split[3] + ": invalid calendar date!");
        }
    }

    /**
     * Drops a given member from a specified fitness class
     * @param input the command line inputs: fitness class name, first name, last name, date of birth
     */
    public void dropMember(String input) {
        String[] dropMemberInput = input.split(" ");
        Member newMem = new Member(dropMemberInput[1], dropMemberInput[2], dropMemberInput[3]);
        Date newDate = new Date(dropMemberInput[3]);
        if (newDate.isValid()) {
            for(int i = 0 ; i < listOfClasses.length; i++) {
                if (dropMemberInput[0].equals(listOfClasses[i].getName())) {
                    if (listOfClasses[i].removeMember(newMem)) {
                        System.out.println(dropMemberInput[1] + " " + dropMemberInput[2] + "dropped " + listOfClasses[i].getName());
                    } else {
                        System.out.println(dropMemberInput[1] + " " + dropMemberInput[2] + "is not a " +
                                "member in " + listOfClasses[i].getName());
                    }
                }
            }
        }
        else {
            System.out.println("DOB: " + newDate + ": invalid calendar date!");
        }
    }


    /**
     * Prints out all the fitness classes' information
     * Will add list of participants if available
     */
    public void printClasses() {
        for(int i = 0; i < listOfClasses.length; i++){
            System.out.println("-Fitness Classes-");
            System.out.println(listOfClasses[i].getName() + " - " + listOfClasses[i].getInstructor() + " " + listOfClasses[i].getTime().getHour() + ":" + listOfClasses[i].getTime().getMinute() + "\n");
            if(!listOfClasses[i].isEmpty()) {
                System.out.println("**participants**");
                System.out.print(listOfClasses[i].getAttendance());
            }
        }
    }
}

