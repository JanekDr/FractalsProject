package fractals;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevyCcurve extends Fractal {

    public LevyCcurve(int width, int height) {
        super(width, height);
        this.maxIterations = 10;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        drawLevyCCurve(g, width / 2, height / 2, 200, 0, maxIterations);
        g.dispose();
    }

    private void drawLevyCCurve(Graphics2D g, double x, double y, double length, double angle, int iteration) {
        if (iteration == 0) {
            double xEnd = x + length * Math.cos(angle);
            double yEnd = y - length * Math.sin(angle);
            g.drawLine((int)x, (int)y, (int)xEnd, (int)yEnd);
        } else {
            length /= Math.sqrt(2);
            iteration--;
            drawLevyCCurve(g, x, y, length, angle + Math.PI / 4, iteration);
            double xMid = x + length * Math.cos(angle + Math.PI / 4);
            double yMid = y - length * Math.sin(angle + Math.PI / 4);
            drawLevyCCurve(g, xMid, yMid, length, angle - Math.PI / 4, iteration);
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
