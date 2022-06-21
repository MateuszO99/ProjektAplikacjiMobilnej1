package pl.edu.uwr.remindme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Common {
    private SharedPreferences sharedPreferences;
    private Integer fontSize;


    public static void changeFontSize(Activity activity, ViewGroup layout){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);

        Integer fontSize = sharedPreferences.getInt("FONT_SIZE", 2);

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
}
