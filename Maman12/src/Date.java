/**
 * Date class stores / copies valid dates (above year 999). The class contains many methods to compare one date to
 * another.
 *
 * @author Ishay Hilzenrat.
 * @version 1.0
 */

import java.lang.Math;

public class Date {

    // instance vars:
    private int int_day, int_month, int_year;

    // final values:
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_DAY = 1;
    private final int MAX_MONTH = 12;
    private final int MIN_MONTH = 1;
    private final int MAX_DAY = 31;
    private final int MIN_DAY = 1;
    private final int MIN_YEAR = 0;
    private final int VALID_YEAR_DIGIT_LEN = 4;
    private final int MAX_DAY_FEB = 29;
    private final int MIN_DAY_IN_WEEK = 0;

    private final int JAN = 1;
    private final int FEB = 2;
    private final int MARCH = 3;
    private final int APRIL = 4;
    private final int JUNE = 6;
    private final int SEP = 9;
    private final int NOV = 11;

    private final int INDEXED_JAN = 13;
    private final int INDEXED_FEB = 14;

    // calculateDate and leap methods constants:
    private final int DAYS_IN_REGULAR_YEAR = 365;
    private final int NUM_DAYS_IN_WEEK = 7;

    // public constructors: //

    /**
     * Date constructor. Will make Date object by a given a date, month and year.
     * if the final date is not valid - the date will be set to: 1/1/2000.
     *
     * @param day   day in month.
     * @param month month in year.
     * @param year  the date's year.
     */
    public Date(int day, int month, int year) {
        int_day = day;
        int_month = month;
        int_year = year;

        if (!isValidDate(int_day, int_month, int_year)) {
            setDefaultDate(); // replacing the current date vars with the default ones (one of the params is wrong).
        }

    }

    /**
     * Date constructor. Will make a copy of a given Date object.
     *
     * @param other other date object to copy..
     */
    public Date(Date other) {
        int_day = other.int_day;
        int_month = other.int_month;
        int_year = other.int_year;
    }

    // public methods: //

    /**
     * will return the date's day.
     *
     * @return int day
     */
    public int getDay() {
        return int_day;
    }

    /**
     * will return the date's month.
     *
     * @return int month
     */
    public int getMonth() {
        return int_month;
    }

    /**
     * will return the date's year.
     *
     * @return int year
     */
    public int getYear() {
        return int_year;
    }

    /**
     * will set the date's day. if the final date will not be valid, not change will happen.
     *
     * @param dayToSet int of day to set.
     */
    public void setDay(int dayToSet) {
        if (isValidDate(dayToSet, int_month, int_year)) {
            int_day = dayToSet;
        }
    }

    /**
     * will set the date's month. if the final date will not be valid, not change will happen.
     *
     * @param monthToSet int of month to set.
     */
    public void setMonth(int monthToSet) {
        if (isValidDate(int_day, monthToSet, int_year)) {
            int_month = monthToSet;
        }
    }

    /**
     * will set the date's year. if the final date will not be valid, not change will happen.
     *
     * @param yearToSet int of year to set.
     */
    public void setYear(int yearToSet) {
        if (isValidDate(int_day, int_month, yearToSet)) {
            int_year = yearToSet;
        }
    }

    // checks if current date is before other date param.

    /**
     * checks if a the object's date if before a given object date.
     *
     * @param otherDate other date to compare with.
     * @return boolean. before or not before.
     */
    public boolean before(Date otherDate) {
        return compareDates(otherDate) < 0;
    }

    /**
     * checks if a the object's date if after a given object date.
     *
     * @param otherDate other date to compare with.
     * @return boolean. after or not after.
     */
    public boolean after(Date otherDate) {
        return otherDate.before(this);
    }

    /**
     * calculates the number of days difference between the object's date and a given date.
     *
     * @param otherDate other date to compare with.
     * @return int. the amount of days between the dates..
     */
    public int difference(Date otherDate) {
        return Math.abs(compareDates(otherDate));
    }

    /**
     * overwrites the toString() Object method. If the user will print the object, he will see the date in an ISO format
     * (D/M/Y).
     *
     * @return String. the date in ISO format.
     */
    public String toString() {
        return int_day + "/" + int_month + "/" + int_year;
    }

    /**
     * calculates the object's day in the week. 1 is Sunday, 0 is Saturday. (0-6 days in the week).
     *
     * @return int. the day in the week.
     */
    public int dayInWeek() {

        int indexMonth = monthForDayInWeek(int_month);
        int indexYear = yearForDayInWeek(int_year, indexMonth);
        int c = indexYear / 100;
        int y = indexYear % 100;

        int dayOfWeek = (int_day + (26 * (indexMonth + 1)) / 10 + y + y / 4 + c / 4 - 2 * c) % NUM_DAYS_IN_WEEK;

        return dayOfWeek >= MIN_DAY_IN_WEEK ? dayOfWeek : Math.floorMod(dayOfWeek, 7);
    }

    /**
     * Checks if one date is the same as the other - same year, month and day.
     *
     * @param otherDate other date to compare with.
     * @return boolean. the same or not.
     */
    public boolean equals(Date otherDate) {
        return (compareDates(otherDate) == 0);
    }


    // private methods: //
    private boolean leap(int year) { // check if the year is a leap year.
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private boolean isValidDay(int day) {
        return (day <= MAX_DAY && day >= MIN_DAY);
    }

    private boolean isValidMonth(int month) {
        return (month <= MAX_MONTH && month >= MIN_MONTH);
    }

    private boolean isValidYear(int year) {
        return (year > MIN_YEAR && Integer.toString(year).length() == VALID_YEAR_DIGIT_LEN);
    }

    private boolean isValidFeb(int day, int month, int year) {
        return (month != FEB || day < MAX_DAY_FEB || leap(year) && day <= MAX_DAY_FEB);
    }

    private boolean isValid31DaysMonth(int month, int day) { // checks if the month has 31 days.
        return (day != MAX_DAY || (month != APRIL && month != JUNE && month != SEP && month != NOV));
    }

    private boolean isValidDate(int day, int month, int year) {
        return (isValidDay(day) && isValidMonth(month) && isValidYear(year) && isValidFeb(day, month, year) &&
                isValid31DaysMonth(month, day));
    }

    private void setDefaultDate() {
        int_day = DEFAULT_DAY;
        int_month = DEFAULT_MONTH;
        int_year = DEFAULT_YEAR;
    }

    private int calculateDate(int day, int month, int year) { // calculates the amount of days passed for each date.
        if (month < MARCH) {
            --year;
            month = month + MAX_MONTH;
        }
        return DAYS_IN_REGULAR_YEAR * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) /
                10 + (day - 62);
    }

    private int monthForDayInWeek(int month) { // returns the right month for the dayInWeek calculation.
        switch (month) {
            case JAN:
                return INDEXED_JAN;
            case FEB:
                return INDEXED_FEB;
            default:
                return month;
        }
    }

    private int yearForDayInWeek(int year, int monthIndex) { // returns the right year for the dayInWeek calculation.
        return monthIndex > MAX_MONTH ? --year : year;
    }

    /*
     * return 0 if they are the same. positive int if this object date it bigger than otherDate.
     * Negative if this object date it smaller than otherDate.
     */
    private int compareDates(Date otherDate) {
        int currentDateCount = calculateDate(int_day, int_month, int_year);
        int otherDateCount = calculateDate(otherDate.int_day, otherDate.int_month, otherDate.int_year);

        return currentDateCount - otherDateCount;
    }

}
