package ua.com.alevel.resource;

public interface MathSetInterface {

    void add(Number n);

    void add(Number... n);

    void join(MathSetImpl ms);

    void join(MathSetImpl... ms);

    void intersection(MathSetImpl ms);

    void intersection(MathSetImpl... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(Number value);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(Number value);

    Number get(int index);

    Number getMax();

    Number getMin();

    Number getAverage();

    Number getMedian();

    Number[] toArray();

    Number[] toArray(int firstIndex, int lastIndex);

    MathSetImpl cut(int firstIndex, int lastIndex);

    void clear();

    void clear(Number[] numbers);
}
