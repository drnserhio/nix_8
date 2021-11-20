package ua.com.alevel.util;

public final class OffsetArrayHelper {
    private OffsetArrayHelper(){}

    public static Number[] sortedEndAsc(Number[] arrayMath, int firstIndex, int lastIndex) {
        var copyStart = SortArray.cutArray(arrayMath, 0, firstIndex+1);
        var copyEnd = SortArray.cutArray(arrayMath, firstIndex+1, lastIndex);
        SortArray.sort(copyEnd);
        return SortArray.joinArray(copyStart, copyEnd);
    }

    public static Number[] sortedStartAsc(Number[] arrayMath, int firstIndex, int lastIndex) {
        var copyStart = SortArray.cutArray(arrayMath, firstIndex, lastIndex+1);
        var copyEnd = SortArray.cutArray(arrayMath, lastIndex+1, arrayMath.length);
        SortArray.sort(copyStart);
        return SortArray.joinArray(copyStart, copyEnd);
    }

    public static Number[] sortedMiddleAsc(Number[] arrayMath, int firstIndex, int lastIndex) {
        var copyfirstPart = SortArray.cutArray(arrayMath, 0, firstIndex);
        var copylastPart = SortArray.cutArray(arrayMath, lastIndex+1, arrayMath.length);
        var cut = SortArray.cutArray(arrayMath,firstIndex, lastIndex+1);
        SortArray.sort(cut);
        var joinWithFirstArray = SortArray.joinArray(copyfirstPart, cut);
        return SortArray.joinArray(joinWithFirstArray, copylastPart);
    }

    public static Number[] sortedEndDesk(Number[] arrayMath, int firstIndex, int lastIndex) {
        var copyStart = SortArray.cutArray(arrayMath, 0, firstIndex+1);
        var copyEnd = SortArray.cutArray(arrayMath, firstIndex+1, lastIndex);
        SortArray.sortDesk(copyEnd);
        return SortArray.joinArray(copyStart, copyEnd);
    }

    public static Number[] sortedStartDesk(Number[] arrayMath, int firstIndex, int lastIndex) {
        var copyStart = SortArray.cutArray(arrayMath, firstIndex, lastIndex+1);
        var copyEnd = SortArray.cutArray(arrayMath, lastIndex+1, arrayMath.length);
        SortArray.sortDesk(copyStart);
        return SortArray.joinArray(copyStart, copyEnd);

    }

    public static Number[] sortedMiddleDesk(Number[] arrayMath, int firstIndex, int lastIndex) {
        var copyfirstPart = SortArray.cutArray(arrayMath, 0, firstIndex);
        var copylastPart = SortArray.cutArray(arrayMath, lastIndex+1, arrayMath.length);
        var cut = SortArray.cutArray(arrayMath,firstIndex, lastIndex+1);
        SortArray.sortDesk(cut);
        var joinWithFirstArray = SortArray.joinArray(copyfirstPart, cut);
        return SortArray.joinArray(joinWithFirstArray, copylastPart);
    }

}
