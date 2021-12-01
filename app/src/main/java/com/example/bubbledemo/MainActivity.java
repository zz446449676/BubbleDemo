package com.example.bubbledemo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubbledemo.view.BubbleViewLayout;
import com.example.bubbledemo.view.util.ImageSpanUtil;
import com.example.bubbledemo.view.util.ViewTools;

public class MainActivity extends AppCompatActivity {
    public static WindowManager mWindowManager;
    public static BubbleViewLayout mBubbleViewLayout;
    Context context;
    private TextView subtitle_text;
    private Button subscribeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        subtitle_text = findViewById(R.id.subtitle_text);
        subscribeBtn = findViewById(R.id.subscribeBtn);

        subscribeBtn.setOnClickListener( v -> {
            Toast.makeText(this, "订阅成功 ！！！",Toast.LENGTH_SHORT).show();
        });

        tipsComponent();
    }

    private void tipsComponent () {
        SpannableStringBuilder span_subscribe_subText = new SpannableStringBuilder("我希望取得會員獨家優惠及最新著數。" + "  ");
        span_subscribe_subText.setSpan(
                new ImageSpanUtil(this, R.drawable.help_black),
                span_subscribe_subText.length() - 2 , span_subscribe_subText.length() - 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        span_subscribe_subText.setSpan(
                //对 ？标识添加点击事件，弹出提示气泡
                new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        mBubbleViewLayout = new BubbleViewLayout(context);
                        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,0,0, PixelFormat.TRANSLUCENT);
                        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
                        params.dimAmount = 0.0f;
                        params.alpha = 0.99f;
                        params.gravity = Gravity.LEFT | Gravity.TOP;
                        mWindowManager.addView(mBubbleViewLayout, params);
                        //定位 ？的位置
                        int[] location = new int[2];
                        subtitle_text.getLocationOnScreen(location);
                        mBubbleViewLayout.setY(location[1] + ViewTools.dp2px(context, 4));
                        mBubbleViewLayout.setOffset((int)(location[0] + ImageSpanUtil.markX) - ViewTools.dp2px(context, 8));
                    }
                },
                span_subscribe_subText.length() - 2 , span_subscribe_subText.length() - 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        subtitle_text.setText(span_subscribe_subText);
        subtitle_text.setMovementMethod(LinkMovementMethod.getInstance());//激活链接
    }

    public void removeTips (View v) {
        if (mWindowManager != null && mBubbleViewLayout != null) {
            mWindowManager.removeViewImmediate(mBubbleViewLayout);
        }
    }
}