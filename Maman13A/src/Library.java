/**
 * Library class stores array of 200 Books (max books allowed). Contains methods to add, remove and analyze the library's
 * content.
 *
 * @author Ishay Hilzenrat.
 * @version 1.0
 */

import java.util.Arrays;

public class Library {
    private final int MAX_BOOKS = 200;

    private Book[] _lib;
    private int _noOfBooks;

    /**
     * Library class public constructor. Creates object with array of 200 Books (all null to begin with), and sets _noOfBooks to 0.
     */
    public Library() {
        _lib = new Book[MAX_BOOKS];
        _noOfBooks = 0;
    }

    /**
     * Adds a new book to the Book array.
     *
     * @param book other Book to add to the Book array.
     * @return true if the book was added. false if wasn't added.
     */
    public boolean addBook(Book book) {
        if (isNull(book) || _noOfBooks == MAX_BOOKS)
            return false;

        _lib[_noOfBooks++] = new Book(book);
        return true;
    }

    /**
     * removes a given book from the Book array. after, arranges so there won't be any 'gaps' inside of it (nulls at the end).
     *
     * @param book other Book to add.
     */
    public void removeBook(Book book) {
        if (isNull(book)) {
            return;
        }
        int bookCnt = _noOfBooks;
        for (int i = 0; i < bookCnt; i++) {
            if (_lib[i].equals(book)) {
                _lib[i] = null;
                _noOfBooks--;
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
     * returns the number of book in the Book array that are borrowed to a specific student.
     *
     * @param studentName name of student to search upon.
     * @return int. number for borrowed books at the given student.
     */
    public int howManyBooksBorrowedToStudent(String studentName) {
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
        String mostPop = _lib[0] == null ? null : _lib[0].getAuthor();
        int mostPopCount = 0;

        for (Book book : _lib) {
            if (isNull(book)) // due to the fact that there are not null between two books, once reached null; return value.
                return mostPop;

            String currentAuthor = book.getAuthor();
            int currentAuthorCount = authorCnt(currentAuthor);

            if (currentAuthorCount > mostPopCount) {
                mostPop = currentAuthor;
                mostPopCount = currentAuthorCount;
            }
        }
        return mostPop;
    }

    /**
     * returns the oldest Book in the Book array by his year of publish.
     *
     * @return Book. new object of the oldest book.
     */
    public Book oldestBook() {
        Book oldest = _lib[0] == null ? null : new Book(_lib[0]);

        for (Book book : _lib) {
            if (isNull(book) || isNull(oldest)) { // due to the fact that there are not null between two books, once reached null; break loop.
                return oldest;
            }
            if (book.getYear() < oldest.getYear()) {
                oldest = new Book(book);
            }
        }
        return oldest;
    }

    /**
     * removes a given Book title from the library (one copy of it - the first one), and returns a new object of the Book.
     *
     * @param title book's title.
     * @return the Book that was removed.
     */
    public Book remove(String title) {
        int BookCnt = _noOfBooks;
        for (int i = 0; i < BookCnt && !isNull(title); i++) {
            if (!isNull(_lib[i].getTitle()) && _lib[i].getTitle().equals(title)) {
                Book bookToRemove = new Book(_lib[i]);
                _lib[i] = null;
                _noOfBooks--;
                arrangeArray();
                return bookToRemove;
            }
        }
        return null; // if didn't reached null and didn't find.
    }

    /**
     * overrides the class toString method. Will return all the Books (using the toString Book method) in the Book array.
     *
     * @return String. All the Books title, author, year of publish and # of pages that are in the library. No nulls.
     */
    public String toString() {
        String books = "";
        for (Book book : _lib) {
            if (!isNull(book))
                books += (book + "\n");
        }

        return books;
    }


    ////////////// private methods //////////////

    private boolean isNull(Book book) {
        return book == null;
    }

    private boolean isNull(String str) {
        return str == null;
    }

    private void arrangeArray() { // appending the non null books into the temp array.
        Book[] temp = new Book[MAX_BOOKS];
        int j = 0; // temp index starting
        for (Book book : _lib) {
            if (!isNull(book)) {
                temp[j++] = new Book(book);
            }
        }
        _lib = temp;
    }

    private int authorCnt(String currentAuth) { // returns each authors library's count.
        int cnt = 0;
        for (Book book : _lib) {
            if (isNull(book)) { // reached the end of lib.
                return cnt;
            }

            if (isNull(currentAuth) && isNull(book.getAuthor())) { // if currentAuth is null (possible), searches for other books with null author.
                cnt++;
            }
            if (!isNull(book.getAuthor()) && book.getAuthor().equals(currentAuth)) {
                cnt++;
            }
        }
        return cnt;
    }


}
