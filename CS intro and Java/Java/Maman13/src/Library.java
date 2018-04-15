/**
 * Created by ishayhilzenrat on 14/04/2018.
 */
public class Library {
    private final int MAX_BOOKS = 200;

    private Book[] _lib = new Book[MAX_BOOKS];
    private int _noOfBooks = 0;

    public Library() {
    }

    public boolean addBook(Book book) {
        if (!isNull(book)) {
            for (int i = 0; i < _lib.length; i++) {
                if (isNull(_lib[i])) {
                    _lib[i] = new Book(book);
                    _noOfBooks++;
                    return true;
                }
            }
        }
        return false;
    }

    public void removeBook(Book book) {
        if (!isNull(book)) {
            for (int i = 0; i < _lib.length; i++) {
                if (!isNull(_lib[i]) && _lib[i].equals(book)) {
                    _lib[i] = null;
                    _noOfBooks--;
                }
            }
        }
        sortArray();
    }

    public int howManyBooksBorrowed() {
        int numB = 0;
        for (Book book : _lib) {
            if (!isNull(book) && book.getBorrowed()) {
                ++numB;
            }
        }
        return numB;
    }

    public int howManyBorrowedAtDate(Date d) {
        int numB = 0;
        for (Book book : _lib) {
            if (!isNull(book) && !isNull(d) && d.equals(book.getBorrowedDate()))
                ++numB;
        }
        return numB;
    }

    public int howManyBorrowedToStudent(String studentName) {
        int numB = 0;
        for (Book book : _lib) {
            if (!isNull(book) && !isNull(studentName) && studentName.equalsIgnoreCase(book.getStudentName()))
                ++numB;
        }
        return numB;
    }

    public String mostPopularAuthor() {
        if (isNull(_lib[0]))
            return null;

        String mostPop = _lib[0].getAuthor();
        int mostPopCount = 0;

        for (Book book : _lib) {
            if (isNull(book)) // due to the fact that there are not null between two books, once reached null; break loop.
                break;

            String currentAuthor = book.getAuthor();
            int currentAuthorCount = 0;

            for (Book book2 : _lib) {
                if (isNull(book2))
                    break;

                if (book2.getAuthor().equals(currentAuthor))
                    ++currentAuthorCount;
            }

            if (mostPopCount < currentAuthorCount) {
                mostPop = currentAuthor;
                mostPopCount = currentAuthorCount;
            }
        }
        return mostPop;
    }

    public Book oldestBook() {
        if (isNull(_lib[0]))
            return null;

        Book oldest = new Book(_lib[0]);

        for (Book book : _lib) {
            if (isNull(book)) // due to the fact that there are not null between two books, once reached null; break loop.
                break;

            if (book.getYear() < oldest.getYear())
                oldest = new Book(book);
        }
        return new Book(oldest);
    }

    public String toString() {
        String books = "";

        for (Book book : _lib)
            if (!isNull(book))
                books += (book + "\n");

        return books;
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

    private void sortArray() {
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

}
