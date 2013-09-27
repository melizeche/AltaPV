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
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class PuntoAdd extends Activity  implements LocationListener{
	  private TextView latituteField;
	  private TextView longitudeField;
	  private LocationManager locationManager;
	  private String provider;
	  
	  private Button bEnviar;
	  private EditText tNombre;
	  private EditText tAgente;
	  private EditText tNumero;
	  private EditText tDireccion;
	  private EditText tBarrio;
	  private EditText tCiudad;
	  private EditText tRuc;
	  private EditText tPropietario;
	  
	    //data input
	  String agente;
	  String numero;
	  String nombre;
	  String actividad;
	  String direccion;
	  String barrio;
	  String ciudad;
	  String ruc;
	  String propietario;
	  String lat;
	  String lng;
	 
	

	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_punto_add);
		 latituteField = (TextView) findViewById(R.id.TextView02);
		    longitudeField = (TextView) findViewById(R.id.TextView04);
		    
		      tNombre = (EditText) findViewById(R.id.textNombre);
			  
			  tNumero = (EditText) findViewById(R.id.textNumero);
			  tDireccion = (EditText) findViewById(R.id.textDireccion);
			  tBarrio = (EditText) findViewById(R.id.textBarrio);
			  tCiudad = (EditText) findViewById(R.id.textCiudad);
			  tRuc = (EditText) findViewById(R.id.textRuc);
			  tPropietario = (EditText) findViewById(R.id.textPropietario);
			  
			  bEnviar = (Button) findViewById(R.id.bEnviar);


		    // Get the location manager
		    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		    // Define the criteria how to select the locatioin provider -> use
		    // default
		    Criteria criteria = new Criteria();
		    provider = locationManager.getBestProvider(criteria, false);
		    Location location = locationManager.getLastKnownLocation(provider);

		    // Initialize the location fields
		    if (location != null) {
		      System.out.println("Provider " + provider + " has been selected.");
		      onLocationChanged(location);
		    } else {
		      latituteField.setText("Location not available");
		      longitudeField.setText("Location not available");
		    }
		    TelephonyManager tMgr =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			  agente = tMgr.getLine1Number();
			  if (agente.isEmpty()){
				  agente = "No disponible";
			  }
	}

	 /* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 5000, 5, this);
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	  @Override
	  public void onLocationChanged(Location location) {
	    float lat = (float) (location.getLatitude());
	    float lng = (float) (location.getLongitude());
	    latituteField.setText(String.valueOf(lat));
	    longitudeField.setText(String.valueOf(lng));
	  }

	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }

	  @Override
	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }
	  
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		        try {
		        	HttpClient httpclient = new DefaultHttpClient();
		    	    HttpPost httppost = new HttpPost("http://192.168.1.113:8000/puntos/add");

		    	    try {
		    	        // Add your data
		    	    	
		    	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    	        nameValuePairs.add(new BasicNameValuePair("nombre", nombre));
		    	        nameValuePairs.add(new BasicNameValuePair("agente", agente));
		    	        nameValuePairs.add(new BasicNameValuePair("propietario", propietario));
		    	        nameValuePairs.add(new BasicNameValuePair("ruc", ruc));
		    	        nameValuePairs.add(new BasicNameValuePair("direccion", direccion));
		    	        nameValuePairs.add(new BasicNameValuePair("barrio", barrio));
		    	        nameValuePairs.add(new BasicNameValuePair("latitud", lat));
		    	        nameValuePairs.add(new BasicNameValuePair("longitud", lng));
		    	        nameValuePairs.add(new BasicNameValuePair("telefono", numero));
		    	        nameValuePairs.add(new BasicNameValuePair("actividad", actividad));
		    	        nameValuePairs.add(new BasicNameValuePair("ciudad", ciudad));
		    	        
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
	 
	  public void clickEnviar(View view) {
		  Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		  long it = spinner.getSelectedItemId();
		  
		  numero = tNumero.getText().toString();
		  nombre= tNombre.getText().toString();
		  direccion= tDireccion.getText().toString();
		  barrio= tBarrio.getText().toString();
		  ciudad= tCiudad.getText().toString();
		  ruc= tRuc.getText().toString();
		  propietario= tPropietario.getText().toString();
		  lat= latituteField.getText().toString();
		  lng= longitudeField.getText().toString();
		  actividad =   String.valueOf(it); 

		  System.out.println(actividad + numero + agente + nombre);
		  bEnviar.setEnabled(false);
		  thread.start(); 


	  }
} 


