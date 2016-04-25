package com.example.express;

import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this, ReceiveOrSent.class);
		
	/*	Bundle b = new Bundle();
		EditText acc=(EditText) findViewById(R.id.account);
		EditText pas=(EditText) findViewById(R.id.password);
		b.putString("acc", acc.getText().toString());  
		b.putString("pas", pas.getText().toString());  
		intent.putExtras(b);     */
		
		startActivity(intent);
		}
		});   
	}
	
}
