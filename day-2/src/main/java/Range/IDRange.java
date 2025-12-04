package Range;

public record IDRange(int min, int max) {
    public static IDRange fromString(String s) {
        var idx = s.indexOf('-');
        return new IDRange(Integer.parseInt(s.substring(0, idx)), Integer.parseInt(s.substring(idx + 1)));
    }

    public static IDRange empty() {
        return new IDRange(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public boolean isEmpty() {
        return min == max && min == Integer.MIN_VALUE;
    }
}
