package Roll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RollLineUtils {
    public static List<RollLine> getAccessible(List<RollLine> input) {
        var result = new ArrayList<RollLine>(input.size());
        for (int i = 0; i < input.size(); i++) {
            var curLine = input.get(i).rollIdxs();
            var resultList = new ArrayList<Optional<Roll>>(curLine.size());
            for (int j = 0; j < curLine.size(); j++) {
                var curRoll = curLine.get(j);
                if (curRoll.isEmpty()) {
                    resultList.add(Optional.empty());
                    continue;
                }

                var freeSpaces = 0;
                var isVerticallyFree = i % (input.size() - 1) == 0;
                var isHorizontallyFree = j % (curLine.size() - 1) == 0;
                if (isVerticallyFree) freeSpaces += 3;
                if (isHorizontallyFree) freeSpaces += 3;
                if (isHorizontallyFree && isVerticallyFree) freeSpaces -= 1;

                for (int ii = -1; ii <= 1; ii++) {
                    if (freeSpaces >= 5) break;
                    if (i == 0 && ii == -1) continue;
                    if (i == input.size() - 1 && ii == 1) continue;
                    for (int jj = -1; jj <= 1; jj++) {
                        if (freeSpaces >= 5) break;
                        if (j == 0 && jj == -1) continue;
                        if (j == curLine.size() - 1 && jj == 1) continue;
                        if (ii == 0 && jj == 0) continue;
                        var neighbourIsFree = input.get(i + ii).rollIdxs().get(j + jj).isEmpty();
                        if (neighbourIsFree) freeSpaces++;
                    }
                }

                resultList.add(Optional.of(new Roll(freeSpaces >= 5)));
            }
            result.add(new RollLine(resultList));
        }
        return result;
    }

    public static int removeAccessible(List<RollLine> input) {
        int result = 0;
        for (var rl : input) {
            var curLine = rl.rollIdxs();
            for (int i = 0; i < curLine.size(); i++) {
                var cur = curLine.get(i);
                if (cur.isEmpty()) continue;
                if (!cur.get().isAccessible()) continue;
                curLine.remove(i);
                curLine.add(i, Optional.empty());
                result++;
            }
        }
        return result;
    }
}
