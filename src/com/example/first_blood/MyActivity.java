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
		
	    //通过反射获取布局文件中的按钮
		Button button=(Button)findViewById(R.id.button1);
		//设置碰触事件的监听
		button.setOnClickListener(new Button.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				Uri uri=Uri.parse("tel:13041131918");//设置拨出的号码
				Intent intent2=new Intent(Intent.ACTION_CALL,uri);
				startActivity(intent2);//页面跳转后实现拨打电话
				Intent intent=new Intent(MyActivity.this,Activity_my2.class);// TODO Auto-generated method stub
				startActivity(intent) ;//初始页面与拨号页面间的页面跳转
				
				//Intent intent3=new Intent(MyActivity.this,ThreadCleanCallogActivity.class);
				//startActivity(intent3) ;
				/*String mobile = uri.toString();
				String str = "";
				for (int i = 0; i < mobile.length(); i++) {
					str+="*";
				}
				System.out.println("电话号码:"+str);*/
				
			    //Intent intent3=new Intent(Intent.ACTION_DIAL,uri);//跳至拨号盘界面
			    
			    //startActivity(intent3);//调用拨打电话界面
				
			}
			
			
		});//设置点击事件的监听
	}//暂时没用

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

