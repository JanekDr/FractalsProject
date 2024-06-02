package fractals;

import java.awt.*;

public class BarnsleyFern extends Fractal {

    private double x = 0, y = 0;

    public BarnsleyFern(int width, int height) {
        super(width, height);
        this.maxIterations = 1000000;
        generateFractal();
    }

    public BarnsleyFern(int width, int height, int maxIterations) {
        super(width, height);
        this.maxIterations = maxIterations;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        for (int i = 0; i < maxIterations; i++) {
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

            int plotX = (int) (width / 2 + x * width / 10);
            int plotY = (int) (height - y * height / 12);
            image.setRGB(plotX, plotY, Color.GREEN.getRGB());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}