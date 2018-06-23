/**
 * practice of: http://www.tau.ac.il/~csedu/itzuv/itzuv_ch1_requ.pdf
 *
 * @author ishayhilzenrat
 */

public class Practice {
    // *****************************************************************************************************
    public static int printBelow(int n, int currentN) {

        if (currentN == n)
            return n;

        if (currentN % 2 == 0)
            System.out.println(currentN);

        return printBelow(n, currentN + 1);
    }

    public static int sumDigits(int n) {
        if (n / 10 == 0)
            return n;

        return n % 10 + sumDigits(n / 10);
    }

    public static int multiple(int a, int b) {
        if (b >= 0) {
            if (b == 0)
                return 0;
            return a + multiple(a, b - 1);

        } else {
            if (b == 0)
                return 0;
            return (a + multiple(a, b + 1)) * -1;
        }
    }

    public static int divide(int a, int b) {
        if (b > a)
            return 0;
        if (b == a)
            return 1;

        return 1 + divide(a - b, b);
    }

    public static int sumArray(int[] arr, int ind) {
        if (ind > arr.length - 1)
            return 0;

        return arr[ind] + sumArray(arr, ind + 1);
    }

    public static int base(int n, int a) {
        if (Math.pow(10, n) < a)
            return -1;
        if (Math.pow(10, n) == a)
            return 0;

        return 1 + base(n, a * 2);
    }

    public static boolean isSwitchNum(int a, int lstNum) {
        int num = a % 10;
        if (a == 0)
            return true;

        if (lstNum % 2 == num % 2 && lstNum != -1)
            return false;

        return isSwitchNum(a / 10, num);
    }

    public static boolean isEvenPair(int a, int lastNum) {
        int num = a % 10;
        if (a == 0)
            return false;
        if (num % 2 == lastNum % 2 && lastNum != -1)
            return true;

        return isEvenPair(a / 10, a);
    }

    public static int mod(int a, int b) { // = b `mod` a
        if (a == 0)
            return 0;
        if (b > a)
            return a;

        return mod(a - b, b);
    }

    public static boolean isPoly(String s) {
        return isPoly(s, 0, s.length() - 1);
    }

    private static boolean isPoly(String s, int leftI, int rightI) {
        if (s.charAt(leftI) != s.charAt(rightI))
            return false;

        if (leftI == rightI || leftI - rightI == 1)
            return true;

        return isPoly(s, leftI + 1, rightI - 1);
    }

    public static int printUpSideDown(int n) {
        if (n == 0)
            return 0;

        System.out.print(n % 10);

        return printUpSideDown(n / 10);
    }

    // *****************************************************************************************************

    public static int firstSeries(int n) {
        if (n < 4)
            return n;

        if (n % 2 == 0)
            return firstSeries(n - 1) + firstSeries(n - 2) + firstSeries(n - 3);

        return Math.abs(firstSeries(n - 1) - firstSeries(n - 3));
    }

    public static int getDown(int n, int current) {
        if (current == n - 1)
            return 1;
        if (current == n - 2)
            return 2;

        return getDown(n, current + 1) + getDown(n, current + 2);
    }

    public static String binaryPrint(int n, String s) {
        if (n == 0) {
            System.out.println(s);
            return s;
        }

        return binaryPrint(n - 1, '1' + s) + binaryPrint(n - 1, '0' + s);
    }

    public static String binaryPrintNo11(int n, String s) {
        if (n == 0) {
            System.out.println(s);
            return s;
        }

        if (s.length() > 0 && s.charAt(s.length() - 1) == '1')
            return binaryPrintNo11(n - 1, s + '0');

        return binaryPrintNo11(n - 1, s + '1') + binaryPrintNo11(n - 1, s + '0');
    }

    public static int sumArray(int[] arr, int left, int right) {
        if (left == right)
            return arr[left];

        int l = sumArray(arr, left, (left + right) / 2);
        int r = sumArray(arr, (left + right) / 2 + 1, right);

        return l + r;
    }

    public static boolean isSorted(int[] arr) {
        return isSorted(arr, 0, arr.length - 1);
    }


    private static boolean isSorted(int[] arr, int left, int right) {
        System.out.println(left + "," + right);
        int left_run, right_run;
        if ((left + right) % 2 != 0) {// even str len
            left_run = (left + right) / 2;
            right_run = (left + right) / 2 + 1;
        } else { // odd str len
            left_run = (left + right) / 2 - 1;
            right_run = (left + right) / 2;
        }

        if (left == right)
            return true;

        if (sumArray(arr, left, left_run) > sumArray(arr, right_run, right)) {
            return false;
        }

        return isSorted(arr, left, right - 1) && isSorted(arr, left + 1, right);
    }

    private static int biggestDif(int[] arr, int left, int right, int max) {
        int val = Math.abs(arr[left] - arr[right]);

        if (val > max)
            max = val;

        if (left == right)
            return max;

        return Math.max(biggestDif(arr, left + 1, right, max), biggestDif(arr, left, right - 1, max));
    }

    public static int biggestDif(int[] arr) {
        return biggestDif(arr, 0, arr.length - 1, -1);
    }




}
