import java.util.List;

public class SecondPartInputProvider {
    public static SecondPartInput testInput() {
        return new SecondPartInput(
                List.of(
                        List.of(4L, 431L, 623L),
                        List.of(175L, 581L, 32L),
                        List.of(8L, 248L, 369L),
                        List.of(356L, 24L, 1L)
                ),
                List.of(Operation.ADD, Operation.MUL, Operation.ADD, Operation.MUL)
        );
    }
}
