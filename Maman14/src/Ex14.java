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
        if (m[0][0] > val) // if not between values - check the row.
            return 0;
        if (m[0][m.length - 1] < val)
            return m.length - 2;

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

    /**
     * Question 2, A:
     * returns the count of subsets in string that start with a given char (x for example),
     * has one x inside of it, and ends with char.
     * worst time complexity: O(n) -> cCounter private method. n = String's length
     * space complexity: O(1).
     *
     * @param s the String.
     * @param c the char.
     * @return int. count of subsets
     */
    public static int subStrC(String s, char c) {
        int counter = cCounter(s, c);
        return counter > 1 ? counter - 2 : 0; // O(n)
    }

    /**
     * Question 2, B:
     * returns the count of subsets in string that start with a given char (x for example),
     * has max of k chars inside of it, and ends with char.
     * worst time complexity: O(n + k). n = String's length.
     * space complexity: O(1 + 1) = O(1).
     *
     * @param s the String.
     * @param c the char.
     * @param k int. the max char appearances.
     * @return int. count of subsets
     */
    public static int subStrMaxC(String s, char c, int k) {
        int cCount = cCounter(s, c); // time comp: O(n) -> (n = s length)
        int subSets = 0;

        /*
         * we can build a series from the amount of subsets for each x in k (0,1,2,..,k).
         * a1 will be the amount of c in s minus (k + 1) -> cCount - k + 1
         * d will be 1
         * n will be (k + 1) mod cCount -> because it can be <= 0 if k >= c.
         * Then, S(n) of this series is the amount of subsets until the given k.
         * S(n) = n * (2 * a1 + (n - 1) * d) / 2
         */
//        int n = cCount > k ? k + 1 : (k + 1) % cCount;
//        int d = -1;
//        int a1 = cCount - 1;
//
//        return n * (2 * a1 + (n - 1) * d) / 2;

        for (int j = 1; j < k + 2; j++) // O(k)
            if (cCount - j >= 0)
                // until the amount doesn't go below 0, keep adding the amount of subsets for each x in k.
                subSets += cCount - j;
            else
                return subSets;

        return subSets;
    }

    private static int cCounter(String s, char c) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) { // time comp: O(n)
            if (s.charAt(i) == c)
                counter++;
        }
        return counter;
    }

    // *****************************************************************************

    public static int spiderman(int n) {
        if (n < 1)
            return 0;

        switch (n) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return spiderman(n - 1) + spiderman(n - 2);
        }
    }

}
