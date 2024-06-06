package GUI;

import fractals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame {
    private JComboBox<String> Fractal_list;
    private JComboBox<String> Color_list;

    private JLabel levels;
    private JTextField levels_text_field;
    private JLabel text_Color;
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
        setSize(800,500);
        setLayout(null);

        Fractal_list = CreateComboBox(new String[]{"MandelbrotSet", "BarnsleyFern", "DragonCurve", "JuliaSet", "KochSnowflake", "LevyCurve", "SierpinskiTriangle"},200,0,585,30);
        add(Fractal_list);

        levels = CreateLabel("Poziom",75,75,200,25);
        levels_text_field = CreateTextField(0,100,200,25);
        add(levels);
        add(levels_text_field);

        text_Color = CreateLabel("Kolor",75,175,200,25);
        Color_list = CreateComboBox(new String[]{"Gradient", "Bialy", "Czerwony", "Niebieski", "Zielony"},0,200,200,25);
        add(text_Color);
        add(Color_list);



        AutoScroll_CheckBox = new JCheckBox();
        checkIcon = new ImageIcon("YES.png");
        xIcon = new ImageIcon("images.png");
        AutoScroll_CheckBox.setText("Auto przyblizanie");
        AutoScroll_CheckBox.setFocusable(false);
        //AutoScroll_CheckBox.setIcon(xIcon);
        //AutoScroll_CheckBox.setSelectedIcon(checkIcon);
        AutoScroll_CheckBox.setBounds(40,275,200,25);
        add(AutoScroll_CheckBox);


        Submit_button = CreateButton("Narysuj",-2,400,200,62,new Color(150,50,50), new Color(255,255,255));
        add(Submit_button);

        fractalPanel = new BarnsleyFern(400, 400);
        fractalPanel.setBounds(300, 50, 500, 400);
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
        String fractalName = Fractal_list.getSelectedItem().toString();
        getContentPane().remove(fractalPanel);
        String colorName = Color_list.getSelectedItem().toString();
//        if(levels_text_field.isValidateRoot()) {
//            int maxIterations = Integer.parseInt(levels_text_field.getText());
//            System.out.println(maxIterations);
//        }
        System.out.println(colorName);


        switch (fractalName) {
            case "MandelbrotSet":
                System.out.println(fractalName);
                fractalPanel = new MandelbrotSet(400, 400);
                break;
            case "BarnsleyFern":
                System.out.println(fractalName);
                fractalPanel = new BarnsleyFern(400, 400);
                break;
            case "DragonCurve":
                System.out.println(fractalName);
                fractalPanel = new DragonCurve(400, 400);
                break;
            case "JuliaSet":
                System.out.println(fractalName);
                fractalPanel = new JuliaSet(400, 400);
                break;
            case "KochSnowflake":
                System.out.println(fractalName);
                fractalPanel = new KochSnowflake(400, 400);
                break;
            case "LevyCcurve":
                System.out.println(fractalName);
                fractalPanel = new LevyCcurve(400, 400);
                break;
            case "SierpinskiTriangle":
                System.out.println(fractalName);
                fractalPanel = new SierpniskiTriangle(400, 400);
                break;
        }
        fractalPanel.setBounds(300, 50, 500, 400);
        add(fractalPanel);
        repaint();
    }
    public static void main(String[] args) {
        new GUI();
    }
}