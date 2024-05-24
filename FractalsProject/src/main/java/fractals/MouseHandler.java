package fractals;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
    private Fractal fractal;
    private int mouseX, mouseY;
    private boolean dragging = false;
    private int dragStartX, dragStartY;

    public MouseHandler(Fractal fractal) {
        this.fractal = fractal;
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
        fractal.offsetX += centerX / fractal.zoom - fractal.width / 2.0 / fractal.zoom;
        fractal.offsetY += centerY / fractal.zoom - fractal.height / 2.0 / fractal.zoom;
        fractal.generateFractal();
        fractal.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        fractal.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            fractal.zoom *= 1.1;
        } else {
            fractal.zoom /= 1.1;
        }
        fractal.generateFractal();
        fractal.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    public boolean isDragging() {
        return dragging;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getDragStartX() {
        return dragStartX;
    }

    public int getDragStartY() {
        return dragStartY;
    }
}
