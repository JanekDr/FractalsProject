package fractals;

import java.awt.*;
import java.awt.geom.Line2D;

public class DragonCurve extends Fractal {
    public DragonCurve(int width, int height) {
        super(width, height);
        this.zoom = 1.2;
        this.maxIterations = 10;
        generateFractal();
    }

    public DragonCurve(int width, int height, int maxIterations, int color) {
        super(width, height);
        this.zoom = 1.0;
        this.maxIterations = maxIterations;
        this.color = color;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(color==1)color=-1;
        g2d.setColor(new Color(color));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        double startX = width / 3.0;
        double startY = height / 3.0;
        double endX = startX + 300;
        double endY = startY;

        drawDragonCurve(g2d, startX, startY, endX, endY, maxIterations);
    }

    private void drawDragonCurve(Graphics2D g2d, double x1, double y1, double x2, double y2, int iterations) {
        if (iterations == 0) {
            double scaledX1 = (x1 - offsetX) * zoom;
            double scaledY1 = (y1 - offsetY) * zoom;
            double scaledX2 = (x2 - offsetX) * zoom;
            double scaledY2 = (y2 - offsetY) * zoom;
            g2d.draw(new Line2D.Double(scaledX1, scaledY1, scaledX2, scaledY2));
        } else {
            double midX = (x1 + x2) / 2 + (y1 - y2) / 2;
            double midY = (y1 + y2) / 2 + (x2 - x1) / 2;

            drawDragonCurve(g2d, x1, y1, midX, midY, iterations - 1);
            drawDragonCurve(g2d, x2, y2, midX, midY, iterations - 1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
