package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Practic_6 extends Activity implements SensorEventListener {
    TextView textView;
    StringBuilder builder = new StringBuilder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = new TextView(this);
        textView.setTextSize(23);

        setContentView(textView);

        SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE); /*получаем экземпляр SensorManager*/

        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) { /*доступность акселерометра*/
            textView.setText("No accelerometer installed");
        } else {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if (!manager.registerListener(
                    this, accelerometer, SensorManager.SENSOR_DELAY_GAME)) { /*конст - частота получения состояния акселер*/
                textView.setText("Couldn't register sensor listener");
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {    /*при возникновении событий акселерометра*/
        builder.setLength(0);
        builder.append("x: ");
        builder.append(event.values[0]); /*х*/
        builder.append("\n" + "y: ");
        builder.append(event.values[1]); /*у*/
        builder.append("\n" + "z: ");
        builder.append(event.values[2]); /*z*/

        textView.setText(builder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        /*при изменении точности акселерометра*/
    }
}
