import java.util.List;

public class InputProvider {
    public static Input testInput() {
        return new Input(
                List.of(
                        List.of(123L, 328L, 51L, 64L),
                        List.of(45L, 64L, 387L, 23L),
                        List.of(6L, 98L, 215L, 314L)
                ),
                List.of(Operation.MUL, Operation.ADD, Operation.MUL, Operation.ADD)
        );
    }
}

