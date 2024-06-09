import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {
    private final int windowWidth = 1000;
    private final int windowHeight = 600;

    private boolean gameActive = false;
    private Timer timer;
    private final int delay = 8;

    private final Player player1;
    private final Player player2;

    public Game() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        player1 = new Player(30, windowHeight / 3);
        player2 = new Player(this.windowWidth - 50, windowHeight / 3);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        // background
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, windowWidth, windowHeight);

        if (!gameActive) {
            // text when game is not active
            graphics.setColor(Color.white);
            graphics.setFont(new Font("arial", Font.BOLD, 30));
            graphics.drawString("Press space to start.", this.windowWidth / 2 - 150, this.windowHeight / 2 - 50);
            return;
        }

        // middle border
        graphics.setColor(Color.white);
        graphics.fillRect(this.windowWidth / 2 - 10, 0, 10, this.windowHeight);

        // players
        graphics.setColor(Color.white);
        graphics.fillRect(player1.getPlayerXPos(), player1.getPlayerYPos(), player1.getWidth(), player1.getHeight());
        graphics.fillRect(player2.getPlayerXPos(), player2.getPlayerYPos(), player2.getWidth(), player2.getHeight());


        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.gameActive = true;
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
