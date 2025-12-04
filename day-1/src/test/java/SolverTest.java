import com.sqherd.Solver;
import com.sqherd.dial.TurnDirection;
import com.sqherd.dial.TurnInstruction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {
    @ParameterizedTest
    @MethodSource("provideSecondExamples")
    void solveSecondExamples(List<TurnInstruction> input, int expectedOutput) {
        int result = Solver.debugForInput(input).solveSecond();
        assertThat(result).isEqualTo(expectedOutput);
    }

    static Stream<Arguments> provideSecondExamples() {
        var firstInput = List.of(
                new TurnInstruction(TurnDirection.L, 68),
                new TurnInstruction(TurnDirection.L, 30),
                new TurnInstruction(TurnDirection.R, 48),
                new TurnInstruction(TurnDirection.L, 5),
                new TurnInstruction(TurnDirection.R, 60),
                new TurnInstruction(TurnDirection.L, 55),
                new TurnInstruction(TurnDirection.L, 1),
                new TurnInstruction(TurnDirection.L, 99),
                new TurnInstruction(TurnDirection.R, 14),
                new TurnInstruction(TurnDirection.L, 82)
        );
        var secondInput = List.of(new TurnInstruction(TurnDirection.R, 1000));
        return Stream.of(
                Arguments.of(firstInput, 6),
                Arguments.of(secondInput, 10)
        );
    }
}
