package com.sqherd;

import com.sqherd.dial.Dial;
import com.sqherd.dial.TurnDirection;
import com.sqherd.dial.TurnInstruction;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class Solver {
    private final List<TurnInstruction> instructions;
    private boolean debug = false;

    public static Solver debugForInput(List<TurnInstruction> instructions) {
        return new Solver(instructions, true);
    }

    public static Solver forInput(List<TurnInstruction> instructions) {
        return new Solver(instructions);
    }

    public int solveFirst() {
        var dial = new Dial(50);
        int zeroCount = 0;

        for (var instruction : instructions) {
            var result = dial.rotate(instruction);
            if (result == 0)
                zeroCount++;
        }

        return zeroCount;
    }

    public int solveSecond() {
        var dial = new Dial(50);
        int zeroCount = 0;

        int prev = 50;
        int current = prev;
        if (debug) {
            System.out.println("Dial position: " + current);
        }
        for (var instruction : instructions) {
            int deltaCount = 0;
            prev = current;
            current = dial.rotate(instruction);

            // Add full rotations
            deltaCount += instruction.distance() / 100;

            // Add partial rotations
            if (
                    prev != 0 &&
                            (current == 0
                                    || (instruction.direction() == TurnDirection.L && current > prev)
                                    || (instruction.direction() == TurnDirection.R && current < prev))) {
                deltaCount++;
            }

            if (debug) {
                System.out.println("Dial position:  " + current);
                System.out.println("Count increase: " + deltaCount);
            }

            zeroCount += deltaCount;
        }

        return zeroCount;
    }
}
