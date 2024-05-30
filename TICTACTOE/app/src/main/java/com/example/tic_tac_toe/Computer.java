package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Computer extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnRestart, btnBack;
    TextView txtWinner, txtTurn;
    int[][] board = new int[3][3];
    boolean playerTurn = true;
    int moveCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);

        init();

        btnBack.setOnClickListener(v -> finish());

        btnRestart.setOnClickListener(v -> resetGame());

        for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            button.setOnClickListener(this::playerMove);
        }
    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnRestart = findViewById(R.id.btnRestart);
        txtWinner = findViewById(R.id.txtWinner);
        txtTurn = findViewById(R.id.txtTurn);
        btnBack = findViewById(R.id.btnBack);

        resetGame();
    }

    private void playerMove(View view) {
        if (!playerTurn) return;

        Button btnCurrent = (Button) view;
        if (btnCurrent.getText().toString().equals("")) {
            btnCurrent.setText("X");
            int row = Character.getNumericValue(view.getTag().toString().charAt(0));
            int col = Character.getNumericValue(view.getTag().toString().charAt(1));
            board[row][col] = 1;
            moveCount++;

            if (checkWinner() == 0) {
                playerTurn = false;
                computerMove();
            }
        }
    }

    private void computerMove() {
        int[] bestMove = findBestMove();
        if (bestMove != null) {
            board[bestMove[0]][bestMove[1]] = 2;
            Button btnCurrent = getButtonByTag(bestMove[0] + "" + bestMove[1]);
            btnCurrent.setText("O");
            moveCount++;

            if (checkWinner() == 0) {
                playerTurn = true;
            }
        }
    }

    private Button getButtonByTag(String tag) {
        switch (tag) {
            case "00":
                return btn1;
            case "01":
                return btn2;
            case "02":
                return btn3;
            case "10":
                return btn4;
            case "11":
                return btn5;
            case "12":
                return btn6;
            case "20":
                return btn7;
            case "21":
                return btn8;
            case "22":
                return btn9;
            default:
                throw new IllegalArgumentException("Invalid tag: " + tag);
        }
    }

    private void resetGame() {
        board = new int[3][3];
        moveCount = 0;
        playerTurn = true;
        txtWinner.setText("");

        for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            button.setText("");
            button.setEnabled(true);
        }
    }

    private int checkWinner() {
        int winner = getWinner();
        if (winner != 0) {
            txtWinner.setText(winner == 1 ? "Player(X) is the Winner!" : "Computer(O) is the Winner!");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            disableButtons();
            return winner;
        } else if (moveCount == 9) {
            txtWinner.setText("It's a Draw!");
            txtWinner.setTextColor(getResources().getColor(R.color.blue));
            return 3;
        }
        return 0;
    }

    private void disableButtons() {
        for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            button.setEnabled(false);
        }
    }

    private int getWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0)
                return board[i][0];
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0)
                return board[0][i];
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0)
            return board[0][0];
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0)
            return board[0][2];
        return 0;
    }

    private int minimax(int depth, boolean isMaximizer) {
        int score = evaluateBoard();
        if (score == 10) return score - depth;
        if (score == -10) return score + depth;

        if (isBoardFull()) return 0;

        if (isMaximizer) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        best = Math.max(best, minimax(depth + 1, false));
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 2;
                        best = Math.min(best, minimax(depth + 1, true));
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        }
    }

    private int evaluateBoard() {
        int winner = getWinner();
        if (winner == 1) return 10;
        if (winner == 2) return -10;
        return 0;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    private int[] findBestMove() {
        int bestVal = Integer.MAX_VALUE;
        int[] bestMove = new int[2];
        boolean moveFound = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 2;
                    int moveVal = minimax(0, true);
                    board[i][j] = 0;

                    if (moveVal < bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                        moveFound = true;
                    }
                }
            }
        }
        return moveFound ? bestMove : null;
    }
}
