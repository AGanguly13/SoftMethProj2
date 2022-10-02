/**
 * User Interface class that processes command line inputs from user
 * Accepts input as single command line or batch
 * Terminates only when "Q" is typed
 * @author Kennan Guan
 */
import java.util.Scanner;
import java.util.Calendar;

public class GymManager {
    private MemberDatabase database = new MemberDatabase();
    private FitnessClass pilates = new FitnessClass(Time.MORNING, "Jennifer");
    private FitnessClass spinning = new FitnessClass(Time.AFTERNOON, "Denise");
    private FitnessClass cardio = new FitnessClass(Time.AFTERNOON, "Kim");

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
                cancelMembership(inputs[1]); //how to remove only given name?
            } else if (inputs[0].equals("P")) {
                database.print();
            } else if (inputs[0].equals("PC")) {
                database.printByCounty();
            } else if (inputs[0].equals("PN")) {
                database.printByName();
            } else if (inputs[0].equals("PD")) {
                database.printByExpirationDate();
            } else if (inputs[0].equals("S")) {
                for (Time slot : Time.values()) {
                    System.out.println(slot.toString());
                }
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
        String city = input.split(" ")[4];
        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.equals(city)) {
                validCity = true;
            }
        }
        if (!validCity){
            System.out.println(city + ": invalid location!");
        }else if(!dateOfBirth.isValid()) {
            System.out.println("DOB " + dateOfBirth + ": invalid calendar date!");
        }else if(!dateOfBirth.isEighteen(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": must be 18 or older to join!");
        }else if(dateOfBirth.isFuture(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": cannot be today or future date!");
        }else if(!expirationDate.isValid()) {
            System.out.println("DOB " + expirationDate + ": invalid calendar date!");
        }else{
            Member newEntry = new Member(input);
            boolean added = database.add(newEntry);
            if(newAddition) System.out.println(newEntry.getFname + " " + newEntry.getLname + " added.");
            else System.out.println(newEntry.getFname + " " + newEntry.getLname + " already in database.");
        }
    }

    public void cancelMembership(String input){
        String[] inputs = input.split(" ");
        Member entry = new Member(inputs[0], inputs[1], inputs[2]);
        database.remove(entry);
    }

    public void checkIn(String input){

    }

    public void dropMember(String input){

    }
}

