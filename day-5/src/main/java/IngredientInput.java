import java.util.Arrays;
import java.util.List;

public record IngredientInput(List<IngredientRange> ranges, List<Long> ids) {
    public static IngredientInput fromString(String s) {
        var temp = s.split("\\s{4}");
        var rangesToBeParsed = temp[0];
        var idsToBeParsed = temp[1];

        var ranges = Arrays.stream(rangesToBeParsed.split("\n"))
                .map(String::trim)
                .map(IngredientRange::fromString)
                .toList();
        var ids = Arrays.stream(idsToBeParsed.split("\n"))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();

        return new IngredientInput(ranges, ids);
    }
}
