import Roll.Roll;
import Roll.RollLine;

import java.util.List;
import java.util.Optional;

public class TestInputProvider {
    public static List<RollLine> getInput() {
        return List.of(
                new RollLine(List.of(e(), e(), r(), r(), e(), r(), r(), r(), r(), e())),
                new RollLine(List.of(r(), r(), r(), e(), r(), e(), r(), e(), r(), r())),
                new RollLine(List.of(r(), r(), r(), r(), r(), e(), r(), e(), r(), r())),
                new RollLine(List.of(r(), e(), r(), r(), r(), r(), e(), e(), r(), e())),
                new RollLine(List.of(r(), r(), e(), r(), r(), r(), r(), e(), r(), r())),
                new RollLine(List.of(e(), r(), r(), r(), r(), r(), r(), r(), e(), r())),
                new RollLine(List.of(e(), r(), e(), r(), e(), r(), e(), r(), r(), r())),
                new RollLine(List.of(r(), e(), r(), r(), r(), e(), r(), r(), r(), r())),
                new RollLine(List.of(e(), r(), r(), r(), r(), r(), r(), r(), r(), e())),
                new RollLine(List.of(r(), e(), r(), e(), r(), r(), r(), e(), r(), e()))
        );
    }

    public static List<RollLine> getExpected() {
        return List.of(
                new RollLine(List.of(e(), e(), a(), a(), e(), a(), a(), r(), a(), e())),
                new RollLine(List.of(a(), r(), r(), e(), r(), e(), r(), e(), r(), r())),
                new RollLine(List.of(r(), r(), r(), r(), r(), e(), a(), e(), r(), r())),
                new RollLine(List.of(r(), e(), r(), r(), r(), r(), e(), e(), r(), e())),
                new RollLine(List.of(a(), r(), e(), r(), r(), r(), r(), e(), r(), a())),
                new RollLine(List.of(e(), r(), r(), r(), r(), r(), r(), r(), e(), r())),
                new RollLine(List.of(e(), r(), e(), r(), e(), r(), e(), r(), r(), r())),
                new RollLine(List.of(a(), e(), r(), r(), r(), e(), r(), r(), r(), r())),
                new RollLine(List.of(e(), r(), r(), r(), r(), r(), r(), r(), r(), e())),
                new RollLine(List.of(a(), e(), a(), e(), r(), r(), r(), e(), a(), e()))
        );
    }

    public static List<RollLine> getExpectedWithRemovedAccessibles() {
        return List.of(
                new RollLine(List.of(e(), e(), e(), e(), e(), e(), e(), r(), e(), e())),
                new RollLine(List.of(e(), r(), r(), e(), r(), e(), r(), e(), r(), r())),
                new RollLine(List.of(r(), r(), r(), r(), r(), e(), e(), e(), r(), r())),
                new RollLine(List.of(r(), e(), r(), r(), r(), r(), e(), e(), r(), e())),
                new RollLine(List.of(e(), r(), e(), r(), r(), r(), r(), e(), r(), e())),
                new RollLine(List.of(e(), r(), r(), r(), r(), r(), r(), r(), e(), r())),
                new RollLine(List.of(e(), r(), e(), r(), e(), r(), e(), r(), r(), r())),
                new RollLine(List.of(e(), e(), r(), r(), r(), e(), r(), r(), r(), r())),
                new RollLine(List.of(e(), r(), r(), r(), r(), r(), r(), r(), r(), e())),
                new RollLine(List.of(e(), e(), e(), e(), r(), r(), r(), e(), e(), e()))
        );
    }

    private static Optional<Roll> e() {
        return Optional.empty();
    }

    private static Optional<Roll> r() {
        return Optional.of(new Roll(false));
    }

    private static Optional<Roll> a() {
        return Optional.of(new Roll(true));
    }
}
