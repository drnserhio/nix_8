package ua.com.alevel.resources.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.alevel.resources.inf.ChangeCalendar;

import static ua.com.alevel.enumeration.Month.JANUARY;


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
    public static final int DAY = 24;


    private long hour = 0;
    private long minute = 0;
    private long second = 0;
    private long millsecond = 0;

    private long day = 0;
    private long month = 0;
    private long year = 0;

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
        long day = this.getDay() - days;
        if (day > 0) {
            this.setDay(day - 1);
            updateTimeStamp(this);
            return;
        } else {
            day = Math.abs(day);

        }
        long updateYears = this.getYear();
        int monthInThisMomement = (int) this.getMonth();
        boolean isLeap = false;

        long countDeleteMonth = 0;
        while (day > 0) {
            if (isLeap(updateYears)) {
                isLeap = true;
            } else {
                isLeap = false;
            }

            if (monthInThisMomement == -1) {
                updateYears--;
                monthInThisMomement = 11;
            }
            if (isLeap) {
                day -= LEAP_MONTH_LENGTH[monthInThisMomement--];
                countDeleteMonth--;
            } else {
                day -= MONTH_LENGTH[monthInThisMomement--];
                countDeleteMonth--;
            }
        }
        if (monthInThisMomement > 0) {
            this.setMonth(monthInThisMomement);
            this.setYear(updateYears);
        } else {
            this.setMonth(MONTH_COUNT - Math.abs(monthInThisMomement));
            this.setYear(updateYears);
        }
        if (day > 0 || Math.abs(day) < 10) {
            if (this.getMonth() - 1 == 1) {
                this.setDay(MONTH_LENGTH[monthInThisMomement - 1] - MONTH_LENGTH[monthInThisMomement]);
            } else {
                this.setDay(Math.abs(day));
            }

        } else {
            if (this.getMonth() == 1 && day < 0) {
                this.setDay(1);
                return;
            }
            if (isLeap(updateYears) && this.getMonth() == 1) {
                this.setDay(Math.abs(day + 2));
            } else if (!isLeap(updateYears) && this.getMonth() == 1) {
                this.setDay(Math.abs(day - 4));
            } else {
                if (MONTH_LENGTH[Math.toIntExact(this.getMonth())] % 2 != 0) {
                    this.setDay(Math.abs(day) + 2);
                } else {
                    this.setDay(Math.abs(++day));
                }
            }
        }
        updateTimeStamp(this);
    }

    @Override
    public void minusMonths(long months) {
        long month = this.getMonth() - months;
        if (month > 0 && month <= 11) {
            this.setMonth(month);
            updateTimeStamp(this);
            return;
        } else {
            month = Math.abs(month);
        }
        if (month > MONTH_COUNT) {
            long m = month - MONTH_COUNT;
            if (m > MONTH_COUNT) {
                long yearToAdd = 1;
                while (true) {

                    if (month - MONTH_COUNT < 0) {
                        break;
                    }
                    yearToAdd++;
                    month -= MONTH_COUNT;
                }
                this.setYear((this.getYear() - yearToAdd));
                this.setMonth(Math.abs(MONTH_COUNT - month));
                return;
            } else {
                this.setYear(this.getYear() - 1);
                this.setMonth(MONTH_COUNT - m);
            }
        } else {
            this.setYear(this.year - 1);
            if (month > 0) {
                this.setMonth(MONTH_COUNT - month);
            } else {
                if (month < 0) {
                    this.setMonth(MONTH_COUNT - Math.abs(month));
                } else {
                    this.setMonth(month);
                }
            }
        }
        updateTimeStamp(this);
    }

    @Override
    public void minusHours(long hours) {
        long hour = this.getHour() - hours;
        long saveHours = 0;
        if (hour > 0) {
            this.setHour(hour);
            updateTimeStamp(this);
            return;
        }
        saveHours = hoursConvertToDaysWithMinus(Math.abs(hour));
        this.setHour(saveHours);
        updateTimeStamp(this);
    }

    @Override
    public void minusMinutes(long minutes) {
        long minute = this.getMinute() - minutes;
        long saveMinute = 0;
        if (minute > 0) {
            this.setMinute(minute);
            updateTimeStamp(this);
            return;
        }
        saveMinute = minuteConvertToHours(Math.abs(this.getMinute() + minutes));
        this.setMinute(saveMinute);
        updateTimeStamp(this);
    }

    @Override
    public void minusSeconds(long seconds) {
        long second = this.getSecond() - seconds;
        long saveSeconds = 0;
        if (second > 0) {
            this.setSecond(second);
            updateTimeStamp(this);
            return;
        }
        saveSeconds = secondsConvertToMinute(Math.abs(this.getSecond() + seconds));
        this.setSecond(saveSeconds);
        updateTimeStamp(this);
    }

    @Override
    public void minusMilliseconds(long milliseconds) {
        long millis = this.getMillsecond() - milliseconds;
        long saveMillseconds = 0;
        if (millis > 0) {
            this.setMillsecond(millis);
            updateTimeStamp(this);
            return;
        }
        saveMillseconds = millsecConvertToSeconds(Math.abs(this.getMillsecond() + milliseconds));
        this.setMillsecond(saveMillseconds);
        updateTimeStamp(this);
    }

    @Override
    public void plusYears(long year) {
        this.setYear(this.getYear() + year);
        updateTimeStamp(this);
    }

    @Override
    public void plusMonths(long month) {
        long updateMonth = this.getMonth() + month;
        if (updateMonth > MONTH_COUNT) {
            long m = updateMonth - MONTH_COUNT;
            if (m > MONTH_COUNT) {
                long countUpdateYears = 0;
                while (countAboutAddMonth(updateMonth) > 0) {
                    if (updateMonth - MONTH_COUNT < 0) {
                        break;
                    }
                    countUpdateYears++;
                    updateMonth -= MONTH_COUNT;
                }
                this.setYear(this.getYear() + countUpdateYears);
                this.setMonth(updateMonth);
                return;
            } else {
                this.setYear(this.getYear() + 1);
                this.setMonth(m);
            }
        } else {
            this.setMonth(updateMonth);
        }
        updateTimeStamp(this);
    }

    @Override
    public void plusDays(long days) {
        long updateDays = this.getDay() + days;
        long updateYears = this.getYear();
        int monthInThisMomement = (int) this.getMonth();
        boolean isLeap = false;
        long countForUpdateMonth = 0;
        long markerYear = JANUARY.getNumberMonth();

        while (updateDays > 0) {
            if (isLeap(updateYears)) {
                isLeap = true;
            } else {
                isLeap = false;
            }
            if (isMonthDiscorrect(monthInThisMomement)) {
                monthInThisMomement = 0;
            }
            if ((updateDays - MONTH_LENGTH[monthInThisMomement] < 0 ||
                    updateDays - LEAP_MONTH_LENGTH[monthInThisMomement] < 0)) {
                break;
            }
            if (markerYear == monthInThisMomement) {
                updateYears++;
            }
            if (isLeap) {
                updateDays -= LEAP_MONTH_LENGTH[monthInThisMomement];
                monthInThisMomement++;
                countForUpdateMonth++;
            } else {
                updateDays -= MONTH_LENGTH[monthInThisMomement];
                monthInThisMomement++;
                countForUpdateMonth++;
            }
        }
        if (countForUpdateMonth > 0) {
            long correctMonth = this.getMonth() + countForUpdateMonth;
            if (isMonthDiscorrect((int) correctMonth)) {
                this.plusMonths(countForUpdateMonth);
            } else {
                this.setMonth(correctMonth);
            }
        }
        if (isDayDiscorrect(updateDays)) {
            this.setDay(1);
        } else {
            this.setDay(updateDays);
        }
        updateTimeStamp(this);
    }

    @Override
    public void plusHours(long hours) {
        long updateHours = this.getHour() + hours;
        long saveHours = 0;
        if (updateHours < DAY) {
            this.setHour(updateHours);
            updateTimeStamp(this);
            return;
        }
        saveHours = hoursConvertToDays(updateHours);
        this.setHour(saveHours);
        updateTimeStamp(this);
    }

    @Override
    public void plusMinutes(long minutes) {
        long updateMinutes = this.getMinute() + minutes;
        long saveMinutes = updateMinutes % MINUTE_COUNT;
        if (updateMinutes > MINUTE_COUNT) {
            long hours = convertMinuteToHours(updateMinutes);
            if (saveMinutes > 0) {
                this.setMinute(saveMinutes);
            }
            plusHours(hours);
            return;
        } else {
            this.setMinute(updateMinutes);
            updateTimeStamp(this);
            return;
        }
    }

    @Override
    public void plusSeconds(long seconds) {
        long updateSeconds = this.getSecond() + seconds;
        long saveSeconds = updateSeconds % SECOND_COUNT;
        if (updateSeconds > SECOND_COUNT) {
            long minute = convertSecondToMinute(updateSeconds);
            if (saveSeconds > 0) {
                this.setSecond(saveSeconds);
            }
            plusMinutes(minute);
            return;
        } else {
            this.setSecond(updateSeconds);
            updateTimeStamp(this);
            return;
        }
    }

    @Override
    public void plusMilliseconds(long milliseconds) {
        long updateMilliseconds = this.getMillsecond() + milliseconds;
        long millisSave = updateMilliseconds % MILLSECOND_COUNT;
        if (updateMilliseconds > MILLSECOND_COUNT) {
            long seconds = convertMillsecondsToSeconds(updateMilliseconds);
            if (millisSave > 0) {
                this.setMillsecond(millisSave);
            }
            plusSeconds(seconds);
            return;
        } else {
            this.setMillsecond(updateMilliseconds);
            updateTimeStamp(this);
            return;
        }
    }

    private long convertMillsecondsToSeconds(long millseconds) {
        return millseconds / MILLSECOND_COUNT;
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

    private long hoursConvertToDaysWithMinus(long hours) {
        long days = convertHoursToDays(hours);
        long saveHours = DAY - ((hours % DAY));
        minusDays(days);

        if (saveHours == DAY) {
            return 0;
        }
        return saveHours;
    }

    private long convertHoursToDays(long hours) {
        return hours / DAY;
    }

    private long hoursConvertToDays(long hours) {
        long days = hours / DAY;
        long saveHours = hours % DAY;
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
//        return saveHours;
    }

    private long convertMinuteToHours(long minutes) {
        return minutes / MINUTE_COUNT;
    }

    private long minuteConvertToHours(long minutes) {
        long hours = convertMinuteToHours(minutes);
        long saveMinute = MINUTE_COUNT - (minutes % MINUTE_COUNT);
        minusHours(hours);
        if (saveMinute == MINUTE_COUNT) {
            return 0;
        }
        return Math.abs(saveMinute) + 2;
    }

    private long convertSecondToMinute(long seconds) {
        return seconds / SECOND_COUNT;
    }

    private long secondsConvertToMinute(long seconds) {
        long minutes = convertSecondToMinute(seconds);
        long saveSeconds = SECOND_COUNT - (seconds % SECOND_COUNT);
        minusMinutes(minutes);
        if (saveSeconds == SECOND_COUNT) {
            return 0;
        }
        return Math.abs(saveSeconds);
    }

    private long millsecConvertToSeconds(long millseconds) {
        long seconds = convertMillsecondsToSeconds(millseconds);
        long saveMillseconds = MILLSECOND_COUNT - (millseconds % MILLSECOND_COUNT);
        minusSeconds(seconds);

        if (saveMillseconds == MILLSECOND_COUNT) {
            return 0;
        }
        return Math.abs(saveMillseconds);
    }

    private long countAboutAddMonth(long month) {
        return month -= MONTH_COUNT;
    }

    private void updateTimeStamp(Calendar newCalendar) {
        Calendar calendar = this.Builder().createTimestamp(newCalendar);
        this.setTimestamp(calendar.getTimestamp());
    }



    private String compareCalendar(Calendar calendar) {
        String format = "The same date: %s year, %s month, %s days, %s hours, %s minutes, %s seconds, %s milliseconds";
        return String.format(format, calendar.getYear(), calendar.getMonth(), calendar.getDay(), calendar.getHour(),  calendar.getMinute(), calendar.getSecond(), calendar.getMillsecond());
    }


    public String compare(Calendar calendar) {
        long f = this.getTimestamp();
        long s = calendar.getTimestamp();
        long compare = Math.abs(f - s);
        Calendar c = this.Builder().convertMillisToDateTimeForCompare(compare);
        return compareCalendar(c);
    }

    @Override
    public String toString() {
        return new DrnDate(
                this.getDay(),
                this.getMonth(),
                this.getYear()) + "T" +
                new DrnTime(
                        this.getHour(),
                        this.getMinute(),
                        this.getSecond(),
                        this.getMillsecond());
    }


    public static Builder of() {
        return Builder();
    }

    public static Builder of(long year, long month, long day) {
        Builder builder = new Calendar().new Builder();
        builder.year(year);
        builder.month(month);
        builder.day(day);
        return builder;
    }

    public static Builder of(long month, long hour) {
        Builder builder = new Calendar().new Builder();
        builder.month(month);
        builder.hour(hour);
        return builder;
    }

    public static Builder of(long year, long hour, long minute, long second) {
        Builder builder = new Calendar().new Builder();
        builder.year(year);
        builder.hour(hour);
        builder.minute(minute);
        return builder;
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

            for (int m = 0; m < MONTH_COUNT; m++) {
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

        private Calendar convertMillisToDateTimeForCompare(long timeStatampDateTime) {
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

            for (int m = 0; m < MONTH_COUNT; m++) {
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
            return Calendar.of(year, month, day, minute, second, millsecond).build();
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

