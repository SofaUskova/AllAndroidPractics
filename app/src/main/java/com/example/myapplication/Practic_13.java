package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.io.IOException;
import java.io.InputStream;

public class Practic_13 extends Activity {
    Bitmap bitmap;

    class RenderView extends View {
        Rect dst = new Rect();
        Paint paint;

        public RenderView(Context context) {
            super(context);
            paint = new Paint();

            try {
                AssetManager assetManager = context.getAssets();

                InputStream inputStream = assetManager.open("tost.png"); /*загрузка изображения*/
                bitmap = BitmapFactory.decodeStream(inputStream);

                inputStream.close();
            } catch (IOException e) {
                //ничего
            } finally {
                // обязательно закрываем потоки
            }
        }

        protected void onDraw(Canvas canvas) {
            dst.set(10, 10, 650,  650);
            canvas.drawBitmap(bitmap, null, dst, null); /*нарисовать*/

            paint.setColor(Color.BLACK);
            paint.setTextSize(28);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Ширина: " + bitmap.getWidth() + "\nВысота: " + bitmap.getHeight(),
                    canvas.getWidth() / 2, canvas.getHeight() / 7, paint);
            canvas.drawText("Цветовая схема: " + bitmap.getConfig(),
                    + canvas.getWidth() / 2, canvas.getHeight() /6, paint);
            invalidate();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new RenderView(this));
    }

    @Override
    public void onStop() {
        super.onStop();
        bitmap.recycle();
    }
}

