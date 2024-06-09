public class Player  {
    private final int playerXPos;
    private int playerYPos;
    private final int width = 10;
    private final int height = 100;
    private final int velocity = 5;

    public Player(int playerXPos, int playerYPos) {
        this.playerXPos = playerXPos;
        this.playerYPos = playerYPos;
    }

    public void moveUp() {
        this.playerYPos -= this.velocity;
    }

    public void moveDown() {
        this.playerYPos += this.velocity;
    }

    public int getPlayerXPos() {
        return playerXPos;
    }

    public int getPlayerYPos() {
        return playerYPos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
