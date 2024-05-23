import javax.swing.*;
import java.awt.*;


public class BarnsleyFern extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int ITERATIONS = 1000000;
    private double x = 0, y = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ITERATIONS; i++) {
            double nextX, nextY;
            double rand = Math.random();

            if (rand < 0.01) {
                nextX = 0;
                nextY = 0.16 * y;
            } else if (rand < 0.86) {
                nextX = 0.85 * x + 0.04 * y;
                nextY = -0.04 * x + 0.85 * y + 1.6;
            } else if (rand < 0.93) {
                nextX = 0.2 * x - 0.26 * y;
                nextY = 0.23 * x + 0.22 * y + 1.6;
            } else {
                nextX = -0.15 * x + 0.28 * y;
                nextY = 0.26 * x + 0.24 * y + 0.44;
            }

            x = nextX;
            y = nextY;

            int plotX = (int) (WIDTH / 2 + x * WIDTH / 10);
            int plotY = (int) (HEIGHT - y * HEIGHT / 12);
            g.drawLine(plotX, plotY, plotX, plotY);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Barnsley Fern");
        BarnsleyFern fern = new BarnsleyFern();
        frame.add(fern);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}