package Range;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RangeUtils {
    public static List<IDRange> getSubrangesWithOrder(IDRange range) {
        int minOrder = (int) Math.log10(Math.max(1, range.min()));
        int maxOrder = (int) Math.log10(range.max());

        var result = new ArrayList<IDRange>(1 + maxOrder - minOrder);
        for (int i = 0; i <= maxOrder - minOrder; i++) {
            int currentOrder = minOrder + i;
            long currentMin = Math.max(range.min(), (long) Math.pow(10, currentOrder));
            long currentMax = Math.min(range.max(), (long) Math.pow(10, currentOrder + 1) - 1);
            result.add(new IDRange(currentMin, currentMax, currentOrder));
        }
        return result;
    }

    public static List<IDRange> getSubrangesWithNMultipleDigits(IDRange range, int n) {
        if (n == 0)
            throw new IllegalArgumentException("n has to be greater than 0");
        return getSubrangesWithOrder(range).stream()
                .filter(it -> (it.order() + 1) % n == 0)
                .toList();
    }

    public static List<IDRange> getSubRangesOfEvenDigits(IDRange range) {
        return RangeUtils.getSubrangesWithNMultipleDigits(range, 2);
    }

    public static List<Long> getAllRepeatingPatterns(IDRange range) {
        if (range.order() == 0)
            return Collections.emptyList();
        var max = range.order() + 1;
        var factors = new ArrayList<Integer>();
        factors.add(max);
        for (int i = 2; i <= max / 2; i++) {
            if (max % i == 0) {
                factors.add(i);
                factors.add(max / i);
            }
        }
        return factors.stream()
                .distinct()
                .map(n -> RangeUtils.getNRepeatingPatterns(range, n))
                .flatMap(Collection::stream)
                .distinct()
                .toList();
    }

    /**
     * We assume that min and max of the range are of the same order and that the order is even.
     *
     * @param range
     * @return
     */
    public static long getSumOfTwiceRepeatingPatternsInRange(IDRange range) {
        return getSumOfNRepeatingPatternsInRange(range, 2);
    }

    /**
     * We assume that min and max of the range are of the same order and that the order is a multiple of n.
     *
     * @param range
     * @return
     */
    public static long getSumOfNRepeatingPatternsInRange(IDRange range, int n) {
        return getNRepeatingPatterns(range, n).stream()
                .reduce(Long::sum)
                .orElseThrow();
    }

    public static List<Long> getNRepeatingPatterns(IDRange range, int n) {
        if (n <= 1)
            throw new IllegalArgumentException("n has to be greater than 1");
        if (range.order() == null || (1 + range.order()) % n != 0)
            throw new IllegalArgumentException("Expected order of range to be multiple of " + (n - 1) + ", but was " + range.order() + " instead");

        int orderIncrements = (1 + range.order()) / n;
        long orderMask = (long) Math.pow(10, orderIncrements);

        var mins = splitByNDigits(range.min(), range.order(), n);

        for (int i = 0; i < mins.size() - 1; i++) {
            if (mins.get(i) > mins.get(i + 1))
                mins.set(i + 1, mins.get(i + 1) + 1);
        }

        var lowerBound = mins.getLast();

        var maxes = splitByNDigits(range.max(), range.order(), n);
        for (int i = 0; i < maxes.size() - 1; i++) {
            if (maxes.get(i) < maxes.get(i + 1))
                maxes.set(i + 1, maxes.get(i + 1) - 1);
        }

        var upperBound = maxes.getLast();
        if (upperBound < lowerBound)
            return Collections.emptyList();

        var result = new ArrayList<Long>((int) (1 + (upperBound - lowerBound)));
        for (long i = lowerBound; i <= upperBound; i++) {
            long cur = 0;
            for (int j = 0; j < n; j++) {
                cur += (long) (i * Math.pow(orderMask, j));
            }
            result.add(cur);
        }

        return result;
    }

    private static ArrayList<Long> splitByNDigits(long input, Integer order, int n) {
        int orderIncrements = (1 + order) / n;
        long orderMask = (long) Math.pow(10, orderIncrements);

        var parts = new ArrayList<Long>(n);
        var cur = input;
        for (int i = 0; i < n; i++) {
            parts.add(cur % orderMask);
            cur /= orderMask;
        }

        return parts;
    }
}
