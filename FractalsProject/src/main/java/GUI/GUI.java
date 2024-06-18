package GUI;

import fractals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame {
    private JComboBox<String> Fractal_list;
    private JComboBox<String> Color_list;
    private JComboBox<String> JuliaConstant_list;

    private JLabel levels;
    private JTextField levels_text_field;
    private JLabel text_Color;
    private JLabel JuliaConstant_label;
    private JCheckBox AutoScroll_CheckBox;
    private ImageIcon xIcon;
    private ImageIcon checkIcon;
    private JButton Submit_button;
    private JButton Scroll_Submit_Button;
    private Fractal fractalPanel;

    private static JComboBox<String> CreateComboBox(String[] options,int x, int y, int width, int height){
        JComboBox<String> list = new JComboBox<>(options);
        list.setBounds(x,y,width,height);
        return list;
    }

    private static JLabel CreateLabel(String text,int x, int y, int width, int height){
        JLabel Label = new JLabel(text);
        Label.setBounds(x,y,width,height);
        return Label;
    }

    private static JTextField CreateTextField(int x, int y, int width, int height){
        JTextField TextField = new JTextField();
        TextField.setBounds(x,y,width,height);
        return TextField;
    }
    private static JButton CreateButton(String text, int x, int y, int width, int height, Color ColBack, Color ColFore) {
        JButton Button = new JButton(text);
        Button.setBounds(x,y,width,height);
        Button.setBackground(ColBack);
        Button.setForeground(ColFore);
        Button.setFocusable(false);
        return Button;
    }


    public GUI (){
        setTitle("Fraktale");
        setSize(1200,800);
        setLayout(null);

        Fractal_list = CreateComboBox(new String[]{"MandelbrotSet", "BarnsleyFern", "DragonCurve", "JuliaSet", "KochSnowflake", "LevyCurve", "SierpinskiTriangle"},
                20,30,200,30);
        add(Fractal_list);

        levels = CreateLabel("Poziom",95,75,200,25);
        levels_text_field = CreateTextField(20,100,200,25);
        add(levels);
        add(levels_text_field);

        text_Color = CreateLabel("Kolor",95,175,200,25);
        Color_list = CreateComboBox(new String[]{"Gradient", "Bialy", "Czerwony", "Niebieski", "Zielony"},
                20,200,200,25);
        add(text_Color);
        add(Color_list);



        AutoScroll_CheckBox = new JCheckBox();
        checkIcon = new ImageIcon("YES.png");
        xIcon = new ImageIcon("images.png");
        AutoScroll_CheckBox.setText("Auto przyblizanie");
        AutoScroll_CheckBox.setFocusable(false);
        //AutoScroll_CheckBox.setIcon(xIcon);
        //AutoScroll_CheckBox.setSelectedIcon(checkIcon);
        AutoScroll_CheckBox.setBounds(60,275,100,25);
        add(AutoScroll_CheckBox);

        JuliaConstant_label = CreateLabel("<html>Stale liczby zespolone<br> dla zbioru Julii (beda dostepne<br>po wyborze tego fraktalu)</html>"
                , 20, 450, 200, 50);
        JuliaConstant_list = CreateComboBox(new String[]{
                "c = -0.7 + 0.27015i",
                "c = 0.355 + 0.355i",
                "c = -0.4 + 0.6i",
                "c = -0.73 + 0.19i"
        }, 20, 500, 200, 25);
        add(JuliaConstant_label);
        add(JuliaConstant_list);
        JuliaConstant_list.setEnabled(false);


        Submit_button = CreateButton("Narysuj",20,670,200,60,new Color(150,50,50), new Color(255,255,255));
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


    private void updateFractal() {
        int maxIterations;

        String fractalName = Fractal_list.getSelectedItem().toString();
        getContentPane().remove(fractalPanel);
        String colorName = Color_list.getSelectedItem().toString();
        System.out.println(colorName);

        try{
            maxIterations = Integer.parseInt(levels_text_field.getText());
            System.out.println(maxIterations);
        } catch (Exception noValue){
            maxIterations = 0;
            System.out.println(maxIterations);
        }
        System.out.println(fractalName);

        JuliaConstant_list.setEnabled(false);
        switch (fractalName) {
            case "MandelbrotSet":
                if(maxIterations!=0){
                    fractalPanel = new MandelbrotSet(900, 750, maxIterations);
                }else {
                    fractalPanel = new MandelbrotSet(900, 750);
                }
                break;
            case "BarnsleyFern":
                if(maxIterations!=0){
                    fractalPanel = new BarnsleyFern(900, 750, maxIterations);
                }else {
                    fractalPanel = new BarnsleyFern(900, 750);
                }
                break;
            case "DragonCurve":
                if(maxIterations!=0){
                    fractalPanel = new DragonCurve(900, 750, maxIterations);
                }else {
                    fractalPanel = new DragonCurve(900, 750);
                }
                break;
            case "JuliaSet":
                JuliaConstant_list.setEnabled(true);

                String selectedConstant = (String) JuliaConstant_list.getSelectedItem();
                double cRe=-0.7;;
                double cIm=0.27015;
                switch (selectedConstant) {
                    case "c = -0.7 + 0.27015i":
                        cRe = -0.7;
                        cIm = 0.27015;
                        break;
                    case "c = 0.355 + 0.355i":
                        cRe = 0.355;
                        cIm = 0.355;
                        break;
                    case "c = -0.4 + 0.6i":
                        cRe = -0.4;
                        cIm = 0.6;
                        break;
                    case "c = -0.73 + 0.19i":
                        cRe = -0.73;
                        cIm = 0.19;
                        break;
                }
                System.out.println(cRe+" "+ cIm);
                if(maxIterations!=0){
                    fractalPanel = new JuliaSet(900, 750, cRe, cIm, maxIterations);
                }else {
                    fractalPanel = new JuliaSet(900, 750, cRe, cIm);
                }

                break;
            case "KochSnowflake":
                if(maxIterations!=0){
                    fractalPanel = new KochSnowflake(900, 750, maxIterations);
                }else {
                    fractalPanel = new KochSnowflake(900, 750);
                }
                break;
            case "LevyCcurve":
                if(maxIterations!=0){
                    fractalPanel = new LevyCcurve(900, 750, maxIterations);
                }else {
                    fractalPanel = new LevyCcurve(900, 750);
                }
                break;
            case "SierpinskiTriangle":
                if(maxIterations!=0){
                    fractalPanel = new SierpinskiTriangle(900, 750, maxIterations);
                }else {
                    fractalPanel = new SierpinskiTriangle(900, 750);
                }
                break;
        }
        levels_text_field.setText("");
        fractalPanel.setBounds(250, 30, 900, 700);
        add(fractalPanel);
        repaint();
    }
    private int selectedColor(){
        String selectedColor = (String) Color_list.getSelectedItem();
        int color=0;
        switch (selectedColor){
            case "Gradient":
                color=1; //symbol dla gradientu
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
    public static void main(String[] args) {
        new GUI();
    }
}