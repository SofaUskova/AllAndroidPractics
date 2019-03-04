package com.example.myapplication;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class Practic_10 extends Activity {
    MediaPlayer mediaPlayer;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prct10);

        textView = findViewById(R.id.textView2);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer(); /*инициализация класса*/

        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("cold_-_a_different_k.ogg");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare(); /*открывает и проверяет можно ли считать и воспроизвести*/
        } catch (IOException e) {
            textView.setText("Couldn't load music file, " + e.getMessage());
            mediaPlayer = null;
        }

    }

    public void start(View view) {
        mediaPlayer.start();
        textView.setText("Playing: \"Cold a different kind\"");
    }

    public void pause(View view) {

        mediaPlayer.pause();
    }

    public void stop(View view) throws IOException {
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare(); /*открывает и проверяет можно ли считать и воспроизвести*/
        } catch (IOException e) {
            textView.setText("Don't play, " + e.getMessage());
            mediaPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
    }
}
