import Range.IDRange;
import com.sqh3rd.inputparser.InputParser;

public class Main {
    public static void main(String[] args) {
        var input = InputParser.serializeInputBySeparator(IDRange::fromString, ",", Main.class);
        System.out.println("First Part: ");
        System.out.println(Solver.forInput(input).solveFirst());

        System.out.println("Second Part: ");
        System.out.println(Solver.forInput(input).solveSecond());
    }
}
