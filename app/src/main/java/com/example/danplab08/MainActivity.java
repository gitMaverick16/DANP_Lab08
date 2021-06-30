package com.example.danplab08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView texto;
    TextView texto2;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView)findViewById(R.id.texto);
        texto2 = (TextView)findViewById(R.id.texto2);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        if(sensor ==null){
            Toast.makeText(this, "No se dispone del sensor", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sensor disponible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x, y, z;
        x= event.values[0];
        y= event.values[1];
        z= event.values[2];

        texto.setText("");
        texto.append("\n" + "Aceleración lineal en X: " + "\t"+ x + "\n" + "Aceleración lineal en Y: "+ "\t" + y + "\n" + "Aceleración lineal en Z: "+ "\t" + z);

        if(y<-7){
            texto2.setText("Caida libre vertical: Si");
        }else{
            texto2.setText("Caida libre vertical: No");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}