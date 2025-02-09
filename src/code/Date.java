import java.util.Arrays;
import java.util.List;

/**
 * A class representing a calendar date with validation and utility functions.
 * Provides functionality to determine day of week, leap years, and various date formatting options.
 *
 * @author Annah Masombuka
 * @version 1.0
 */
public class Date implements Printable
{
    private static final int MIN_YEAR        = 1800;
    private static final int CURRENT_YEAR    = 2025;
    private static final int VALID_MIN_MONTH = 1;
    private static final int VALID_MAX_MONTH = 12;
    private static final int MONTHS_OFFSET   = 1;
    private static final int MIN_VALID_DAYS  = 1;

    private static final int YEAR_TAIL_MASK      = 100;
    private static final int QUARTER_DENOMINATOR = 4;
    private static final int MILLENNIUM          = 2000;
    private static final int MILLENNIUM_ADJUST   = 6;
    private static final int LEAP_YEAR_ADJUST    = 6;
    private static final int CENTURY_START       = 1900;
    private static final int CENTURY_ADJUST      = 6;
    private static final int NUM_DAYS_IN_A_WEEK = 7;

    private static final int LEAP_FEB_DAYS       = 29;
    private static final int REG_FEB_DAYS        = 28;
    private static final int LONG_MONTH_DAYS     = 31;
    private static final int SHORT_MONTH_DAYS    = 30;

    private static final int LEAP_YEAR_INTERVAL  = 4;
    private static final int CENTURY_INTERVAL    = 100;
    private static final int GRAND_LEAP_INTERVAL = 400;
    private static final int NO_REMAINDER        = 0;

    private static final String[] MONTHS   = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    private static final String[] DAYS_OF_THE_WEEK = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday"};

    private static final int JANUARY      = 1;
    private static final int FEBRUARY     = 2;
    private static final int APRIL        = 4;
    private static final int JUNE         = 6;
    private static final int SEPTEMBER    = 9;
    private static final int NOVEMBER     = 11;

    private static final int[] MONTH_CODES = {1, 4, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6};

    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructs a new Date object with the specified year, month, and day.
     *
     * @param year  The year
     * @param month The month (VALID_MIN_MONTH- MAX_VALID_MONTH)
     * @param day   The day of the month (must be valid for the specified month and year)
     */
    Date(final int year,
         final int month,
         final int day)
    {
        if (year < MIN_YEAR || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Invalid year: " + year);
        }

        if(day < MIN_VALID_DAYS || day > maxValidDays(day, month, year))
        {
            throw new IllegalArgumentException("Invalid number of days provided for month: " + day);
        }

        if(month < VALID_MIN_MONTH || month > VALID_MAX_MONTH)
        {
            throw new IllegalArgumentException("Invalid month provided: " + month);
        }

        this.year  = year;
        this.month = month;
        this.day   = day;
    }

    /**
     * Determines if the specified year is a leap year.
     *
     * @param year The year to check
     * @return true if the year is a leap year, false otherwise
     */
    private static boolean isLeapYear(int year)
    {
        return (year % LEAP_YEAR_INTERVAL == NO_REMAINDER &&
                year % CENTURY_INTERVAL != NO_REMAINDER) ||
                (year % GRAND_LEAP_INTERVAL == NO_REMAINDER);
    }

    /**
     * Gets the year component of this date.
     *
     * @return The year
     */
    int getYear()
    {
        return year;
    }

    /**
     * Gets the month name of this date.
     *
     * @return The full name of the month (e.g., "January")
     */
    String getMonth()
    {
        return MONTHS[month - MONTHS_OFFSET];
    }

    /**
     * Gets the day component of this date.
     *
     * @return The day of the month
     */
    int getDay()
    {
        return day;
    }

    /**
     * Returns the date in YYYY-MM-DD format.
     *
     * @return A string representation of the date in YYYY-MM-DD format
     */
    public String getYyyyMmDd()
    {
        return month + " " + day + "," + year;
    }

    @Override
    public void display()
    {
        System.out.println(month + day + "," + year);
    }

    /**
     * Calculates the day of the week for this date.
     *
     * @return The full name of the day of the week (e.g., "Monday")
     */
    public String getDayOfWeek()
    {
        int lastTwoDigitsInYear;
        int monthCode;
        int step1;
        int step2;
        int step3;
        int step4;
        int step5;
        int step6;
        int step7;

        monthCode           = MONTH_CODES[month - MONTHS_OFFSET];
        lastTwoDigitsInYear = year % YEAR_TAIL_MASK;

        step1 = lastTwoDigitsInYear / VALID_MAX_MONTH;
        step2 = lastTwoDigitsInYear - VALID_MAX_MONTH * step1;
        step3 = step2 / QUARTER_DENOMINATOR;
        step4 = day + step1 + step2 + step3;

        step5 = step4 + monthCode;

        if (year >= MILLENNIUM)
        {
            step5 += MILLENNIUM_ADJUST;
        }
        else if (year < CENTURY_START)
        {
            step5 += CENTURY_ADJUST;
        }

        if(isLeapYear(year) && (month == JANUARY || month == FEBRUARY))
        {
            step5 += LEAP_YEAR_ADJUST;
        }

        step6 = step5 % NUM_DAYS_IN_A_WEEK;
        step7 = step6;

        return DAYS_OF_THE_WEEK[step7];
    }

    /**
     * Determines the maximum valid number of days for a given month and year.
     * Takes into account leap years for February and months with SHORT_MONTH_DAYS vs LONG_MONTH_DAYS days.
     *
     * @param day   The day to validate (not used in current implementation)
     * @param month The month
     * @param year  The year
     * @return The maximum number of days for the specified month and year
     */
    public static int maxValidDays(final int day,
                                   final int month,
                                   final int year)
    {
        final List<Integer> shortMonths;
        shortMonths = Arrays.asList(APRIL, JUNE, SEPTEMBER, NOVEMBER);

        if(month == FEBRUARY)
        {
            if(isLeapYear(year))
            {
                return LEAP_FEB_DAYS;
            }
            else
            {
                return REG_FEB_DAYS;
            }
        }
        else if(shortMonths.contains(month))
        {
            return SHORT_MONTH_DAYS;
        }
        else
        {
            return LONG_MONTH_DAYS;
        }
    }
}