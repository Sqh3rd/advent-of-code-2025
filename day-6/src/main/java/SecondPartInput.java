import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

import static java.util.function.Predicate.not;

public record SecondPartInput(List<List<Long>> numbers, List<Operation> operations) {
    public static SecondPartInput fromString(String s) {
        var lines = s.split("\n");
        var numbersToParse = Arrays.copyOfRange(lines, 0, lines.length - 1);

        var numbers = new ArrayList<List<Long>>();
        var length = numbersToParse[0].length();
        var currentList = new ArrayList<Long>();
        for (int i = 0; i < length; i++) {
            int finalI = i;
            var possibleNum = Arrays.stream(numbersToParse)
                    .map(it -> it.charAt(length - finalI - 1))
                    .map(Object::toString)
                    .reduce(String::concat)
                    .filter(not(String::isBlank))
                    .map(String::trim);
            if (possibleNum.isPresent()) {
                currentList.add(Long.parseLong(possibleNum.get()));
            } else if (!currentList.isEmpty()) {
                numbers.add(currentList);
                currentList = new ArrayList<>();
            }
        }
        numbers.add(currentList);

        var operations = Arrays.stream(lines[lines.length - 1].split("\\s"))
                .filter(not(String::isBlank))
                .map(Operation::fromString)
                .toList()
                .reversed();
        return new SecondPartInput(numbers, operations);
    }

    public List<Long> solveEquations() {
        if (numbers.size() != operations.size())
            throw new IllegalArgumentException("Expected size of numbers and size of operations to equal");
        var result = new ArrayList<Long>(numbers.size());
        for (int i = 0; i < numbers.size(); i++) {
            BinaryOperator<Long> operation = switch (operations.get(i)) {
                case ADD -> this::add;
                case MUL -> this::mul;
            };
            result.add(numbers.get(i).stream()
                    .reduce(operation)
                    .orElseThrow());
        }
        return result;
    }

    private Long add(Long a, Long b) {
        return a + b;
    }

    private Long mul(Long a, Long b) {
        return a * b;
    }
}
