import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {
    @Test
    void solveFirst() {
        var input = new IngredientInput(List.of(
                new IngredientRange(3L, 5L),
                new IngredientRange(10L, 14L),
                new IngredientRange(16L, 20L),
                new IngredientRange(12L, 18L)
        ), List.of(
                1L,
                5L,
                8L,
                11L,
                17L,
                32L
        ));

        var result = Solver.debugForInput(input).solveFirst();
        assertThat(result).isEqualTo(3);
    }

    @Test
    void solveSecond() {
        var input = new IngredientInput(List.of(
                new IngredientRange(3L, 5L),
                new IngredientRange(10L, 14L),
                new IngredientRange(16L, 20L),
                new IngredientRange(12L, 18L)
        ), List.of(
                1L,
                5L,
                8L,
                11L,
                17L,
                32L
        ));

        var result = Solver.debugForInput(input).solveSecond();
        assertThat(result).isEqualTo(14);
    }
}
