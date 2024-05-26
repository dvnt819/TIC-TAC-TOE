package com.example.bmicalculator;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtResult;
        TextInputEditText inputWeight,inputHeightFt,inputHeightIn;
        Button btnCalculate;
        LinearLayout main;

        txtResult=findViewById(R.id.txtResult);
        inputWeight=findViewById(R.id.inputWeight);
        inputHeightIn=findViewById(R.id.inputHeightIn);
        inputHeightFt=findViewById(R.id.inputHeightFt);
        btnCalculate=findViewById(R.id.btnCalculate);
        main=findViewById(R.id.main);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String wtString = inputWeight.getText().toString();
                String ftString = inputHeightFt.getText().toString();
                String inString = inputHeightIn.getText().toString();
                if (TextUtils.isEmpty(wtString) || TextUtils.isEmpty(ftString) || TextUtils.isEmpty(inString)) {
                    txtResult.setText("Input fields can't be empty");
                }
                else{
                    int wt = Integer.parseInt(wtString);
                    int ft = Integer.parseInt(ftString);
                    int in = Integer.parseInt(inString);

                    double total_m=(ft*12+in)*0.0253;
                    double bmi=wt/(total_m*total_m);
                    if(in>12 || in<0 || ft<=0 || wt<0){
                        txtResult.setText("Inches must be between 0 to 11");
                    }
                    else{
                        if(bmi>25){
                            txtResult.setText("You are OverWeight");
                            main.setBackgroundColor(getResources().getColor(R.color.red));
                        }else if(bmi<18){
                            txtResult.setText("You are UnderWeight");
                            main.setBackgroundColor(getResources().getColor(R.color.yellow));
                        }else{
                            txtResult.setText("You are Healthy");
                            main.setBackgroundColor(getResources().getColor(R.color.green));
                        }
                    }
                }
            }
        });
    }
}