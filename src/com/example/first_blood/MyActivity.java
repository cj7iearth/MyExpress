package com.example.first_blood;

import java.lang.reflect.Method;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		
	    //ͨ�������ȡ�����ļ��еİ�ť
		Button button=(Button)findViewById(R.id.button1);
		//���������¼��ļ���
		button.setOnClickListener(new Button.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				Uri uri=Uri.parse("tel:13041131918");//���ò����ĺ���
				Intent intent2=new Intent(Intent.ACTION_CALL,uri);
				startActivity(intent2);//ҳ����ת��ʵ�ֲ���绰
				Intent intent=new Intent(MyActivity.this,Activity_my2.class);// TODO Auto-generated method stub
				startActivity(intent) ;//��ʼҳ���벦��ҳ����ҳ����ת
				
				//Intent intent3=new Intent(MyActivity.this,ThreadCleanCallogActivity.class);
				//startActivity(intent3) ;
				/*String mobile = uri.toString();
				String str = "";
				for (int i = 0; i < mobile.length(); i++) {
					str+="*";
				}
				System.out.println("�绰����:"+str);*/
				
			    //Intent intent3=new Intent(Intent.ACTION_DIAL,uri);//���������̽���
			    
			    //startActivity(intent3);//���ò���绰����
				
			}
			
			
		});//���õ���¼��ļ���
	}//��ʱû��

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

