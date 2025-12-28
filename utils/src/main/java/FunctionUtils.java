import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionUtils {
    public static <T1, T2, R> Function<T1, Function<T2, R>> toFactory(BiFunction<T1, T2, R> input) {
        return (T1 t1) -> (T2 t2) -> input.apply(t1, t2);
    }

    public static <T1, T2> Function<T1, Predicate<T2>> toFactory(BiPredicate<T1, T2> input) {
        return (T1 t1) -> (T2 t2) -> input.test(t1, t2);
    }
}
