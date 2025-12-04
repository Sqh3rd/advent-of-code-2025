import Range.IDRange;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    public int solveFirst() {

    }
}
