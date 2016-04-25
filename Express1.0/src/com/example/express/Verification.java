package com.example.express;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Verification extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout3);
		
		TextView r1=(TextView) findViewById(R.id.r1);
		TextView r2=(TextView) findViewById(R.id.r2);
		TextView r3=(TextView) findViewById(R.id.r3);
		TextView r4=(TextView) findViewById(R.id.r4);
		TextView re1=(TextView) findViewById(R.id.re1);
		TextView re2=(TextView) findViewById(R.id.re2);
		TextView re3=(TextView) findViewById(R.id.re3);
		TextView re4=(TextView) findViewById(R.id.re4);
		TextView rec=(TextView) findViewById(R.id.rec);
		
		Intent mIntent = getIntent();  
		Bundle b = mIntent.getExtras(); 
		
		r1.setText(b.getString("a1"));
		r2.setText(b.getString("a2"));
		r3.setText(b.getString("a3"));
		r4.setText(b.getString("a4"));
		re1.setText(b.getString("b1"));
		re2.setText(b.getString("b2"));
		re3.setText(b.getString("b3"));
		re4.setText(b.getString("b4"));
		rec.setText(b.getString("c"));
		}
	 

}
