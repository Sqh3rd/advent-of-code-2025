import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {
    @Test
    void solveFirst() {
        var input = InputProvider.testInput();
        var result = Solver.solveFirst(input);
        assertThat(result).isEqualTo(4277556L);
    }

    @Test
    void solveSecond() {
        var input = SecondPartInputProvider.testInput();
        var result = Solver.solveSecond(input);
        assertThat(result).isEqualTo(3263827L);
    }
}
