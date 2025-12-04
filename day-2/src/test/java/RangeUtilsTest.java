import Range.IDRange;
import Range.RangeUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class RangeUtilsTest {
    @ParameterizedTest
    @MethodSource("provideSubRangesOfEvenOrder")
    void getSubRangesOfEvenOrder(IDRange range, List<IDRange> expectedSubRanges) {
        var result = RangeUtils.getSubRangesOfEvenDigits(range);
        assertThat(result).containsAll(expectedSubRanges);
    }

    static Stream<Arguments> provideSubRangesOfEvenOrder() {
        return Stream.of(
                Arguments.of(new IDRange(1, 9), Collections.emptyList()),
                Arguments.of(new IDRange(10, 99), List.of(new IDRange(10, 99))),
                Arguments.of(new IDRange(33, 99), List.of(new IDRange(33, 99))),
                Arguments.of(new IDRange(10, 66), List.of(new IDRange(10, 66))),
                Arguments.of(new IDRange(33, 66), List.of(new IDRange(33, 66))),
                Arguments.of(new IDRange(1, 99), List.of(new IDRange(10, 99))),
                Arguments.of(new IDRange(1, 66), List.of(new IDRange(10, 66))),
                Arguments.of(new IDRange(1, 10000), List.of(new IDRange(10, 99)), new IDRange(1000, 9999)),
                Arguments.of(new IDRange(15, 6666), List.of(new IDRange(15, 99)), new IDRange(1000, 6666))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSubRangesWithNMultipleDigits")
    void getSubRangesWithNMultipleDigits(IDRange range, int n, List<IDRange> expectedSubRanges) {
        var result = RangeUtils.getSubrangesWithNMultipleDigits(range, n);
        assertThat(result).containsAll(expectedSubRanges);
    }

    static Stream<Arguments> provideSubRangesWithNMultipleDigits() {
        return Stream.of(
                Arguments.of(new IDRange(1, 9), 2, Collections.emptyList()),
                Arguments.of(new IDRange(10, 99), 2, List.of(new IDRange(10, 99))),
                Arguments.of(new IDRange(33, 99), 2, List.of(new IDRange(33, 99))),
                Arguments.of(new IDRange(10, 66), 2, List.of(new IDRange(10, 66))),
                Arguments.of(new IDRange(33, 66), 2, List.of(new IDRange(33, 66))),
                Arguments.of(new IDRange(1, 99), 2, List.of(new IDRange(10, 99))),
                Arguments.of(new IDRange(1, 66), 2, List.of(new IDRange(10, 66))),
                Arguments.of(new IDRange(1, 10000), 2, List.of(new IDRange(10, 99)), new IDRange(1000, 9999)),
                Arguments.of(new IDRange(15, 6666), 2, List.of(new IDRange(15, 99)), new IDRange(1000, 6666)),

                Arguments.of(new IDRange(1, 10000), 3, List.of(new IDRange(100, 999))),
                Arguments.of(new IDRange(1, 1000000), 3, List.of(new IDRange(100, 999), new IDRange(100000, 999999))),

                Arguments.of(new IDRange(1, 10000), 4, List.of(new IDRange(1000, 9999))),
                Arguments.of(new IDRange(1, 100000000), 4, List.of(new IDRange(1000, 9999), new IDRange(10000000, 99999999)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSumOfTwiceRepeatingPatterns")
    void getSumOfTwiceRepeatingPatterns(IDRange range, long expectedSum) {
        var result = RangeUtils.getSumOfTwiceRepeatingPatternsInRange(range);
        assertThat(result).isEqualTo(expectedSum);
    }

    static Stream<Arguments> provideSumOfTwiceRepeatingPatterns() {
        long sum1000to9999 = 0;
        for (int i = 10; i <= 99; i++) {
            sum1000to9999 += i;
            sum1000to9999 += i * 100;
        }
        return Stream.of(
                Arguments.of(new IDRange(10, 99, 1), 11L + 22L + 33L + 44L + 55L + 66L + 77L + 88L + 99L),
                Arguments.of(new IDRange(33, 99, 1), 33L + 44L + 55L + 66L + 77L + 88L + 99L),
                Arguments.of(new IDRange(10, 66, 1), 11L + 22L + 33L + 44L + 55L + 66L),
                Arguments.of(new IDRange(33, 66, 1), 33L + 44L + 55L + 66L),
                Arguments.of(new IDRange(1000, 9999, 3), sum1000to9999)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAllRepeatingPatterns")
    void getAllRepeatingPatterns(IDRange range, List<Long> repeats) {
        var result = RangeUtils.getAllRepeatingPatterns(range);
        assertThat(result).containsAll(repeats);
    }

    static Stream<Arguments> provideAllRepeatingPatterns() {
        return Stream.of(
                Arguments.of(new IDRange(11, 22, 1), List.of(11L, 22L)),
                Arguments.of(new IDRange(95, 99, 1), List.of(99L)),
                Arguments.of(new IDRange(100, 115, 2), List.of(111L)),
                Arguments.of(new IDRange(998, 999, 2), List.of(999L)),
                Arguments.of(new IDRange(1000, 1012, 3), List.of(1010L)),
                Arguments.of(new IDRange(1188511880, 1188511890, 9), List.of(1188511885L)),
                Arguments.of(new IDRange(1698522, 1698528, 6), Collections.emptyList()),
                Arguments.of(new IDRange(446443, 446449, 5), List.of(446446L)),
                Arguments.of(new IDRange(38593856, 38593862, 7), List.of(38593859L)),
                Arguments.of(new IDRange(565653, 565659, 5), List.of(565656L)),
                Arguments.of(new IDRange(824824821, 824824827, 8), List.of(824824824L)),
                Arguments.of(new IDRange(2121212118, 2121212124, 9), List.of(2121212121L))
        );
    }
}
