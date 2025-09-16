import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FancyCurrencyConverter 
{
    private static final double RATE = 82.5; // 1 Dollar = 82.5 Rupees

    public static void main(String[] args) 
    {
        // Frame
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Rupees -> Dollar Converter", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(123, 44, 191)); // purple theme

        // Input Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 255));
        JLabel lbl = new JLabel("Enter Rupees: ");
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField input = new JTextField(10);
        input.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton convertBtn = new JButton("~Convert");
        convertBtn.setBackground(new Color(46, 134, 222)); // Blue button
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFont(new Font("Arial", Font.BOLD, 15));
        convertBtn.setFocusPainted(false);
        convertBtn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        panel.add(lbl);
        panel.add(input);
        panel.add(convertBtn);

        // Result Panel (fancy output area)
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(250, 250, 250));
        resultPanel.setBorder(BorderFactory.createTitledBorder("💵 Conversion Result"));
        JLabel result = new JLabel("Waiting for input...", JLabel.CENTER);
        result.setFont(new Font("Segoe UI", Font.BOLD, 16));
        result.setForeground(new Color(64, 64, 64));
        resultPanel.add(result);

        // Add to frame
        frame.add(title, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(resultPanel, BorderLayout.SOUTH);

        // Action
        convertBtn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    double rupees = Double.parseDouble(input.getText());
                    double dollars = rupees / RATE;

                    result.setText(rupees + " INR = " 
                                   + String.format("%.2f", dollars) + " USD");
                    result.setForeground(new Color(34, 139, 34)); // Green output
                } 
                catch (Exception ex) {
                    result.setText("⚠️ Invalid Input! Please enter numbers only.");
                    result.setForeground(Color.RED);
                }
                input.setText("");
            }
        });

        // Show window
        frame.setVisible(true);
    }
}
