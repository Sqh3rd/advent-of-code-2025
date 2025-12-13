import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Solver {
    public static long solveFirst(Input input) {
        return input.solveEquations()
                .stream()
                .reduce(Long::sum)
                .orElseThrow();
    }

    public static long solveSecond(SecondPartInput input) {
        return input.solveEquations()
                .stream()
                .reduce(Long::sum)
                .orElseThrow();
    }
}
