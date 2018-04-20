import java.util.Arrays;

/**
 * Created by ishayhilzenrat on 14/04/2018.
 */

public class Library {
    private final int MAX_BOOKS = 200;

    private Book[] _lib = new Book[MAX_BOOKS];
    private int _noOfBooks = 0;

    /**
     * Library class public constructor. Creates object with array of 200 Books (all null to begin with).
     */
    public Library() {
    }

    /**
     * Adds a new book to the Book array.
     *
     * @param book other Book to add to the Book array.
     * @return true if the book was added. false if wasn't added.
     */
    public boolean addBook(Book book) {
        if (isNull(book))
            return false;

        for (int i = 0; i < _lib.length; i++) {
            if (isNull(_lib[i])) {
                _lib[i] = new Book(book);
                _noOfBooks++;
                return true;
            }
        }
        return false;
    }

    /**
     * removes a given book from the Book array. after, arranges so there won't be any 'gaps' inside of it (nulls at the end).
     *
     * @param book other Book to add.
     */
    public void removeBook(Book book) {
        if (!isNull(book)) {
            for (int i = 0; i < _lib.length; i++) {
                if (!isNull(_lib[i]) && _lib[i].equals(book)) {
                    _lib[i] = null;
                    _noOfBooks--;
                }
            }
        }
        arrangeArray();
    }

    /**
     * returns the number of book in the Book array that are borrowed at the moment.
     *
     * @return int. number for borrowed books.
     */
    public int howManyBooksBorrowed() {
        int numB = 0;
        for (Book book : _lib) {
            if (isNull(book))
                return numB;
            else if (book.getBorrowed())
                ++numB;
        }
        return numB;
    }

    /**
     * returns the number of book in the Book array that are borrowed at a specific date.
     *
     * @param date date of borrow to search upon.
     * @return int. number for borrowed books at the given date.
     */
    public int howManyBorrowedAtDate(Date date) {
        int numB = 0;
        for (Book book : _lib) {
            if (isNull(date) || isNull(book))
                return numB;
            if (date.equals(book.getBorrowedDate()))
                ++numB;
        }
        return numB;
    }

    /**
     * returns the number of book in the Book array that are borrowed to a specific student.
     *
     * @param studentName name of student to search upon.
     * @return int. number for borrowed books at the given student.
     */
    public int howManyBorrowedToStudent(String studentName) {
        int numB = 0;
        for (Book book : _lib) {
            if (isNull(book) || isNull(studentName))
                return numB;
            if (studentName.equalsIgnoreCase(book.getStudentName()))
                ++numB;
        }
        return numB;
    }

    /**
     * returns the most popular author in the Book array.
     *
     * @return String. the name of the most popular author.
     */
    public String mostPopularAuthor() {
        return longestSequence(_lib);
    }

    /**
     * returns the oldest Book in the Book array by his year of publish.
     *
     * @return Book. new object of the oldest book.
     */
    public Book oldestBook() {
        if (isNull(_lib[0])) // first one is null -> all is null.
            return null;

        Book oldest = new Book(_lib[0]);

        for (Book book : _lib) {
            if (isNull(book)) // due to the fact that there are not null between two books, once reached null; break loop.
                return new Book(oldest);
            if (book.getYear() < oldest.getYear())
                oldest = new Book(book);
        }
        return new Book(oldest);
    }

    /**
     * overrides the class toString method. Will return all the Books (using the toString Book method) in the Book array.
     *
     * @return String. All the Books title, author, year of publish and # of pages that are in the library. No nulls.
     */
    public String toString() {
        StringBuilder books = new StringBuilder();

        for (Book book : _lib) {
            if (!isNull(book))
                books.append(book).append("\n");
        }

        return books.toString();
    }


    ////////////// private methods //////////////

    private boolean isNull(Book book) {
        return book == null;
    }

    private boolean isNull(Date date) {
        return date == null;
    }

    private boolean isNull(String str) {
        return str == null;
    }

    private void arrangeArray() { // appending the non null books into the temp array.
        Book[] temp = new Book[MAX_BOOKS];
        int j = 0; // temp index starting
        for (Book book : _lib) {
            if (!isNull(book)) {
                temp[j] = new Book(book);
                ++j;
            }
        }
        _lib = temp;
    }


    /*
    making a new String array of authors. empty String instead of null so they could be sorted.
    sort - O(nlog(n)).
    building new array - O(n).
    total - O(nlog(n) + n).
     */
    private String[] copyAuthors(Book[] lib) {
        String[] authors = new String[MAX_BOOKS];
        for (int i = 0; i < authors.length; i++) { // O(n)
            if (isNull(lib[i]))
                authors[i] = "";
            else
                authors[i] = lib[i].getAuthor();
        }
        Arrays.sort(authors); // O(n*log(n))
        return authors;
    }

    private String longestSequence(Book[] lib) { // returning the longest "batch" after sorted. O(nlogn(n) + 2n).
        if (isNull(lib[0]))
            return null;

        String[] authors = copyAuthors(lib);

        int currentBatchCnt = 0;
        String currentBatch = authors[0];
        int longestCnt = 0;
        String longest = authors[0];

        for (Book book : lib) { // O(n)
            if (isNull(book))
                return longest;

            currentBatchCnt++;
            if (book.getAuthor().equals(longest)) {
                longestCnt++;
            } else if (!book.getAuthor().equals(currentBatch)) {
                currentBatchCnt = 1;
            } else if (currentBatchCnt > longestCnt) {
                longestCnt = currentBatchCnt;
                longest = book.getAuthor();
            }
            currentBatch = book.getAuthor();
        }
        return longest;
    }

}
