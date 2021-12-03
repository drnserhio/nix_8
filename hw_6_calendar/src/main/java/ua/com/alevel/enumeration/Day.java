package ua.com.alevel.enumeration;

public enum Day {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SUNDAY(5),
    SATURDAY(6);

    private int numberDay;

    Day(int numberDay) {
        this.numberDay = numberDay;
    }

    public int getNumberDay() {
        return numberDay;
    }
}
