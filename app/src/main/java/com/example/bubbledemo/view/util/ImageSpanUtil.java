package com.example.bubbledemo.view.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * 列表页富文本，支持图片换行居中
 */
public class ImageSpanUtil extends ImageSpan {
    public static float markX;
    public static int markY;

    public ImageSpanUtil(Context context, int resourceId) {
        super(context,resourceId);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = this.getDrawable();
        canvas.save();
        int transY;
        //要显示的文本高度-图片高度除2等居中位置+top(换行情况)
        transY = ((bottom - top) - b.getBounds().bottom) / 2 + top;
        //偏移画布后开始绘制
        canvas.translate(x, transY);

        markX = x;
        markY = y;
        b.draw(canvas);
        canvas.restore();
    }
}
