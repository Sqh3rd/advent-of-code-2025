public record Beam2(int x, int duplicates) {
    public static Beam2 collectDuplicates(Beam2 b1, Beam2 b2) {
        if (b1.x() != b2.x()) return null;
        return new Beam2(b1.x(), b1.duplicates() + b2.duplicates());
    }
}
