import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Solver {
    public static int debugFirst(Field input) {
        return solveFirst(input, true);
    }
    public static int solveFirst(Field input) {
        return solveFirst(input, false);
    }
    private static int solveFirst(Field input, boolean debug) {
        Consumer<String> log = getLogFunction(debug);
        int result = 0;
        log.accept(input.toString());
        log.accept("\n");
        while (!input.isDone()) {
            var temp = input.step();
            result += temp;
            log.accept(input.toString());
        }
        return result;
    }

    public static int debugSecond(Field2 input) {
        return solveSecond(input, true);
    }
    public static int solveSecond(Field2 input) {
        return solveSecond(input, false);
    }
    public static int solveSecond(Field2 input, boolean debug) {
        int result = 0;
        while (!input.isDone()) {
            result += input.step();
        }
        return ++result;
    }

    private static void logString(String s) {
        System.out.println(s);
    }

    private static Consumer<String> getLogFunction(boolean debug) {
        return debug ? Solver::logString : (String s) -> {};
    }
}
