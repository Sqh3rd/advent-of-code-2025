import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

import static java.util.function.Predicate.not;

public record Input(List<List<Long>> numbers, List<Operation> operations) {
    public static Input fromString(String s) {
        var lines = s.split("\n");
        var numbersToParse = Arrays.copyOfRange(lines, 0, lines.length - 1);
        var numbers = Arrays.stream(numbersToParse)
                .map(it -> Arrays.stream(it.trim().split("\\s"))
                        .filter(not(String::isBlank))
                        .map(Long::parseLong)
                        .toList())
                .toList();
        var operations = Arrays.stream(lines[lines.length - 1].split("\\s"))
                .filter(not(String::isBlank))
                .map(Operation::fromString)
                .toList();
        return new Input(numbers, operations);
    }

    public List<Long> solveEquations() {
        int size = guardForSize();
        return solveEquations(size);
    }

    private int guardForSize() {
        var size = numbers.getFirst().size();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).size() != size)
                throw new IllegalArgumentException("Size of all nested number lists needs to be equal");
        }
        if (operations.size() != size)
            throw new IllegalArgumentException("Size of operations lists needs to be equal to size of nested numbers lists");
        return size;
    }

    private ArrayList<Long> solveEquations(int size) {
        var results = new ArrayList<Long>(size);
        for (int i = 0; i < size; i++) {
            var result = numbers.getFirst().get(i);
            BinaryOperator<Long> operator = switch (operations.get(i)) {
                case Operation.ADD -> this::add;
                case Operation.MUL -> this::mul;
            };
            for (int j = 1; j < numbers.size(); j++) {
                result = operator.apply(result, numbers.get(j).get(i));
            }
            results.add(result);
        }
        return results;
    }

    private Long mul(Long a, Long b) {
        return a * b;
    }

    private Long add(Long a, Long b) {
        return a + b;
    }
}
