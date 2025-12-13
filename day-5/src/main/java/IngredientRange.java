import java.util.Objects;

public record IngredientRange(Long min, Long max) {
    public static IngredientRange fromString(String s) {
        var nums = s.split("-");
        return new IngredientRange(Long.parseLong(nums[0]), Long.parseLong(nums[1]));
    }

    public static int compare(IngredientRange range1, IngredientRange range2) {
        return range1.min.compareTo(range2.min);
    }

    public static boolean canBeMerged(IngredientRange range1, IngredientRange range2) {
        return range1.equals(range2)
                || range1.encapsulates(range2)
                || range2.encapsulates(range1)
                || range1.overlaps(range2);
    }

    public static IngredientRange merge(IngredientRange range1, IngredientRange range2) {
        if (!canBeMerged(range1, range2))
            throw new RuntimeException(new IllegalArgumentException("Input ranges can not be merged together"));
        return new IngredientRange(Math.min(range1.min, range2.min), Math.max(range1.max, range2.max));
    }

    private boolean equals(IngredientRange other) {
        return Objects.equals(min, other.min) && Objects.equals(max, other.max);
    }

    private boolean encapsulates(IngredientRange other) {
        return other.min > min && other.max < max;
    }

    private boolean overlaps(IngredientRange other) {
        return (min <= other.min + 1 && max >= other.min - 1) || (min <= other.max + 1 && max >= other.max - 1);
    }

    public boolean contains(Long check) {
        return check >= min && check <= max;
    }

    public Long size() {
        return max - min + 1;
    }

    @Override
    public String toString() {
        return min + "-" + max;
    }
}
