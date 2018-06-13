/**
 * Created by ishayhilzenrat on 12/06/2018.
 */
public class Tester {
    public static void main(String[] args) {

        BigNumber b = new BigNumber(1995);
        BigNumber b1 = new BigNumber(71);
//        System.out.println(b.subtractBigNumber(b1));

        System.out.println(b.compareTo(b1));

//        System.out.println(b.multBigNumber(b1));


        BigNumber bigNumber2 = new BigNumber(1234567890);
        BigNumber bigNumber3 = new BigNumber(645654464);

        System.out.println(bigNumber2.multBigNumber(bigNumber3));

//        System.out.println(bigNumber2.multiple(1000000, bigNumber2));
//        System.out.println(bigNumber2.multiple(12, b1));
        System.out.println("should be " + "797104269289560960");

    }
}
