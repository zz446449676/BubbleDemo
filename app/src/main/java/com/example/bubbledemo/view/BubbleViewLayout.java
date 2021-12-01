package com.example.bubbledemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bubbledemo.R;

import org.jetbrains.annotations.Nullable;

public class BubbleViewLayout extends LinearLayout {
    View view;
    BubbleView mBubbleView;
    public BubbleViewLayout (Context context) {
        this(context,null);
    }

    public BubbleViewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.question_mark_tips, this);
        view = inflater.inflate(R.layout.question_mark_tips, this);
        mBubbleView = view.findViewById(R.id.bubble_view);
    }

    public void setOffset (int mOffset) {
        mBubbleView.setOffset(mOffset);
    }
}

