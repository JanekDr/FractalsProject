package fractals;

import java.awt.event.*;

public class MouseHandler extends MouseAdapter {
    private Fractal fractal;
    private int dragStartX, dragStartY;
    private int mouseX, mouseY;
    private boolean dragging;

    public MouseHandler(Fractal fractal) {
        this.fractal = fractal;
    }
    @Override
    public void mouseClicked(MouseEvent e){
        fractal.offsetX += (e.getX() - fractal.width / 2.0) / fractal.zoom;
        fractal.offsetY += (e.getY() - fractal.height / 2.0) / fractal.zoom;
        fractal.generateFractal();
        fractal.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragStartX = e.getX();
        dragStartY = e.getY();
        dragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = dragStartX - e.getX();
        int dy = dragStartY - e.getY();
        fractal.offsetX += dx / fractal.zoom;
        fractal.offsetY += dy / fractal.zoom;
        dragStartX = e.getX();
        dragStartY = e.getY();
        fractal.generateFractal();
        fractal.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            fractal.zoom *= 1.1;
        } else {
            fractal.zoom /= 1.1;
        }
        fractal.generateFractal();
        fractal.repaint();
    }

    public int getDragStartX() {
        return dragStartX;
    }

    public int getDragStartY() {
        return dragStartY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isDragging() {
        return dragging;
    }
}
