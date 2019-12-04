package foundationOop.week8;

/*
  A date class
  Written by: Guy J. Brown
  First written: 14/10/02
  Last modified: 14/10/02
  Cleaned up: 31/08/06 by YG
*/

public class ADate {
    public static final int JANUARY   = 1;
    public static final int FEBRUARY  = 2;
    public static final int MARCH     = 3;
    public static final int APRIL     = 4;
    public static final int MAY       = 5;
    public static final int JUNE      = 6;
    public static final int JULY      = 7;
    public static final int AUGUST    = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER   = 10;
    public static final int NOVEMBER  = 11;
    public static final int DECEMBER  = 12;

    private int day, month, year;

    public ADate() {
        day = 0;
        month = 0;
        year = 0;
    }

    public ADate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public ADate copy() {
        ADate d = new ADate();
        d.day = day;
        d.month = month;
        d.year = year;
        return d;
    }

    public void setDay(int d) {
        day = d;
    }

    public void setMonth(int m) {
        month = m;
    }

    public void setYear(int y) {
        year = y;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return ("(" + day + ", " + month + ", " + year + ")");
    }
}