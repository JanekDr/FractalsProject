package fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Abstrakcyjna klasa reprezentująca fraktal.
 */
public abstract class Fractal extends JPanel {
    protected int width;
    protected int height;
    protected double zoom;
    protected int color;
    protected double offsetX;
    protected double offsetY;
    protected int maxIterations;
    protected BufferedImage image;
    protected MouseHandler mouseHandler;

    /**
     * Konstruktor fraktala o podanej szerokości i wysokości.
     *
     * @param width  szerokość fraktala
     * @param height wysokość fraktala
     */
    public Fractal(int width, int height) {
        this.width = width;
        this.height = height;
        this.zoom = 150;
        this.offsetX = 0;
        this.offsetY = 0;
        this.color = 1;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.mouseHandler = new MouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Abstrakcyjna metoda do generowania fraktala.
     */
    public abstract void generateFractal();
    public double getZoom() {
        return zoom;
    }
}
