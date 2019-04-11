package com.example.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCalculate;
    private EditText txtHeight, txtweight;
    private TextView tvOutput;

    float height, weight, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        txtHeight = findViewById(R.id.txtHeight);
        txtweight = findViewById(R.id.txtWeight);
        tvOutput = findViewById(R.id.tvOutput);

        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalculate) {
            if (validation()) {
                height = Float.parseFloat(txtHeight.getText().toString());
                weight = Float.parseFloat(txtweight.getText().toString());
                BMI obj = new BMI();
                obj.setHeight(height);
                obj.setWeight(weight);
                res = obj.calcBMI();
                tvOutput.setText("Your BMI is " + Float.toString(res));

                if (res < 18.5) {
                    Toast.makeText(MainActivity.this, "Under Weight", Toast.LENGTH_LONG).show();
                } else if (res >= 18.5 && res <= 24.9) {
                    Toast.makeText(MainActivity.this, "Normal Weight", Toast.LENGTH_LONG).show();
                } else if (res >= 25 && res <= 29.9) {
                    Toast.makeText(MainActivity.this, "Over Weight", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Obesity", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public boolean validation(){
        boolean flag = true;
        if (TextUtils.isEmpty(txtHeight.getText().toString())){
            txtHeight.setError("Please enter Height");
            txtHeight.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(txtweight.getText().toString())){
            txtweight.setError("Please enter Height");
            txtweight.requestFocus();
            flag = false;
        }
        return flag;
    }
}
