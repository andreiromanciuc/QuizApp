package com.andrei.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;
    private  int mUserScore;

    private QuizModel[] questionCollection = new QuizModel[]{

            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false),

    };

    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.txtQuestion);

        QuizModel q1 = questionCollection[mQuestionIndex];

        mQuizQuestion = q1.getQuestion();

        mTextView.setText(questionCollection[mQuestionIndex].getQuestion());

        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView = findViewById(R.id.txtQuizStats);

        btnTrue = findViewById(R.id.btnTrue);


        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUserAnswer(true);
                changeQuestionOnButtOnClick();
            }
        });

        btnWrong = findViewById(R.id.btnWrong);

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUserAnswer(false);
                changeQuestionOnButtOnClick();
            }
        });

    }

    private void changeQuestionOnButtOnClick() {
        mQuestionIndex = (mQuestionIndex + 1)%10;
        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
        mTextView.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatsTextView.setText(mUserScore + "");
    }

    private void evaluateUserAnswer(boolean userGuess) {

        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();

        if (currentQuestionAnswer == userGuess) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast_message, Toast.LENGTH_SHORT).show();
            mUserScore = mUserScore + 1;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show();
        }
    }

}