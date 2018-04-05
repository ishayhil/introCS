/**
 * The Train class will calculate the final distance between two different trains given the km/h speed and the travel
 * duration (in minutes) of each of these trains.
 *
 * @author Ishay Hilzenrat. ID: 313534398
 * @version 1.0
 */

import java.lang.Math;
import java.util.Scanner;

public class Train {

    /**
     * the main method for class Train. Will print the the distance between the trains at the end of the method.
     *
     * @return void
     */

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter 4 integers.");

        // first train's values:
        System.out.println("Please enter the speed of train 1: ");
        // absolute value of the speed. No negative speed in this case.
        int v1 = Math.abs(scan.nextInt());

        System.out.println("Please enter the time of train 1: ");
        // absolute value of the time. No negative time in this case.
        int t1 = Math.abs(scan.nextInt());


        // second train's values:
        System.out.println("Please enter the speed of train 2: ");
        // absolute value of the speed. No negative speed in this case.
        int v2 = Math.abs(scan.nextInt());

        System.out.println("Please enter the time of train 2: ");
        int t2 = Math.abs(scan.nextInt()); // absolute value of the time. No negative time in this case.


        // train's duration will be divided in 60 in order to convert the minutes to hours. Also converted to double for
        // distance accuracy (decimal and not rounded number).
        double firstTrainDistance = v1 * ((double) t1 / 60);
        double secondTrainDistance = v2 * ((double) t2 / 60);


        /* distance calculation and result printing:
         * distanceBetweenTrains var to abs so the distance will be positive.
         * Will round the results using the Math class round method. Will also multiple the converted double in 100
         * and divide in 100.0 (double) to round the value to 2 decimal numbers after the dot: */
        double distanceBetweenTrains = Math.abs(firstTrainDistance - secondTrainDistance);
        System.out.println("\nThe distance between the trains is " +
                Math.round(distanceBetweenTrains * 100) / 100.0 + " km.");

    } // end of method main

} // end of class Train
