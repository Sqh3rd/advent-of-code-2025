import Roll.RollLine;
import Roll.RollLineUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Solver {
    private final List<RollLine> input;
    private final boolean debug;

    public static Solver forInput(List<RollLine> input) {
        return new Solver(input, false);
    }

    public static Solver debugForInput(List<RollLine> input) {
        return new Solver(input, true);
    }

    public int solveFirst() {
        return RollLineUtils.removeAccessible(RollLineUtils.getAccessible(input));
    }

    public int solveSecond() {
        var prevResult = 0;
        var result = 0;

        var cur = RollLineUtils.getAccessible(input);
        if (debug) print(cur);
        do {
            if (debug) System.out.println();
            prevResult = result;
            result += RollLineUtils.removeAccessible(cur);
            cur = RollLineUtils.getAccessible(cur);
            if (debug) print(cur);
        } while (prevResult != result);

        return result;
    }

    private void print(List<RollLine> rl) {
        for (var i : rl) {
            System.out.println(i);
        }
    }
}
