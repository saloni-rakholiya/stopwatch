package com.example.stopwatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class custom extends View {

    Paint outline,fortext;
    float midx,midy,innerr,outerr,minr,secr;
    int minutes=0,seconds=0;
    float millisec=0;

    int reset=1;
    int start=0;
    int pause=0;
    private float nextsecx;
    private float nextsecy;
    private float nextminy;
    private float nextminx;




    public custom(Context context) {
        super(context);
        init(null);
    }

    public custom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public custom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    Paint white;
    private void init(AttributeSet attrs) {


        white=new Paint();
        white.setColor(Color.WHITE);




    outline=new Paint();
    outline.setColor(Color.WHITE);
    outline.setStyle(Paint.Style.STROKE);
    outline.setStrokeWidth(5f);

    fortext=new Paint();
    fortext.setColor(Color.RED);
    fortext.setTextSize(30f);
    }

    public custom(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        midx=canvas.getWidth()/2;
        midy=canvas.getHeight()/2;
        innerr=Math.min(canvas.getWidth(),canvas.getHeight())/4;
        outerr=Math.min(canvas.getWidth(),canvas.getHeight())/2;
        minr=innerr-30;
        secr=outerr-30;

        canvas.drawCircle(midx,midy,20,white);

        super.onDraw(canvas);
        canvas.drawCircle(midx,midy,innerr,outline);
        canvas.drawCircle(midx,midy,outerr,outline);

        for(int i=1;i<=60;i++){
            canvas.drawText(Integer.toString(i),(float)(midx+secr*Math.sin(Math.PI*i*2/60)),(float)(midy-secr*Math.cos(Math.PI*i*2/60)),fortext);
        }
       // canvas.drawLine(midx,midy,2*minr,midy,outline);

        for(int i=1;i<=15;i++){
            canvas.drawText(Integer.toString(i),(float) (midx+minr*Math.sin(Math.PI*i/7.5)),(float)(midy-minr*Math.cos(Math.PI*i/7.5)),fortext);
        }
        /*for(int i=1;i<=60;i++){
         }*/

        if(start==1)
        ticking(canvas);

        if(reset==1)
        {
            nextsecx=(float)(midx+secr*Math.sin(Math.PI*2));
            nextsecy=(float)(midy-secr*Math.cos(Math.PI*2));
            nextminx=(float)(midx+minr*Math.sin(Math.PI*2));
            nextminy=(float)(midy-minr*Math.cos(Math.PI*2));
            canvas.drawLine(midx, midy, nextminx, nextminy, outline);
            canvas.drawLine(midx, midy, nextsecx, nextsecy, outline);

        }
        if(pause==1)
        {canvas.drawLine(midx, midy, nextminx, nextminy, outline);
            canvas.drawLine(midx, midy, nextsecx, nextsecy, outline);
        }






    }

    public void ticking(Canvas canvas)
    {

        midx=canvas.getWidth()/2;
        midy=canvas.getHeight()/2;

        if(minutes<180)

        { seconds=(int)(millisec*12/ (60));
            if (seconds == 0) {
                nextsecx =(float)(midx+secr*Math.sin(Math.PI*60/30));
                nextsecy =(float)(midy-secr*Math.cos(Math.PI*60/30));
            } else {
                nextsecx = (float)(midx+secr*Math.sin(Math.PI*seconds/360));
                nextsecy = (float)(midy-secr*Math.cos(Math.PI*seconds/360));
            }
            canvas.drawLine(midx, midy, nextsecx, nextsecy, outline);
            minutes = seconds / 60;
            if (minutes == 0) {
                nextminx = (float)(midx+minr*Math.sin(Math.PI*2));
                nextminy = (float)(midy-minr*Math.cos(Math.PI*2));
                canvas.drawLine(midx, midy, nextminx, nextminy, outline);
            } else {
                nextminx =(float) (midx+minr*Math.sin(Math.PI*minutes/90));
                nextminy = (float)(midy-minr*Math.cos(Math.PI*minutes/90));
                canvas.drawLine(midx, midy, nextminx, nextminy, outline);
            }

            millisec= (float) (millisec+1);


            invalidate();}
        else { millisec=0;
            seconds=0;
            minutes=0;
            nextsecx=(float)(midx+secr*Math.sin(Math.PI*2));
            nextsecy=(float)(midy-secr*Math.cos(Math.PI*2));
            nextminx=(float)(midx+minr*Math.sin(Math.PI*2));
            nextminy=(float)(midy-minr*Math.cos(Math.PI*2));
            invalidate();}
    }

    public void start()
    {
        start=1;pause=0;reset=0;
        invalidate();
    }

    public void pause()
    {
        start=0;pause=1;reset=0;
        invalidate();
    }

    public void reset()
    {
        start=0; pause=0;reset=1;
        millisec=0;
        seconds=0;
        minutes=0;
        nextsecx=(float)(midx+secr*Math.sin(Math.PI*2));
        nextsecy=(float)(midy-secr*Math.cos(Math.PI*2));
        nextminx=(float)(midx+minr*Math.sin(Math.PI*2));
        nextminy=(float)(midy-minr*Math.cos(Math.PI*2));
        invalidate();
    }

}
