import Range.IDRange;
import Range.RangeUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@RequiredArgsConstructor
public class Solver {
    private final List<IDRange> ranges;
    private boolean debug = false;

    public static Solver debugForInput(List<IDRange> ranges) {
        return new Solver(ranges, true);
    }

    public static Solver forInput(List<IDRange> ranges) {
        return new Solver(ranges);
    }

    public long solveFirst() {
        return ranges.stream()
                .map(RangeUtils::getSubRangesOfEvenDigits)
                .flatMap(Collection::stream)
                .peek(execIfDebug(System.out::println))
                .map(RangeUtils::getSumOfTwiceRepeatingPatternsInRange)
                .peek(execIfDebug(System.out::println))
                .reduce(Long::sum)
                .orElseThrow();
    }

    public long solveSecond() {
        return ranges.stream()
                .map(RangeUtils::getSubrangesWithOrder)
                .flatMap(Collection::stream)
                .peek(execIfDebug(System.out::println))
                .map(RangeUtils::getAllRepeatingPatterns)
                .peek(execIfDebug(System.out::println))
                .flatMap(Collection::stream)
                .reduce(Long::sum)
                .orElseThrow();
    }

    private <T> Consumer<T> execIfDebug(Consumer<T> input) {
        return it -> {
            if (debug)
                input.accept(it);
        };
    }
}
