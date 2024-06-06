package fractals;

import java.awt.*;
import java.awt.geom.Line2D;

public class DragonCurve extends Fractal {
    public DragonCurve(int width, int height) {
        super(width, height);
        this.maxIterations = 10;
        generateFractal();
    }

    public DragonCurve(int width, int height, int maxIterations) {
        super(width, height);
        this.maxIterations = maxIterations;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double startX = 115;
        double startY = 200;
        double endX = 3*startX;
        double endY = 200;
        drawDragonCurve(g2d, startX, startY, endX, endY, maxIterations);
    }

    private void drawDragonCurve(Graphics2D g2d, double x1, double y1, double x2, double y2, int iterations) {
        if (iterations == 0) {
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
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
        if (mouseHandler.isDragging()) {
            g.setColor(Color.WHITE);
            g.drawRect(Math.min(mouseHandler.getMouseX(), mouseHandler.getDragStartX()),
                    Math.min(mouseHandler.getMouseY(), mouseHandler.getDragStartY()),
                    Math.abs(mouseHandler.getMouseX() - mouseHandler.getDragStartX()),
                    Math.abs(mouseHandler.getMouseY() - mouseHandler.getDragStartY()));
        }
    }
}