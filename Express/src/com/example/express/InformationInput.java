package com.example.express;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InformationInput extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout2);
		
		Button buttonV = (Button) findViewById(R.id.buttonVerify);
		buttonV.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		Intent intent = new Intent(InformationInput.this, Verification.class);
		
		Bundle b = new Bundle();
		EditText a1=(EditText) findViewById(R.id.senderName);
		EditText a2=(EditText) findViewById(R.id.senderPhone);
		EditText a3=(EditText) findViewById(R.id.companyName);
		EditText a4=(EditText) findViewById(R.id.senderAddress);
		EditText b1=(EditText) findViewById(R.id.receiverName);
		EditText b2=(EditText) findViewById(R.id.receiverPhone);
		EditText b3=(EditText) findViewById(R.id.receivercompanyName);
		EditText b4=(EditText) findViewById(R.id.receiverAddress);
		EditText c=(EditText) findViewById(R.id.thingName);
		
		
		
		b.putString("a1", "寄件人姓名:  "+a1.getText().toString());  
		b.putString("a2", "寄件人电话：  "+a2.getText().toString());  
		b.putString("a3", "寄件单位名称： "+a3.getText().toString());  
		b.putString("a4", "寄件地址：   "+a4.getText().toString());  
		b.putString("b1", "收件人姓名: "+b1.getText().toString());  
		b.putString("b2", "收件人电话： "+b2.getText().toString());  
		b.putString("b3", "收件单位名称： "+b3.getText().toString());  
		b.putString("b4", "收件地址： "+b4.getText().toString());  
		b.putString("c",  "物件名称： "+c.getText().toString());  
		
		
		
		intent.putExtras(b); 
		
		startActivity(intent);
		}
		}); 
	}

}
