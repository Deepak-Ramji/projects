import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String currentPlayer = player1;
    
    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.WHITE);
        frame.add(boardPanel);

        for(int r = 0; r<3; r++){
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.BLACK);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            checkTie();
                            if(!gameOver){
                                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                                textLabel.setText(currentPlayer + "'s Turn.");
                            }
                        }
                    }
                });
            }
        }
    }
    void checkWinner() {
        //Horizontal
        for (int r = 0; r < 3; r++) {
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()){
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
            }
            
        }

        //Vertical
        for (int c = 0; c < 3; c++) {
            if(board[0][c].getText() == "") continue;

            if(board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()){
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
            }
            
        }

        //Diagonal
        //1. top-left -> bottom-right
        if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != ""){
            for (int i = 0; i < 3; i++) {
                    setWinner(board[i][i]);
            }
            gameOver = true;
        }

        //2. top-right -> bottom-left
        if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != ""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
        }
    }

    void checkTie() {
        if(turns == 9 && !gameOver){   
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.BLACK);
        textLabel.setText(currentPlayer + " is the Winner!");
    }

    void setTie(JButton tile){
         tile.setForeground(Color.YELLOW);
        tile.setBackground(Color.BLACK);
        textLabel.setText( "TIE!");
    }
}
