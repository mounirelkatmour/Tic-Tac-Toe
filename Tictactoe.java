import javax.swing.*;
import java.awt.*;

public class Tictactoe extends JFrame {
    private static Tictactoe instance;
    private JPanel panel = new JPanel();
    private JButton[] buttons = new JButton[9];
    private static int[] board = new int[9];
    private static boolean isX = true;

    private Tictactoe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 300);
        initializeGame();
        setVisible(true);
    }

    private void initializeGame() {
        panel.setLayout(new GridLayout(3, 3));
        add(panel);

        for (int i = 0; i < 9; i++) {
            board[i] = 2;
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            final int index = i;
            buttons[i].addActionListener(e -> handleMove(index));
        }
    }

    private void handleMove(int index) {
        if (board[index] == 2) {
            board[index] = isX ? 1 : 0;
            if (isX) {
                buttons[index].setText("X");
            } else {
                buttons[index].setText("O");
            }
            buttons[index].setEnabled(false);
            isX = !isX;

            if (checkWin()) {
                if (isX) {
                    JOptionPane.showMessageDialog(this, "Player O wins!");
                } else {
                    JOptionPane.showMessageDialog(this, "Player X wins!");
                }
                resetGame();
            } else if (!isInGame()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            }
        }
    }

    private boolean checkWin() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}            
        };

        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] != 2 &&
                board[pattern[0]] == board[pattern[1]] &&
                board[pattern[1]] == board[pattern[2]]) {
                return true;
            }
        }
        return false;
    }

    private boolean isInGame() {
        for (int cell : board) {
            if (cell == 2) {
                return true;
            }
        }
        return false;
    }

    private void resetGame() {
        int answer = JOptionPane.showConfirmDialog(this, "Do you want to start a new game?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            for (int i = 0; i < 9; i++) {
                board[i] = 2;
                buttons[i].setText("");
                buttons[i].setEnabled(true);
            }
            isX = true;
        } else {
            instance.dispose();
        }
    }

    public static Tictactoe getInstance() {
        if (instance == null) {
            instance = new Tictactoe();
        }
        return instance;
    }

    public static void main(String[] args) {
        Tictactoe.getInstance();
    }
}
