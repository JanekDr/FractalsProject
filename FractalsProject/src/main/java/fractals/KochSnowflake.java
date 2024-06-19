package fractals;

import java.awt.*;

public class KochSnowflake extends Fractal {
    public KochSnowflake(int width, int height) {
        super(width, height);
        this.maxIterations = 5;
        this.zoom = 0.8;
        generateFractal();
    }

    public KochSnowflake(int width, int height, int maxIterations, int color) {
        super(width, height);
        this.maxIterations = maxIterations;
        this.zoom = 0.8;
        this.color = color;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        Graphics2D g2d = image.createGraphics();
        if(color==1)color=-1;
        g2d.setColor(new Color(color));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);

        double size = Math.min(width, height) * zoom;
        double x1 = width / 2 - size / 2 - offsetX;
        double y1 = height / 2 + size / (2 * Math.sqrt(3)) - offsetY;
        double x2 = width / 2 + size / 2 - offsetX;
        double y2 = y1;
        double x3 = width / 2 - offsetX;
        double y3 = height / 2 - size / Math.sqrt(3) - offsetY;

        drawKochSnowflake(g2d, maxIterations, x1, y1, x2, y2);
        drawKochSnowflake(g2d, maxIterations, x2, y2, x3, y3);
        drawKochSnowflake(g2d, maxIterations, x3, y3, x1, y1);

        g2d.dispose();
    }

    private void drawKochSnowflake(Graphics2D g2d, int iterations, double x1, double y1, double x2, double y2) {
        if (iterations == 0) {
            g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        } else {
            double deltaX = x2 - x1;
            double deltaY = y2 - y1;

            double xA = x1 + deltaX / 3;
            double yA = y1 + deltaY / 3;

            double xB = x1 + 2 * deltaX / 3;
            double yB = y1 + 2 * deltaY / 3;

            double xC = (x1 + x2) / 2 - Math.sqrt(3) * (y1 - y2) / 6;
            double yC = (y1 + y2) / 2 + Math.sqrt(3) * (x1 - x2) / 6;

            drawKochSnowflake(g2d, iterations - 1, x1, y1, xA, yA);
            drawKochSnowflake(g2d, iterations - 1, xA, yA, xC, yC);
            drawKochSnowflake(g2d, iterations - 1, xC, yC, xB, yB);
            drawKochSnowflake(g2d, iterations - 1, xB, yB, x2, y2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

