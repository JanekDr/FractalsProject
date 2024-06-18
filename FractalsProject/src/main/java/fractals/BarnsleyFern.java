package fractals;

import java.awt.*;
import java.util.Random;

public class BarnsleyFern extends Fractal {

    private static final double[][] transformations = {
            {0.0, 0.0, 0.0, 0.16, 0.0, 0.0},
            {0.85, 0.04, -0.04, 0.85, 0.0, 1.6},
            {0.2, -0.26, 0.23, 0.22, 0.0, 1.6},
            {-0.15, 0.28, 0.26, 0.24, 0.0, 0.44}
    };
    private static final double[] probabilities = {0.01, 0.85, 0.07, 0.07};
    private Random random;

    public BarnsleyFern(int width, int height) {
        super(width, height);
        this.random = new Random();
        this.zoom = 50;
        this.maxIterations = 1;
        generateFractal();
    }

    public BarnsleyFern(int width, int height, int maxIterations) {
        super(width, height);
        this.random = new Random();
        this.zoom = 50;
        this.maxIterations = maxIterations;
        generateFractal();
    }

    @Override
    public void generateFractal() {
        // Clear the image before generating the fractal
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        double x = 0, y = 0;

        for (int i = 0; i < 10000*maxIterations; i++) {
            double randomValue = random.nextDouble();
            double cumulativeProbability = 0.0;
            int transformationIndex = 0;
            for (int j = 0; j < probabilities.length; j++) {
                cumulativeProbability += probabilities[j];
                if (randomValue < cumulativeProbability) {
                    transformationIndex = j;
                    break;
                }
            }

            double newX = transformations[transformationIndex][0] * x + transformations[transformationIndex][1] * y + transformations[transformationIndex][4];
            double newY = transformations[transformationIndex][2] * x + transformations[transformationIndex][3] * y + transformations[transformationIndex][5];
            x = newX;
            y = newY;

            // Transform to screen coordinates
            int pixelX = (int) ((x - offsetX) * zoom + width / 2);
            int pixelY = (int) ((height / 2) - (y + offsetY) * zoom)+250;

            if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
                image.setRGB(pixelX, pixelY, Color.GREEN.getRGB());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
