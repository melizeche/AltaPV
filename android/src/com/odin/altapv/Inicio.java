package com.odin.altapv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Inicio extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicio, menu);
		return true;
	}
	
	public void lanzarRegistro(View view) {
		Intent intent = new Intent(this, MainActivity.class);

		   startActivity(intent);
	}
	
	public void lanzarPunto(View view) {
		Intent intent = new Intent(this, PuntoAdd.class);

		   startActivity(intent);
	}

}
