package fractals;

import java.awt.*;
import java.awt.event.*;

public class JuliaSet extends Fractal{

    public JuliaSet(int width, int height) {
        super(width, height);
        this.maxIterations = 1000;
        generateFractal();
    }

    @Override
    public void generateFractal() {
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(image, 0, 0, this);
    }
}