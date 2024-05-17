package fractals;

import java.awt.*;
import java.awt.event.*;

public class MandelbrotSet extends Fractal implements MouseListener, MouseMotionListener, MouseWheelListener {
    private int mouseX, mouseY;
    private boolean dragging = false;
    private int dragStartX, dragStartY;

    public MandelbrotSet(int width, int height) {
        super(width, height);
        this.maxIterations = 1000;
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        generateFractal();
    }

    @Override
    protected void generateFractal() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double cX = (x - width / 2) / zoom + offsetX;
                double cY = (y - height / 2) / zoom + offsetY;
                int iter = calculateEscapeTime(cX, cY);
                if (iter > 0) {
                    double ratio = (double) iter / maxIterations;
                    int color = Color.HSBtoRGB(0.7f + 10 * (float) Math.sqrt(ratio), 1f, 1f);
                    image.setRGB(x, y, color);
                } else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
    }

    private int calculateEscapeTime(double cX, double cY) {
        double zx = 0;
        double zy = 0;
        int iter = maxIterations;
        while (zx * zx + zy * zy < 4 && iter > 0) {
            double tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter--;
        }
        return iter;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        if (dragging) {
            g.setColor(Color.WHITE);
            g.drawRect(Math.min(mouseX, dragStartX), Math.min(mouseY, dragStartY),
                    Math.abs(mouseX - dragStartX), Math.abs(mouseY - dragStartY));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragging = true;
        dragStartX = mouseX = e.getX();
        dragStartY = mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        double minX = Math.min(dragStartX, mouseX);
        double minY = Math.min(dragStartY, mouseY);
        double maxX = Math.max(dragStartX, mouseX);
        double maxY = Math.max(dragStartY, mouseY);
        double centerX = (minX + maxX) / 2;
        double centerY = (minY + maxY) / 2;
        offsetX += centerX / zoom - width / 2.0 / zoom;
        offsetY += centerY / zoom - height / 2.0 / zoom;
        generateFractal();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            zoom *= 1.1;
        } else {
            zoom /= 1.1;
        }
        generateFractal();
        repaint();
    }
}
