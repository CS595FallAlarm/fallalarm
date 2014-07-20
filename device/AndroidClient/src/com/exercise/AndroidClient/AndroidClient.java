/*
package com.exercise.AndroidClient;

import android.app.Activity;
import android.os.Bundle;

public class AndroidClient extends Activity {
    /** Called when the activity is first created. */
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
*/

package com.exercise.AndroidClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import static java.lang.Math.pow;
import static java.lang.Math.round;

public class AndroidClient extends Activity  implements SensorEventListener {

EditText textOut;
TextView textIn;
    public SensorManager sensorManager;
    TextView xCoor1; // declare X axis object
    TextView yCoor1; // declare Y axis object
    TextView zCoor1; // declare Z axis object

    TextView xCoor2; // declare X axis object
    TextView yCoor2; // declare Y axis object
    TextView zCoor2; // declare Z axis object
    TextView result;
    TextView ppid;
    String pid;
    float x1;
    float y1;
    float z1;
    float x2;
    float y2;
    float z2;
   // String pid= "3847534534";
 /** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
       ppid=(TextView)findViewById(R.id.pid);
     xCoor1=(TextView)findViewById(R.id.xcoor1); // create X axis object
     yCoor1=(TextView)findViewById(R.id.ycoor1); // create Y axis object
     zCoor1=(TextView)findViewById(R.id.zcoor1); // create Z axis object

     xCoor2=(TextView)findViewById(R.id.xcoor2); // create X axis object
     yCoor2=(TextView)findViewById(R.id.ycoor2); // create Y axis object
     zCoor2=(TextView)findViewById(R.id.zcoor2); // create Z axis object
     result=(TextView)findViewById(R.id.Risk);

     sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
     // add listener. The listener will be HelloAndroid (this) class
     sensorManager.registerListener(this,
             sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
             SensorManager.SENSOR_DELAY_NORMAL);
     sensorManager.registerListener(this,
             sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
             SensorManager.SENSOR_DELAY_NORMAL);

     textOut = (EditText)findViewById(R.id.textout);
     Button buttonSend = (Button)findViewById(R.id.send);
     textIn = (TextView)findViewById(R.id.textin);
     buttonSend.setOnClickListener(buttonSendOnClickListener);
    /* if (android.os.Build.VERSION.SDK_INT > 9) {

         StrictMode.ThreadPolicy policy =
                 new StrictMode.ThreadPolicy.Builder().permitAll().build();
         StrictMode.setThreadPolicy(policy);
     }*/

 }


    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){

            // assign directions
           x2=event.values[0];
           y2=event.values[1];
           z2=event.values[2];
            pid = "3495873243948";

            ppid.setText("Patient ID: " + pid);
            xCoor2.setText("Orientation X2: "+x2);
            yCoor2.setText("Orientation Y2: "+y2);
            zCoor2.setText("Orientation Z2: "+z2);
        }
        // check sensor type
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

            // assign directions
            x1=event.values[0];
            y1=event.values[1];
            z1=event.values[2];
            int risk;
            xCoor1.setText("Acceleration X1: "+x1);
            yCoor1.setText("Acceleration Y1: "+y1);
            zCoor1.setText("Acceleration Z1: "+z1);
            int result1=(int)round(pow(x1, 2) + pow(y1, 2) + pow(z1, 2));

            if(result1<1)
                risk=5;
            else if(result1<4)
                risk=4;
            else if(result1<7)
                risk=3;
            else if(result1<9)
                risk=2;
            else
                risk=1;
            result.setText("Risk:  "+  Integer.toString(risk));
            textOut.setText(
                            "\n Patient ID : " + pid +
                            "\n Acceleration X1: "+x1 +
                            "\n Acceleration Y1: "+y1+
                            "\n Acceleration Z1: "+z1+
                            "\n Orientation X2: "+x2 +
                            "\n Orientation Y2: "+y2 +
                            "\n Orientation Z2: "+z2 +
                    "\n Risk:" +Integer.toString(risk));
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    Button.OnClickListener buttonSendOnClickListener
 = new Button.OnClickListener(){

public void onClick(View arg0) {
 // TODO Auto-generated method stub
 Socket socket = null;
 DataOutputStream dataOutputStream = null;
 DataInputStream dataInputStream = null;

 try {
 socket = new Socket("10.0.2.2", 5000);
    // socket = new Socket("192.168.43.214",5000);
  dataOutputStream = new DataOutputStream(socket.getOutputStream());
  dataInputStream = new DataInputStream(socket.getInputStream());
  dataOutputStream.writeUTF(textOut.getText().toString());
  textIn.setText(dataInputStream.readUTF());
 } catch (UnknownHostException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 } catch (IOException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 }
 finally{
  if (socket != null){
   try {
    socket.close();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }

  if (dataOutputStream != null){
   try {
    dataOutputStream.close();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }

  if (dataInputStream != null){
   try {
    dataInputStream.close();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }
 }
}};
}