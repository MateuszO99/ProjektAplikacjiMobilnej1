package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private EditText addressEditText;
    private EditText fontSizeEditText;
    private SharedPreferences sharedPreferences;
    private String address;
    private Integer fontSize;
    Spinner spinner;

    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addressEditText = findViewById(R.id.editTextAddress);
        fontSizeEditText = findViewById(R.id.editTextFontSize);

        sharedPreferences = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        address = sharedPreferences.getString("ADDRESS", "Wyspa Slodowa");
        fontSize = sharedPreferences.getInt("FONT_SIZE", 2);

        addressEditText.setText(address);
        fontSizeEditText.setText(fontSize.toString());

        ViewGroup layout = findViewById(R.id.activity_settings_layout);
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

    @SuppressLint("SetTextI18n")
    public void saveData(View view) {
        address = addressEditText.getText().toString();
        try{
            fontSize = Integer.parseInt(fontSizeEditText.getText().toString());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ADDRESS", address);
            editor.putInt("FONT_SIZE", fontSize);
            editor.apply();

            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        }
        catch (Exception e){
            addressEditText.setText(fontSize.toString());
        }

    }
}