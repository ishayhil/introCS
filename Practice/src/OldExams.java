/**
 * OpenUniversity old intoToCs exams solved questions.
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

    public static boolean balancedPartition1(int[] arr) {
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

    // ***************************************** 2013B moed A ************************************************* //

    private static int minPoints(int[][] arr, int sum, int row, int col, int min) {
        sum += arr[row][col];
        if (min > sum)
            min = sum;

        if (row == arr.length - 1 && col == arr[0].length - 1)
            return min;

        if (canIGoRight(arr, col + 1) && canIGoDown(arr, row + 1))
            return max(minPoints(arr, sum, row + 1, col, min), minPoints(arr, sum, row, col + 1, min));

        if (canIGoRight(arr, col + 1))
            return minPoints(arr, sum, row, col + 1, min);

        return minPoints(arr, sum, row + 1, col, min);
    }

    private static boolean canIGoRight(int[][] arr, int col) {
        return col < arr[0].length;
    }

    private static boolean canIGoDown(int[][] arr, int row) {
        return row < arr.length;
    }

    private static int max(int a, int b) {
        if (b > a)
            return b;

        return a;
    }

    public static int minPoints(int[][] arr) {
        return -1 * minPoints(arr, 0, 0, 0, 0) + 1;
    }

    public static boolean findX(int[] arr, int val) { // binary search implementation.
        if (arr == null || arr.length == 1)
            return false;

        int left = 0;
        int right = arr.length - 1;

        while (left != right) { // O(log(n))
            int m = (left + right) / 2;

            if (m - 1 < 0 & arr[m] + arr[m + 1] != val)
                return false;
            if (m + 1 > arr.length - 1 && arr[m] + arr[m - 1] != val)
                return false;

            if (arr[m] + arr[m - 1] == val || arr[m] + arr[m + 1] == val)
                return true;

            if (arr[m] + arr[m - 1] > val)
                right = m - 1;
            else
                left = m + 1;
        }
        return false;
    }

    // ***************************************** 2016A moed A ************************************************* //

    public static int minDiff(int[] arr) {
        return minDiff(arr, sumA(arr, 0, arr.length - 1, 0), 0, arr.length - 1);
    }

    private static int minDiff(int[] arr, int currentMin, int left, int right) {
        int currentD = Math.abs(sumA(arr, left, right, 0) -
                (sumA(arr, 0, left - 1, 0) + sumA(arr, right + 1, arr.length - 1, 0)));

        if (currentD < currentMin)
            currentMin = currentD;

        if (left == right)
            return currentMin;

        return Math.min(minDiff(arr, currentMin, left + 1, right), minDiff(arr, currentMin, left, right - 1));
    }

    private static int sumA(int[] arr, int start, int end, int sum) {
        if (end < 0 || start > arr.length - 1)
            return 0;

        if (start == end)
            return sum + arr[start];

        return sumA(arr, start + 1, end, sum + arr[start]);
    }

    public static int passingCars(int[] arr) { // complexity: O(2n) = O(n)
        // 0 = east
        // 1 = west
        // (a,b) are passing <=> a < b
        if (arr == null)
            return 0;

        int cntWest = countWest(arr); // O(n)
        int total = 0;

        for (int car : arr) { // O(n)
            if (car == 1)
                cntWest--;
            else
                total += cntWest;
        }
        return total;
    }

    private static int countWest(int[] arr) { // O(n)
        int cnt = 0;
        for (int car : arr) {
            if (car == 1)
                cnt++;
        }
        return cnt;
    }

    // ***************************************** 2018A moed A ************************************************* //

    private static int longestSlope(int[][] arr, int row, int col, int num) {
        int nextR = row;
        int nextC = col + 1;
        if (col == arr[0].length) {
            nextC = 0;
            nextR = row + 1;
        }

        if (row == arr.length)
            return 0;

        int thisS = howManyForThis(arr, row, col, num);
        int nextS = longestSlope(arr, nextR, nextC, num);

        return Math.max(thisS, nextS);

    }

    private static int howManyForThis(int[][] arr, int row, int col, int num) {
        int max = 1;
        if (isValidPlace(arr, row, col + 1) && arr[row][col] + num == arr[row][col + 1])
            max = Math.max(max, 1 + howManyForThis(arr, row, col + 1, num));

        if (isValidPlace(arr, row, col - 1) && arr[row][col] + num == arr[row][col - 1])
            max = Math.max(max, 1 + howManyForThis(arr, row, col - 1, num));

        if (isValidPlace(arr, row + 1, col) && arr[row][col] + num == arr[row + 1][col])
            max = Math.max(max, 1 + howManyForThis(arr, row + 1, col, num));

        if (isValidPlace(arr, row - 1, col) && arr[row][col] + num == arr[row - 1][col])
            max = Math.max(max, 1 + howManyForThis(arr, row - 1, col, num));

        return max;

    }

    public static int longestSlope(int[][] arr, int num) {
        return longestSlope(arr, 0, 0, num);
    }

    private static boolean isValidPlace(int[][] arr, int row, int col) {
        return row > -1 && col > -1 && row < arr.length && col < arr[0].length;
    }

    // ***************************************** 2009B ************************************************* //

    public static boolean samePattern(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0 || s1.length() == 0 && s2.length() == 1 && s2.charAt(0) == '*')
            return true;

        if (s2.length() == 0 || s1.length() == 0)
            return false;

        if (s1.charAt(0) != s2.charAt(0) && s2.charAt(0) != '*')
            return false;


        return samePattern(s1.substring(1), s2.substring(1)) || (samePattern(s1.substring(1), s2) && s2.charAt(0) == '*');
    }

    // ****************************************************************************************** //

    public static boolean isPythagorean(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int middle = right - 1;

        while (right != middle) {
            int a = arr[left] * arr[left];
            int b = arr[middle] * arr[middle];
            int c = arr[right] * arr[right];

            if (a + b == c)
                return true;

            if (a + b > c)
                middle--;
            else
                left++;

            if (left == middle) {
                right--;
                left = 0;
                middle = right - 1;
            }
        }
        return false;
    }

    // ****************************************************************************************** //

    private static boolean splitEqualMult(int[] arr, int i, int sum1, int sum2) {
        if (i == arr.length) {

            if (sum1 == sum2)
                return true;

            return false;
        }
        return splitEqualMult(arr, i + 1, sum1 * arr[i], sum2) ||
                splitEqualMult(arr, i + 1, sum1, sum2 * arr[i]);
    }

    public static boolean splitEqualMult(int[] arr) {
        return splitEqualMult(arr, 0, 1, 1);
    }

    private static int calculateRemaining(int[] arr, int i) {
        if (i == arr.length)
            return 1;

        return arr[i] * calculateRemaining(arr, i + 1);
    }

    private static void mirror(int[] arr, int i) {
        if (i < arr.length / 2) {
            int[] temp = copyArr(arr, new int[arr.length]);
            temp[arr.length - 1 - i] = arr[i];
            temp[i] = arr[arr.length - 1 - i];

            mirror(temp, i + 1);

            prinArr(temp);

            mirror(arr, i + 1);
        } else
            prinArr(arr);
    }

    private static void prinArr(int[] arr, int i) {
        if (i == arr.length) {
            System.out.println();
            return;
        }

        System.out.print(arr[i] + " ");

        prinArr(arr, i + 1);
    }

    private static void prinArr(int[] arr) {
        prinArr(arr, 0);
    }

    private static int[] copyArr(int[] arr, int i, int[] new_arr) {
        if (i == arr.length)
            return new_arr;

        new_arr[i] = arr[i];

        return copyArr(arr, i + 1, new_arr);
    }

    private static int[] copyArr(int[] arr, int[] other) {
        return copyArr(arr, 0, other);
    }


    public static void mirror(int[] arr) {
        mirror(arr, 0);
    }

    private static int minChess(int[][] mat, int kingRow, int kingCol, int currentRow, int currentCol, int runs, int[][] places) {
        if (!isValidPlace(mat, currentRow, currentCol) || mat[currentRow][currentCol] == 1)
            return 0;

        if (currentRow == kingRow && currentCol == kingCol) {
//            System.out.println("return" + runs);
            return runs;
        }

        mat[currentRow][currentCol] = 1;

        int up = minNoZero(minChess(mat, kingRow, kingCol, currentRow - 2, currentCol + 1, runs + 1, places),
                minChess(mat, kingRow, kingCol, currentRow - 2, currentCol - 1, runs + 1, places));

        int down = minNoZero(minChess(mat, kingRow, kingCol, currentRow + 2, currentCol + 1, runs + 1, places),
                minChess(mat, kingRow, kingCol, currentRow + 2, currentCol - 1, runs + 1, places));

        int left = minNoZero(minChess(mat, kingRow, kingCol, currentRow + 1, currentCol - 2, runs + 1, places),
                minChess(mat, kingRow, kingCol, currentRow - 1, currentCol - 2, runs + 1, places));

        int right = minNoZero(minChess(mat, kingRow, kingCol, currentRow + 1, currentCol + 2, runs + 1, places),
                minChess(mat, kingRow, kingCol, currentRow - 1, currentCol + 2, runs + 1, places));

        int a = minNoZero(up, down);
        int b = minNoZero(left, right);

        mat[currentRow][currentCol] = 0;
        return minNoZero(a, b);
    }

    public static int minChess(int[][] mat, int kingRow, int kingCol, int knighRow, int knightCol) {
        int[][] places = new int[mat.length][mat[0].length];
        return minChess(mat, kingRow, kingCol, knighRow, knightCol, 0, places);
    }

    private static int minNoZero(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        if (a > b)
            return b;
        else
            return a;
    }

    // ***************************************** 2012A A2 ************************************************* //
    private static boolean isSumOf(int[] arr, int i, int sumToFind, String s) {
        if (i == arr.length || sumToFind < 0)
            return false;

        if (sumToFind == 0) {
            System.out.println(s);
            return true;
        }

        return isSumOf(arr, i, sumToFind - arr[i], s + arr[i] + " ") | isSumOf(arr, i + 1, sumToFind, s);
    }

    public static boolean isSumOf(int[] arr, int n) {
        return isSumOf(arr, 0, n, "");
    }

    public static int count(int[] arr, int n) { // O(log(n))
        int leftInd = searchIndex(arr, n, true); // logn
        int rightInd = searchIndex(arr, n, false); // logn

        if (leftInd == -1 && rightInd == -1)
            return 0;
        if (leftInd == -1 || rightInd == -1)
            return 1;

        return rightInd - leftInd + 1;
    }

    private static int searchIndex(int[] arr, int n, boolean isLeftSide) { // logn
        int left = 0;
        int right = arr.length - 1;

        while (right >= left) { // binary search implementation
            int m = (left + right) / 2;
            boolean isEdge = (m == arr.length - 1 || m == 0);

            if (arr[m] == n) {
                if (m == 0 && isLeftSide || m == arr.length - 1 && !isLeftSide)
                    return m;

                if (!isEdge && arr[m] > arr[m - 1] && isLeftSide)
                    return m;
                if (!isEdge && arr[m] < arr[m + 1] && !isLeftSide)
                    return m;

                if (isLeftSide)
                    right = m - 1;
                else
                    left = m + 1;
            }

            if (arr[m] > n)
                right = m - 1;
            else
                left = m + 1;
        }
        return -1;
    }

    // ********************************************** 2015A 91B ******************************************** //
    private static int makeSum(int[] lengths, int k, int num, int i) {
        if (i == lengths.length || num < 0 || k < 0)
            return 0;

        if (k == 0 && num == 0)
            return 1;

        return makeSum(lengths, k - lengths[i], num - 1, i) + makeSum(lengths, k, num, i + 1);
    }

    public static int makeSum(int[] lengths, int k, int num) {
        return makeSum(lengths, k, num, 0);
    }

    public static int[] crossSort(int[] arr) { // time: O(2n) = O(n). space: O(n).
        int leftPointerOdd = 1;
        int rightPointerOdd = (arr.length - 1) % 2 == 0 ? arr.length - 2 : arr.length - 1;

        while (leftPointerOdd < rightPointerOdd) { // O(n)
            int lowestOdd = arr[rightPointerOdd];
            arr[rightPointerOdd] = arr[leftPointerOdd];
            arr[leftPointerOdd] = lowestOdd;

            leftPointerOdd += 2;
            rightPointerOdd -= 2;
        }
        prinArr(arr);
        int[] tmp = new int[arr.length];
        int iEven = 0, iOdd = 1;

        for (int i = 0; i < arr.length; i++) { // merge sort implementation.
            if (iEven > arr.length - 1) {
                tmp[i] = arr[iOdd];
                iOdd += 2;
            } else if (iOdd > arr.length - 1) {
                tmp[i] = arr[iEven];
                iEven += 2;
            } else if (arr[iEven] < arr[iOdd]) {
                tmp[i] = arr[iEven];
                iEven += 2;
            } else {
                tmp[i] = arr[iOdd];
                iOdd += 2;
            }
        }
        prinArr(tmp);
        return tmp;
    }

    public static void printClosest(int[] first, int[] second, int x) {
        int left = 0;
        int right = first.length - 1;

        int closest = Math.abs(x - (first[right] + second[left]));
        int a = 0;
        int b = 0;

        while (left < second.length && right > -1) {
            if (first[right] + second[left] == x) {
                System.out.println(a + " AND " + b);
                return;
            }

            if (Math.abs(x - (first[right] + second[left])) < closest) {
                closest = Math.abs(x - (first[right] + second[left]));
                a = first[right];
                b = second[left];
            }

            if (first[right] + second[left] > x) {
                right--;
            } else
                left++;
        }
        System.out.println(a + " AND " + b);
    }

    // ********************************************** 2017A 90B ******************************************** //

    public static int findSmallest(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; i++) { // O(n)
            if (arr[i] - sum > 1)
                return sum + 1;
            sum += arr[i];
        }
        return sum + 1;
    }

    private static int c(int[][] mat, int sum, int row, int col, int maxSum) {
        if (row > mat.length - 1 || col > mat[0].length - 1)
            return smallestNum(mat, 0, 0, 0) * mat.length * mat[0].length;

        if (row == mat.length - 1 && col == mat[0].length - 1) {
            return maxSum;
        }

        if (sum + mat[row][col] < maxSum)
            maxSum = sum + mat[row][col];

        return Math.max(
                c(mat, sum + mat[row][col], row + 1, col, maxSum),
                c(mat, sum + mat[row][col], row, col + 1, maxSum)
        );
    }

    public static int c(int[][] mat) {
        int v = c(mat, 0, 0, 0, 0);

        return v > 0 ? 0 : v * -1 + 1;
    }

    private static int smallestNum(int[][] mat, int row, int col, int min) {
        if (col == mat[0].length) {
            row++;
            col = 0;
        }

        if (row == mat.length)
            return min;

        if (min > mat[row][col])
            min = mat[row][col];

        return smallestNum(mat, row, col + 1, min);
    }

    static private boolean covers(int[][] mat, int[] arr, int k, int row, int col, int itemsToFind) {
        if (itemsToFind == 0 && k > 0)
            return true;

        if (k == 0 || row == mat.length)
            return false;

        int i = checkRow(mat[row][col], arr, 0);
        if (i != -1) {
            itemsToFind--;
            arr[i] *= -1;
        }

        if (col == arr.length) {
            col = 0;
            row++;
            k--;
        }

        boolean a = covers(mat, arr, k, row, col + 1, itemsToFind);
//       arr[i] *= -1;
        col = -1;
        boolean b = covers(mat, arr, k, row + 1, col + 1, itemsToFind);

        return a || b;
    }

    static private int checkRow(int x, int[] arr, int i) {
        if (i == arr.length)
            return -1;

        if (arr[i] == x)
            return i;

        return checkRow(x, arr, i + 1);
    }

    public static boolean covers(int[] arr, int[][] mat, int k) {
        return covers(mat, arr, k, 0, 0, arr.length);
    }

    private static void fillHadamard(int[][] mat, int n, int row, int col) {
        if (n == 1) {
            mat[row][col] = 1;
            return;
        }

        fillHadamard(mat, n - 1, row, col);

        fillQuarters(mat, row, col, n);
    }

    private static void fillQuarters(int[][] mat, int row, int col, int n) {
        if (col == n / 2) {
            row++;
            col = 0;
        }

        if (row == n / 2)
            return;

        mat[row][col + n / 2] = mat[row][col]; // right upper quarter
        mat[row + n / 2][col] = mat[row][col]; // left bottom quarter
        mat[row + n / 2][col + n / 2] = mat[row][col] * -1; // left bottom quarter

        fillQuarters(mat, row, col + 1, n);
    }

    public static void fillHadamard(int[][] mat, int n) {
        fillHadamard(mat, n, 0, 0);
        printMat(mat);
    }

    private static void printMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    public static int alternating(String s) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) == '1')
                sum1++;
            else if (s.charAt(i) == '0')
                sum2++;
        }
        return Math.min(sum1, sum2);
    }

    private static void printArray(boolean[][] arr) {
        for (boolean[] a : arr) {
            for (boolean b : a)
                System.out.print((b ? 1 : 0) + " ");
            System.out.println();
        }
    }

    private static int oneFiveSever(int n, int cnt) {
        if (n == 0)
            return cnt;
        if (n < 0)
            return -1;


        int a = oneFiveSever(n - 1, cnt + 1);
        int b = oneFiveSever(n - 5, cnt + 1);
        int c = oneFiveSever(n - 7, cnt + 1);

        return min(min(a, b), c);
    }

    public static int oneFiveSever(int n) {
        return oneFiveSever(n, 0);
    }

    private static int min(int a, int b) {
        if (a < 0 && b < 0)
            return -1;
        if (a < 0)
            return b;
        if (b < 0)
            return a;

        if (a < b)
            return a;
        return b;
    }

    public static int maximalDrop(int[] arr) {
        int maxDrop = 0;
        int maxHill = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxHill)
                maxHill = arr[i];

            else if (maxHill - arr[i] > maxDrop)
                maxDrop = maxHill - arr[i];
        }
        return maxDrop;
    }


    // ********************************************** 2018 ******************************************** //


    public static int smallestSub(int[] arr, int x) {
        int total = 0;
        for (int i = 0; i < arr.length; i++)
            total += arr[i];

        int left = 0;
        int right = arr.length - 1;
        int min;
        if (x > total)
            return arr.length + 1;
        else
            min = arr.length;

        while (left < right) {
            if (total - arr[right] - arr[left] > x) {
                min -= 2;
                total -= (arr[right] + arr[left]);
                left++;
                right--;
            } else if (total - arr[right] > x) {
                min--;
                total -= arr[right];
                right--;
            } else if (total - arr[left] > x) {
                min--;
                total -= arr[left];
                left++;
            } else
                return min;
        }
        return min;
    }


    public static int howManySorted(int n, int max, int min) {
        if (n == 0)
            return 1;

        if (min == max + 1)
            return 0;

        int a = howManySorted(n, max, min + 1);
        int b = howManySorted(n - 1, max, min);

        return a + b;
    }

    public static boolean what(int[] arr, int x) {
        int total = 0;
        for (int i = 0; i < arr.length; i++)
            total += arr[i];

        if (total < x)
            return false;

        int left = 0, right = arr.length - 1;

        while (left < right && total != x) {
            if (total - (arr[right] + arr[left]) == x || total - arr[right] == x || total - arr[left] == x)
                return true;
            if (total - (arr[right] + arr[left]) > x) {
                total -= (arr[right] + arr[left]);
                left++;
                right--;
            } else if (total - arr[right] > x) {
                total -= arr[right];
                right--;
            } else if (total - arr[left] > x) {
                total -= arr[left];
                left++;
            } else
                return false;
        }
        return total == x;
    }


    private static int cheapestRoute(int[] arr, int i, int sum) {
        if (sum == 49)
            System.out.println();

        if (i > arr.length - 1)
            return -1;

        if (i == arr.length - 1)
            return sum + arr[i];

        return min(cheapestRoute(arr, i + 1, sum + arr[i]), cheapestRoute(arr, i + 2, sum + arr[i]));
    }

    public static int cheapestRoute(int[] arr) {
        return cheapestRoute(arr, 0, 0);
    }

    private static int where(int[] arr, int left, int right, int m) {
        if (m == arr.length)
            return -1;

        if (sumA(arr, left, m - 1, 0) == sumA(arr, m, right, 0))
            return m;

        return where(arr, left, right, m + 1);
    }

    public static int where(int[] arr) {
        return where(arr, 0, arr.length - 1, 1);
    }

    private static int shortestPath(int[][] mat, int row, int col, int lastVal, int path) {
        if (row >= mat.length || row <= -1 || col <= -1 || col >= mat[0].length || lastVal > mat[row][col] || mat[row][col] == -1)
            return -1;

        if (row == mat.length - 1 && col == mat[0].length - 1)
            return path;

        int temp = mat[row][col];
        mat[row][col] = -1;

        int a = min(shortestPath(mat, row + 1, col, temp, path + 1),
                shortestPath(mat, row, col + 1, temp, path + 1));
        int b = min(shortestPath(mat, row - 1, col, temp, path + 1),
                shortestPath(mat, row, col - 1, temp, path + 1));

        return min(a, b);
    }

    public static int shortestPath(int[][] mat) {
        return shortestPath(mat, 0, 0, -1, 1);
    }


    private static int longOrdNum(String s, int longest, int last, int cnt) {
        if (s.length() == 0)
            return longest;

        if (s.charAt(0) > '9' || s.charAt(0) <= last)
            cnt = 0;

        if (s.charAt(0) <= '9' && s.charAt(0) > last)
            cnt++;

        if (cnt > longest)
            longest = cnt;

        return longOrdNum(s.substring(1), longest, s.charAt(0), cnt);
    }

    public static int longOrdNum(String s) {
        return longOrdNum(s, 0, 0, 0);
    }

    private static boolean balancedPartition(int[] arr, int i, int sum1, int sum2, int cnt1, int cnt2) {
        if (i == arr.length) {
            if (cnt1 == cnt2 && sum1 == sum2)
                return true;

            return false;
        }
        return balancedPartition(arr, i + 1, sum1 + arr[i], sum2, cnt1 + 1, cnt2) ||
                balancedPartition(arr, i + 1, sum1, sum2 + arr[i], cnt1, cnt2 + 1);
    }

    public static boolean balancedPartition(int[] arr) {
        return balancedPartition(arr, 0, 0, 0, 0, 0);
    }

    public static int included(int x, int y) {
        int[] temp = new int[10];

        while (y != 0) {
            temp[y % 10]++;
            y /= 10;
        }

        int[] temp2 = new int[10];
        while (x != 0) {
            int val = x % 10;
            if (temp[val] == 0)
                temp2[val]++;
            x /= 10;
        }

        int cnt = 0;
        for (int i = 0; i < 10; i++)
            if (temp2[i] != 0)
                cnt++;

        return cnt;
    }

    private static int count(int sum, int left) {
        if (sum == 0)
            return 1;

        if (sum < 0 || left == 0)
            return 0;

        return count(sum - left, left - 1) + count(sum, left - 1);
    }

    public static int count(int sum) {
        return count(sum, sum);
    }

    private static int countRopesForVal(int[][] mat, int row, int col) {
        if (row == mat.length || col == 0 || col == mat[0].length)
            return 0;

        if (row == mat.length - 1)
            return 1;

        return countRopesForVal(mat, row + 1, col + 1) + countRopesForVal(mat, row + 1, col - 1) +
                countRopesForVal(mat, row + 1, col);
    }

    private static int countRopes(int[][] mat, int row, int col) {
        if (col == mat[0].length)
            return 0;

        return countRopesForVal(mat, row, col) + countRopes(mat, row, col + 1);
    }

    public static int countRopes(int[][] mat) {
        return countRopes(mat, 0, 0);
    }

}
