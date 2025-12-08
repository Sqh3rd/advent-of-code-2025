import java.util.ArrayList;
import java.util.List;

public record BatteryBank(List<Integer> joltages) {
    public static BatteryBank fromString(String s) {
        var joltages = new ArrayList<Integer>(s.length());
        for (int i = 0; i < s.length(); i++) {
            String c = s.substring(i, i + 1);
            joltages.add(Integer.parseInt(c));
        }
        return new BatteryBank(joltages);
    }
}
