package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OneVsOne extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnRestart,btnBack;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int count = 0, flag = 0;

    TextView txtWinner, txtTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OneVsOne.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
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
        btnBack=findViewById(R.id.btnBack);
    }

    public void Check(View view) {
        Button btnCurrent = (Button) view;
        if (btnCurrent.getText().toString().equals("")) {
            count++;
            if (flag == 0) {
                txtTurn.setText("Turn to play : O");
                btnCurrent.setText("X");
                flag = 1;
            } else {
                txtTurn.setText("Turn to play : X");
                btnCurrent.setText("O");
                flag = 0;
            }
            if (count > 4) {
                checkWinner();
            }
        }
    }

    private void checkWinner() {
        b1 = btn1.getText().toString();
        b2 = btn2.getText().toString();
        b3 = btn3.getText().toString();
        b4 = btn4.getText().toString();
        b5 = btn5.getText().toString();
        b6 = btn6.getText().toString();
        b7 = btn7.getText().toString();
        b8 = btn8.getText().toString();
        b9 = btn9.getText().toString();

        boolean isWinner = false;

        if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            txtWinner.setText(b1 + " is Winner");
            isWinner = true;
        } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
            txtWinner.setText(b4 + " is Winner");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            isWinner = true;
        } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
            txtWinner.setText(b7 + " is Winner");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            isWinner = true;
        } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
            txtWinner.setText(b1 + " is Winner");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            isWinner = true;
        } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
            txtWinner.setText(b2 + " is Winner");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            isWinner = true;
        } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
            txtWinner.setText(b3 + " is Winner");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            isWinner = true;
        } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
            txtWinner.setText(b1 + " is Winner");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            isWinner = true;
        } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
            txtWinner.setText(b3 + " is Winner");
            txtWinner.setTextColor(getResources().getColor(R.color.green));
            isWinner = true;
        } else if (count == 9) {
            txtWinner.setText("It's a Draw");
            txtWinner.setTextColor(getResources().getColor(R.color.blue));
            isWinner = true;
        }

        if (isWinner) {
            disableButtons();
        }
    }

    private void disableButtons() {
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
    }

    private void enableButtons() {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
    }

    private void resetGame() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        txtWinner.setText("");
        count = 0;
        enableButtons();
    }
}
