package com.android.huminskiy1325.myapplication;

/**
 * Created by cubru on 04.07.2016.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;

    TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }


}
