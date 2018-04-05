/**
 * Temperature class will convert between C, F & K degrees units of measure by the inputs given by the user (temp unit and value).
 *
 * @author Ishay Hilzenrat. 
 * @version 1.0
 */

import java.lang.Math;
import java.util.Scanner;

public class Temperature {

    /**
     * the main method for class Temperature. Will print the converted temp values for types C, F and K.
     *
     * @return void
     */

    public static void main(String[] args) {

        // unit conversion constants:
        final double _273_POINT_15 = 273.15;
        final int _32 = 32;
        final double _9_DIVIDE_5 = (double) 9 / 5;
        final double _5_DIVIDE_9 = 1 / _9_DIVIDE_5;

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter a temperature unit (C or K or F) in uppercase:");
        // takes change the tempUnit to  uppercase in order to prevent user input mistakes.
        char tempUnit = scan.nextLine().toUpperCase().charAt(0);

        System.out.println("Please enter a temperature value:");
        double tempValue = scan.nextDouble();

        double convertedC, convertedK, convertedF; // declaring the C,K and F converted value vars.

        System.out.println();


        // the units conversion based on the user's unit input:
        if (tempUnit == 'C') {
            convertedC = tempValue;
            convertedK = tempValue + _273_POINT_15;
            convertedF = (_9_DIVIDE_5 * tempValue) + _32;
        } else if (tempUnit == 'F') {
            convertedC = (_5_DIVIDE_9) * (tempValue - _32);
            convertedK = (_5_DIVIDE_9) * (tempValue - _32) + _273_POINT_15;
            convertedF = tempValue;
        } else {
            convertedC = tempValue - _273_POINT_15;
            convertedK = tempValue;
            convertedF = (_9_DIVIDE_5) * (tempValue - _273_POINT_15) + _32;
        }

        /* Will round the results using the Math class round method. Will also multiple the converted double in 100
         * and divide in 100.0 (double) to round the value 2 decimal numbers after the dot: */
        System.out.println("C " + Math.round(convertedC * 100) / 100.0);
        System.out.println("F " + Math.round(convertedF * 100) / 100.0);
        System.out.println("K " + Math.round(convertedK * 100) / 100.0);

    } // end of method main

} // end of class Temperature
