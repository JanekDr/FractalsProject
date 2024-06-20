package fractals;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.*;

/**
 * Klasa obsługująca zdarzenia myszy dla fraktali.
 */
public class MouseHandler extends MouseAdapter {
    private Fractal fractal;
    private int dragStartX, dragStartY;
    private int mouseX, mouseY;
    private boolean dragging;

    /**
     * Konstruktor MouseHandler dla podanego fraktala.
     *
     * @param fractal fraktal do obsługi zdarzeń myszy
     */
    public MouseHandler(Fractal fractal) {
        this.fractal = fractal;
    }

    /**
     * Obsługuje zdarzenia kliknięcia myszy.
     *
     * @param e zdarzenie myszy
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        fractal.offsetX += (e.getX() - fractal.width / 2.0) / fractal.zoom;
        fractal.offsetY += (e.getY() - fractal.height / 2.0) / fractal.zoom;
        fractal.generateFractal();
        fractal.repaint();
    }

    /**
     * Obsługuje zdarzenia naciśnięcia przycisku myszy.
     *
     * @param e zdarzenie myszy
     */
    @Override
    public void mousePressed(MouseEvent e) {
        dragStartX = e.getX();
        dragStartY = e.getY();
        dragging = true;
    }

    /**
     * Obsługuje zdarzenia zwolnienia przycisku myszy.
     *
     * @param e zdarzenie myszy
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }

    /**
     * Obsługuje zdarzenia przeciągania myszy.
     *
     * @param e zdarzenie myszy
     */
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

    /**
     * Obsługuje zdarzenia przewijania kółkiem myszy.
     *
     * @param e zdarzenie kółka myszy
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            fractal.zoom *= 1.1;
        } else {
            fractal.zoom /= 1.1;
        }
        fractal.generateFractal();
        fractal.repaint();
        ((GUI)SwingUtilities.getWindowAncestor(fractal)).updateZoomLabel();
    }

    /**
     * Pobiera współrzędną x, w której rozpoczęto przeciąganie.
     *
     * @return współrzędna x
     */
    public int getDragStartX() {
        return dragStartX;
    }

    /**
     * Pobiera współrzędną y, w której rozpoczęto przeciąganie.
     *
     * @return współrzędna y
     */
    public int getDragStartY() {
        return dragStartY;
    }

    /**
     * Pobiera bieżącą współrzędną x myszy.
     *
     * @return współrzędna x
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Pobiera bieżącą współrzędną y myszy.
     *
     * @return współrzędna y
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * Sprawdza, czy mysz jest w trakcie przeciągania.
     *
     * @return true jeśli przeciąganie, false w przeciwnym razie
     */
    public boolean isDragging() {
        return dragging;
    }
}
