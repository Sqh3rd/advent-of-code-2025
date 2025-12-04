package Range;

public class RangeUtils {
    public static IDRange getSubRangeWithEvenAmountOfDigits(IDRange range) {
        var min = Math.log10(range.min());
        var max = Math.log10(range.max());
        if (min == max && min % 2 != 0)
            return IDRange.empty();
    }
}
