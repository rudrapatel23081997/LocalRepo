import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StylishCalculator extends JFrame implements ActionListener 
{
    JTextField textField;
    String operator = "";
    double num1, num2, result;

    StylishCalculator() 
    {
        setTitle("Stylish Calculator");
        setSize(400, 550);  // 🔹 Bigger frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));  // 🔹 More spacing

        // 🔹 Display
        textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.BOLD, 34)); // 🔹 Bigger font
        textField.setBackground(new Color(20, 20, 20));
        textField.setForeground(Color.CYAN);   // 🔹 Neon text
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // padding
        add(textField, BorderLayout.NORTH);

        // 🔹 Button Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 12, 12)); // 🔹 More gaps between buttons
        panel.setBackground(new Color(30, 30, 30));

        String[] buttons = {
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "0",".","=","+"
        };

        for (String text : buttons) 
        {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 24)); // 🔹 Bigger button font
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true)); // 🔹 Rounded effect

            // 🔹 Button colors
            if (text.matches("[0-9.]")) 
            {
                btn.setBackground(new Color(240, 240, 240));
                btn.setForeground(Color.BLACK);
            } 
            else if (text.equals("=")) 
            {
                btn.setBackground(new Color(0, 180, 90));
                btn.setForeground(Color.WHITE);
            } 
            else 
            {
                btn.setBackground(new Color(255, 100, 50));
                btn.setForeground(Color.WHITE);
            }

            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        // 🔹 Frame background
        getContentPane().setBackground(new Color(25, 25, 25));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9.]")) 
        {
            textField.setText(textField.getText() + cmd);
        } 
        else if (cmd.matches("[+\\-*/]")) 
        {
            num1 = Double.parseDouble(textField.getText());
            operator = cmd;
            textField.setText("");
        } 
        else if (cmd.equals("=")) 
        {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) 
            {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 != 0) 
                        result = num1 / num2; 
                    else 
                    {
                        JOptionPane.showMessageDialog(this, "Error: Division by zero");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new StylishCalculator();
    }
}
