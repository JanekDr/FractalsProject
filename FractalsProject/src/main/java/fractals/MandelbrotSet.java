package fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final int MAX_ITER = 1000;
    private double ZOOM = 150;
    private double offsetX = 0;
    private double offsetY = 0;
    private BufferedImage image;
    private int mouseX, mouseY;
    private boolean dragging = false;

    public MandelbrotSet() {
        int width = 800;
        int height = 600;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        calculateMandelbrot();
    }

    private void calculateMandelbrot() {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                double zx = 0;
                double zy = 0;
                double cX = (x - 400) / ZOOM + offsetX;
                double cY = (y - 300) / ZOOM + offsetY;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                if (iter > 0) {
                    double ratio = (double) iter / MAX_ITER;
                    int color = Color.HSBtoRGB(0.7f + 10 * (float) Math.sqrt(ratio), 1f, 1f);
                    image.setRGB(x, y, color);
                } else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        if (dragging) {
            g.setColor(Color.WHITE);
            g.drawRect(Math.min(mouseX, dragStartX), Math.min(mouseY, dragStartY),
                    Math.abs(mouseX - dragStartX), Math.abs(mouseY - dragStartY));
        }
    }

    private int dragStartX, dragStartY;

    @Override
    public void mousePressed(MouseEvent e) {
        dragging = true;
        dragStartX = mouseX = e.getX();
        dragStartY = mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        double minX = Math.min(dragStartX, mouseX);
        double minY = Math.min(dragStartY, mouseY);
        double maxX = Math.max(dragStartX, mouseX);
        double maxY = Math.max(dragStartY, mouseY);
        double centerX = (minX + maxX) / 2;
        double centerY = (minY + maxY) / 2;
        double sizeX = maxX - minX;
        double sizeY = maxY - minY;
        offsetX += centerX / ZOOM - 400 / ZOOM;
        offsetY += centerY / ZOOM - 300 / ZOOM;
        ZOOM *= Math.max(sizeX / 800, sizeY / 600);
        calculateMandelbrot();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            // Zoom in
            ZOOM *= 1.1;
        } else {
            // Zoom out
            ZOOM /= 1.1;
        }
        calculateMandelbrot();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mandelbrot Set");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new MandelbrotSet());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
