import lombok.SneakyThrows;

public abstract class AbstractSolver<T> {
    @SneakyThrows
    public static <T, E extends AbstractSolver<T>> E forInput(Class<E> solver, T input) {
        return solver.getDeclaredConstructor(input.getClass()).newInstance(input);
    }
}
