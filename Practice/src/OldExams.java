/**
 * OpenUniversity old exams solved questions.
 *
 * @author Ishay Hilzenrat
 */
public class OldExams {

    // ***************************************** 2018b ************************************************* //
    public static int cntTrueReg(boolean[][] mat) {
        if (mat == null || mat.length != mat[0].length)
            return 0;

        int[][] intM = new int[mat.length][mat.length];

        return cntTrueReg(mat, 0, 0, intM);
    }

    private static int cntTrueReg(boolean[][] mat, int row, int col, int[][] intM) {
        if (!isValidPlace(mat.length, row, col) || intM[row][col] == 1)
            return 0;

        intM[row][col] = 1; // flagging the visited values.

        if (mat[row][col] && row == 0 && col == 0) {
            markT(mat, row, col);
            return 1 + cntTrueReg(mat, row + 1, col, intM) +
                    cntTrueReg(mat, row, col + 1, intM) + cntTrueReg(mat, row + 1, col + 1, intM);
        }

        if (mat[row][col]) {
            markT(mat, row, col);
            return 1;
        }

        return cntTrueReg(mat, row + 1, col, intM) + cntTrueReg(mat, row, col + 1, intM) +
                cntTrueReg(mat, row + 1, col + 1, intM);
    }

    private static boolean markT(boolean[][] m, int row, int col) {
        if (!isValidPlace(m.length, row, col) || !m[row][col])
            return false;

        m[row][col] = false;

        return markT(m, row + 1, col) || markT(m, row - 1, col) ||
                markT(m, row, col + 1) || markT(m, row, col - 1);
    }


    private static boolean isValidPlace(int mLen, int row, int col) {
        return row > -1 && col > -1 && row < mLen && col < mLen;
    }

    // ***************************************** practice ************************************************* //
    public static int sumEven(int n) {
        if (n == 0)
            return 0;

        return ((n % 10) % 2 == 0 ? n % 10 : 0) + sumEven(n / 10);
    }

    // ***************************************** 2017b moed A ************************************************* //
    public static int edit(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0)
            return s1.length() + s2.length();

        if (s1.charAt(0) == s2.charAt(0))
            return edit(s1.substring(1), s2.substring(1));

        if (isLetterNeeded(s1.charAt(0), s2)) { // if letter is needed, keep it
            return 1 + edit(s2.charAt(0) + s1, s2);
        }

        return 1 + edit(s1.substring(1), s2); // if letter is not needed, remove it
    }

    private static boolean isLetterNeeded(char c, String s2) {
        if (s2.length() == 0)
            return false;

        if (s2.charAt(0) == c)
            return true;

        return isLetterNeeded(c, s2.substring(1));
    }

    public static int countTriplets(int[] arr, int num) {
        int cnt = 0;
        int low = 0;
        int mid = 1;
        int high = arr.length - 1;

        while (high != mid) { // time comp: O(n^2). while high and low are doing O(n) together, middle is running beside them.
            if (arr[low] + arr[mid] + arr[high] < num) {
                cnt += (high - mid);
                if (mid + 1 != high)
                    mid++;
                else {
                    low++;
                    mid = low + 1;
                }
            } else
                high--;
        }
        return cnt;
    }

    // ***************************************** 2013A moed B ************************************************* //

    public static boolean match(int[] a, int[] pattern) {
        return match(a, pattern, 0, 0, 0);
    }

    private static boolean match(int[] a, int[] pattern, int beginning, int i, int counter) {
        if (pattern.length == 0 || counter == pattern.length)
            return true;

        if (beginning + i > a.length - 1)
            return false;

        if (a[beginning + i] > 9 && a[beginning + i] < 100 && pattern[i] == 2 || a[beginning + i] < 10 && pattern[i] == 1
                || a[beginning + i] < 100 && pattern[i] == 0)
            return match(a, pattern, beginning, i + 1, counter + 1);

        return match(a, pattern, beginning + 1, 0, 0);
    }

    // ***************************************** 2013B moed A ************************************************* //

    public static boolean balancedPartition(int[] arr) {
        int sumToFind = sumArr(arr, 0);
        if (sumToFind % 2 != 0)
            return false;

        return balancedPartition(arr, 0, 0, 0, 0, 0, sumToFind / 2);
    }

    private static boolean balancedPartition(int[] arr, int i, int sumA, int counterA, int sumB, int counterB, int sumToFind) {
        if (arr.length % 2 != 0)
            return false;

        if (i > arr.length - 1) {
            if (counterA == counterB && sumA == sumToFind && sumB == sumToFind)
                return true;
            else
                return false;
        }

        return balancedPartition(arr, i + 1, sumA + arr[i], 1 + counterA, sumB, counterB, sumToFind) ||
                balancedPartition(arr, i + 1, sumA, counterA, sumB + arr[i], counterB + 1, sumToFind);
    }

    private static int sumArr(int[] arr, int ind) {
        if (ind > arr.length - 1)
            return 0;

        return arr[ind] + sumArr(arr, ind + 1);
    }

}
