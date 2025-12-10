import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {
    @Test
    void solveFirst() {
        var input = TestInputProvider.getInput();
        var expected = 13L;
        var result = Solver.forInput(input).solveFirst();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void solveSecond() {
        var input = TestInputProvider.getInput();
        var expected = 43L;
        var result = Solver.debugForInput(input).solveSecond();
        assertThat(result).isEqualTo(expected);
    }
}
