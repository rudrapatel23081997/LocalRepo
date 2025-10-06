import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class ColourTicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusLabel;
    private char currentPlayer = 'X';
    private boolean gameActive = true;
    private Timer flashTimer;
    private int flashCount = 0;
    private final int MAX_FLASH = 6; // Number of flashes

    public ColourTicTacToeGUI() {
        setTitle("Tic-Tac-Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create status panel
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Player X's Turn");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusPanel.add(statusLabel);

        // Create game board panel
        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        initializeButtons(gamePanel);

        // Create control panel
        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("New Game");
        resetButton.addActionListener(e -> resetGame());
        controlPanel.add(resetButton);

        // Add panels to frame
        add(statusPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(350, 375);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeButtons(JPanel gamePanel) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setOpaque(true);
                buttons[i][j].addActionListener(this);

                // Add hover effect
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        JButton btn = (JButton) e.getSource();
                        if (btn.getText().equals("") && gameActive) {
                            btn.setBackground(new Color(220, 220, 255)); // light blue
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        JButton btn = (JButton) e.getSource();
                        if (btn.getText().equals("") && gameActive) {
                            btn.setBackground(Color.WHITE);
                        }
                    }
                });

                gamePanel.add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameActive) return;

        JButton clickedButton = (JButton) e.getSource();

        // Find which button was clicked
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton && buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText(String.valueOf(currentPlayer));
                    buttons[i][j].setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);
                    buttons[i][j].setBackground(currentPlayer == 'X' ? new Color(173, 216, 230) : new Color(255, 182, 193)); // light blue or light pink

                    if (checkWin()) {
                        statusLabel.setText("Player " + currentPlayer + " Wins!");
                        gameActive = false;
                        highlightWinningButtonsWithFlash();
                    } else if (checkDraw()) {
                        statusLabel.setText("It's a Draw!");
                        gameActive = false;
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        statusLabel.setText("Player " + currentPlayer + "'s Turn");
                    }
                    return;
                }
            }
        }
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][j].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][j].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void highlightWinningButtonsWithFlash() {
        // Identify winning buttons and flash them
        flashCount = 0;

        // Store winning buttons
        JButton[] winningButtons = new JButton[3];

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                winningButtons[0] = buttons[i][0];
                winningButtons[1] = buttons[i][1];
                winningButtons[2] = buttons[i][2];
                startFlashing(winningButtons);
                return;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][j].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][j].getText().equals(String.valueOf(currentPlayer))) {
                winningButtons[0] = buttons[0][j];
                winningButtons[1] = buttons[1][j];
                winningButtons[2] = buttons[2][j];
                startFlashing(winningButtons);
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            winningButtons[0] = buttons[0][0];
            winningButtons[1] = buttons[1][1];
            winningButtons[2] = buttons[2][2];
            startFlashing(winningButtons);
            return;
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            winningButtons[0] = buttons[0][2];
            winningButtons[1] = buttons[1][1];
            winningButtons[2] = buttons[2][0];
            startFlashing(winningButtons);
        }
    }

    private void startFlashing(JButton[] winningButtons) {
        Color flashColor = Color.GREEN;
        Color originalColor1 = winningButtons[0].getBackground();
        Color originalColor2 = winningButtons[1].getBackground();
        Color originalColor3 = winningButtons[2].getBackground();

        flashTimer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flashCount >= MAX_FLASH) {
                    // Stop flashing and set final color
                    winningButtons[0].setBackground(flashColor);
                    winningButtons[1].setBackground(flashColor);
                    winningButtons[2].setBackground(flashColor);
                    flashTimer.stop();
                    return;
                }

                if (flashCount % 2 == 0) {
                    winningButtons[0].setBackground(flashColor);
                    winningButtons[1].setBackground(flashColor);
                    winningButtons[2].setBackground(flashColor);
                } else {
                    winningButtons[0].setBackground(originalColor1);
                    winningButtons[1].setBackground(originalColor2);
                    winningButtons[2].setBackground(originalColor3);
                }
                flashCount++;
            }
        });
        flashTimer.start();
    }

    private void resetGame() {
        if (flashTimer != null && flashTimer.isRunning()) {
            flashTimer.stop();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE);
            }
        }
        currentPlayer = 'X';
        gameActive = true;
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ColourTicTacToeGUI());
    }
}
