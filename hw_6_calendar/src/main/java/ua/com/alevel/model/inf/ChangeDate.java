package ua.com.alevel.model.inf;

public interface ChangeDate {

    void plusDays(long days);
    void plusMonths(long month);
    void plusYears(long year);

    void minusYears(long years);
    void minusDays(long days);
    void minusMonths(long months);

}
