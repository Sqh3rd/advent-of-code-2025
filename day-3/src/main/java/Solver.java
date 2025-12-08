import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Solver {
    private List<BatteryBank> input;

    public static Solver forInput(List<BatteryBank> input) {
        return new Solver(input);
    }

    public Long solveFirst() {
        return input.stream()
                .map(BatteryBankUtils::getLargest2DigitJoltage)
                .reduce(Long::sum)
                .orElseThrow();
    }

    public Long solveSecond() {
        return input.stream()
                .map(BatteryBankUtils::getLargest12DigitJoltage)
                .reduce(Long::sum)
                .orElseThrow();
    }
}
