package ua.com.alevel.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.alevel.model.inf.ChangeCalendar;

import static ua.com.alevel.constant.Month.JANUARY;


@EqualsAndHashCode
@Data
public class Calendar implements ChangeCalendar {

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
    public static final int MINUTE_COUNT = 60;
    public static final int SECOND_COUNT = 60;
    public static final int MILLSECOND_COUNT = 1000;


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

    @Override
    public void minusYears(long years) {
        long minusWithYears = this.getYear() - years;
        if (minusWithYears < 0) {
            this.setYear(0);
            updateTimeStamp(this);
            return;
        }
        this.setYear(minusWithYears);
        updateTimeStamp(this);
    }

    @Override
    public void minusDays(long days) {
        long minusWihtNewDays = this.getDay() - days;

        if ( minusWihtNewDays > 0) {
            this.setDay(minusWihtNewDays);
            updateTimeStamp(this);
            return;
        } else {
            minusWihtNewDays = Math.abs(minusWihtNewDays);
            if (days > 120) {
                --minusWihtNewDays;
            }
        }
        long updateYears = this.getYear();
        int monthInTheMomement = (int) this.getMonth();
        boolean isLeap = false;

        long countMonthToDell = 0;
        while (minusWihtNewDays > 0) {
            if (isLeap(updateYears)) {
                isLeap = true;
            } else {
                isLeap = false;
            }

            if (monthInTheMomement == -1) {
                updateYears--;
                monthInTheMomement = 11;
                if (isLeap(updateYears)) {
                    isLeap = true;
                } else {
                    isLeap = false;
                }
            }

            if (isLeap) {
                minusWihtNewDays -= LEAP_MONTH_LENGTH[monthInTheMomement--];
                countMonthToDell--;
            } else {
                minusWihtNewDays -= MONTH_LENGTH[monthInTheMomement--];
                countMonthToDell--;
            }
        }



        if (Math.abs(countMonthToDell) > 0) {
            long correctMonth = this.getMonth() - countMonthToDell;
            if (isMonthDiscorrect((int) correctMonth)) {
                this.minusMonths(Math.abs(countMonthToDell));
            } else {
                this.setMonth(correctMonth);
            }
        }

        if (isDayDiscorrect(minusWihtNewDays)) {
            this.setDay(1);
        } else {
                this.setDay(Math.abs(++minusWihtNewDays));
            }

        updateTimeStamp(this);

    }

    @Override
    public void minusMonths(long months) {
        long minusWithNewMonth = Math.abs(this.getMonth() - months);
        if (minusWithNewMonth < 0) {

        }
        if (minusWithNewMonth > MONTH_COUNT) {
            long m = minusWithNewMonth - MONTH_COUNT;
            if (m > MONTH_COUNT) {
                long yearToAdd = 0;
                while (countMonthToAddMonth(minusWithNewMonth) > 0) {
                    if (minusWithNewMonth - MONTH_COUNT < 0) {
                        break;
                    }
                    yearToAdd++;
                    minusWithNewMonth -= MONTH_COUNT;
                }
                this.setYear((this.getYear() - yearToAdd) - 1);
                this.setMonth(Math.abs(MONTH_COUNT - minusWithNewMonth));
                return;
            } else {
                this.setYear(this.getYear() - 1);
                this.setMonth(MONTH_COUNT - m);
            }
        } else {
            this.setYear(this.year - 1);
            this.setMonth(minusWithNewMonth );
        }
        updateTimeStamp(this);
    }

    @Override
    public void minusHours(long hours) {

    }

    @Override
    public void minusMinutes(long minutes) {

    }

    @Override
    public void minusSeconds(long seconds) {

    }

    @Override
    public void minusMilliseconds(long milliseconds) {

    }

    @Override
    public void plusYears(long year) {
        this.setYear(this.getYear() + year);
        updateTimeStamp(this);
    }

    @Override
    public void plusMonths(long month) {
        long sumWithNewMonth = this.getMonth() + month;
        if (sumWithNewMonth > MONTH_COUNT) {
            long m = sumWithNewMonth - MONTH_COUNT;
            if (m > MONTH_COUNT) {
                long yearToAdd = 0;
                while (countMonthToAddMonth(sumWithNewMonth) > 0) {
                    if (sumWithNewMonth - MONTH_COUNT < 0) {
                        break;
                    }
                    yearToAdd++;
                    sumWithNewMonth -= MONTH_COUNT;
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
        updateTimeStamp(this);
    }

    @Override
    public void plusDays(long days) {
        long sumWihtNewDays = this.getDay() + days;
        long updateYears = this.getYear();
        int monthInTheMomement = (int) this.getMonth();
        boolean isLeap = false;

        long countMonthToAdd = 0;
        long markerYear = JANUARY;
        while (sumWihtNewDays > 0) {
            if (isLeap(updateYears)) {
                isLeap = true;
            } else {
                isLeap = false;
            }
            if (isMonthDiscorrect(monthInTheMomement)) {
                monthInTheMomement = 0;
            }

            if ((sumWihtNewDays - MONTH_LENGTH[monthInTheMomement] < 0 ||
                    sumWihtNewDays - LEAP_MONTH_LENGTH[monthInTheMomement] < 0)) {
                break;
            }


            //If the same month what to do ??)))
            if (markerYear == monthInTheMomement) {
                updateYears++;
            }
            if (isLeap) {
                sumWihtNewDays -= LEAP_MONTH_LENGTH[monthInTheMomement];
                monthInTheMomement++;
                countMonthToAdd++;
            } else {
                sumWihtNewDays -= MONTH_LENGTH[monthInTheMomement];
                monthInTheMomement++;
                countMonthToAdd++;
            }
        }
//        if (updateYears > 0) {
//            this.setYear(updateYears);
//        }
        if (countMonthToAdd > 0) {
            long correctMonth = this.getMonth() + countMonthToAdd;
            if (isMonthDiscorrect((int) correctMonth)) {
                this.plusMonths(countMonthToAdd);
            } else {
                this.setMonth(correctMonth);
            }
        }

        if (isDayDiscorrect(sumWihtNewDays)) {
            this.setDay(1);
        } else {
            this.setDay(sumWihtNewDays);
        }
        updateTimeStamp(this);
    }

    @Override
    public void plusHours(long hours) {
        long sumWithNewHours = this.getHour() + hours;
        long saveHours = 0;

        if (sumWithNewHours < 24) {
            this.setHour(sumWithNewHours);
            updateTimeStamp(this);
            return;
        }
        saveHours = hoursConvertToDays(sumWithNewHours);
        this.setHour(saveHours);
        updateTimeStamp(this);
    }

    private long hoursConvertToDays(long hours) {
        long days = hours / 24;
        long saveHours = hours % 24;
        if (days % 2 == 0 || isLeap(this.getYear())) {
            plusDays(days);
        } else {
            plusDays(days - 1);
        }

        if (saveHours > 0) {
            return saveHours;
        } else {
            return hours;
        }

    }

    @Override
    public void plusMinutes(long minutes) {
        long sumWithMinutes = this.getMinute() + minutes;
        long saveMinutes = sumWithMinutes % MINUTE_COUNT;
        if (sumWithMinutes > MINUTE_COUNT) {
            long hours = convertMinuteToHours(sumWithMinutes);
            if (saveMinutes > 0) {
                this.setMinute(saveMinutes);
            }
            plusHours(hours);

            return;
        } else {
            this.setMinute(sumWithMinutes);
            updateTimeStamp(this);
            return;
        }
    }

    private long convertMinuteToHours(long minutes) {
        return minutes / MINUTE_COUNT;
    }

    @Override
    public void plusSeconds(long seconds) {
        long sumWithSeconds = this.getSecond() + seconds;
        long saveSeconds = sumWithSeconds % SECOND_COUNT;
        if (sumWithSeconds > SECOND_COUNT) {
            long minute = convertSecondToMinute(sumWithSeconds);
            if (saveSeconds > 0) {
                this.setSecond(saveSeconds);
            }
            plusMinutes(minute);
            return;
        } else {
            this.setSecond(sumWithSeconds);
            updateTimeStamp(this);
            return;
        }
    }

    private long convertSecondToMinute(long seconds) {
        return seconds / SECOND_COUNT;
    }

    @Override
    public void plusMilliseconds(long milliseconds) {

        long sumWithMillseconds = this.getMillsecond() + milliseconds;
        long saveSeconds = sumWithMillseconds % MILLSECOND_COUNT;
        if (sumWithMillseconds > MILLSECOND_COUNT) {
            long seconds = convertMillsecondsToSeconds(sumWithMillseconds);
            if (saveSeconds > 0) {
                this.setMillsecond(saveSeconds);
            }
            plusSeconds(seconds);
            return;
        } else {
            this.setMillsecond(sumWithMillseconds);
            updateTimeStamp(this);
            return;
        }
    }

    private long convertMillsecondsToSeconds(long millseconds) {
        return millseconds / MILLSECOND_COUNT;
    }


    private void updateTimeStamp(Calendar newCalendar) {
        Calendar calendar = this.Builder().createTimestamp(newCalendar);
        this.setTimestamp(calendar.getTimestamp());
    }

    private boolean isLeap(long year) {
        return this.of().isLeap(year);
    }

    private boolean isDayDiscorrect(long day) {
        if (day == 0) {
            return true;
        }
        return false;
    }

    private boolean isMonthDiscorrect(int month) {
        if (month > 11) {
            return true;
        }
        return false;
    }

    private void millisToDate() {
        this.Builder().convertMillisToDateTime(this.getMillsecond());
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


//    private long countDaysToAddMonth(long days) {
//
//    }

//    public boolean isTheNextMonthLeap(long month) {
//        switch ((int) month) {
//            case FEBRUARY: return true;
//            case APRIL: return true;
//            case JUNE: return true;
//            case SEPTEMBER: return true;
//            case NOVEMBER:return true;
//        }
//        return false;
//    }


    private long countMonthToAddMonth(long month) {
        return month -= 12;
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

    public static Builder of() {
        return Builder();
    }

    public static Builder of(long year, long month, long day, long hour, long minute, long second) {
        Builder builder = new Calendar().new Builder();
        builder.year(year);
        builder.month(month);
        builder.day(day);
        builder.hour(hour);
        builder.minute(minute);
        builder.second(second);
        return builder;
    }

    public static Builder of(long year, long month, long day, long hour, long minute, long second, long millsecond) {
        Builder builder = new Calendar().new Builder();
        builder.year(year);
        builder.month(month);
        builder.day(day);
        builder.hour(hour);
        builder.minute(minute);
        builder.second(second);
        builder.millSecond(millsecond);
        return builder;
    }

    private static Builder Builder() {
        return new Calendar().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder hour(long hour) {
            Calendar.this.hour = hour;
            return this;
        }

        public Builder minute(long minute) {
            Calendar.this.minute = minute;
            return this;
        }

        public Builder second(long second) {
            Calendar.this.second = second;
            return this;
        }

        public Builder millSecond(long millsecond) {
            Calendar.this.millsecond = millsecond;
            return this;
        }

        public Builder day(long day) {
            Calendar.this.day = day;
            return this;

        }

        public Builder month(long month) {
            Calendar.this.month = month;
            return this;
        }

        public Builder year(long year) {
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
                    calendar.getMillsecond() == 0) {
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
            while (year < calendar.getYear()) {

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


            long millisDays = calendar.getDay() - 1 - ONE_DAY;
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
                    if (millis + LEAP_YEAR_IN_MILLIS > timeStatampDateTime) {
                        break;
                    } else {
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

            for (int m = 0; m < 12; m++) {
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

