/**
 * Book class stores / copies books. The class contains method to borrow/return a book from the library, check book's status
 * and compare one book to another.
 *
 * @author Ishay Hilzenrat.
 * @version 1.0
 */

public class Book {

    // instance vars:
    private String _title, _author, _studentName;
    private int _yearPublished, _noOfPages;
    private boolean _borrowed;
    private Date _borrowedDate, _returnDate;

    // final values:
    private final int MAX_DAYS = 30;
    private final int DOUBLE_MAX_DAYS = 60;
    private final int ONE_DAY_FINE = 5;
    private final int DOUBLE_ONE_DAY_FINE = 2 * ONE_DAY_FINE;
    private final int MIN_YEAR = 1800;
    private final int MAX_YEAR = 2018;
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_PAGE_COUNT = 1;
    private final int MIN_PAGE_COUNT = 0;
    private final int FRIDAY = 6;
    private final int SATURDAY = 0;


    // public constructors: //

    /**
     * Book constructor. Will make Book object by a given book title, author, published year and # of pages.
     *
     * @param title         String. the book's title.
     * @param author        String. the book's author.
     * @param yearPublished int. the year the book was published.
     * @param noOfPages     int. the number of pages the book has.
     */
    public Book(String title, String author, int yearPublished, int noOfPages) {
        _title = title;
        _author = author;
        _studentName = null;
        _yearPublished = returnValidYear(yearPublished);
        _noOfPages = returnValidPageCount(noOfPages);
        _borrowed = false;
        _borrowedDate = _returnDate = null;
    }

    /**
     * Book constructor. will make a new copy of a given Book object.
     *
     * @param otherBook Book. other Book object to copy.
     */
    public Book(Book otherBook) {
        _title = otherBook._title;
        _author = otherBook._author;
        _studentName = otherBook._studentName;
        _yearPublished = otherBook._yearPublished;
        _noOfPages = otherBook._noOfPages;
        _borrowed = otherBook._borrowed;
        _borrowedDate = returnValidDate(otherBook._borrowedDate);
        _returnDate = returnValidDate(otherBook._returnDate);
    }


    // public methods: //

    /**
     * Get the book's title.
     *
     * @return String of book's title.
     */
    public String getTitle() {
        return _title;
    }

    /**
     * sets the book's title.
     *
     * @param title String of book's new title.
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Get the book's author.
     *
     * @return String of book's author.
     */
    public String getAuthor() {
        return _author;
    }

    /**
     * sets the book's author.
     *
     * @param author String of book's new author.
     */
    public void setAuthor(String author) {
        _author = author;
    }

    /**
     * Get the name of the student that borrowed the book.
     *
     * @return String with the name of the student.
     */
    public String getStudentName() {
        return _studentName;
    }

    /**
     * sets the name of the student who borrowed the book.
     *
     * @param studentName String of the student's name.
     */
    public void setStudentName(String studentName) {
        _studentName = studentName;
    }

    /**
     * Get year the book was published.
     *
     * @return int contains the year the book was published.
     */
    public int getYear() {
        return _yearPublished;
    }

    /**
     * sets the year the book was published at. if the year isn't valid, nothing will be changed.
     *
     * @param year int of the published year.
     */
    public void setYear(int year) {
        if (isValidYear(year)) {
            _yearPublished = year;
        }
    }

    /**
     * Get book's number of pages..
     *
     * @return int contains book's number of pages.
     */
    public int getPages() {
        return _noOfPages;
    }

    /**
     * sets the book's number of pages. if the num of pages isn't valid, nothing will be changed.
     *
     * @param pageCnt int of the number of pages.
     */
    public void setPages(int pageCnt) {
        if (isValidPageCount(pageCnt)) {
            _noOfPages = pageCnt;
        }
    }

    /**
     * Get book's borrowed status.
     *
     * @return boolean of the book's borrowed status.
     */
    public boolean getBorrowed() {
        return _borrowed;
    }

    /**
     * sets the book's borrowed status.
     *
     * @param borrowed boolean of the status.
     */
    public void setBorrowed(boolean borrowed) {
        _borrowed = borrowed;
    }

    /**
     * Get book's borrowed date.
     *
     * @return Date of the book's borrowed date.
     */
    public Date getBorrowedDate() {
        return returnValidDate(_borrowedDate);
    }

    /**
     * sets the book borrowed date.
     *
     * @param borrowedDate Date of the borrow.
     */
    public void setBorrowedDate(Date borrowedDate) {
        _borrowedDate = new Date(borrowedDate);
    }

    /**
     * Get book's return date.
     *
     * @return Date of the book's return date.
     */
    public Date getReturnDate() {
        return returnValidDate(_returnDate);
    }

    /**
     * sets the book return date.
     *
     * @param returnDate Date of the return.
     */
    public void setReturnDate(Date returnDate) {
        _returnDate = new Date(returnDate);
    }

    /**
     * check if one book equals to another (same title, author and amount of pages).
     *
     * @param otherBook another book to compare.
     * @return boolean. equals or not.
     */
    public boolean equals(Book otherBook) {
        return (_title.equals(otherBook._title) && _author.equals(otherBook._author) && _noOfPages == otherBook._noOfPages);
    }

    public String toString() {
        return "Title: " + _title + "\t\t" + "Author: " + _author + "\t\t" + "Year: " + _yearPublished + ", "
                + _noOfPages + " pages";
    }

    /**
     * check if the book is older (published before) another book.
     *
     * @param otherBook book to compare.
     * @return boolean. older or not.
     */
    public boolean olderBook(Book otherBook) {
        return (_yearPublished < otherBook._yearPublished);
    }

    /**
     * check if the book has the same author as the other book.
     *
     * @param otherBook book to compare.
     * @return boolean. same author or not.
     */
    public boolean sameAuthor(Book otherBook) {
        return _author.equals(otherBook._author);
    }

    /**
     * borrow the book to a student (if not already borrowed). also will change the boolean value of _borrowed to true.
     *
     * @param studentName  String contains the name of the student who borrowed the book.
     * @param borrowedDate Date of borrow.
     */
    public void borrowBook(String studentName, Date borrowedDate) {
        if (!getBorrowed()) {
            _studentName = studentName;
            _borrowedDate = new Date(borrowedDate);
            _borrowed = true;
        }
    }

    /**
     * return the book to the library (if not already borrowed). also will change the boolean value of _borrowed to false.
     * the String value of _studentName to null and the Date value of _borrowedDate to null.
     *
     * @param returnDate date of return.
     * @return boolean. true if late on return, false if not.
     */
    public boolean returnBook(Date returnDate) {
        boolean lateOnReturn = isLateOnReturn(returnDate);
        if (_borrowed) {
            setReturnDate(returnDate);
            _studentName = null;
            _borrowedDate = null;
            _borrowed = false;
        }
        return lateOnReturn;
    }

    /**
     * calculates the number of days the book was borrowed. if isn't borrowed or the current date is before the borrowed date
     * will return 0.
     *
     * @param todayDate the current date.
     * @return int. the number of days the book was borrowed.
     */
    public int howLongBorrowed(Date todayDate) {
        return (!_borrowed || todayDate.before(_borrowedDate)) ? 0 : todayDate.difference(_borrowedDate);
    }

    /**
     * check if the book is available. If the book is not borrowed or it's Friday/Saturday today will return false.
     *
     * @param todayDate the current date.
     * @return boolean. the book is available today or not.
     */
    public boolean isAvailable(Date todayDate) {
        return (!_borrowed && todayDate.dayInWeek() != FRIDAY && todayDate.dayInWeek() != SATURDAY);
    }

    /**
     * calculates the penalty value in ILS. if the student returned the book after 30 days, the penalty is 5 ILS per day.
     * If the student returns it after 60 days, the penalty is 10 ILS per each day after the 30th.
     *
     * @param returnDate Date of the return.
     * @return int. the penalty in ILS.
     */
    public int computePenalty(Date returnDate) {
        int numberOfDaysBorrowed = howLongBorrowed(returnDate);
        if (!_borrowed || !isLateOnReturn(returnDate)) { // if book isn't borrowed or book isn't late.
            return 0;
        } else if (numberOfDaysBorrowed <= DOUBLE_MAX_DAYS) { // if below or equal to 60 days.
            return (numberOfDaysBorrowed - MAX_DAYS) * ONE_DAY_FINE;
        }

        return (MAX_DAYS * ONE_DAY_FINE) + ((numberOfDaysBorrowed - DOUBLE_MAX_DAYS) * DOUBLE_ONE_DAY_FINE); // if above 60 days.
    }


    // private methods: //
    private boolean isValidYear(int yearPublished) {
        return (yearPublished >= MIN_YEAR && yearPublished <= MAX_YEAR);
    }

    private boolean isValidPageCount(int noOfPages) { // checks if the page count is valid (not below 0).
        return noOfPages > MIN_PAGE_COUNT;
    }

    private int returnValidYear(int year) { // if get's an invalid year, will return a valid default one (year 2000).
        if (isValidYear(year)) {
            return year;
        }
        return DEFAULT_YEAR;
    }

    private int returnValidPageCount(int pageCount) { // if get's an invalid page cnt, will return a default one (1).
        if (isValidPageCount(pageCount)) {
            return pageCount;
        }
        return DEFAULT_PAGE_COUNT;
    }

    private boolean isLateOnReturn(Date returnDate) {
        return (_borrowedDate != null && returnDate.difference(_borrowedDate) > MAX_DAYS);
    }

    private Date returnValidDate(Date otherDate) { // returns null if received null. else, creates new object of Date.
        return otherDate == null ? null : new Date(otherDate);
    }

} // end of class Book
