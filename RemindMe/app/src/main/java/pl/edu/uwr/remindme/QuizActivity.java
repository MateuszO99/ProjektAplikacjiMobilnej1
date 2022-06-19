package pl.edu.uwr.remindme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private TextView textView;
    private TextView correct_ans;
    private TextView incorrect_ans;
    private TextView score;
    private Button tButton;
    private Button fButton;
    private Button showCorrectAnswerButton;
    private int id;
    private int numOfAnswers;
    private int points;
    private boolean stateOfButtons;
    private boolean answerMode;

    private final Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, true),
            new Question(R.string.question4, false),
            new Question(R.string.question5, false),
    };
    private boolean[] answer = new boolean[questions.length];
    private boolean[] answered = new boolean[questions.length];

    private void findButtons(){
        tButton = findViewById(R.id.true_button);
        fButton = findViewById(R.id.false_button);
        showCorrectAnswerButton = findViewById(R.id.show_correct_answer_button);
    }

    private void setButtons(boolean state){
        tButton.setEnabled(state);
        fButton.setEnabled(state);
        showCorrectAnswerButton.setEnabled(!state);
    }

    private void checkButtonState(){
        if (answered[id]){
            stateOfButtons = false;
            setButtons(false);
        }
        else{
            stateOfButtons = true;
            setButtons(true);
        }
    }

    private int correctAnswers(){
        int result = 0;
        for (int i = 0; i < questions.length; i++){
            if (questions[i].isAnswer() == answer[i])
                result++;
        }
        return result;
    }

    private void setSummary(){
        correct_ans = findViewById(R.id.correct_ans);
        incorrect_ans = findViewById(R.id.incorrect_ans);
        score = findViewById(R.id.score);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void showSummary(){
        double score = points / (double)questions.length * 100;
        correct_ans.setText("Correct answer: " + points);
        incorrect_ans.setText("Incorrect answer: " + (questions.length - points));
        this.score.setText("Score: " + String.format("%.2f", score) + "%");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textView = findViewById(R.id.question_text);
        id = 0;
        textView.setText(questions[id].getTextId());

        findButtons();
        showCorrectAnswerButton.setEnabled(false);

        showCorrectAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Correct Answer");
                builder.setMessage(Boolean.toString(questions[id].isAnswer()));

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        numOfAnswers = 0;
        stateOfButtons = true;
        answerMode = false;

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("questions_state"));
            id = savedInstanceState.getInt("id_state");
            numOfAnswers = savedInstanceState.getInt("num_of_answers_state");
            points = savedInstanceState.getInt("points_state");
            stateOfButtons = savedInstanceState.getBoolean("buttons_state");
            answerMode = savedInstanceState.getBoolean("answer_mode_state");
            answer = savedInstanceState.getBooleanArray("answer_state");
            answered = savedInstanceState.getBooleanArray("answered_state");

            setButtons(stateOfButtons);

            if (answerMode){
                setContentView(R.layout.summary);
                setSummary();
                showSummary();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String text = textView.getText().toString();
        outState.putString("questions_state", text);
        outState.putInt("id_state", id);
        outState.putInt("num_of_answers_state", numOfAnswers);
        outState.putInt("points_state", points);
        outState.putBoolean("buttons_state", stateOfButtons);
        outState.putBoolean("answer_mode_state", answerMode);
        outState.putBooleanArray("answer_state", answer);
        outState.putBooleanArray("answered_state", answered);
    }

    public void prevButton(View view) {
        id--;
        if (id < 0)
            id = questions.length - 1;
        textView.setText(questions[id].getTextId());

        checkButtonState();
    }

    public void nextButton(View view) {
        id++;
        if (id == questions.length)
            id = 0;
        textView.setText(questions[id].getTextId());

        checkButtonState();
    }

    public void trueFalseButtons(View view){
        numOfAnswers++;
        stateOfButtons = false;
        setButtons(false);

        String text = ((Button) view).getText().toString();
        answer[id] = text.equals("TRUE");
        answered[id] = true;

        if (numOfAnswers == questions.length) {
            setContentView(R.layout.summary);
            setSummary();
            answerMode = true;
            points = correctAnswers();
            showSummary();
        }
    }

    public void resetButton(View view) {
        setContentView(R.layout.activity_quiz);
        id = 0;
        textView = findViewById(R.id.question_text);
        textView.setText(questions[id].getTextId());
        stateOfButtons = true;
        setButtons(true);
        numOfAnswers = 0;
        points = 0;
        answerMode = false;

        findButtons();
        showCorrectAnswerButton.setEnabled(false);

        for (int i = 0; i < questions.length; i++){
            answer[i] = false;
            answered[i] = false;
        }
    }
}