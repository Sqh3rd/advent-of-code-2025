import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BatteryBankUtilsTest {
    @ParameterizedTest
    @MethodSource("provideLargest2DigitJoltage")
    void getLargest2DigitJoltage(BatteryBank bb, Long expected) {
        var result = BatteryBankUtils.getLargest2DigitJoltage(bb);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> provideLargest2DigitJoltage() {
        return Stream.of(
                Arguments.of(new BatteryBank(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1)), 98L),
                Arguments.of(new BatteryBank(List.of(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9)), 89L),
                Arguments.of(new BatteryBank(List.of(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)), 78L),
                Arguments.of(new BatteryBank(List.of(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1)), 92L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLargest12DigitJoltage")
    void getLargest12DigitJoltage(BatteryBank bb, Long expected) {
        var result = BatteryBankUtils.getLargest12DigitJoltage(bb);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> provideLargest12DigitJoltage() {
        return Stream.of(
                Arguments.of(new BatteryBank(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1)), 987654321111L),
                Arguments.of(new BatteryBank(List.of(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9)), 811111111119L),
                Arguments.of(new BatteryBank(List.of(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)), 434234234278L),
                Arguments.of(new BatteryBank(List.of(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1)), 888911112111L)
        );
    }
}
