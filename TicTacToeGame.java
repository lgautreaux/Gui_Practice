import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

// TicTacToeGame class inherits from JFrame
public class TicTacToeGame extends JFrame {

    private JPanel jPanel; // Panel to hold the Tic Tac Toe buttons
    private ArrayList<JButton> buttons = new ArrayList<>(); // List of all the game buttons
    private final int ROWS = 3, COLUMNS = 3; // Number of rows and columns in the game
    private String currentPlayer = "x"; // Start with player 'x'
    private Font font; // Font for the buttons
    private JMenuBar menuBar; // Menu bar for game options
    private JMenu menu; // Menu in the menu bar
    private JMenuItem resetGameItem; // Menu item for resetting the game
    private int xWins = 0; // Tracks wins for player 'x'
    private int oWins = 0; // Tracks wins for player 'o'

    // Constructor to initialize the Tic Tac Toe game
    public TicTacToeGame() {
        super("Tic Tac Toe Game"); // Set the title of the game window

        setupMenu(); // Set up the menu bar and its components
        setupBoard(); // Set up the game board
        setupFrame(); // Configure the main JFrame properties
    }

    // Setup the menu bar and its components
    private void setupMenu() {
        menuBar = new JMenuBar(); // Create the menu bar
        menu = new JMenu("Game Options"); // Create a menu
        resetGameItem = new JMenuItem("Reset Game"); // Create menu item for reset
        resetGameItem.addActionListener(e -> resetGame()); // Add action listener for reset

        menu.add(resetGameItem); // Add the reset item to the menu
        menuBar.add(menu); // Add the menu to the menu bar
        setJMenuBar(menuBar); // Set the menu bar to the frame
    }

    // Setup the game board with buttons in a grid layout
    private void setupBoard() {
        jPanel = new JPanel(new GridLayout(ROWS, COLUMNS)); // Grid layout for the game board
        font = new Font(Font.SERIF, Font.BOLD, 100); // Define font for buttons

        // Create 3x3 grid of buttons
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            JButton button = new JButton(); // Create a new button
            button.addActionListener(this::buttonClicked); // Add event listener for button clicks
            button.setFont(font); // Set the button font
            buttons.add(button); // Add the button to the list
            jPanel.add(button); // Add the button to the panel
        }

        setContentPane(jPanel); // Set the content pane of the frame
    }

    // Setup main JFrame properties
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the app on window close
        setSize(300, 300); // Set the frame size
        setVisible(true); // Make the frame visible
    }

    // Reset the game board for a new game
    private void resetGame() {
        currentPlayer = "x"; // Reset to the starting player
        for (JButton button : buttons) { // Loop through all buttons
            button.setText(""); // Clear button text
            button.setBackground(null); // Reset background color
            button.setEnabled(true); // Enable the button
        }
    }

    // Handle button click events
    private void buttonClicked(ActionEvent event) {
        JButton buttonClicked = (JButton) event.getSource(); // Get the clicked button
        buttonClicked.setText(currentPlayer); // Set the button text to current player
        buttonClicked.setEnabled(false); // Disable the button

        // Set the background color based on the current player
        if (currentPlayer.equals("x")) {
            buttonClicked.setBackground(Color.RED);
        } else {
            buttonClicked.setBackground(Color.GREEN);
        }

        boolean winnerFound = checkWinner(); // Check if there's a winner

        if (winnerFound) {
            JOptionPane.showMessageDialog(this, currentPlayer + " has won the game!"); // Notify of the winner
            updateScores(); // Update the win count
            disableButtons(); // Disable all buttons after winning
        } else if (isBoardFull()) { // Check for a tie
            JOptionPane.showMessageDialog(this, "It's a tie!"); // Notify of the tie
        } else {
            switchPlayer(); // Switch the current player
        }
    }

    // Disable all buttons after a win or tie
    private void disableButtons() {
        for (JButton button : buttons) { // Loop through all buttons
            button.setEnabled(false); // Disable the button
        }
    }

    // Switches to the other player
    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("x") ? "o" : "x"; // Toggle between 'x' and 'o'
    }

    // Check if the board is full (no more moves available)
    private boolean isBoardFull() {
        for (JButton button : buttons) { // Loop through all buttons
            if (button.getText().isEmpty()) { // If any button is empty
                return false; // Board is not full
            }
        }
        return true; // Board is full
    }

    // Update the scores for each player
    private void updateScores() {
        if (currentPlayer.equals("x")) {
            xWins++; // Increment wins for 'x'
        } else {
            oWins++; // Increment wins for 'o'
        }
        displayScores(); // Display the updated scores
    }

    // Display the scores for both players
    private void displayScores() {
        JOptionPane.showMessageDialog(this, "Scores: X = " + xWins + ", O = " + oWins); // Show scores
    }

    // Check if current player has won
    private boolean checkWinner() {
        int[][] winningPositions = { 
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        for (int[] pos : winningPositions) { // Loop through all winning positions
            if (buttons.get(pos[0]).getText().equals(currentPlayer) && 
                buttons.get(pos[1]).getText().equals(currentPlayer) && 
                buttons.get(pos[2]).getText().equals(currentPlayer)) {
                return true; // If there's a winning combination, return true
            }
        }

        return false; // No winner found
    }
}
