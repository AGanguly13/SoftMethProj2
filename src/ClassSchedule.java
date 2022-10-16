import java.util.ArrayList;
/**
 * Defines a schedule of fitness classes.
 * A class schedule is defined by a list of classes on the schedule and a counter to keep track of the classes in the list.
 * Offers functionality to add classes amd grow automatically.
 * Has two getter methods to get the size and the class list.
 * @author Kennan Guan, Adwait Ganguly
 */
public class ClassSchedule {
    private FitnessClass[] classes;
    private int numClasses;
    private static int ARRAYGROWTH = 4;

    /**
     * Constructor for class schedule.
     * Sets the array size to a default number of four and number of classes to 0.
     */
    public ClassSchedule() {
        classes = new FitnessClass[ARRAYGROWTH];
        numClasses = 0;
    }

    /**
     * Will grow the size of the classes array by an increment of four.
     * Called by the addClass() method.
     */
    private void grow() {
        FitnessClass[] newSchedule = new FitnessClass[numClasses + ARRAYGROWTH];

        for (int i = 0; i < numClasses; i++) {
            newSchedule[i] = classes[i];
        }

        classes = newSchedule;

    }

    /**
     * Adds a specified fitness class into the schedule of classes.
     * @param fitness the fitness class to be added.
     */
    public void addClass(FitnessClass fitness) {
        if (numClasses == classes.length) {
            grow();
        }

        classes[numClasses] = fitness;
        numClasses++;
    }

    /**
     * Getter method for the number of classes in the array.
     * @return the number of classes
     */
    public int getSize() {
        return numClasses;
    }

    /**
     * Getter method for the list of fitness classes.
     * @return the list of fitness classes
     */
    public FitnessClass[] getClasses() {
        return classes;
    }

    /**
     * Prints all the fitness classes in the lists.
     */
    public void print() {
        if (numClasses == 0) {
            System.out.println("Fitness class schedule is empty.");
        } else {
            for (int i = 0; i < numClasses; i++) {
                System.out.println(classes[i]);
                ArrayList attendance = classes[i].getAttendance();
                if (attendance.size() != 0) {
                    System.out.println("- Participants -");
                }
                for (int j = 0; j < attendance.size(); j++) {
                    System.out.println(attendance.get(j));
                }
            }
            System.out.println("-End of class list.");
        }
    }

}