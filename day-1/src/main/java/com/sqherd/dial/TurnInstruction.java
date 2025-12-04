package com.sqherd.dial;

public record TurnInstruction(TurnDirection direction, int distance) {
    public static TurnInstruction fromString(String s) {
        var dir = TurnDirection.fromString(s.substring(0, 1));
        var dist = Integer.parseInt(s.substring(1));
        return new TurnInstruction(dir, dist);
    }

    @Override
    public String toString() {
        return direction.name() + distance;
    }
}
