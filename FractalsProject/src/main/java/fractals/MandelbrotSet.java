package fractals;

import java.awt.*;

/**
 * Klasa reprezentująca fraktal zestawu Mandelbrota.
 */
public class MandelbrotSet extends Fractal {

    /**
     * Konstruktor zestawu Mandelbrota o podanej szerokości i wysokości.
     *
     * @param width  szerokość fraktala
     * @param height wysokość fraktala
     */
    public MandelbrotSet(int width, int height) {
        super(width, height);
        this.maxIterations = 1000;
        this.zoom = 250;
        generateFractal();
    }

    /**
     * Konstruktor zestawu Mandelbrota o podanych parametrach.
     *
     * @param width         szerokość fraktala
     * @param height        wysokość fraktala
     * @param maxIterations maksymalna liczba iteracji
     * @param color         schemat kolorów fraktala
     */
    public MandelbrotSet(int width, int height, int maxIterations, int color) {
        super(width, height);
        this.maxIterations = 1000 * maxIterations;
        this.zoom = 250;
        this.color = color;
        generateFractal();
    }

    /**
     * Generuje fraktal zestawu Mandelbrota.
     */
    @Override
    public void generateFractal() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double cX = (x - width / 2) / zoom + offsetX;
                double cY = (y - height / 2) / zoom + offsetY;
                int iter = calculateEscapeTime(cX, cY);
                if (iter > 0) {
                    double ratio = (double) iter / maxIterations;
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
     * Oblicza czas ucieczki dla danego punktu na płaszczyźnie zespolonej.
     *
     * @param cX rzeczywista część liczby zespolonej
     * @param cY urojona część liczby zespolonej
     * @return czas ucieczki
     */
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
