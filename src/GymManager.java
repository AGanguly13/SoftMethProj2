import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/**
 * This is the User Interface class that processes command line inputs from user.
 * Accepts input as single command line or batch.
 * Terminates only when "Q" is typed.
 * @author Kennan Guan, Adwait Ganguly
 */
public class GymManager {
    private MemberDatabase database = new MemberDatabase();
    private ClassSchedule listOfClasses;

    /**
     * Runs the Gym Manager and accepts input from command line.
     * Only terminates upon typing "Q".
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
            } else if (inputs[0].equals("AF")) {

            } else if (inputs[0].equals("AP")) {

            } else if (inputs[0].equals("R")) {
                cancelMembership(inputs[1]);
            } else if (inputs[0].equals("P")) {
                if (database.isEmpty()) {
                    database.print();
                }
                else {
                    System.out.println("-list of members-");
                    database.print();
                    System.out.println("-end of list-");
                    System.out.println();
                }
            } else if (inputs[0].equals("PC")) {
                database.printByCounty();
            } else if (inputs[0].equals("PN")) {
                database.printByName();
            } else if (inputs[0].equals("PD")) {
                database.printByExpirationDate();
            } else if (inputs[0].equals("PF")) {

            } else if (inputs[0].equals("S")) {
                listOfClasses.print();
            } else if (inputs[0].equals("C")) {
                checkIn(inputs[1]);
            } else if (inputs[0].equals("CG")) {

            } else if (inputs[0].equals("D")) {
                dropMember(inputs[1]);
            } else if (inputs[0].equals("DG")) {

            } else if (inputs[0].equals("LS")) {
                loadClasses();
            } else if (inputs[0].equals("LM")) {

            } else if (inputs[0].equals("")) {
                System.out.println();
            } else {
                System.out.println(inputs[0] + " is an invalid command!");
            }
        }
        System.out.println("Gym Manager Terminated");
    }

    /**
     * Helper method that adds a new member into the gym database.
     * Will also check for valid date of birth and expiration date.
     * @param input the customer data: first name, last name, date of birth, membership expiration date, and gym location.
     */
    private void addMember(String input) {
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
        } else if (!dateOfBirth.isValid()) {
            System.out.println("DOB " + input.split(" ")[2] + ": invalid calendar date!");
        } else if (!dateOfBirth.isFuture(dateOfBirth)) {
            System.out.println("DOB " + input.split(" ")[2] + ": cannot be today or future date!");
        } else if (!dateOfBirth.isEighteen(dateOfBirth)) {
            System.out.println("DOB " + input.split(" ")[2] + ": must be 18 or older to join!");
        } else if (!expirationDate.isValid()) {
            System.out.println("Expiration Date " + input.split(" ")[3] + ": invalid calendar date!");
        } else {
            Member newEntry = new Member(input);
            if (database.add(newEntry)) {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            }
            else {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " is already in database.");
            }
        }
    }

    /**
     * Helper method that cancels and removes a member from the member database.
     * @param input the command line inputs: first name, last name, date of birth, membership expiration date, location.
     */
    private void cancelMembership(String input) {
        String[] inputs = input.split(" ");
        Member entry = new Member(inputs[0], inputs[1], inputs[2]);
        if (database.remove(entry)) {
            System.out.println(inputs[0] + " " + inputs[1] + " removed.");
            System.out.println();
        }
        else {
            System.out.println(inputs[0] + " " + inputs[1] + " is not in the database.");
        }
    }

    /**
     * Helper method that adds a new member into a specified fitness class.
     * @param input the command line inputs: fitness class name, instructor, location, first name, last name, date of birth.
     */
    private void checkIn(String input) {
        String[] split = input.split(" ");
        Member entry = new Member(split[3], split[4], split[5]);
        Member storedEntry = database.isMemberInArray(entry);
        Date today = new Date();
        Date DOB = new Date(split[3]);

        if (!DOB.isValid()) {
            System.out.println(DOB + ": invalid calendar date!");
        } else if (today.compareTo(storedEntry.getExpire()) <= 0) {
            System.out.println(split[3] + " " + split[4] + " " + split[5] + " membership expired.");
        } else if (storedEntry == null) {
            System.out.println(split[3] + " " + split[4] + " " + split[5] + " is not in the database.");
        } else {
            FitnessClass checkInClass = findClass(listOfClasses.getClasses(), split[2], split[1], split[0]);
            if(checkInClass.addMember(storedEntry)) {
                System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " checked in " + checkInClass);
                System.out.println("- Participants -");
                for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                    System.out.print("    ");
                    System.out.println(checkInClass.getAttendance().get(i));
                }
            }
        }
    }

    private FitnessClass findClass(FitnessClass[] fitnessClasses, String location, String instructor, String session) {
        if (!validInstructor(instructor) && !validClass(session) && !validGym(location)) {
            return null;
        }

        for (int i = 0; i < fitnessClasses.length; i++) {
            String fitnessName = fitnessClasses[i].getName();
            String fitnessInstructor = fitnessClasses[i].getInstructor();
            Location fitnessLocation = fitnessClasses[i].getLocation();
            if (fitnessName.equalsIgnoreCase(session) &&  fitnessLocation == Location.valueOf(location) && fitnessInstructor.equalsIgnoreCase(instructor)) {
                return fitnessClasses[i];
            }
        }
        System.out.println(session + " by " + instructor + " does not exist at " + location);
        return null;
    }

    private boolean validGym(String gymLocation) {
        for (Location gym : Location.values()) {
            if (gym.name().equalsIgnoreCase(gymLocation)) {
                return true;
            }
        }
        System.out.println(gymLocation + " - invalid location.");
        return false;
    }

    private boolean validInstructor(String name) {
        for (int i = 0; i < listOfClasses.getClasses().length; i++) {
            if (name.equalsIgnoreCase(listOfClasses.getClasses()[i].getInstructor())) {
                return true;
            }
        }
        System.out.println(name + " - instructor does not exist.");
        return false;
    }

    private boolean validClass(String name) {
        for (int i = 0; i < listOfClasses.getClasses().length; i++) {
            if (name.equalsIgnoreCase(listOfClasses.getClasses()[i].getName())) {
                return true;
            }
        }
        System.out.println(name + " - class does not exist");
        return false;
    }


    /**
     * Helper method that drops a given member from a specified fitness class.
     * @param input the command line inputs: fitness class name, first name, last name, date of birth.
     */
    private void dropMember(String input) {
        String[] dropMemberInput = input.split(" ");
        Member newMem = database.isMemberInArray(new Member(dropMemberInput[1], dropMemberInput[2], dropMemberInput[3]));
        String className = dropMemberInput[0].toUpperCase();
        Date newDate = new Date(dropMemberInput[3]);
        if (newDate.isValid()) {
            if (className.equals("PILATES") || className.equals("SPINNING") || className.equals("CARDIO")) {
                for (int i = 0; i < listOfClasses.length; i++) {
                    if (dropMemberInput[0].equalsIgnoreCase(listOfClasses[i].getName())) {
                        if (listOfClasses[i].removeMember(newMem)) {
                            System.out.println(dropMemberInput[1] + " " + dropMemberInput[2] + " dropped " + listOfClasses[i].getName() + ".");
                        }
                        else {
                            System.out.println(dropMemberInput[1] + " " + dropMemberInput[2] + " is not a " +
                                    "participant in " + listOfClasses[i].getName() + ".");
                        }
                    }
                }
            }
            else {
                System.out.println(dropMemberInput[0] + " class does not exist");
            }
        }
        else {
            System.out.println("DOB: " + newDate + ": invalid calendar date!");
        }
    }

//    /**
//     * Helper method that prints out all the fitness classes' information.
//     * Will add list of participants if available.
//     */
//    private void printClasses() {
//        System.out.println();
//        System.out.println("-Fitness Classes-");
//        for (int i = 0; i < listOfClasses.length; i++) {
//            System.out.println(listOfClasses[i].getName() + " - " + listOfClasses[i].getInstructor() + " " + listOfClasses[i].getTime().getClock());
//            if (!listOfClasses[i].isEmpty()) {
//                System.out.println("     **participants**");
//                listOfClasses[i].getAttendance().print();
//            }
//        }
//        System.out.println();
//    }

    private void loadClasses() {
        try {
            Scanner sc = new Scanner(new File("classSchedule.txt"));

            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split(" ");
                String classType = inputs[0];
                String instructor = inputs[1];
                Time time = Time.valueOf(inputs[2].toUpperCase());
                Location gymLocation = Location.valueOf(inputs[3].toUpperCase());

                listOfClasses.addClass(new FitnessClass(time, instructor, classType, gymLocation));
            }
        }
        catch (Exception FileNotFoundException) {
            System.out.println("classSchedule.txt file not found");
        }
    }
}

