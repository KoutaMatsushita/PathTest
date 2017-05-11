package com.example.k_matsushita.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/**
 * Created by k-matsushita on 2017/05/11.
 */

public class TestView extends SubsamplingScaleImageView {

    String svg = "M218.5 139.5l0 0l-1 0l-2 0l-1 0l-2 0l-1 0l-1 0l-2 0l-1 0l-1 0l-1 0l2 -1l4 -2l5 -2l4 -2l11 -4l10 -1l10 -3l8 0l11 0l6 0l6 0l5 2l5 7l3 7l2 8l2 8l1 10l0 9l0 10l-4 8l-6 9l-9 10l-9 6l-9 6l-4 1l-8 4l-13 4l-8 1l-5 0l-5 0l-5 -3l-2 -4l-2 -8l-1 -7l0 -10l0 -8l0 -9l6 -9l5 -7l7 -6l4 -1l11 -7l6 -1l7 0l6 0l5 0l5 0l4 5l3 5l1 8l1 8l0 11l-5 25l-6 7l-5 4l-5 2l-9 5l-6 1l-3 1l-4 0l-4 0l-3 0l-1 -2l-2 -2l0 -2l0 -4l0 -2l0 -2l0 -1l1 -2l4 -3l2 -2l2 -1l4 -2l3 -1l2 -2l2 -1";
    int strokeWidth;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        float density = getResources().getDisplayMetrics().densityDpi;
        strokeWidth = (int)(density/60f);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isReady()) return;

        Path path = new Path();
        String[] svgs = svg.split("l");
        String[] hoge = svgs[0].substring(1).split(" ");
        float from = Float.parseFloat(hoge[0]);
        float to = Float.parseFloat(hoge[1]);
        PointF pointF = sourceToViewCoord(from,to);
        path.moveTo(pointF.x,pointF.y);
        for (int i = 1;i < svgs.length;i++){
            String[] lhoge = svgs[i].split(" ");
            float lfrom = Float.parseFloat(lhoge[0]);
            float lto = Float.parseFloat(lhoge[1]);
            path.rLineTo(lfrom,lto);
        }

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth * 2);
        paint.setColor(Color.BLACK);

        canvas.drawPath(path,paint);
    }


}
