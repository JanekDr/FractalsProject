package GUI;

import fractals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa GUI odpowiedzialna za interfejs graficzny aplikacji fraktali.
 */
public class GUI extends JFrame {
    private JComboBox<String> Fractal_list;
    private JComboBox<String> Color_list;
    private JComboBox<String> JuliaConstant_list;
    private JLabel levels;
    private JTextField levels_text_field;
    private JLabel text_Color;
    private JLabel JuliaConstant_label;
    private JLabel fractalListLabel;
    private JCheckBox AutoScroll_CheckBox;
    private JButton Submit_button;
    private Fractal fractalPanel;

    /**
     * Tworzy JComboBox z podanymi opcjami i ustawia jego pozycję i rozmiar.
     *
     * @param options lista opcji do wyświetlenia
     * @param x       pozycja x
     * @param y       pozycja y
     * @param width   szerokość
     * @param height  wysokość
     * @return JComboBox z ustawionymi parametrami
     */
    private static JComboBox<String> CreateComboBox(String[] options, int x, int y, int width, int height) {
        JComboBox<String> list = new JComboBox<>(options);
        list.setBounds(x, y, width, height);
        return list;
    }

    /**
     * Tworzy JLabel z podanym tekstem i ustawia jego pozycję i rozmiar.
     *
     * @param text   tekst etykiety
     * @param x      pozycja x
     * @param y      pozycja y
     * @param width  szerokość
     * @param height wysokość
     * @return JLabel z ustawionymi parametrami
     */
    private static JLabel CreateLabel(String text, int x, int y, int width, int height) {
        JLabel Label = new JLabel(text);
        Label.setBounds(x, y, width, height);
        return Label;
    }

    /**
     * Tworzy JTextField i ustawia jego pozycję i rozmiar.
     *
     * @param x      pozycja x
     * @param y      pozycja y
     * @param width  szerokość
     * @param height wysokość
     * @return JTextField z ustawionymi parametrami
     */
    private static JTextField CreateTextField(int x, int y, int width, int height) {
        JTextField TextField = new JTextField();
        TextField.setBounds(x, y, width, height);
        return TextField;
    }

    /**
     * Tworzy JButton z podanym tekstem, kolorem tła i kolorem czcionki oraz ustawia jego pozycję i rozmiar.
     *
     * @param text     tekst przycisku
     * @param x        pozycja x
     * @param y        pozycja y
     * @param width    szerokość
     * @param height   wysokość
     * @param ColBack  kolor tła
     * @param ColFore  kolor czcionki
     * @return JButton z ustawionymi parametrami
     */
    private static JButton CreateButton(String text, int x, int y, int width, int height, Color ColBack, Color ColFore) {
        JButton Button = new JButton(text);
        Button.setBounds(x, y, width, height);
        Button.setBackground(ColBack);
        Button.setForeground(ColFore);
        Button.setFocusable(false);
        return Button;
    }

    /**
     * Konstruktor klasy GUI, inicjalizujący interfejs graficzny.
     */
    public GUI() {
        setTitle("Fraktale");
        setSize(1200, 800);
        setLayout(null);

        fractalListLabel = CreateLabel("Wybor fraktala", 75, 55, 200, 25);
        Fractal_list = CreateComboBox(new String[]{"MandelbrotSet", "BarnsleyFern", "DragonCurve", "JuliaSet", "KochSnowflake", "LevyCcurve", "SierpinskiTriangle"},
                20, 80, 200, 30);
        add(fractalListLabel);
        add(Fractal_list);

        levels = CreateLabel("Poziom", 95, 175, 200, 25);
        levels_text_field = CreateTextField(20, 200, 200, 25);
        add(levels);
        add(levels_text_field);

        text_Color = CreateLabel("Kolor", 95, 295, 200, 25);
        Color_list = CreateComboBox(new String[]{"Domyslny", "Bialy", "Czerwony", "Niebieski", "Zielony"},
                20, 320, 200, 25);
        add(text_Color);
        add(Color_list);

        JuliaConstant_label = CreateLabel("<html>Stale liczby zespolone<br> dla zbioru Julii (beda dostepne<br>po wyborze tego fraktalu)</html>", 20, 400, 200, 50);
        JuliaConstant_list = CreateComboBox(new String[]{
                "c = -0.7 + 0.27015i",
                "c = 0.355 + 0.355i",
                "c = -0.4 + 0.6i",
                "c = -0.73 + 0.19i"
        }, 20, 450, 200, 25);
        add(JuliaConstant_label);
        add(JuliaConstant_list);
        JuliaConstant_list.setEnabled(false);

        Submit_button = CreateButton("Narysuj", 20, 670, 200, 60, new Color(150, 50, 50), new Color(255, 255, 255));
        add(Submit_button);

        fractalPanel = new MandelbrotSet(900, 750);
        fractalPanel.setBounds(250, 30, 900, 700);
        add(fractalPanel);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        Submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFractal();
            }
        });
    }

    /**
     * Aktualizuje wyświetlany fraktal na podstawie wybranych opcji.
     */
    private void updateFractal() {
        int maxIterations;
        String fractalName = Fractal_list.getSelectedItem().toString();
        getContentPane().remove(fractalPanel);
        String colorName = Color_list.getSelectedItem().toString();
        int colorParameter = getSelectedColor(colorName);
        try {
            maxIterations = Integer.parseInt(levels_text_field.getText());
        } catch (Exception noValue) {
            maxIterations = 1;
        }
        System.out.println(colorName);
        System.out.println(fractalName);
        System.out.println(maxIterations);
        System.out.println(colorParameter);
        JuliaConstant_list.setEnabled(false);
        switch (fractalName) {
            case "MandelbrotSet":
                fractalPanel = new MandelbrotSet(900, 750, maxIterations, colorParameter);
                break;
            case "BarnsleyFern":
                fractalPanel = new BarnsleyFern(900, 750, maxIterations, colorParameter);
                break;
            case "DragonCurve":
                fractalPanel = new DragonCurve(900, 750, maxIterations, colorParameter);
                break;
            case "JuliaSet":
                JuliaConstant_list.setEnabled(true);
                double[] constant = getSelectedConstant(String.valueOf(JuliaConstant_list.getSelectedItem()));
                double cRe = constant[0];
                double cIm = constant[1];
                fractalPanel = new JuliaSet(900, 750, cRe, cIm, maxIterations, colorParameter);
                break;
            case "KochSnowflake":
                fractalPanel = new KochSnowflake(900, 750, maxIterations, colorParameter);
                break;
            case "LevyCcurve":
                fractalPanel = new LevyCcurve(900, 750, maxIterations, colorParameter);
                break;
            case "SierpinskiTriangle":
                fractalPanel = new SierpinskiTriangle(900, 750, maxIterations, colorParameter);
                break;
        }
        levels_text_field.setText("");
        fractalPanel.setBounds(250, 30, 900, 700);
        add(fractalPanel);
        repaint();
    }

    /**
     * Pobiera wybrany kolor na podstawie nazwy koloru.
     *
     * @param colorName nazwa koloru
     * @return wartość RGB koloru
     */
    private int getSelectedColor(String colorName) {
        int color = 1;
        switch (colorName) {
            case "Domyslny":
                color = 1;
                break;
            case "Bialy":
                color = Color.WHITE.getRGB();
                break;
            case "Czerwony":
                color = Color.RED.getRGB();
                break;
            case "Niebieski":
                color = Color.BLUE.getRGB();
                break;
            case "Zielony":
                color = Color.GREEN.getRGB();
                break;
        }
        return color;
    }

    /**
     * Pobiera wybraną stałą zespoloną dla fraktala Julii.
     *
     * @param selectedConstant wybrana stała zespolona
     * @return tablica z wartościami rzeczywistą i urojoną stałej zespolonej
     */
    private double[] getSelectedConstant(String selectedConstant) {
        double[] constant = {-0.7, 0.27015};
        double cRe;
        double cIm;
        System.out.println(selectedConstant);
        switch (selectedConstant) {
            case "c = -0.7 + 0.27015i":
                cRe = -0.7;
                cIm = 0.27015;
                constant = new double[]{cRe, cIm};
                break;
            case "c = 0.355 + 0.355i":
                cRe = 0.355;
                cIm = 0.355;
                constant = new double[]{cRe, cIm};
                break;
            case "c = -0.4 + 0.6i":
                cRe = -0.4;
                cIm = 0.6;
                constant = new double[]{cRe, cIm};
                break;
            case "c = -0.73 + 0.19i":
                cRe = -0.73;
                cIm = 0.19;
                constant = new double[]{cRe, cIm};
                break;
        }
        return constant;
    }
}
