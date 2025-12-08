import com.sqh3rd.inputparser.InputParser;

public class Main {
    public static void main(String[] args) {
        var input = InputParser.serializeInputByLines(BatteryBank::fromString, Main.class);
        var solver = Solver.forInput(input);
        System.out.println("First Result:  " + solver.solveFirst());
        System.out.println("Second Result: " + solver.solveSecond());
    }
}
