/*Flow Explanation:
1.Program starts → Random number generate hota hai (1–100).
2.attempts = 0 set hota hai.
3.GUI window open hoti hai (input box, button, label).
4.User number enter karta hai aur "Guess" button click karta hai.
5.attempts++ hota hai, input read hota hai.
6.Agar input number nahi hai → error message.
7.Agar number hai →
Correct → "🎉 Correct" show karo + button disable.
Wrong → "⬆️ Higher" ya "⬇️ Lower" hint.
8.User continue karta hai jab tak correct guess na kare.
9.Correct hone ke baad game stop ho jata hai.*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class FancyGuessApp 
{
    private int number;
    private int attempts;

    public FancyGuessApp() 
    {
        number = new Random().nextInt(100) + 1;
        attempts = 0;

        // Frame
        JFrame frame = new JFrame("🎮 Number Guessing Game");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title Label
        JLabel title = new JLabel("Guess the Number (1 - 100)", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(72, 61, 139)); // Dark purple

        // Input and button panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(230, 230, 250)); // lavender
        JTextField input = new JTextField(10);
        JButton guessBtn = new JButton("🎯 Guess");
        guessBtn.setBackground(new Color(100, 149, 237)); // Cornflower blue
        guessBtn.setForeground(Color.WHITE);
        guessBtn.setFont(new Font("Arial", Font.BOLD, 14));

        inputPanel.add(new JLabel("Enter Number: "));
        inputPanel.add(input);
        inputPanel.add(guessBtn);

        // Result label
        JLabel result = new JLabel("You have 0 attempts", JLabel.CENTER);
        result.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        result.setForeground(new Color(25, 25, 112)); // Midnight Blue

        // Add components to frame
        frame.add(title, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(result, BorderLayout.SOUTH);

        // Action on button click
        guessBtn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                attempts++;
                try {
                    int guess = Integer.parseInt(input.getText());
                    if (guess == number) 
                    {
                        result.setText("🎉 Correct in " + attempts + " tries! And Life is just like this. failure is key to sucess..!!Try Try and Try you will sucess one time!!!");
                        result.setForeground(new Color(0, 128, 0)); // Green
                        guessBtn.setEnabled(false);
                    } 
                    else if (guess < number) 
                    {
                        result.setText("⬆️ Higher! Attempts: " + attempts);
                        result.setForeground(new Color(178, 34, 34)); // Red
                    } 
                    else 
                    {
                        result.setText("⬇️ Lower! Attempts: " + attempts);
                        result.setForeground(new Color(178, 34, 34));
                    }
                } catch (Exception ex) 
                {
                    result.setText("⚠️ Please enter a valid number!");
                    result.setForeground(Color.RED);
                }
                input.setText(""); // clear box
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) 
    {
        new FancyGuessApp();
    }
}
