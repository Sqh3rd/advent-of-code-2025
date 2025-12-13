import com.sqh3rd.inputparser.InputParser;

public class Main {
    public static void main(String[] args) {
        var input = InputParser.serializeInput(IngredientInput::fromString, Main.class);
        var solver = Solver.forInput(input);
        System.out.println("First Result:  " + solver.solveFirst());

        solver = Solver.debugForInput(input);
        System.out.println("Second Result: " + solver.solveSecond());
    }
}
