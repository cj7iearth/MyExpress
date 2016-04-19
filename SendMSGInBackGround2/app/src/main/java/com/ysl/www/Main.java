package com.ysl.www;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	private Button mButton1;
	private TextView mTextView01;
	private EditText mEditText1, mEditText2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mTextView01 = (TextView) findViewById(R.id.myTextView1);
		mEditText1 = (EditText) findViewById(R.id.myEditText1);// phoneNum

		mEditText2 = (EditText) findViewById(R.id.myEditText2);// msg context
		mButton1 = (Button) findViewById(R.id.myButton1);

		mEditText1.setText("");
		mEditText2.setText("您好，您的快递已经到达。请您在方便时下楼来取，谢谢！短信可直接回复。");

		mButton1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String phoneNum = mEditText1.getText().toString();
				String content = mEditText2.getText().toString();
				SendMSG sendMsg = new SendMSG(Main.this);
				sendMsg.sendMSG(phoneNum, content);
			}
		});
	}

	private YSLServiceReceiver mReceiver01, mReceiver02;

	protected void onResume() {
		SendResult result = new SendResult() {
			public void isSendSuccess(boolean isOK) {
				Toast.makeText(Main.this, "信息发送状态:"+isOK, Toast.LENGTH_LONG).show();
			}
		};
		IntentFilter mFilter01;
		mFilter01 = new IntentFilter(SendMSG.SMS_SEND_ACTIOIN);
		mReceiver01 = new YSLServiceReceiver(result);
		registerReceiver(mReceiver01, mFilter01);

		mFilter01 = new IntentFilter(SendMSG.SMS_DELIVERED_ACTION);
		mReceiver02 = new YSLServiceReceiver(result);
		registerReceiver(mReceiver02, mFilter01);
		super.onResume();
	}

	@Override
	protected void onPause() {
		unregisterReceiver(mReceiver01);
		unregisterReceiver(mReceiver02);
		super.onPause();
	}
}