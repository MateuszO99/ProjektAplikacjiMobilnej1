package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

public class GoHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_home);

        SharedPreferences sharedPreferences = getSharedPreferences("MY_DATA", MODE_PRIVATE);

        String googleString = "google.navigation:q=" +
                sharedPreferences.
                        getString("ADDRESS", "Wyspa Slodowa")
                + "&mode=w";

        Uri gmmIntentUri = Uri.parse(googleString);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}