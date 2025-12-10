import Roll.RollLine;
import com.sqh3rd.inputparser.InputParser;

public class Main {
    public static void main(String[] args) {
        var input = InputParser.serializeInputByLines(RollLine::fromString, Main.class);
        var solver = Solver.forInput(input);
        System.out.println("First Part:  " + solver.solveFirst());

        System.out.println("Second Part: " + solver.solveSecond());
    }
}
