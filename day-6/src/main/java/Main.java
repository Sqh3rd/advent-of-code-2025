import com.sqh3rd.inputparser.InputParser;

public class Main {
    public static void main(String[] args) {
        var input = InputParser.serializeInput(Input::fromString, Main.class);
        System.out.println("First Result:  " + Solver.solveFirst(input));

        var secondInput = InputParser.serializeInput(SecondPartInput::fromString, Main.class);
        System.out.println("Second Result:  " + Solver.solveSecond(secondInput));
    }
}