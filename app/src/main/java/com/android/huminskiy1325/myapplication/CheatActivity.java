package com.android.huminskiy1325.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cubru on 11.07.2016.
 */


public class CheatActivity extends AppCompatActivity {

    private boolean mAnswerIsTrue;
    public static final String EXTRA_ANSWER_SHOWN = "com.android.huminskiy1325.geoquiz.answer_shown";
    public static final String Save_Information = "saveInformation";
    public static final String Tag = "CheatActivity";

    private Button mShowAnswer;
    private TextView mAnswerTextView;


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(QuizActivity.EXTRA_ANSWER_IS_TRUE, false);

        setAnswerShownResult(false);

        mAnswerTextView = (TextView) findViewById(R.id.answerText);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else
                    mAnswerTextView.setText(R.string.false_button);

                setAnswerShownResult(true);
            }
        });

        if (savedInstanceState != null) {
            mAnswerIsTrue = savedInstanceState.getBoolean(Save_Information, true);
            if (mAnswerIsTrue) {
                mAnswerTextView.setText(R.string.true_button);
            } else
                mAnswerTextView.setText(R.string.false_button);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedIState) {
        super.onSaveInstanceState(savedIState);
       // Log.d(Tag, "onSaveInstanceState");
        savedIState.putBoolean(Save_Information, mAnswerIsTrue);
    }
}
