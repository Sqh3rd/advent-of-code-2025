package Roll;

public record Roll(boolean isAccessible) {
    public static Roll fromChar(char c) {
        if (c != '@' && c != 'x') return null;
        return new Roll(c == 'x');
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Roll r)) return false;
        return r.isAccessible == isAccessible;
    }

    @Override
    public String toString() {
        return isAccessible ? "x" : "@";
    }
}
