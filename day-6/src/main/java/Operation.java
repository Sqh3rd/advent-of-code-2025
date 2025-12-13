import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Operation {
    MUL('*'),
    ADD('+');

    private final char identifier;

    public static Operation fromString(String s) {
        var possibleIdentifier = s.trim().charAt(0);
        return Arrays.stream(Operation.values()).filter(it -> it.isIdentifiedBy(possibleIdentifier))
                .findFirst()
                .orElse(null);
    }

    private boolean isIdentifiedBy(char c) {
        return identifier == c;
    }
}
