package app;
public class Tile {
    private int x;
    private int y;

    // Cant set in the game class as they are abiding pixel laws and not the tile laws
    // but we can have 2 different fields for tile and pixel position

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        // the tile number from up and the tile number from left
    }

    // Get the actual pixel position of each tile (the top left corner's position)
    public int getPixelX () {
        return x * 25;
    }

    public int getPixelY() {
        return y * 25;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int tileWidth() {
        return 25;
    }
}
