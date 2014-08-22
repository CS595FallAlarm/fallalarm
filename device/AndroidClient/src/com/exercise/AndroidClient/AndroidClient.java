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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AndroidClient extends Activity implements SensorEventListener {

	private static final String serverHost = "192.168.1.128";
	
	private static final String ACTIVITY_MSG_ID = "101";  
	
	EditText textOut;
	TextView textIn;
	public SensorManager sensorManager;
	TextView xCoor1; // declare X axis object
	TextView yCoor1; // declare Y axis object
	TextView zCoor1; // declare Z axis object

	TextView xCoor2; // declare X axis object
	TextView yCoor2; // declare Y axis object
	TextView zCoor2; // declare Z axis object
	TextView risk;
	TextView result;
	TextView ppid;
	String pid = "10001";
	float x1;
	float y1;
	float z1;
	float x2;
	float y2;
	float z2;
	
	private CardanLinearAcceleration cardanLinearAcceleration;

	// Outputs for the acceleration and LPFs
	private float[] acceleration = new float[3];
	private float[] linearAcceleration = new float[3];
	private float[] magnetic = new float[3];
	private float[] g = new float[3];
	
	private LowPassFilter lpfAcceleration;
	private LowPassFilter lpfMagnetic;

	private MeanFilter meanFilterAcceleration;
	private MeanFilter meanFilterMagnetic;

	private static Settings settings;
	
	private List<LinearAcceleration> linearAccels = new ArrayList<LinearAcceleration>();
	
	private long startTime;
	
	private long currTime;
	
	private float sensorRate = 0.100f;  //100 ms
	
	protected ServerSocket serverSocket;
	protected int port = 8080;

	private Socket clientSocket; 
	
	private boolean sensorEvent;
	
	static {
		settings = new Settings();
		settings.setPatientId(10001);
	}
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ppid = (TextView) findViewById(R.id.pid);
		xCoor1 = (TextView) findViewById(R.id.accel_xcoor1); // create X axis object
		yCoor1 = (TextView) findViewById(R.id.accel_ycoor1); // create Y axis object
		zCoor1 = (TextView) findViewById(R.id.accel_zcoor1); // create Z axis object

		xCoor2 = (TextView) findViewById(R.id.mag_xcoor2); // create X axis object
		yCoor2 = (TextView) findViewById(R.id.mag_ycoor2); // create Y axis object
		zCoor2 = (TextView) findViewById(R.id.mag_zcoor2); // create Z axis object
		result = (TextView) findViewById(R.id.Risk);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		// Register for sensor updates.
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);

		// Register for sensor updates.
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_FASTEST);
		textOut = (EditText) findViewById(R.id.textout);
		Button buttonSend = (Button) findViewById(R.id.send);
		textIn = (TextView) findViewById(R.id.textin);
		buttonSend.setOnClickListener(buttonSendOnClickListener);
		/*
		 * if (android.os.Build.VERSION.SDK_INT > 9) {
		 * 
		 * StrictMode.ThreadPolicy policy = new
		 * StrictMode.ThreadPolicy.Builder().permitAll().build();
		 * StrictMode.setThreadPolicy(policy); }
		 */
		
		// Create the low-pass filters
		cardanLinearAcceleration = new CardanLinearAcceleration(this,
				lpfAcceleration, lpfMagnetic, meanFilterAcceleration,
				meanFilterMagnetic);


	}
	
	private void openServerSocket(){
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("Server socket on port "+port+" opened");
			clientSocket = this.serverSocket.accept();
			System.out.println("Server socket accepted client socket connection");
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            byte[] buff = new byte[1024];
            IOUtils.read(input, buff);
            String readings = new String(buff);
            String[] values = readings.split(":");
		} catch (IOException e) {
			throw new RuntimeException(
					"Error while opening server socket - port " + port, e);
		}
	}
	

	

	public void onSensorChanged(SensorEvent event) {
		
		sensorEvent = false;
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			//System.out.println("acceleration :"+event.values[0]+","+event.values[1]+","+event.values[2]);
			// Get a local copy of the sensor values
			System.arraycopy(event.values, 0, acceleration, 0,
					event.values.length);
			sensorEvent = true;
		}

		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
		{
			//System.out.println("magnetic :"+event.values[0]+","+event.values[1]+","+event.values[2]);
			// Get a local copy of the sensor values
			System.arraycopy(event.values, 0, magnetic, 0, event.values.length);
			sensorEvent = true;
		}

		if(sensorEvent) {
			currTime = System.currentTimeMillis();
			if(startTime == 0) {
				startTime = currTime;
			}
			xCoor1.setText("Accel X:"+acceleration[0]);
			yCoor1.setText("Accel Y:"+acceleration[1]+"");
			zCoor1.setText("Accel Z:"+acceleration[2]+"");
			
			System.out.println("acceleration -"+acceleration[0]+","+acceleration[1]+","+acceleration[2]);
	
			xCoor2.setText("Magnetic X:"+magnetic[0]+"");
			yCoor2.setText("Magnetic Y:"+magnetic[1]+"");
			zCoor2.setText("Magnetic Z:"+magnetic[2]+"");
			System.out.println("magentic -"+magnetic[0]+","+magnetic[1]+","+magnetic[2]);
			linearAcceleration = cardanLinearAcceleration.addSamples(acceleration,
					magnetic);
			
			LinearAcceleration linAcc = new LinearAcceleration();
			linAcc.setAccelX(linearAcceleration[0]);
			linAcc.setAccelY(linearAcceleration[1]);
			linAcc.setAccelZ(linearAcceleration[2]);
			float maxVel = 0;
			float maxDist = 0;
			//After every 1 sec check the velocity and distance 
			if((currTime - startTime) > 1000) { 
				//calculate the velocity taking 100 ms collection rate.
				if(linearAccels.size()>0) {
					float accelX = 0.0f;
					float velX = 0.0f;
					float distanceX = 0.0f;
					float accelY = 0.0f;
					float velY = 0.0f;
					float distanceY = 0.0f;
					float accelZ = 0.0f;
					float velZ = 0.0f;
					float distanceZ = 0.0f;
					
					//calculate the velocities and distances of acceleration reading within a sec
					for(LinearAcceleration acc : linearAccels) {
						accelX = acc.getAccelX();
						velX = velX + (accelX * (sensorRate));
						distanceX = velX * (sensorRate);
						accelY = acc.getAccelY();
						velY = velY + (accelY * (sensorRate));
						distanceY = velY * (sensorRate);
						accelZ = acc.getAccelZ();
						velZ = velZ + (accelZ * (sensorRate));
						distanceZ = velZ * (sensorRate);
					}
					maxVel = velX;
					if(velY > maxVel){
						maxVel = velY;
					}
					if(velZ > maxVel){
						maxVel = velZ;
					}
					maxDist = distanceX;
					if(distanceY > maxDist){
						maxDist = distanceY;
					}
					if(distanceZ > maxDist){
						maxDist = distanceZ;
					}
					System.out.println("Maximum velocity :"+maxVel +" m/s");
					System.out.println("Maximum distance :"+maxDist +" m");
				}
				startTime = 0;
				linearAccels.clear();
			}
			linearAccels.add(linAcc);
			
			int riskLevel = 0;
			//Calculate risk level
			if(maxVel > 2) {
				if(maxDist > 1) {
					riskLevel = 3;
					final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
					Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM); 
					MediaPlayer player = new MediaPlayer();
					try {
						player.setDataSource(this, alert);
						if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
						    player.setAudioStreamType(AudioManager.STREAM_ALARM);
						    player.setLooping(true);
						    player.prepare();
						    player.start();
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				}else {
					riskLevel = 2;
				}
			}else if(maxDist > 1){
				riskLevel = 3;
			}else {
				riskLevel = 1;
			}
			result.setText("Risk :"+riskLevel+"");
			//System.out.println("linearaccel -"+linearAcceleration[0]+","+linearAcceleration[1]+","+linearAcceleration[2]);
			StringBuilder sb = new StringBuilder();
			sb.append(pid+",");
			sb.append(acceleration[0]+",");
			sb.append(acceleration[1]+",");
			sb.append(acceleration[2]);
			
			StringBuilder msgBldr = new StringBuilder();
			msgBldr.append(pid+",");
			msgBldr.append(acceleration[0]+",");
			msgBldr.append(acceleration[1]+",");
			msgBldr.append(acceleration[2]+",");
			msgBldr.append(magnetic[0]+",");
			msgBldr.append(magnetic[1]+",");
			msgBldr.append(magnetic[2]+",");
			msgBldr.append(riskLevel);
			SendPatientActivityMessage activityMsgSender = new SendPatientActivityMessage();
			activityMsgSender.execute(msgBldr.toString());
		}	
		
		//textOut.setText(sb.toString());
		
		/*
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {

			// assign directions
			x2 = event.values[0];
			y2 = event.values[1];
			z2 = event.values[2];
			pid = "3495873243948";

			ppid.setText("Patient ID: " + pid);
			xCoor2.setText("Orientation X2: " + x2);
			yCoor2.setText("Orientation Y2: " + y2);
			zCoor2.setText("Orientation Z2: " + z2);
		}
		// check sensor type
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

			// assign directions
			x1 = event.values[0];
			y1 = event.values[1];
			z1 = event.values[2];
			int risk;
			System.out.println("Acceleration -x:"+x1+", y:"+y1+", z:"+z1);
			xCoor1.setText("Acceleration X1: " + x1);
			yCoor1.setText("Acceleration Y1: " + y1);
			zCoor1.setText("Acceleration Z1: " + z1);
			int result1 = (int) round(pow(x1, 2) + pow(y1, 2) + pow(z1, 2));

			if (result1 < 1)
				risk = 5;
			else if (result1 < 4)
				risk = 4;
			else if (result1 < 7)
				risk = 3;
			else if (result1 < 9)
				risk = 2;
			else
				risk = 1;
			result.setText("Risk:  " + Integer.toString(risk));
//			textOut.setText("\n Patient ID : " + pid + "\n Acceleration X1: "
//					+ x1 + "\n Acceleration Y1: " + y1 + "\n Acceleration Z1: "
//					+ z1 + "\n Orientation X2: " + x2 + "\n Orientation Y2: "
//					+ y2 + "\n Orientation Z2: " + z2 + "\n Risk:"
//					+ Integer.toString(risk));
			
			StringBuilder sb = new StringBuilder();
			sb.append(pid+",");
			sb.append(x1+",");
			sb.append(y1+",");
			sb.append(z1+",");
			sb.append(x2+",");
			sb.append(y2+",");
			sb.append(z2+",");
			sb.append(risk);
			textOut.setText(sb.toString());
			
		}
		*/
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}

	Button.OnClickListener buttonSendOnClickListener = new Button.OnClickListener() {

		public void onClick(View arg0) {
			new SendPatientActivityMessage().execute(textOut.getText().toString());
		}
	};
	
	class SendPatientActivityMessage extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			Socket socket = null;
			DataOutputStream dataOutputStream = null;
			DataInputStream dataInputStream = null;

			try {
				//String ip = Inet4Address.getLocalHost().getHostAddress();
				socket = new Socket(serverHost, 8080);
				// socket = new Socket("192.168.43.214",5000);
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				dataInputStream = new DataInputStream(socket.getInputStream());
				String message = ACTIVITY_MSG_ID +","+params[0];
				System.out.println("Message "+message);
				dataOutputStream.writeUTF(message);
				//textIn.setText(dataInputStream.readUTF());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (dataInputStream != null) {
					try {
						dataInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return "success";
		}
		
	}
	
	
	class SendMessage extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			Socket socket = null;
			DataOutputStream dataOutputStream = null;
			DataInputStream dataInputStream = null;

			try {
				//String ip = Inet4Address.getLocalHost().getHostAddress();
				socket = new Socket("192.168.1.128", 8080);
				// socket = new Socket("192.168.43.214",5000);
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				dataInputStream = new DataInputStream(socket.getInputStream());
				//message format - "messageid,patientid,content"
				StringBuffer message = new StringBuffer(); 
				message.append("103"+",");
				message.append(settings.getPatientId()+",");
				message.append(params[0]);
				System.out.println("Message "+message.toString());
				dataOutputStream.writeUTF(message.toString());
				//textIn.setText(dataInputStream.readUTF());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (dataInputStream != null) {
					try {
						dataInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return "success";
		}
		
	}
	
	
}