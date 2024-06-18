package fractals;

import java.awt.*;


public class JuliaSet extends Fractal {
    private double cRe, cIm; // Constants for the Julia set

    public JuliaSet(int width, int height) {
        super(width, height);
        this.zoom=250;
        this.maxIterations = 1000;
        this.cRe = -0.4;
        this.cIm = 0.6;
        generateFractal();
    }

    public JuliaSet(int width, int height, double cRe, double cIm) {
        super(width, height);
        this.maxIterations = 1000;
        this.zoom=250;
        this.cRe = cRe;
        this.cIm = cIm;
        generateFractal();
    }
    public JuliaSet(int width, int height, double cRe, double cIm, int maxIterations) {
        super(width, height);
        this.zoom=250;
        this.maxIterations = 1000*maxIterations;
        this.cRe = cRe;
        this.cIm = cIm;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double zx = (x - width / 2) / zoom  + offsetX;
                double zy = (y - height / 2) / zoom + offsetY;
                float i = maxIterations;
                while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy + cRe;
                    zy = 2.0 * zx * zy + cIm;
                    zx = tmp;
                    i--;
                }
                if(i>0){
                    double ratio = (double) i / maxIterations;
                    int color = Color.HSBtoRGB(0.7f + 10 * (float) Math.sqrt(ratio), 1f, 1f);
//                    int color = Color.HSBtoRGB(1f,1f,1f);
                    image.setRGB(x, y, color);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
