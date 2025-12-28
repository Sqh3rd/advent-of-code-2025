import com.sqh3rd.inputparser.InputParser;

public class Main {
    public static void main(String[] args) {
        var firstInput = InputParser.serializeInput(Field::fromString, Main.class);
        var secondInput = InputParser.serializeInput(Field2::fromString, Main.class);
        System.out.println("First Result:  " + Solver.solveFirst(firstInput));
        System.out.println("Second Result: " + Solver.debugSecond(secondInput));
    }
}
