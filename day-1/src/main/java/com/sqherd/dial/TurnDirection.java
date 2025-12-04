package com.sqherd.dial;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum TurnDirection {
    L(-1),
    R(1);

    public final int sign;

    public static TurnDirection fromString(String s) {
        return Arrays.stream(TurnDirection.values())
                .filter(it -> s.equals(it.name()))
                .findFirst()
                .orElseThrow();
    }
}
