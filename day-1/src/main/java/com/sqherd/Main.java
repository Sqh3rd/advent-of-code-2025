package com.sqherd;

import com.sqh3rd.inputparser.InputParser;
import com.sqherd.dial.TurnInstruction;
import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        var input = InputParser.serializeInputByLines(TurnInstruction::fromString, Main.class);

        System.out.println("First Part: ");
        System.out.println(Solver.forInput(input).solveFirst());

        System.out.println("Second Part: ");
        System.out.println(Solver.forInput(input).solveSecond());
    }
}