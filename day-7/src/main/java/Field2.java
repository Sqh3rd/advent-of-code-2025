import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Field2 implements Cloneable {
    private final int width;
    private final int height;
    private final int startX;
    private final boolean[][] splitters;
    private List<Beam2> beams = new ArrayList<>();

    private int curY = 0;

    public static Field2 fromString(String s) {
        var lines = s.split("\n");
        int width = lines[0].length();
        int height = lines.length;
        int startX = 0;
        boolean[][] splitters = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var curChar = lines[y].charAt(x);
                if (curChar == '.') continue;
                if (curChar == '^') splitters[y][x] = true;
                if (curChar == 'S') startX = x;
            }
        }
        return Field2.builder()
                     .width(width)
                     .height(height)
                     .startX(startX)
                     .splitters(splitters)
                     .build();
    }

    @Builder(toBuilder = true)
    public Field2(int width, int height, int startX, boolean[][] splitters) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.splitters = splitters;

        beams.add(new Beam2(startX, 1));
    }

    public int step() {
        if (curY == height) return -1;
        List<Beam2> nextBeams = new ArrayList<>();
        int splits = 0;
        for (var beam : beams) {
            var curSplitter = splitters[curY][beam.x()];
            if (!curSplitter) {
                nextBeams.add(beam);
                continue;
            }
            splits += beam.duplicates();
            if (beam.x() > 0) nextBeams.add(new Beam2(beam.x() - 1, beam.duplicates()));
            if (beam.x() < width - 1) nextBeams.add(new Beam2(beam.x() + 1, beam.duplicates()));
        }
        System.out.println(curSliceToString());
        beams = nextBeams.stream()
                         .collect(Collectors.groupingBy(Beam2::x))
                         .values()
                         .stream()
                         .map(it -> it.stream()
                                      .reduce(Beam2::collectDuplicates)
                                      .orElseThrow())
                         .toList();
        curY++;
        return splits;
    }

    public boolean isDone() {
        return curY == height;
    }

    public Field2 clone() {
        return toBuilder().build();
    }

    public String curSliceToString() {
        List<Character> line = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            line.add(splitters[curY][x] ? '^' : ' ');
        }

        if (curY == 0) {
            line.set(startX, 'S');
        }

        for (var beam : beams) {
            line.set(beam.x(), '|');
        }

        return line.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
