package com.odin.altapv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private Button bEnviar;
	private EditText tNombre;
	private EditText tNumero;
	String number;
	String nombre;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//asignar boton a variables
		
		bEnviar = (Button) findViewById(R.id.enviar);
		tNumero = (EditText) findViewById(R.id.textNumero);
		tNombre = (EditText) findViewById(R.id.textNombre);
		
		bEnviar.setOnClickListener(
				new OnClickListener() {
					public void onClick(View view){
						
						sendPost(view);
						//finish();
						//lanzarPuntuaciones(view);
			 }
		 }
		);
	TelephonyManager tMgr =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		  number = tMgr.getLine1Number();
		  tNumero.setText(number);
		  System.out.println(number);
		  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	Thread thread = new Thread(new Runnable(){
	    @Override
	    public void run() {
	        try {
	        	HttpClient httpclient = new DefaultHttpClient();
	    	    HttpPost httppost = new HttpPost("http://192.168.1.118:8000/agentes/add");

	    	    try {
	    	        // Add your data
	    	    	
	    	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    	        nameValuePairs.add(new BasicNameValuePair("nombre", nombre));
	    	        nameValuePairs.add(new BasicNameValuePair("numero", number));
	    	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	    	        // Execute HTTP Post Request
	    	        System.out.println("Antes");
	    	       
					HttpResponse response = httpclient.execute(httppost);
	    	        System.out.println("Funciono");
	    	        System.out.println(response.toString());
	    	        bEnviar.setEnabled(true);
	    	        //Toast.makeText(this, "Enviado Correctamente", Toast.LENGTH_SHORT).show();
	    	        
	    	    } catch (ClientProtocolException e) {
	    	    	System.out.println("exception 1");
	    	        // TODO Auto-generated catch block
	    	    } catch (IOException e) {
	    	    	System.out.println("exception 2");
	    	        // TODO Auto-generated catch block
	    	    }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	});

	
	
	public void sendPost(View view) {
		System.out.println("aaaa");
		//Intent i = new Intent(this, Puntuaciones.class);
		//startActivity(i);
		// Create a new HttpClient and Post Header
		nombre =  tNombre.getText().toString();
		bEnviar.setEnabled(false);
		thread.start(); 
		
	    
	}

}
