package Range;

public record IDRange(long min, long max, Integer order) {
    public static IDRange fromString(String s) {
        var idx = s.indexOf('-');
        return new IDRange(Long.parseLong(s.substring(0, idx)), Long.parseLong(s.substring(idx + 1)));
    }

    public IDRange(long min, long max) {
        this(min, max, null);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IDRange idRange)) return false;

        return min == idRange.min && max == idRange.max;
    }
}
