/**
 * Maman 14
 *
 * @author Ishay Hilzenrat
 * @version 1.0
 */
public class Ex14 {

    // *****************************************************************************

    /**
     * Question 1, A:
     * 3, 5, 6 -> true
     **/

    /**
     * Question 1, B.1:
     * will return true if the value is in sorted the matrix, false if not.
     * Will only work on column and row ascending sorted matrix.
     * worst time complexity: O(n + n) = O(n).
     * space complexity: O(1 + 1 + 1) = O(1).
     *
     * @param m   matrix
     * @param val value to find
     * @return true if value inside of matrix, false if not.
     */
    public static boolean findValWhat(int[][] m, int val) {
        int n = m.length;
        int row = 0; // top row
        int col = n - 1; // most right column

        while (row < n && col >= 0) { // can max run n + (n-1) times.
            if (m[row][col] == val)
                return true;

            // if value is bigger, none of the columns to the left can be the value. So go one down.
            if (m[row][col] < val)
                row++;
            else // if value is smaller, none of the rows in that column below can be the value, so go left.
                col--;
        }
        return false;
    }

    /**
     * Question 1, B.2:
     * will return true if the value is in the matrix.
     * Will only work on matrix the all values on the row below are greater or equal
     * to the values on the row above.
     * worst time complexity: O(n + 2n) = O(n).
     * space complexity: O(1 + 1) = O(1).
     *
     * @param m   matrix
     * @param val value to find
     * @return true if value inside of matrix, false if not.
     */
    public static boolean findValTest(int[][] m, int val) {
        int initialRow = findTestValRow(m, val); // time comp: O(n). Space comp: O(1).
        int n = m.length;

        if (initialRow == -1) // if row index doesn't exist
            return false;

        for (int i = 0; i < 2; i++) // O(2n) = O(n)
            for (int col = 0; col < n; col++) { // running through the two rows's columns
                if (m[initialRow + i][col] == val)
                    return true;
            }
        return false; // if row index exist, but value does not
    }

    private static int findTestValRow(int[][] m, int val) {
        // find the initial row that the value is between/equals
        // time complexity: O(n - 1) = O(n).
        // space complexity: O(1).
        for (int i = 0; i < m.length - 1; i++) {
            if (m[i][0] == val || m[i + 1][0] == val) // if value equals to current row or one below
                return i;

            // if value is between current row and one below, it have to be in one of them (if exist).
            if (m[i][0] < val && m[i + 1][0] > val)
                return i;
        }
        return -1; // if no row index -> value isn't inside matrix.
    }

    // *****************************************************************************

    public static int subStrC(String s, char c) {
        int n = s.length();
        int cCounter = 0;
        int subSetCounter = 0;

        if (n < 3)
            return 0;

        for (int i = 0; i < s.length(); i++) { // O(n)
            if (s.charAt(i) != c)
                continue;

            cCounter++;
            if (cCounter == 3) {
                subSetCounter++;
                cCounter = 2;
            }
        }
        return subSetCounter;
    }


}
