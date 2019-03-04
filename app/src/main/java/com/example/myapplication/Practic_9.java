package com.example.myapplication;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import android.view.View.OnTouchListener;

public class Practic_9 extends Activity implements OnTouchListener {
    SoundPool soundPool;
    int explosionId = -1;

    /*звукове эффекты - хранятся в памяти и длятся не более нескольких секунд*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setOnTouchListener(this);
        setContentView(textView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);/*кнопки громкости в нужном потоке
         *вызывается один раз за жизненый жикл активности*/
        /*кол-во звук эффектов, воспроизвдимых одновременно, звуковой поток(музыкальный)*/
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

        try {
            AssetManager assetManager = getAssets(); /*загружаем файлы из assets*/
            AssetFileDescriptor descriptor = assetManager.openFd("feerverks.mp3");
            explosionId = soundPool.load(descriptor, 1);
        } catch (IOException e) {
            textView.setText("Couldn't load sound effect from asset, " + e.getMessage());
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (explosionId != -1) {
                soundPool.play(explosionId, 1, 1, 0, 0, 1);
            }
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundPool.release();
    }
}
