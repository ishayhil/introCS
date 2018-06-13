/**
 * Created by ishayhilzenrat on 12/06/2018.
 */
public class Tester {
    public static void main(String[] args) {

        BigNumber b = new BigNumber(1995);
        BigNumber b1 = new BigNumber(89);
//        System.out.println(b.subtractBigNumber(b1));

        System.out.println(b.compareTo(b1));

        System.out.println(b.multBigNumber(b1));

    }
}
