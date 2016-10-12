package com.android.huminskiy1325.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mPrevButton;
    private ImageButton mNextButton;
    private Button mCheatButton;
    private TextView mQuestionsTextView;
    private TextView mSDK_Version;

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    public static final String EXTRA_ANSWER_IS_TRUE = "com.android.huminskiy1325.geoquiz.answer_is_true";
    private static final String Tag = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String CHEATING_INDEX = "Cheating_Is_Wrong";

    private TrueFalse[] mQuestionsBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private void updateQuetion() {
        int question = mQuestionsBank[mCurrentIndex].getQuestion();
        mQuestionsTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;

        if (mIsCheater)
            messageResId = R.string.judgment_toast;
        else {
            if (userPressedTrue == answerIsTrue)
                messageResId = R.string.correct_toast;
            else
                messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Tag, "onCreate called");
        setContentView(R.layout.activity_quiz);

     //   mSDK_Version = (TextView) findViewById(R.id.SDK_Version);
       // String SDK_Version = "API level "+ Build.VERSION.SDK_INT;

//        mSDK_Version.setText(SDK_Version);

        mQuestionsTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuetion();
            }
        });


        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean mCheating = false;
                if (savedInstanceState != null)
                    mCheating = savedInstanceState.getBoolean(CHEATING_INDEX);
                if (mCheating)
                    mIsCheater = mCheating;
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean mCheating = false;
                if (savedInstanceState != null)
                    mCheating = savedInstanceState.getBoolean(CHEATING_INDEX);
                if (mCheating)
                    mIsCheater = mCheating;
                checkAnswer(false);
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_question_button);

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
                    mIsCheater = false;
                    updateQuetion();
                } else {
                    Toast.makeText(QuizActivity.this, "That\'s the first question",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_question_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != (mQuestionsBank.length - 1)) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                    mIsCheater = false;
                    updateQuetion();
                } else {
                    Toast.makeText(QuizActivity.this, "That\'s was the last question.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                //startActivity(i);
                startActivityForResult(i, 0);
            }
        });
        updateQuetion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedIState) {
        super.onSaveInstanceState(savedIState);
        Log.i(Tag, "onSaveInstanceState");
        savedIState.putInt(KEY_INDEX, mCurrentIndex);
        savedIState.putBoolean(CHEATING_INDEX, mIsCheater);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag, "onStop   called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "onDestroy called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag, "onPause called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag, "onResume called");
    }
}
