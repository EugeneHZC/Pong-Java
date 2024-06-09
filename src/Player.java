public class Player  {
    private final int playerXPos;
    private int playerYPos;
    private final int width = 10;
    private final int height = 100;
    private final int velocity = 10;
    private int score = 0;

    public Player(int playerXPos, int playerYPos) {
        this.playerXPos = playerXPos;
        this.playerYPos = playerYPos;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void setPlayerYPos(int playerYPos) {
        this.playerYPos = playerYPos;
    }

    public void moveUp() {
        this.playerYPos -= this.velocity;
    }

    public void moveDown() {
        this.playerYPos += this.velocity;
    }

    public void addScore() {
        this.score += 5;
    }

    public int getScore() {
        return score;
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
