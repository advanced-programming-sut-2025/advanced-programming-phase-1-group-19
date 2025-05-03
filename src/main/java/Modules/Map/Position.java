package Modules.Map;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void move(Direction direction) {
        int dx = direction.getDx();
        int dy = direction.getDy();
        move(dx, dy);
    }
}
