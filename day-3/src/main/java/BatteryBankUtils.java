import java.util.ArrayList;

public class BatteryBankUtils {
    public static Long getLargest2DigitJoltage(BatteryBank bb) {
        return getLargestNDigitJoltage(bb, 2);
    }

    public static Long getLargest12DigitJoltage(BatteryBank bb) {
        return getLargestNDigitJoltage(bb, 12);
    }

    private static Long getLargestNDigitJoltage(BatteryBank bb, int n) {
        var idxs = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            idxs.add(BatteryBankUtils.getMaxIdxInRange(bb, idxs.isEmpty() ? 0 : idxs.getLast() + 1, bb.joltages().size() - (n - i)));
        }
        var result = 0L;
        for (int i = 0; i < n; i++) {
            var power = (long) Math.pow(10, n - 1 - i);
            result += (long) bb.joltages().get(idxs.get(i)) * power;
        }
        return result;
    }

    private static Integer getMaxIdxInRange(BatteryBank bb, int start, int end) {
        var max = 0;
        var maxIdx = 0;
        for (int i = start; i <= end; i++) {
            var cur = bb.joltages().get(i);
            if (cur > max) {
                max = cur;
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
