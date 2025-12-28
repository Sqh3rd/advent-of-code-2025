import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Field implements Cloneable {
    private final int width;
    private final int height;
    private final int startX;
    private final int startY;
    private final List<Splitter> splitters;
    private final List<Beam> beams = new ArrayList<>();

    private final Map<Integer, Map<Integer, Splitter>> splitterMap = new TreeMap<>(Integer::compareTo);

    public static Field fromString(String s) {
        int startX = 0;
        int startY = 0;
        int width;
        int height;
        List<Splitter> splitters = new ArrayList<>();
        var lines = s.split("\n");
        height = lines.length;
        width = lines[0].length();

        for (int y = 0; y < lines.length; y++) {
            var curLine = lines[y];
            for (int x = 0; x < curLine.length(); x++) {
                var curChar = curLine.charAt(x);
                switch (curChar) {
                    case '^':
                        splitters.add(new Splitter(x, y));
                        break;
                    case 'S':
                        startX = x;
                        startY = y;
                        break;
                }
            }
        }

        return Field.builder()
                    .width(width)
                    .height(height)
                    .startX(startX)
                    .startY(startY)
                    .splitters(splitters)
                    .build();
    }

    @Builder
    Field(int width, int height, int startX, int startY, List<Splitter> splitters) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
        this.splitters = splitters;

        this.beams.add(new Beam(startX, startY));

        calculateSplitterMap();
    }

    private void calculateSplitterMap() {
        for (Splitter splitter : splitters) {
            splitterMap.putIfAbsent(splitter.y(), new TreeMap<>(Integer::compareTo));
            splitterMap.get(splitter.y()).put(splitter.x(), splitter);
        }
    }

    public int step() {
        List<Beam> toBeCreated = new ArrayList<>();
        List<Beam> toBeDeleted = new ArrayList<>();
        int splits = 0;
        for (Beam beam : beams) {
            if (beam.isFinished()) continue;
            var nextY = beam.getCurY() + 1;
            if (nextY >= height) {
                beam.finish();
                continue;
            }
            var yMap = splitterMap.get(nextY);
            if (Objects.isNull(yMap)) {
                beam.incY();
                continue;
            }
            var splitter = yMap.get(beam.getX());
            if (Objects.isNull(splitter)) {
                beam.incY();
                continue;
            }
            beam.finish();
            List<Beam> leftAndRightBeams = new ArrayList<>(2);
            if (beam.getX() > 0) leftAndRightBeams.add(new Beam(beam.getX() - 1, nextY));
            if (beam.getX() < width - 1) leftAndRightBeams.add(new Beam(beam.getX() + 1, nextY));
            toBeCreated.addAll(leftAndRightBeams);
            splits++;
        }
        beams.addAll(toBeCreated.stream()
                                .distinct()
                                .filter(
                                        tbc -> beams.stream()
                                                    .noneMatch(FunctionUtils.toFactory(Beam::overlaps).apply(tbc)))
                                .toList());
        return splits;
    }

    public boolean isDone() {
        return beams.stream()
                    .allMatch(Beam::isFinished);
    }

    public long beamCount() {
        return beams.stream()
                    .filter(Predicate.not(Beam::isFinished))
                    .count();
    }

    @Override
    public String toString() {
        List<List<Character>> lines = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            List<Character> chars = new ArrayList<>(width);
            for (int j = 0; j < width; j++) chars.add('.');
            lines.add(chars);
        }
        for (var splitter : splitters) {
            lines.get(splitter.y()).set(splitter.x(), '^');
        }
        for (var beam : beams) {
            for (int y = beam.getStartY(); y <= beam.getCurY(); y++) {
                lines.get(y).set(beam.getX(), '|');
            }
        }
        lines.get(startY).set(startX, 'S');
        return lines.stream()
                    .map(line -> line.stream()
                                     .map(Object::toString)
                                     .collect(Collectors.joining()))
                    .collect(Collectors.joining("\n"));
    }

    @Override
    public Field clone() {
        return new Field(width, height, startX, startY, splitters);
    }
}
