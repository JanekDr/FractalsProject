package fractals;

import java.awt.event.*;

public class JuliaSet extends Fractal implements MouseListener, MouseMotionListener, MouseWheelListener {
    private int mouseX, mouseY;
    private boolean dragging = false;
    private int dragStartX, dragStartY;

    public JuliaSet(int width, int height) {
        super(width, height);
        this.maxIterations = 1000;
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        generateFractal();
    }

    @Override
    protected void generateFractal() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}