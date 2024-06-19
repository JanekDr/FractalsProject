package fractals;

import java.awt.*;

/**
 * Klasa reprezentująca fraktal zestawu Julii.
 */
public class JuliaSet extends Fractal {
    private double cRe, cIm;

    /**
     * Konstruktor zestawu Julii o podanej szerokości i wysokości.
     *
     * @param width  szerokość fraktala
     * @param height wysokość fraktala
     */
    public JuliaSet(int width, int height) {
        super(width, height);
        this.zoom = 250;
        this.maxIterations = 1000;
        this.cRe = -0.4;
        this.cIm = 0.6;
        generateFractal();
    }

    /**
     * Konstruktor zestawu Julii o podanych parametrach.
     *
     * @param width  szerokość fraktala
     * @param height wysokość fraktala
     * @param cRe    rzeczywista część stałej zespolonej
     * @param cIm    urojona część stałej zespolonej
     */
    public JuliaSet(int width, int height, double cRe, double cIm) {
        super(width, height);
        this.maxIterations = 1000;
        this.zoom = 250;
        this.cRe = cRe;
        this.cIm = cIm;
        generateFractal();
    }

    /**
     * Konstruktor zestawu Julii o podanych parametrach.
     *
     * @param width         szerokość fraktala
     * @param height        wysokość fraktala
     * @param cRe           rzeczywista część stałej zespolonej
     * @param cIm           urojona część stałej zespolonej
     * @param maxIterations maksymalna liczba iteracji
     * @param color         schemat kolorów fraktala
     */
    public JuliaSet(int width, int height, double cRe, double cIm, int maxIterations, int color) {
        super(width, height);
        this.zoom = 250;
        this.maxIterations = 1000 * maxIterations;
        this.cRe = cRe;
        this.cIm = cIm;
        this.color = color;
        generateFractal();
    }

    /**
     * Generuje fraktal zestawu Julii.
     */
    @Override
    public void generateFractal() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double zx = (x - width / 2) / zoom + offsetX;
                double zy = (y - height / 2) / zoom + offsetY;
                float i = maxIterations;
                while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy + cRe;
                    zy = 2.0 * zx * zy + cIm;
                    zx = tmp;
                    i--;
                }
                if (i > 0) {
                    double ratio = (double) i / maxIterations;
                    if (color == 1) {
                        int pixelColor = Color.HSBtoRGB(0.7f + 10 * (float) Math.sqrt(ratio), 1f, 1f);
                        image.setRGB(x, y, pixelColor);
                    } else {
                        image.setRGB(x, y, color);
                    }
                } else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
    }

    /**
     * Maluje komponent fraktala.
     *
     * @param g obiekt Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
