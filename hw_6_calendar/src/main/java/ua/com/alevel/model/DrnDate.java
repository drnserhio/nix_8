package ua.com.alevel.model;

import lombok.Data;

@Data
public class DrnDate {

    private final static String DASH = "-";
    private long day = 0;
    private long month = 0;
    private long year = 0;
//    private long weak;


    public DrnDate(long day, long month, long year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return year + DASH + month + DASH + day;
    }
}
