import javax.swing.*;


public class GUI extends JFrame {
    private JComboBox<String> Fractal_list;

    private JLabel levels;
    private JTextField levels_text_field;
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

    //private static void ComboAction(ActionEvent choice){
    //  if (choice.getSource()==)
    //}
    public GUI (){
        setTitle("Fraktale");
        setSize(800,500);
        setLayout(null);

        Fractal_list = CreateComboBox(new String[]{"Fraktal1", "Fraktal2", "Fraktal3"},200,0,585,30);
        add(Fractal_list);

        levels = CreateLabel("Poziom",75,75,200,25);
        levels_text_field = CreateTextField(0,100,200,25);
        add(levels);
        add(levels_text_field);

        JLabel FieldLabel2 = CreateLabel("Pole2",75,175,200,25);
        JTextField textField2 = CreateTextField(0,200,200,25);
        add(FieldLabel2);
        add(textField2);

        JLabel FieldLabel3 = CreateLabel("Pole3",75,275,200,25);
        JTextField textField3 = CreateTextField(0,300,200,25);
        add(FieldLabel3);
        add(textField3);




        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }

    public static void main(String[] args) {
        new GUI();
    }
}