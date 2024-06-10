import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {
    private final int windowWidth = 1000;
    private final int windowHeight = 600;
    private final int winningScore = 5;

    private boolean gameActive = false;
    private boolean newGame = true;
    private boolean gameOver = false;
    private Timer timer;
    private final int delay = 8;

    private final Player player1;
    private final Player player2;
    private final Ball ball;

    private Rectangle player1Rect;
    private Rectangle player2Rect;
    private Rectangle ballRect;

    public Game() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        player1 = new Player(30, windowHeight / 3);
        player2 = new Player(this.windowWidth - 50, windowHeight / 3);
        ball = new Ball(this.windowWidth / 2 - 10, this.windowHeight / 2 - 100);

        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        // background
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, windowWidth, windowHeight);

        if (!gameActive) {
            if (newGame) {
                // text when game is not active
                graphics.setColor(Color.white);
                graphics.setFont(new Font("arial", Font.BOLD, 30));
                graphics.drawString("Press space to start.", this.windowWidth / 2 - 150, this.windowHeight / 2 - 50);
                return;
            } else {
                // Score points text
                graphics.setColor(Color.green);
                graphics.setFont(new Font("arial", Font.BOLD, 30));

                if (ball.getBallXPos() + ball.getBallWidth() <= -5 && player2.getScore() < winningScore) {
                    graphics.drawString("Player 2 scores a point", this.windowWidth / 2 - 150, this.windowHeight / 2 - 50);
                }

                if (ball.getBallXPos() >= this.windowWidth && player1.getScore() < winningScore) {
                    graphics.drawString("Player 1 scores a point", this.windowWidth / 2 - 150, this.windowHeight / 2 - 50);
                }

                graphics.drawString("Press space to continue", this.windowWidth / 2 - 150, this.windowHeight / 2);
            }
        }
        // middle border
        graphics.setColor(Color.white);
        graphics.fillRect(this.windowWidth / 2 - 10, 0, 10, this.windowHeight);

        // players
        graphics.setColor(Color.white);
        graphics.fillRect(player1.getPlayerXPos(), player1.getPlayerYPos(), player1.getWidth(), player1.getHeight());
        graphics.fillRect(player2.getPlayerXPos(), player2.getPlayerYPos(), player2.getWidth(), player2.getHeight());

        // ball
        graphics.setColor(Color.yellow);
        graphics.fillOval(ball.getBallXPos(), ball.getBallYPos(), ball.getBallWidth(), ball.getBallHeight());

        // scores
        graphics.setColor(Color.white);
        graphics.setFont(new Font("arial", Font.BOLD, 30));
        graphics.drawString("Score: " + player1.getScore(), 20, 40);
        graphics.drawString("Score: " + player2.getScore(), this.windowWidth - 150, 40);

        if (player1.getScore() >= winningScore) {
            graphics.setColor(Color.green);
            graphics.setFont(new Font("arial", Font.BOLD, 30));
            graphics.drawString("Player 1 wins!", this.windowWidth / 2 - 110, this.windowHeight / 2 - 50);

            gameOver = true;
            return;
        }

        if (player2.getScore() >= winningScore) {
            graphics.setColor(Color.green);
            graphics.setFont(new Font("arial", Font.BOLD, 30));
            graphics.drawString("Player 2 wins!", this.windowWidth / 2 - 110, this.windowHeight / 2 - 50);

            gameOver = true;
            return;
        }

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (gameActive) {
            ball.moveBall();

            player1Rect = new Rectangle(player1.getPlayerXPos(), player1.getPlayerYPos(), player1.getWidth(), player1.getHeight());
            player2Rect = new Rectangle(player2.getPlayerXPos(), player2.getPlayerYPos(), player2.getWidth(), player2.getHeight());
            ballRect = new Rectangle(ball.getBallXPos(), ball.getBallYPos(), ball.getBallWidth(), ball.getBallHeight());

            // Top and bottom border collision
            if (ball.getBallYPos() <= 0 || ball.getBallYPos() + ball.getBallHeight() >= this.windowHeight - 30) {
                ball.setBallYDir(-ball.getBallYDir());
            }

            // Ball and player paddle collisions
            if ((ball.getBallXPos() <= player1.getPlayerXPos() + player1.getWidth() && ballRect.intersects(player1Rect)) ||
                    (ball.getBallXPos() + ball.getBallWidth() >= player2.getPlayerXPos() && ballRect.intersects(player2Rect))) {
                ball.setBallXDir(-ball.getBallXDir());
            }

            // When the ball exceeded any one of the player's borders
            if (ball.getBallXPos() + ball.getBallWidth() <= -5) {
                player2.addScore();
                gameActive = false;
                ball.setBallXDir(-ball.getBallXDir());
            }

            if (ball.getBallXPos() >= this.windowWidth) {
                player1.addScore();
                gameActive = false;
                ball.setBallXDir(-ball.getBallXDir());
            }

        }

        repaint();
    }

    private void reset() {
        ball.setBallXPos(this.windowWidth / 2 - 10);
        ball.setBallYPos(this.windowHeight / 2 - 100);
        player1.setPlayerYPos(this.windowHeight / 3);
        player2.setPlayerYPos(this.windowHeight / 3);

        if (gameOver) {
            player1.resetScore();
            player2.resetScore();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameActive) {
            this.gameActive = true;
            this.newGame = false;
            this.reset();
        }

        if (e.getKeyCode() == KeyEvent.VK_W && player1.getPlayerYPos() >= 0 && gameActive) {
            player1.moveUp();
        }

        if (e.getKeyCode() == KeyEvent.VK_S && player1.getPlayerYPos() + player1.getHeight() <= this.windowHeight && gameActive) {
            player1.moveDown();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && player2.getPlayerYPos() >= 0 && gameActive) {
            player2.moveUp();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && player2.getPlayerYPos() + player2.getHeight() <= this.windowHeight && gameActive) {
            player2.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
