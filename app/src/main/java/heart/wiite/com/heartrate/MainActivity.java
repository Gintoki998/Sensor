package heart.wiite.com.heartrate;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements SensorEventListener {
    TextView mHeartTextView;

    private SensorManager sensorManager2;
    public static float mems_data[] = new float[]{0, 0, 0, 0, 0};
    private int heartRate;
    Sensor sensor2;

    @Override
    protected void init() {
        setContentView(R.layout.activity_main);
        mHeartTextView = (TextView) this.findViewById(R.id.tvtitle);
        sensorManager2 = (SensorManager) this.getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void setSensorHelp() {
        sensor2 = sensorManager2
                .getDefaultSensor(Sensor.TYPE_HEART_RATE);
        sensorManager2.registerListener(this, sensor2,
                                        SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterSensor();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        sensorManager2.registerListener(this, sensor2,
                SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        unRegisterSensor();
    }

    protected void unRegisterSensor() {
        if (sensorManager2 != null) {
            sensorManager2.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_HEART_RATE:

                mems_data[0] = event.values[0];
                //mems_data[1] = event.values[1];
                //mems_data[2] = event.values[2];
                //mems_data[3] = event.values[3];
                //mems_data[4] = event.values[4];
                Log.d("qs_onSensorChanged: ", event.values.length + "");

                heartRate = (int) mems_data[0];
                mHeartTextView.setText(" 心率：" + heartRate);
                Log.d("qs_heartRate", " 心率：" + mems_data[0] + "," + mems_data[1] + "," + mems_data[2] + "," + mems_data[3] + "," + mems_data[4]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        Log.d("heartRate_qs", " onAccuracyChanged");

        switch (sensor.getType()) {

            case Sensor.TYPE_HEART_RATE:

                Log.d("heartRate_qs", "onAccuracyChanged_TYPE_HEART_RATE");

        }

    }

}
