package Gui;

import javax.swing.*;
import java.awt.*;


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

        Fractal_list = CreateComboBox(new String[]{"Mandelbrot", "Drzewo", "Fraktal3"},200,0,585,30);
        add(Fractal_list);

        levels = CreateLabel("Poziom",75,75,200,25);
        levels_text_field = CreateTextField(0,100,200,25);
        add(levels);
        add(levels_text_field);

        text_Color = CreateLabel("Kolor",75,175,200,25);
        Color_list = CreateComboBox(new String[]{"Czarny", "Czerwony", "Niebieski"},0,200,200,25);
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



        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }

    public static void main(String[] args) {
        new GUI();
    }
}