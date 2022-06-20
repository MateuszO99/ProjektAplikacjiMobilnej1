package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class NumberMemoryGameActivity extends AppCompatActivity {

    TextView txtLevel, txtNumber;
    EditText editNumber;
    Button btnConfirm, btnReset;

    Random r;

    int currentLevel = 1;
    String generatedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory_game);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtLevel = findViewById(R.id.txtLevel);
        txtNumber = findViewById(R.id.txtNumber);
        editNumber = findViewById(R.id.editNumber);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnReset = findViewById(R.id.btnReset);

        r = new Random();

        editNumber.setVisibility(View.GONE);
        btnConfirm.setVisibility(View.GONE);
        txtNumber.setVisibility(View.VISIBLE);

        txtLevel.setText("Level: " + currentLevel);

        generatedNumber = generateNumber(currentLevel);
        txtNumber.setText(generatedNumber);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editNumber.setVisibility(View.VISIBLE);
                btnConfirm.setVisibility(View.VISIBLE);
                txtNumber.setVisibility(View.GONE);
                editNumber.requestFocus();
            }
        }, 2000);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (generatedNumber.equals(editNumber.getText().toString())) {
                    editNumber.setVisibility(View.GONE);
                    btnConfirm.setVisibility(View.GONE);
                    txtNumber.setVisibility(View.VISIBLE);
                    editNumber.setText("");
                    currentLevel++;
                    txtLevel.setText("Level: " + currentLevel);
                    generatedNumber = generateNumber(currentLevel);
                    txtNumber.setText(generatedNumber);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            editNumber.setVisibility(View.VISIBLE);
                            btnConfirm.setVisibility(View.VISIBLE);
                            txtNumber.setVisibility(View.GONE);
                            editNumber.requestFocus();
                        }
                    }, 2000);
                } else {
                    txtLevel.setText("Game Over! The number was " + generatedNumber);
                    btnConfirm.setEnabled(false);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLevel = 1;
                editNumber.setVisibility(View.GONE);
                btnConfirm.setVisibility(View.GONE);
                txtNumber.setVisibility(View.VISIBLE);
                txtLevel.setText("Level: " + currentLevel);
                generatedNumber = generateNumber(currentLevel);
                txtNumber.setText(generatedNumber);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        editNumber.setVisibility(View.VISIBLE);
                        btnConfirm.setVisibility(View.VISIBLE);
                        txtNumber.setVisibility(View.GONE);
                        editNumber.requestFocus();
                    }
                }, 2000);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        int fontSize = sharedPreferences.getInt("FONT_SIZE", 2);

        ViewGroup layout = findViewById(R.id.activity_number_memory_game_layout);
        for (int i = 0; i < layout.getChildCount(); i++){
            View child = layout.getChildAt(i);

            if (child instanceof Button){
                Button button = (Button) child;
                button.setTextSize(fontSize * 10);
            }

            else if (child instanceof EditText){
                EditText editText = (EditText) child;
                editText.setTextSize(fontSize * 10);
            }

            else if (child instanceof TextView){
                TextView textView = (TextView) child;
                textView.setTextSize(fontSize * 10);
            }
        }
    }

    private String generateNumber(int digits) {
        String output = "";
        for (int i = 1; i <= digits; i++) {
            int randomDigit = r.nextInt(10);
            output  = output + "" + randomDigit;
        }
        return output;
    }
}