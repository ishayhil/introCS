/**
 * Created by ishayhilzenrat on 12/04/2018.
 */
public class Tester {

    private static int[] range(int start, int finish) {
        int[] lst = new int[finish];
        for (int i = start; i < finish; i++) {
            lst[i] = i;
        }
        return lst;
    }


    public static void main(String[] args) {
//
//        int[] lst = range(0, 200);
//
//        for (int n : lst) {
//                System.out.println(n);
//        }
//    }

        Book b1 = new Book("a", "book1", 1995, 1);
        b1.borrowBook("ishay", new Date(1,1,2018));
        Book b2 = new Book("a", "book2", 2001, 1);
        Book b3 = new Book("a", "book3", 2000, 1);

        Library l1 = new Library();

        l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);
        l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);l1.addBook(b1);

        l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);
        l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);l1.addBook(b2);

        l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);
        l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);
        l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);l1.addBook(b3);

        System.out.println(l1.mostPopularAuthor());
        System.out.println(l1.oldestBook());

        System.out.println(l1.howManyBooksBorrowed());
        System.out.println(l1.howManyBorrowedAtDate(new Date(1,1,2018)));
        System.out.println(l1.howManyBorrowedToStudent(null));

        System.out.println(l1);
    }
}
