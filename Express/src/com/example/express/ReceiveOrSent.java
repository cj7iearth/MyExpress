package com.example.express;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReceiveOrSent extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout1);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		Intent intent = new Intent(ReceiveOrSent.this, InformationInput.class);
		
		startActivity(intent);
		}
		});   
<<<<<<< HEAD
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v1){
			Intent intent2 = new Intent(ReceiveOrSent.this,Callpage.class);
			startActivity(intent2);
		}
		});
		}
		
	}



=======
	}


}
>>>>>>> 236054a6a4a0979c5f61445d61e749266e886a2d









