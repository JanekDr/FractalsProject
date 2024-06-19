package fractals;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Klasa reprezentująca fraktal trójkąta Sierpińskiego.
 */
public class SierpinskiTriangle extends Fractal {

    /**
     * Konstruktor trójkąta Sierpińskiego o podanej szerokości i wysokości.
     *
     * @param width  szerokość fraktala
     * @param height wysokość fraktala
     */
    public SierpinskiTriangle(int width, int height) {
        super(width, height);
        this.maxIterations = 7;
        this.zoom = 0.9;
        generateFractal();
    }

    /**
     * Konstruktor trójkąta Sierpińskiego o podanych parametrach.
     *
     * @param width         szerokość fraktala
     * @param height        wysokość fraktala
     * @param maxIterations maksymalna liczba iteracji
     * @param color         schemat kolorów fraktala
     */
    public SierpinskiTriangle(int width, int height, int maxIterations, int color) {
        super(width, height);
        this.maxIterations = maxIterations;
        this.zoom = 0.9;
        this.color = color;
        generateFractal();
    }

    /**
     * Generuje fraktal trójkąta Sierpińskiego.
     */
    @Override
    public void generateFractal() {
        Graphics2D g2d = image.createGraphics();
        if (color == 1) color = -1;
        g2d.setColor(new Color(color));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);

        double size = Math.min(width, height) * zoom;

        Point2D.Double p1 = new Point2D.Double(width / 2.0, (height - size * Math.sqrt(3) / 2) / 2.0);
        Point2D.Double p2 = new Point2D.Double((width - size) / 2.0, (height + size * Math.sqrt(3) / 2) / 2.0);
        Point2D.Double p3 = new Point2D.Double((width + size) / 2.0, (height + size * Math.sqrt(3) / 2) / 2.0);

        p1.x -= offsetX;
        p1.y -= offsetY;
        p2.x -= offsetX;
        p2.y -= offsetY;
        p3.x -= offsetX;
        p3.y -= offsetY;

        generateSierpinskiTriangle(g2d, maxIterations, p1, p2, p3);
    }

    /**
     * Rysuje trójkąt Sierpińskiego.
     *
     * @param g     obiekt Graphics2D
     * @param depth głębokość rekurencji
     * @param p1    pierwszy wierzchołek trójkąta
     * @param p2    drugi wierzchołek trójkąta
     * @param p3    trzeci wierzchołek trójkąta
     */
    private void generateSierpinskiTriangle(Graphics2D g, int depth, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        if (depth == 0) {
            Polygon triangle = new Polygon();
            triangle.addPoint((int) p1.x, (int) p1.y);
            triangle.addPoint((int) p2.x, (int) p2.y);
            triangle.addPoint((int) p3.x, (int) p3.y);
            g.fillPolygon(triangle);
        } else {
            Point2D.Double mid1 = midpoint(p1, p2);
            Point2D.Double mid2 = midpoint(p2, p3);
            Point2D.Double mid3 = midpoint(p3, p1);

            generateSierpinskiTriangle(g, depth - 1, p1, mid1, mid3);
            generateSierpinskiTriangle(g, depth - 1, mid1, p2, mid2);
            generateSierpinskiTriangle(g, depth - 1, mid3, mid2, p3);
        }
    }

    /**
     * Oblicza środek dwóch punktów.
     *
     * @param p1 pierwszy punkt
     * @param p2 drugi punkt
     * @return środek dwóch punktów
     */
    private Point2D.Double midpoint(Point2D.Double p1, Point2D.Double p2) {
        return new Point2D.Double((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    /**
     * Maluje komponent fraktala.
     *
     * @param g obiekt Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
