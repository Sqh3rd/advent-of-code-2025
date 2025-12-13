import java.util.ArrayList;
import java.util.List;

public class IngredientRangeUtils {
    public static List<IngredientRange> mergeOverlappingRanges(List<IngredientRange> ranges) {
        var mutableRanges = new ArrayList<>(ranges);
        mutableRanges.sort(IngredientRange::compare);
        var result = new ArrayList<IngredientRange>();
        var candidate = mutableRanges.getFirst();

        for (int i = 1; i < mutableRanges.size(); i++) {
            if (!IngredientRange.canBeMerged(candidate, mutableRanges.get(i))) {
                result.add(candidate);
                candidate = mutableRanges.get(i);
                continue;
            }
            candidate = IngredientRange.merge(candidate, mutableRanges.get(i));
        }
        result.add(candidate);
        System.out.println("Merged " + ranges.size() + " ranges to " + result.size() + " non-overlapping ranges");
        return result;
    }
}
