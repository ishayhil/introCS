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
        if (m == null)
            return false;

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
        if (m == null)
            return false;

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
        if (m[0][0] > val) // if value is smaller than the first value, check first row.
            return 0;
        if (m[m.length - 1][0] < val) // if value is larger than last value in first col, check last row.
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
        int counter = cCounter(s, c); // by subtracting 2 from the c counter, be get all Cs with 1 c between them.
        return counter > 1 ? counter - 2 : 0; // time comp: O(n)
    }

    /**
     * Question 2, B:
     * returns the count of subsets in string that start with a given char (x for example),
     * has max of k chars inside of it, and ends with char.
     * worst time complexity: O(n) -> cCounter private method. n = String's length.
     * space complexity: O(5) = O(1).
     *
     * @param s the String.
     * @param c the char.
     * @param k int. the max char appearances.
     * @return int. count of subsets
     */
    public static int subStrMaxC(String s, char c, int k) {
        int cCount = cCounter(s, c); // time comp: O(n) -> (n = s length)

        /*
         * we can build a series from the amount of subsets for each x in k (cCount - 1, cCount - 2, cCount - 3...) -> n is k + 1.
         * a1 is cCount - 1.
         * d will be -1.
         * n is c - 1 if k + 1 (the n in our series) is bigger than the n max (because then we will go below 0 and start summing negative numb).
         * i got the n max by: An = c - 1 + (n-1)*-1 ->  0 = cCount - n ->  n = c.
         * Then, S(n) of this series is the amount of subsets until the given k.
         * S(n) = n * (2 * a1 + (n - 1) * d) / 2
         */
        int n_max = k + 1 > cCount ? cCount : k + 1;
        int d = -1;
        int a1 = cCount - 1;

        return n_max * (2 * a1 + (n_max - 1) * d) / 2; // S(n)
    }

    private static int cCounter(String s, char c) {
        if (s == null)
            return 0;

        int counter = 0;
        for (int i = 0; i < s.length(); i++) { // time comp: O(n)
            if (s.charAt(i) == c)
                counter++;
        }
        return counter;
    }

    // *****************************************************************************

    /**
     * Question 3.A.
     * Will calculate the number of paths spiderman can have in order to get to floor n.
     * Spiderman can only jump one OR two floors at once.
     *
     * @param n last floor.
     * @return int. number of paths.
     */
    public static int spiderman(int n) {
        if (n < 1) {
            return 0;
        }

        switch (n) {
            case 1: // only one way to get to 1st floor.
                return 1;
            case 2: // only 2 ways to get to 2nd floor.
                return 2;
            default:
                // {n} = amount of ways to get to floor n.
                // {n} = {n-1} + {n-2} -> all the paths to the floor below + all the paths to 2 floors below
                // due to the jump's two possibilities.
                // so, by returning both sums, we will get the total count recursively.
                return spiderman(n - 1) + spiderman(n - 2);

        }
    }

    /**
     * Question 3.B.
     * Will calculate the number of paths spiderman can get to n floor (20 <= n <= 29). But, if getting to the 20th
     * floor, an elevator will take spiderman directly to n floor.
     *
     * @param n the last floor.
     * @return int. amount of paths.
     */
    public static int spidermanPhoneBooth20(int n) {
        if (n < 20 || n > 29)
            return 0;

        if (n == 20) // calculate 20 regularly.
            return spiderman(20);

        // if each floor > 20, there is spiderman(n-20) paths to get to 20. We need to subtract all of these
        // paths, and add only one spiderman(20) -> the way to get to the n floor with the elevator.
        // i.e: spiderman(23)' = spiderman(23) - spiderman(20) * 3 + spiderman(20) because
        // {23} = {22} + {21}, {22} = {21} + {20}, {21} = {20} + {19} -> there are 3*{20} in {23}.
        return spiderman(n) - (spiderman(20) * (spiderman(n - 20) - 1));
    }

    // *****************************************************************************

    /**
     * Question 4.
     * countPaths will count the amount of valid paths between matrix[0][0] to the end of the matrix (matrix[rows-1][columns-1]).
     * it will work on a recursive way -> gives the ability to see if each cell can go forward without getting outside of the matrix/hit 0 value.
     * By doing so, be cover all options for both cases.
     *
     * @param mat the matrix.
     * @return int. the amount of possible legit paths.
     */
    public static int countPaths(int[][] mat) {
        if (mat == null)
            return 0;

        return countPaths(mat, 0, 0);
    }

    private static int countPaths(int[][] mat, int currentRow, int currentCol) { // countPaths overloading.
        if (currentRow == mat.length - 1 && currentCol == mat[0].length - 1) // if reached to the last matrix value.
            return 1;
        if (currentRow > mat.length - 1 || currentCol > mat[0].length - 1 || mat[currentRow][currentCol] == 0)
            return 0; // if outside of matrix OR 0 is the current value (endless loop) -> not legit path

        if (mat[currentRow][currentCol] % 10 == mat[currentRow][currentCol] / 10)
            // if both ways will take you to the same cell (i.e 11,22,33,44..), then to avoid duplicated counting -> limit to one next option.
            return countPaths(mat, currentRow + mat[currentRow][currentCol] % 10, currentCol + mat[currentRow][currentCol] / 10);

        // by returning countPaths with ones to the rows and tens to the columns i get all available valid paths for this option.
        // by adding the countPaths with tens to the rows and ones to the columns, i get all the available valid paths for this option.
        // so the sum will give all the available valid paths for both cases.
        // % 10 -> ones. / 10 -> tens.
        return countPaths(mat, currentRow + mat[currentRow][currentCol] % 10, currentCol + mat[currentRow][currentCol] / 10) +
                countPaths(mat, currentRow + mat[currentRow][currentCol] / 10, currentCol + mat[currentRow][currentCol] % 10);
    }

}
