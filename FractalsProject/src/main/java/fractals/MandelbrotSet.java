package fractals;

import java.awt.*;

public class MandelbrotSet extends Fractal{
    public MandelbrotSet(int width, int height) {
        super(width, height);
        this.maxIterations = 1000;
        this.zoom=250;
        generateFractal();
    }

    public MandelbrotSet(int width, int height, int maxIterations) {
        super(width, height);
        this.maxIterations = maxIterations;
        this.zoom=250;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double cX = (x - width / 2) / zoom + offsetX;
                double cY = (y - height / 2) / zoom + offsetY;
                int iter = calculateEscapeTime(cX, cY);
                if (iter > 0) {
                    double ratio = (double) iter / maxIterations;
                    int color = Color.HSBtoRGB(0.7f + 10 * (float) Math.sqrt(ratio), 1f, 1f);
//                    int color = Color.RED.getRGB();
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
    }
}
