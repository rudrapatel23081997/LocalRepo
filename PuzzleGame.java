import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PuzzleGame extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private final int SIZE = 3;
    private JPanel panel;

    public PuzzleGame() {
        setTitle("Sliding Puzzle Game");
        setSize(300, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(SIZE, SIZE, 5, 5));
        panel.setBackground(Color.DARK_GRAY);

        initializePuzzle();
        add(panel, BorderLayout.CENTER);

        JButton resetButton = new JButton("New Game");
        resetButton.addActionListener(e -> initializePuzzle());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializePuzzle() {
        panel.removeAll(); // clear old buttons

        // Use fully-qualified java.util.List to avoid ambiguity with java.awt.List
        java.util.List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 8; i++) numbers.add(i);
        numbers.add(0);

        // Shuffle until it's not already solved
        do {
            Collections.shuffle(numbers);
        } while (isSolved(numbers));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(numbers.get(i) == 0 ? "" : String.valueOf(numbers.get(i)));
            buttons[i].setFont(new Font("Arial", Font.BOLD, 24));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.WHITE);
            panel.add(buttons[i]);
        }

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        int clickedIndex = -1;
        int emptyIndex = -1;

        // find clicked and empty
        for (int i = 0; i < 9; i++) {
            if (buttons[i] == clicked) clickedIndex = i;
            if (buttons[i].getText().equals("")) emptyIndex = i;
        }

        // only swap if adjacent
        if (clickedIndex != -1 && emptyIndex != -1 && isAdjacent(clickedIndex, emptyIndex)) {
            buttons[emptyIndex].setText(buttons[clickedIndex].getText());
            buttons[clickedIndex].setText("");
        }

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "🎉 You Win!");
            initializePuzzle();
        }
    }

    private boolean isAdjacent(int i1, int i2) {
        int r1 = i1 / SIZE, c1 = i1 % SIZE;
        int r2 = i2 / SIZE, c2 = i2 % SIZE;
        return (Math.abs(r1 - r2) + Math.abs(c1 - c2)) == 1;
    }

    private boolean checkWin() {
        for (int i = 0; i < 8; i++) {
            if (!buttons[i].getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return buttons[8].getText().equals("");
    }

    // Fully-qualified parameter type to avoid ambiguity
    private boolean isSolved(java.util.List<Integer> list) {
        for (int i = 0; i < 8; i++) {
            if (list.get(i) != i + 1) return false;
        }
        return list.get(8) == 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PuzzleGame::new);
    }
}
