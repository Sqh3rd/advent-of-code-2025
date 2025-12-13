import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputTest {
    @Test
    void fromString() {
        var input = """
                 123 328  51 64 
                 45 64  387 23 
                  6 98  215 314
                *   +   *   + """;
        var result = Input.fromString(input);
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(InputProvider.testInput());
    }

    @Test
    void solveEquations() {
        var input = InputProvider.testInput();

        var result = input.solveEquations();
        assertThat(result).containsExactlyElementsOf(
                List.of(33210L, 490L, 4243455L, 401L)
        );
    }
}
