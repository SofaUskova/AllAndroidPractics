package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Practic_12 extends Activity {
    class RenderView extends View {
        Paint paint;

        public RenderView(Context context) {
            super(context);
            paint = new Paint();
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255, 255, 255);

            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(5);
            canvas.drawLine(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1, paint);
            canvas.drawLine(canvas.getWidth() - 1, 0, 0, canvas.getHeight() - 1, paint);

            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(360, 360, 50, paint);

            paint.setColor(Color.BLUE);
            canvas.drawRect(canvas.getWidth() - 400, canvas.getHeight() - 400,
                    canvas.getWidth() - 300, canvas.getHeight() - 300, paint);

            invalidate();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new RenderView(this));
    }
}
