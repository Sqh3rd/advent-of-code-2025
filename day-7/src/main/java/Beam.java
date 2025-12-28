import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Beam {
    private final int x;
    private final int startY;
    private int curY;

    public Beam(int x, int startY) {
        this.x = x;
        this.startY = startY;
        this.curY = startY;
    }

    private boolean finished;

    public void incY() {
        if (!finished) curY++;
    }

    public void finish() {
        finished = true;
    }

    public boolean overlaps(Beam other) {
        return x == other.x &&
                !finished &&
                !other.finished &&
                (startY == other.startY ||
                        curY == other.curY ||
                        (startY >= other.startY && startY <= other.curY) ||
                        (curY >= other.startY && curY >= other.curY));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Beam o)) return false;
        return overlaps(o);
    }

    @Override
    public int hashCode() {
        return x + startY;
    }
}
