package com.sqherd.dial;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Dial {
    private int value;

    public int rotate(TurnInstruction instruction) {
        var delta = instruction.distance() % 100;
        this.value += 100 + (instruction.direction().sign * delta);
        this.value %= 100;
        return this.value;
    }

}
