package Roll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record RollLine(List<Optional<Roll>> rollIdxs) {
    public static RollLine fromString(String s) {
        var rollIdxs = new ArrayList<Optional<Roll>>(s.length());
        for (int i = 0; i < s.length(); i++) {
            rollIdxs.add(Optional.ofNullable(Roll.fromChar(s.charAt(i))));
        }
        return new RollLine(rollIdxs);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RollLine rl)) return false;
        if (rollIdxs.size() != rl.rollIdxs.size()) return false;
        for (int i = 0; i < rollIdxs.size(); i++) {
            if (!(rollIdxs.get(i).equals(rl.rollIdxs.get(i)))) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rollIdxs.stream()
                .map(it -> it.map(Roll::toString).orElse("."))
                .reduce(String::concat)
                .orElseThrow();
    }
}
