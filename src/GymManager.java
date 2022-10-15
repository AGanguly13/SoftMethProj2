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
    private ClassSchedule listOfClasses = new ClassSchedule();

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
     * @param input the customer data: first name, last name, date of birth, and gym location.
     */
    private void addMember(String input) {
        Date dateOfBirth = new Date(input.split(" ")[2]);
        String city = input.split(" ")[3].toUpperCase();
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
        } else {
            Member newEntry = new Member(input.split(" ")[0], input.split(" ")[1], input.split(" ")[2]);
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
            if (findConflict(checkInClass.getTime(), storedEntry) != null) {
                System.out.println("TIME CONFLICT - " + findConflict(checkInClass.getTime(), storedEntry).toString());
            } else if(checkInClass.addMember(storedEntry)) {
                System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " checked in " + checkInClass);
                System.out.println("- Participants -");
                for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                    System.out.print("    ");
                    System.out.println(checkInClass.getAttendance().get(i));
                }
            }
        }
    }

    /**
     * Helper method to find if the given member has a class at a given time.
     * @param time the time object of the fitness class
     * @param member the member object of the member
     * @return the fitness class that conflicts, otherwise null
     */
    public FitnessClass findConflict(Time time, Member member) {
        for (int i = 0; i < listOfClasses.getSize(); i++) {
            if (listOfClasses.getClasses()[i].getTime() == time && listOfClasses.getClasses()[i].getAttendance().contains(member)) {
                return listOfClasses.getClasses()[i];
            }
        }
        return null;
    }

    /**
     * Helper method to find the correct fitness class based on location, instructor name, and class name.
     * Will return null if a class is not found or the inputted information is invalid and print an error statement.
     * @param fitnessClasses an array of the fitness classes
     * @param location the location of the fitness class
     * @param instructor the name of the instructor of the fitness class
     * @param session the class name
     * @return the desired fitness class, otherwise null
     */
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

    /**
     * Helper method to determine if the given gym location is valid.
     * Will print an error statement if location is invalid.
     * @param gymLocation the gym location.
     * @return true if the gym is valid, false otherwise.
     */
    private boolean validGym(String gymLocation) {
        for (Location gym : Location.values()) {
            if (gym.name().equalsIgnoreCase(gymLocation)) {
                return true;
            }
        }
        System.out.println(gymLocation + " - invalid location.");
        return false;
    }

    /**
     * Helper method to determine if the given instructor name is valid.
     * @param name the name of the instructor.
     * @return true if the instructor is valid, false otherwise.
     */
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
     * @param input the command line inputs: fitness class name, instructor, location, first name, last name, date of birth.
     */
    private void dropMember(String input) {
        String[] dropMemberInput = input.split(" ");
        Member storedEntry = database.isMemberInArray(new Member(dropMemberInput[3], dropMemberInput[4], dropMemberInput[5]));
        Date DOB = new Date(dropMemberInput[5]);

        if (!DOB.isValid()) {
            System.out.println(DOB + ": invalid calendar date!");
        } else if (storedEntry == null) {
            System.out.println(dropMemberInput[3] + " " + dropMemberInput[4] + " " + dropMemberInput[5] + " is not in the database.");
        } else {
            FitnessClass checkInClass = findClass(listOfClasses.getClasses(), dropMemberInput[2], dropMemberInput[1], dropMemberInput[0]);
            if(checkInClass.removeMember(storedEntry)) {
                System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " done with the class");
            }
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

    /**
     * Loads the fitness classes from a text file.
     * @throws FileNotFoundException when file not found
     */
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
            System.out.println("-Fitness classes Loaded-");
        } catch (FileNotFoundException e) {
            System.out.println("classSchedule.txt file not found.");
        }
    }
}

