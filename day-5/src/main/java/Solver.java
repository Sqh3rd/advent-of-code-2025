import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.function.Predicate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Solver {
    private final IngredientInput input;
    private final boolean debug;

    public static Solver debugForInput(IngredientInput input) {
        return new Solver(input, true);
    }

    public static Solver forInput(IngredientInput input) {
        return new Solver(input, false);
    }

    public long solveFirst() {
        var pred = contains(input.ranges());
        return input.ids()
                .stream()
                .filter(pred)
                .peek(this::log)
                .count();
    }

    private Predicate<Long> contains(Collection<IngredientRange> ranges) {
        return (id) -> ranges.stream().anyMatch(range -> range.contains(id));
    }

    private void log(Object id) {
        if (debug) System.out.println(id);
    }

    public long solveSecond() {
        return IngredientRangeUtils.mergeOverlappingRanges(input.ranges()).stream()
                .peek(this::log)
                .map(IngredientRange::size)
                .reduce(Long::sum)
                .orElseThrow();
    }
}
