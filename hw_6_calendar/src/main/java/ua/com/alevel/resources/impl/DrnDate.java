package ua.com.alevel.resources.impl;

import lombok.Data;

@Data
public class DrnDate{

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
      String m = month > 9? String.valueOf(month) : "0" + month;
      String d = day > 9? String.valueOf(day) : "0" + day;

        return year + DASH + m + DASH + d;
    }

}
