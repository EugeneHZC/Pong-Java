public class Ball {
    private int ballXPos;
    private int ballYPos;

    private final int ballWidth = 20;
    private final int ballHeight = 20;
    private final int ballVelocity = 2;

    private int ballXDir = -1;
    private int ballYDir = 2;

    public Ball(int ballXPos, int ballYPos) {
        this.ballXPos = ballXPos;
        this.ballYPos = ballYPos;
    }

    public int getBallXPos() {
        return ballXPos;
    }

    public int getBallYPos() {
        return ballYPos;
    }

    public int getBallWidth() {
        return ballWidth;
    }

    public int getBallHeight() {
        return ballHeight;
    }

    public int getBallXDir() {
        return ballXDir;
    }

    public void setBallXDir(int ballXDir) {
        this.ballXDir = ballXDir;
    }

    public int getBallYDir() {
        return ballYDir;
    }

    public void setBallYDir(int ballYDir) {
        this.ballYDir = ballYDir;
    }

    public void moveBall() {
        this.ballXPos += this.ballVelocity * this.ballXDir;
        this.ballYPos += this.ballVelocity * this.ballYDir;
    }
}
