import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        Game game = new Game();
        jFrame.setBounds(10, 10, game.getWindowWidth(), game.getWindowHeight());
        jFrame.setTitle("Pong");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(game);
    }
}