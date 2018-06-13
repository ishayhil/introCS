import jdk.nashorn.internal.ir.IndexNode;

/**
 * Created by ishayhilzenrat on 12/06/2018.
 */

public class BigNumber {
    private IntNode head;

    public BigNumber() {
        head = new IntNode(0, null);
    }

    public BigNumber(long number) {
        long current;
        head = new IntNode((int) number % 10, null);
        IntNode temp = head;

        number /= 10;
        while (number != 0) {
            current = number % 10;

            temp.setNext(new IntNode((int) current, null));
            temp = temp.getNext();
            number /= 10;
        }
    }

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

    public void printIt() {
        IntNode temp = head;
        while (temp != null) {
            System.out.print(temp.getValue() + " -> ");
            temp = temp.getNext();
        }
        System.out.println("null");
    }

    public String toString() {
        return toString(head);
    }

    private String toString(IntNode temp) { // O(n)
        if (temp.getNext() == null)
            return temp.getValue() + ""; // when reached end, return it

        return toString(temp.getNext()) + temp.getValue(); // when going back, add all other digits.
    }

    public BigNumber addBigNumber(BigNumber other) {
        if (other == null)
            return new BigNumber(this); // nothing to add.

        IntNode tempThis = head;
        IntNode tempOther = other.head;

        int result = tempOther.getValue() + tempThis.getValue();
        int add = result / 10;
        BigNumber b = new BigNumber(result % 10); // defining the head

        IntNode tempNew = b.head;

        while (tempThis.getNext() != null || tempOther.getNext() != null) {
            int tempOtherVal = tempOther.getNext() != null ? tempOther.getNext().getValue() : 0;
            int tempThisVal = tempThis.getNext() != null ? tempThis.getNext().getValue() : 0;

            result = tempOtherVal + tempThisVal + add;

            tempNew.setNext(new IntNode(result % 10, null));
            add = result / 10;

            if (tempThis.getNext() != null && tempOther.getNext() != null) {
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
        return b;
    }

    public BigNumber addLong(long num) {
        return this.addBigNumber(new BigNumber(num));
    }

    private int length() {
        int cnt = 0;
        IntNode temp = head;

        while (temp.getNext() != null) {
            cnt++;
            temp = temp.getNext();
        }
        return cnt;
    }

    public int compareTo(BigNumber other) {
        if (other == null)
            return 1;

        int lenThis = length();
        int lenOther = other.length();

        if (lenThis > lenOther)
            return 1;
        else if (lenOther > lenThis)
            return -1;

        return recursiveComparator(head, other.head);
    }

    private int recursiveComparator(IntNode tempThis, IntNode tempOther) {
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

    public BigNumber subtractBigNumber(BigNumber other) {
        IntNode small, big; // head alias

        if (this.compareTo(other) == 1) {
            big = this.head;
            small = other.head;
        } else {
            big = other.head;
            small = this.head;
        }

        int result = big.getValue() - small.getValue();
        int toSubtract = 0;
        if (result < 0) {
            result += 10;
            toSubtract = 1;
        }

        BigNumber b = new BigNumber(result);
        IntNode tempB = b.head;

        while (big.getNext() != null) {
            int bigV = big.getNext().getValue() - toSubtract;
            int smallV = small.getNext() != null ? small.getNext().getValue() : 0;

            result = bigV - smallV;

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
        b.removeZero(b.head);
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

    public BigNumber multBigNumber(BigNumber other) {
        IntNode big, small;
        BigNumber b = new BigNumber();
        int tens = 0;

        if (other == null)
            return new BigNumber(this);

        IntNode tempThis = head;

        while (tempThis != null) {
            BigNumber temp = multiple(tempThis.getValue(), other);

            b = b.addBigNumber(temp.multiple((int) Math.pow(10, tens), temp));
            tens += 1;

            tempThis = tempThis.getNext();
        }
        return b;
    }

    public BigNumber multiple(int x, BigNumber y) {
        BigNumber b = new BigNumber();
        IntNode tempB = b.head;
        IntNode tempY = y.head;

        int result = tempY.getValue() * x;
        int add = result / 10;
        tempB.setValue(result % 10);

        while (tempY.getNext() != null) {
            int valY = tempY.getNext().getValue();

            result = valY * x + add;

            tempB.setNext(new IntNode(result % 10, null));
            add = result / 10;

            tempY = tempY.getNext();
            tempB = tempB.getNext();
        }

        if (add > 0)
            tempB.setNext(new IntNode(add, null));

        return b;
    }


}
