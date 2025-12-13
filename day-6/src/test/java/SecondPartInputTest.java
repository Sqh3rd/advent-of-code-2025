import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SecondPartInputTest {
    @Test
    void fromString() {
        var input = """
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   +\s""";
        var result = SecondPartInput.fromString(input);
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(SecondPartInputProvider.testInput());
    }

    @Test
    void solveEquations() {
        var input = SecondPartInputProvider.testInput();
        var result = input.solveEquations();
        assertThat(result).containsExactlyElementsOf(
                List.of(1058L, 3253600L, 625L, 8544L)
        );
    }
}
