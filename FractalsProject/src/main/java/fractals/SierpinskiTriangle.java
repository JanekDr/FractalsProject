package fractals;

import java.awt.*;

public class SierpinskiTriangle extends Fractal{
    public SierpinskiTriangle(int width, int height) {
        super(width, height);
        generateFractal();
    }
    public SierpinskiTriangle(int width, int height, int maxIterations) {
        super(width, height);
        this.maxIterations = maxIterations;
        generateFractal();
    }

    @Override
    public void generateFractal() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
