package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText addressEditText;
    private SharedPreferences sharedPreferences;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addressEditText = findViewById(R.id.editTextAddress);

        sharedPreferences = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        address = sharedPreferences.getString("ADDRESS", "Wyspa Slodowa");

        addressEditText.setText(address);

    }

    public void saveData(View view) {
        address = addressEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ADDRESS", address);
        editor.apply();

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}