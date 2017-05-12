package com.example.k_matsushita.pathtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.davemorrissey.labs.subscaleview.ImageSource;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String pathSvg = "<svg id='message-48273' xmlns='http://www.w3.org/2000/svg' version='1.1' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:svgjs='http://svgjs.com/svgjs' width='100%' height='100%' viewBox='0 0 400 368'><defs id='SvgjsDefs1001'></defs><path id='SvgjsPath1007' d='M218.5 139.5l0 0l-1 0l-2 0l-1 0l-2 0l-1 0l-1 0l-2 0l-1 0l-1 0l-1 0l2 -1l4 -2l5 -2l4 -2l11 -4l10 -1l10 -3l8 0l11 0l6 0l6 0l5 2l5 7l3 7l2 8l2 8l1 10l0 9l0 10l-4 8l-6 9l-9 10l-9 6l-9 6l-4 1l-8 4l-13 4l-8 1l-5 0l-5 0l-5 -3l-2 -4l-2 -8l-1 -7l0 -10l0 -8l0 -9l6 -9l5 -7l7 -6l4 -1l11 -7l6 -1l7 0l6 0l5 0l5 0l4 5l3 5l1 8l1 8l0 11l-5 25l-6 7l-5 4l-5 2l-9 5l-6 1l-3 1l-4 0l-4 0l-3 0l-1 -2l-2 -2l0 -2l0 -4l0 -2l0 -2l0 -1l1 -2l4 -3l2 -2l2 -1l4 -2l3 -1l2 -2l2 -1' fill='none' stroke='#e9546b' stroke-linecap='round' stroke-linejoin='round' stroke-width='6'></path></svg>";
        String rectSvg = "<svg id='message-48582' xmlns='http://www.w3.org/2000/svg' version='1.1' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:svgjs='http://svgjs.com/svgjs' width='100%' height='100%' viewBox='0 0 242 223'><defs id='SvgjsDefs1001'></defs><rect id='SvgjsRect1009' width='52' height='35' x='52.203125' y='50' fill='none' stroke='#e9546b' stroke-width='6' stroke-linecap='round' stroke-linejoin='round' transform='scale(1, 1) translate(0, 0)'></rect></svg>";
        String ellipseSvg = "<svg id='message-48588' xmlns='http://www.w3.org/2000/svg' version='1.1' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:svgjs='http://svgjs.com/svgjs' width='100%' height='100%' viewBox='0 0 391 360'><defs id='SvgjsDefs1008'></defs><ellipse id='SvgjsEllipse1009' rx='67' ry='65' cx='147.15625' cy='123' fill='none' stroke='#e9546b' stroke-width='6'></ellipse></svg>";
        String lineSvg = "<svg id='message-48591' xmlns='http://www.w3.org/2000/svg' version='1.1' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:svgjs='http://svgjs.com/svgjs' width='100%' height='100%' viewBox='0 0 391 360'><defs id='SvgjsDefs1008'></defs><line id='SvgjsLine1010' x1='58.15625' y1='103' x2='268.15625' y2='133' stroke='#e9546b' stroke-width='6' stroke-linecap='round' stroke-linejoin='round'></line></svg>";

        TestView view = (TestView)findViewById(R.id.view);
        view.setImage(ImageSource.resource(R.drawable.img));
        view.setSVG(rectSvg);
    }

}
