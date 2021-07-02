package com.media.mqtttestapp;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    MqttHelper mqttHelper;
    private final String tag = "MQTT";
    TextView mqttData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mqttData = (TextView) findViewById(R.id.dataReceived);
        startMqtt();
    }

    private void startMqtt(){
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.d(tag, "Connection complete");
                mqttData.append("Connection Complete\n");
            }

            @Override
            public void connectionLost(Throwable throwable) {
                Log.d(tag, "Connection Lost");
                mqttData.append("Connection Lost\n");
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.v("Debug",mqttMessage.toString());

                mqttData.append(mqttMessage.toString() + "\n");
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                Log.v(tag,"delivery complete");
            }
        });
    }
}