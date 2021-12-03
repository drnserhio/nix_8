package ua.com.alevel.model;

import lombok.*;


@EqualsAndHashCode
@Data
public class Calendar {

    public long timestamp;

    static final int MONTH_LENGTH[]
            = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 0-based
    static final int LEAP_MONTH_LENGTH[]
            = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 0-based

    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;
    private static final int ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;
    private static final long ONE_WEEK = 7 * ONE_DAY;

    private static final long YEARS = 365;
    private static final long LEAP_YEAR = YEARS + 1;

    private static final long YEAR_IN_MILLIS = YEARS * ONE_DAY;
    private static final long LEAP_YEAR_IN_MILLIS = LEAP_YEAR * ONE_DAY;

    public static final int MONTH_COUNT = 12;

    private long hour = 0;
    private long minute = 0;
    private long second = 0;
    private long millsecond = 0;

    private long day = 0;
    private long month = 0;
    private long year = 0;
//    private long weak;

//    private DrnDate date;
//    private DrnTime time;

    private Calendar() {
    }





    public static String formattingCalendar(Calendar calendar) {
        String format = "%s year, %s month, %s days, %s hours, %s minutes, %s seconds, %s milliseconds";
        return String.format(
                format,
                calendar.getYear(),
                calendar.getMonth(),
                calendar.getDay(),
                calendar.getHour(),
                calendar.getMinute(),
                calendar.getSecond(),
                calendar.getMillsecond()
        );
    }

    public String compareCalendar(Calendar calendar) {
        String format = "The same date: %s year, %s days, %s hours,  %s minutes, %s seconds, %s milliseconds";
        long year = compareDateCalendar(this.getYear(), calendar.getYear());
        long days = compareDateCalendar(this.getDay(), calendar.getDay());
        long hours = compareDateCalendar(this.getHour(), calendar.getHour());
        long minute = compareDateCalendar(this.getMinute(), calendar.getMinute());
        long seconds = compareDateCalendar(this.getSecond(), calendar.getSecond());
        long milliseconds = compareDateCalendar(this.getMillsecond(), calendar.getMillsecond());
        return String.format(format, year, days, hours, minute, seconds, milliseconds);
    }

    private static long compareDateCalendar(long firstDate, long secondDate) {
        return Math.max(firstDate, secondDate) - Math.min(firstDate, secondDate);
    }

    public void millisToDate() {
        this.Builder().convertMillisToDateTime(this.getMillsecond());
    }

    public void plusYear(long year) {
        this.setYear(this.getYear() + year);
        Calendar calendar = this.Builder().createTimestamp(this);
        this.setTimestamp(calendar.getTimestamp());
    }

    public void plusMonths(long month) {
      long sumWithNewMonth = this.getMonth() +  month;
        if (sumWithNewMonth > MONTH_COUNT) {
            long m = sumWithNewMonth - MONTH_COUNT;
            if (m > MONTH_COUNT) {
                long yearToAdd = 0;
                while ( countMonthToAddMonth(sumWithNewMonth) > 0) {
                    if (sumWithNewMonth - MONTH_COUNT < 0) {
                        break;
                    }
                    yearToAdd++;
                    sumWithNewMonth -=MONTH_COUNT;
                }

                this.setYear(this.getYear() + yearToAdd);
                this.setMonth(sumWithNewMonth);
                return;
            } else {
                this.setYear(this.getYear() + 1);
                this.setMonth(m);
            }
        } else {
            this.setMonth(sumWithNewMonth);
        }
        Calendar calendar = this.Builder().createTimestamp(this);
        this.setTimestamp(calendar.getTimestamp());
    }

    private long countMonthToAddMonth(long month) {
       return  month-=12;
    }




    @Override
    public String toString() {
        //TODO : HARDCODE
        return new DrnDate(this.getDay(), this.getMonth(), this.getYear()) + "T" + new DrnTime(this.getHour(), this.getMinute(), this.getSecond(), this.getMillsecond());
    }

//    public DrnDate convertToDate() {
//
//    }
//
//    public DrnDate convertToTime() {
//
//    }



    ///Builder--->
    public static Builder Builder() {
        return new Calendar().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setHour(long hour) {
            Calendar.this.hour = hour;
            return this;
        }

        public Builder setMinute(long minute) {
            Calendar.this.minute = minute;
            return this;
        }

        public Builder setSecond(long second) {
            Calendar.this.second = second;
            return this;
        }

        public Builder setMillsecond(long millsecond) {
            Calendar.this.millsecond = millsecond;
            return this;
        }

        public Builder setDay(long day) {
            Calendar.this.day = day;
            return this;

        }

        public Builder setMonth(long month) {
            Calendar.this.month = month;
            return this;
        }

        public Builder setYear(long year) {
            Calendar.this.year = year;
            return this;
        }

        public Calendar build() {
            Calendar calendar = Calendar.this;
            if (calendar.getYear() == 0 &&
                    calendar.getDay() == 0 &&
                        calendar.getHour() == 0 &&
                            calendar.getMinute() == 0 &&
                                calendar.getSecond() == 0 &&
                                    calendar.getMillsecond() ==0) {
                return calendar;
            } else {
                return createTimestamp(calendar);
            }
        }

        private boolean isLeap(long year) {
            return year % 4 == 0 && year % 100 != 0;
        }


        private Calendar createTimestamp(Calendar calendar) {
            long millis = 0L;
            long year = 0;
            while (year <  calendar.getYear()) {

                if (isLeap(year)) {
                    millis += LEAP_YEAR_IN_MILLIS;
                } else {
                    millis += YEAR_IN_MILLIS;
                }
                year++;
            }

            for (int m = 0; m < calendar.getMonth() - 1; m++) {
                long millisMonth = convertMonthToMillis(m, isLeap(year));
                millis += millisMonth;
            }


            long millisDays = calendar.getDay() -1  - ONE_DAY;
            long millisHours = calendar.getHour() * ONE_HOUR;
            long millisMinutes = calendar.getMinute() * ONE_MINUTE;
            long millisSeconds = calendar.getSecond() * ONE_SECOND;
            long mills = calendar.getMillsecond();

            millis += millisDays;
            millis += millisHours;
            millis += millisMinutes;
            millis += millisSeconds;
            millis += mills;
            calendar.setTimestamp(millis);
            return calendar;
        }

        private void convertMillisToDateTime(long timeStatampDateTime) {
            long millis = 0L;
            while (true) {

                if (isLeap(year)) {
                    if (millis + LEAP_YEAR_IN_MILLIS >  timeStatampDateTime) {
                        break;
                    } else  {
                        millis += LEAP_YEAR_IN_MILLIS;
                    }
                } else {
                   if (millis + YEAR_IN_MILLIS > timeStatampDateTime) {
                       break;
                   } else {
                       millis += YEAR_IN_MILLIS;
                   }
                }
                year++;
            }

            millis = 0L;
            long monthInMillis = timeStatampDateTime - millis;

            for (int m = 0; m < 11; m++) {
                long millisMonth = convertMonthToMillis(m, isLeap(year));
                if (millis + millisMonth > monthInMillis) {
                    m++;
                    break;
                }
                millis += monthInMillis;
            }


            long millisToDays = monthInMillis - millis;
            long millisToHours = millisToDays % ONE_DAY;
            long millisToMinutes = millisToHours % ONE_HOUR;
            long millisToSeconds = millisToMinutes % ONE_MINUTE;


            day += (millisToDays / ONE_DAY) + 1;
            hour += (millisToHours / ONE_HOUR);
            minute += (millisToMinutes / ONE_MINUTE);
            second += (millisToSeconds / ONE_SECOND);
            millsecond += (millisToSeconds % ONE_SECOND);

        }

        private long convertMonthToMillis(int month, boolean leap) {
            if (leap) {
                return LEAP_MONTH_LENGTH[month] * ONE_DAY;
            } else {
                return MONTH_LENGTH[month] * ONE_DAY;
            }
        }



    }


}

