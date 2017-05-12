package com.example.k_matsushita.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by k-matsushita on 2017/05/11.
 */

public class TestView extends SubsamplingScaleImageView {

    String TAG = "TestView";
    Path path;
    Paint paint;
    String s;
    float dp;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dp =  getResources().getDisplayMetrics().density;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isReady() || s == null) return;
        createPath();
        canvas.drawPath(path,paint);
    }

    public void setSVG(String svg){
        this.s = svg;
    }

    private void createPath(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(s.getBytes("utf-8")));
            Element root = document.getDocumentElement();

            int svgWidth = Integer.parseInt(root.getAttribute("viewBox").split(" ")[2]);
            int svgHeigth = Integer.parseInt(root.getAttribute("viewBox").split(" ")[3]);
            float magWidth = getSWidth()/svgWidth;
            float magHeight = getSHeight()/svgHeigth;

            NodeList list = root.getChildNodes();
            for (int i = 0; i< list.getLength(); i++){
                Node node = list.item(i);
                switch (node.getNodeName()){
                    case "path":
                        drawPath((Element)node,magWidth,magHeight);
                        break;
                    case "rect":
                        drawRect((Element)node,magWidth,magHeight);
                        break;
                    case "ellipse":
                        drawEllipse((Element)node,magWidth,magHeight);
                        break;
                    case "line":
                        drawLine((Element)node,magWidth,magHeight);
                        break;
                }
                if (paint == null) {
                    paint = new Paint();

                    paint.setAntiAlias(true);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    paint.setStrokeJoin(Paint.Join.ROUND);
                    paint.setStrokeWidth(Float.parseFloat(((Element) node).getAttribute("stroke-width")) * dp);
                    paint.setColor(Color.parseColor(((Element) node).getAttribute("stroke")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawLine(Element line, float magWidth, float magHeight) {
        path = new Path();
        float vx = Float.parseFloat(line.getAttribute("x1")) * magWidth;
        float vy = Float.parseFloat(line.getAttribute("y1")) * magHeight;
        float x = Float.parseFloat(line.getAttribute("x2")) * magWidth * getScale();
        float y = Float.parseFloat(line.getAttribute("y2")) * magHeight * getScale();

        PointF pointF = sourceToViewCoord(vx, vy);
        path.moveTo(pointF.x,pointF.y);
        path.rLineTo(x,y);

        /*
        x1='58.15625'
        y1='103'
        x2='268.15625'
        y2='133'
        stroke='#e9546b'
        stroke-width='6'
        stroke-linecap='round'
        stroke-linejoin='round'
         */
    }

    private void drawEllipse(Element ellipse, float magWidth, float magHeight) {
        path = new Path();
        float vx = Float.parseFloat(ellipse.getAttribute("cx")) * magWidth;
        float vy = Float.parseFloat(ellipse.getAttribute("cy")) * magHeight;
        float x = Float.parseFloat(ellipse.getAttribute("rx")) * magHeight;
        float y = Float.parseFloat(ellipse.getAttribute("ry")) * magWidth;
        vx -= x / 2;
        vy -= y / 2;
        x *= getScale();
        y *= getScale();

        PointF pointF = sourceToViewCoord(vx, vy);

        path.addOval(pointF.x,pointF.y, pointF.x + x, pointF.y + y, Path.Direction.CW);

        /*
        rx='67'
        ry='65'
        cx='147.15625'
        cy='123'
        fill='none'
        stroke='#e9546b'
        stroke-width='6'
         */
    }

    private void drawRect(Element rect, float magWidth, float magHeight) {
        path = new Path();
        float vx = Float.parseFloat(rect.getAttribute("x")) * magWidth;
        float vy = Float.parseFloat(rect.getAttribute("y")) * magHeight;

        PointF pointF = sourceToViewCoord(vx, vy);

        float x = Integer.parseInt(rect.getAttribute("width"));
        float y = Integer.parseInt(rect.getAttribute("height"));
        x *= magWidth * getScale();
        y *= magHeight * getScale();

        path.addRect(pointF.x,pointF.y,pointF.x + x,pointF.y + y, Path.Direction.CW);

        /*
        width='52'
        height='35'
        x='52.203125'
        y='50'
        fill='none'
        stroke='#e9546b'
        stroke-width='6'
        stroke-linecap='round'
        stroke-linejoin='round'
        transform='scale(1, 1) translate(0, 0)'
         */
    }

    private void drawPath(Element pathE,float magWidth,float magHeight){
        path = new Path();

        String[] svgs = pathE.getAttribute("d").split("l");
        String[] hoge = svgs[0].substring(1).split(" ");
        // 218.5 139.5

        float vx = Float.parseFloat(hoge[0]) * magWidth;
        float vy = Float.parseFloat(hoge[1]) * magHeight;

        PointF pointF = sourceToViewCoord(vx,vy);
        path.moveTo(pointF.x,pointF.y);

        for (int k = 1;k < svgs.length;k++){
            String[] lhoge = svgs[k].split(" ");
            float x = Float.parseFloat(lhoge[0]) * magWidth * getScale();
            float y = Float.parseFloat(lhoge[1]) * magHeight * getScale();
            path.rLineTo(x,y);
        }

        /*
        d="M218.5 139.5l0 0l-1 0l-2 0l-1 0l-2 0l-1 0l-1 0l-2 0l-1 0l-1 0l-1 0l2 -1l4 -2l5 -2l4 -2l11 -4l10 -1l10 -3l8 0l11 0l6 0l6 0l5 2l5 7l3 7l2 8l2 8l1 10l0 9l0 10l-4 8l-6 9l-9 10l-9 6l-9 6l-4 1l-8 4l-13 4l-8 1l-5 0l-5 0l-5 -3l-2 -4l-2 -8l-1 -7l0 -10l0 -8l0 -9l6 -9l5 -7l7 -6l4 -1l11 -7l6 -1l7 0l6 0l5 0l5 0l4 5l3 5l1 8l1 8l0 11l-5 25l-6 7l-5 4l-5 2l-9 5l-6 1l-3 1l-4 0l-4 0l-3 0l-1 -2l-2 -2l0 -2l0 -4l0 -2l0 -2l0 -1l1 -2l4 -3l2 -2l2 -1l4 -2l3 -1l2 -2l2 -1"
        fill="none"
        stroke="#e9546b"
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width="6"
        */

    }
}
