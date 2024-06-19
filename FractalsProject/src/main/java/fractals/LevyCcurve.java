package fractals;

import java.awt.*;
import java.awt.geom.Line2D;

public class LevyCcurve extends Fractal {

    public LevyCcurve(int width, int height) {
        super(width, height);
        this.maxIterations = 12;
        this.zoom = 0.9;
        generateFractal();
    }

    public LevyCcurve(int width, int height, int maxIterations, int color) {
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

        double x1 = (width / 4.0 - offsetX)*zoom;
        double y1 = (height / 2.0 - offsetY);
        double x2 = (3 * width / 4.0 - offsetX)*zoom;
        double y2 = (height / 2.0 - offsetY);

        drawLevyCurve(g2d, maxIterations, x1, y1, x2, y2);

        g2d.dispose();
    }

    private void drawLevyCurve(Graphics2D g2d, int iterations, double x1, double y1, double x2, double y2) {
        if (iterations == 0) {
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        } else {
            double midX = (x1 + x2) / 2 + (y2 - y1) / 2;
            double midY = (y1 + y2) / 2 - (x2 - x1) / 2;

            drawLevyCurve(g2d, iterations - 1, x1, y1, midX, midY);
            drawLevyCurve(g2d, iterations - 1, midX, midY, x2, y2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}