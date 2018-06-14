/**
 * BigNubmer class lets the user store a very big number in a connected list. It uses 3 constrictors: copy, build one from long type, and empty.
 *
 * @author Ishay Hilzenrat
 * @version 1.0
 */

public class BigNumber {
    private IntNode head;

    /**
     * empty constructor. Head value = 0.
     */
    public BigNumber() {
        head = new IntNode(0, null);
    }

    /**
     * Makes a connected list from a given long. The last digit will be the head, and so on.
     *
     * @param number long to make the connected list from.
     */
    public BigNumber(long number) {
        long current;
        head = new IntNode((int) number % 10, null);
        IntNode temp = head;

        number /= 10; // takes the last digit from each number, then remove if from the big number, until finished.
        while (number != 0) {
            current = number % 10;

            temp.setNext(new IntNode((int) current, null));
            temp = temp.getNext();
            number /= 10;
        }
    }

    /**
     * Copy constructor.
     * Time comp: O(n). Space comp O(1).
     *
     * @param other BingNumber
     */
    public BigNumber(BigNumber other) {
        if (other != null && other.head != null) {
            IntNode temp1 = other.head;
            head = new IntNode(temp1.getValue(), null);
            IntNode temp2 = head;

            while (temp1.getNext() != null) {
                temp2.setNext(new IntNode(temp1.getNext().getValue(), null));
                temp1 = temp1.getNext();
                temp2 = temp2.getNext();
            }
        }
    }

    /**
     * @return String of the full number (as it should look). O(n) time comp. O(1) space comp.
     */
    public String toString() {
        return toString(head);
    }

    private String toString(IntNode temp) { // time comp O(n)
        if (temp.getNext() == null)
            return temp.getValue() + ""; // when reached end, return it

        return toString(temp.getNext()) + temp.getValue(); // when going back, add all other digits.
    }

    /**
     * adds a big number to the current object.
     * Time comp: O(n). n = longer number digit length.
     * Space comp: O(1).
     *
     * @param other BigNumber to add
     * @return BigNumber the sum of other and this.
     */
    public BigNumber addBigNumber(BigNumber other) {
        if (other == null)
            return new BigNumber(this); // nothing to add.

        IntNode tempThis = head;
        IntNode tempOther = other.head;

        int result = tempOther.getValue() + tempThis.getValue();
        int add = result / 10;
        BigNumber b = new BigNumber(result % 10); // defining the head

        IntNode tempNew = b.head;

        while (tempThis.getNext() != null || tempOther.getNext() != null) { //
            int tempOtherVal = tempOther.getNext() != null ? tempOther.getNext().getValue() : 0; // if reached end, val=0.
            int tempThisVal = tempThis.getNext() != null ? tempThis.getNext().getValue() : 0;

            result = tempOtherVal + tempThisVal + add;

            tempNew.setNext(new IntNode(result % 10, null));
            add = result / 10;

            if (tempThis.getNext() != null && tempOther.getNext() != null) { // choosing who to progress
                tempOther = tempOther.getNext();
                tempThis = tempThis.getNext();
            } else if (tempThis.getNext() != null)
                tempThis = tempThis.getNext();
            else
                tempOther = tempOther.getNext();

            tempNew = tempNew.getNext();
        }
        if (add > 0)
            tempNew.setNext(new IntNode(add, null)); // adding the last 'add' digit if exists

        b.removeZero(b.head); // O(n)
        return b;
    }

    /**
     * adds a long to the current object.
     * Time comp: O(n). n = longer number digit length.
     * Space comp: O(1).
     *
     * @param num long add
     * @return BigNumber the sum of long and this.
     */
    public BigNumber addLong(long num) {
        return this.addBigNumber(new BigNumber(num));
    }

    private int length() { // O(n)
        int cnt = 0;
        IntNode temp = head;

        while (temp.getNext() != null) {
            cnt++;
            temp = temp.getNext();
        }
        return cnt;
    }

    /**
     * compares between 2 BigNumber objects.
     * Time comp: O(n). n = longer number digit length.
     * Space comp: O(1)
     *
     * @param other BigNumber object.
     * @return 1 if this > other. 0 if this == other. -1 if this < other.
     */
    public int compareTo(BigNumber other) {
        if (other == null)
            return 1;

        int lenThis = length();
        int lenOther = other.length();

        if (lenThis > lenOther) // if not same length, one must be bigger than the other.
            return 1;
        else if (lenOther > lenThis)
            return -1;

        return recursiveComparator(head, other.head);
    }

    private int recursiveComparator(IntNode tempThis, IntNode tempOther) { // O(n)
        if (tempThis.getNext() == null) {
            if (tempThis.getValue() > tempOther.getValue())
                return 1;
            if (tempThis.getValue() < tempOther.getValue())
                return -1;

            return 0;
        }

        int a = recursiveComparator(tempThis.getNext(), tempOther.getNext()); // the end, the highest number
        // starting going back:
        if (a != 0)
            return a;

        if (tempThis.getValue() > tempOther.getValue())
            return 1;
        if (tempThis.getValue() < tempOther.getValue())
            return -1;

        return 0;
    }

    /**
     * subtract this object and other BigNumber object (abs).
     * Time comp: O(n). n = longer BigNumber digit length.
     * Space comp: O(1).
     *
     * @param other BugNumber to subtract
     * @return the product of subtracting these two objects.
     */
    public BigNumber subtractBigNumber(BigNumber other) {
        IntNode small, big; // head alias

        if (this.compareTo(other) == 1) { // temps for the bigger and smaller numbers.
            big = this.head;
            small = other.head;
        } else {
            big = other.head;
            small = this.head;
        }

        int result = big.getValue() - small.getValue(); // settings the temp product head.
        int toSubtract = 0;
        if (result < 0) {
            result += 10;
            toSubtract = 1;
        }

        BigNumber b = new BigNumber(result);
        IntNode tempB = b.head; // temp product head.

        while (big.getNext() != null) { // O(n)
            int bigV = big.getNext().getValue();
            int smallV = small.getNext() != null ? small.getNext().getValue() : 0;

            result = bigV - smallV - toSubtract;

            toSubtract = 0;
            if (result < 0) {
                result += 10;
                toSubtract = 1;
            }

            tempB.setNext(new IntNode(result, null));

            if (small.getNext() != null)
                small = small.getNext();

            big = big.getNext();
            tempB = tempB.getNext();
        }
        b.removeZero(b.head); // O(n)
        return b;
    }

    private boolean removeZero(IntNode temp) { // O(n)
        if (temp.getNext() == null && temp.getValue() == 0) {
            if (temp.getValue() == 0)
                return true;
        }

        if (temp.getNext() == null)
            return false;

        boolean isZero = removeZero(temp.getNext());

        if (isZero) {
            temp.setNext(null);
            if (temp.getValue() == 0)
                return true;
        }
        return false;
    }

    /**
     * multiplies two BigNumber object (this and other).
     * Time comp: O(n^2). n = longer BigNumber digit length.
     * Space comp: O(1).
     *
     * @param other BigNumber to multiple.
     * @return BigNumber product of the multiple.
     */
    public BigNumber multBigNumber(BigNumber other) {
        BigNumber b = new BigNumber();
        int tens = 0;

        if (other == null || other.head == null)
            return new BigNumber(this);

        IntNode tempOther = other.head;

        while (tempOther != null) { // O(n^2)
            BigNumber temp = multiple(tempOther.getValue()); // O(n)
            temp.addZeros(tens); // multiple by 10^tens

            b = b.addBigNumber(temp); // sums the result
            tens += 1;

            tempOther = tempOther.getNext();
        }
        return b;
    }

    private void addZeros(int n) {
        while (n != 0) {
            head = new IntNode(0, head);
            n--;
        }
    }

    private BigNumber multiple(int x) { // multiple an int to this object by multiplying each of it's digits.
        BigNumber b = new BigNumber();
        IntNode tempB = b.head;
        IntNode tempThis = this.head;

        int result = tempThis.getValue() * x;
        int add = result / 10;
        tempB.setValue(result % 10);

        while (tempThis.getNext() != null) { // O(n)
            int valY = tempThis.getNext().getValue();

            result = valY * x + add;

            tempB.setNext(new IntNode(result % 10, null));
            add = result / 10;

            tempThis = tempThis.getNext();
            tempB = tempB.getNext();
        }

        if (add > 0)
            tempB.setNext(new IntNode(add, null)); // adding the last add to the end.

        return b;
    }


}
