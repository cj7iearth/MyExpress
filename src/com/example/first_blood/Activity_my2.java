package com.example.first_blood;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class Activity_my2 extends Activity {
	private LayoutInflater inflater;
	private View topWindow;// ��һ��topwindow
	private View topWindow2;// �ڶ���topWindow
	private Button button01;// window01�İ�ť
	private Button backButton01;// window01�ķ��ذ�ť
	private Button backButton02;// window02�ķ��ذ�ť

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findById();
		button01.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("onclick!!");
				showTopWindow(topWindow2);
			}

			//@Override
			//public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			//}
		});
		backButton01.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearTopWindow(topWindow);
			}

			//@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		backButton02.setOnClickListener(new View.OnClickListener() {
			
		

			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearTopWindow(topWindow2);
			}

			
		});
	}

	/**
	 * 
	 * @Method: findById
	 * @Description: ��ʼ��
	 */
	public void findById() {
		inflater = getLayoutInflater().from(Activity_my2.this);
		topWindow = inflater.inflate(R.layout.window01, null);
		topWindow2 = inflater.inflate(R.layout.window02, null);
		button01 = (Button) topWindow.findViewById(R.id.button01);
		backButton01 = (Button) topWindow.findViewById(R.id.backBtn01);
		backButton02 = (Button) topWindow2.findViewById(R.id.backBtn02);
	}

	/**
	 * 
	 * @Method: btnOnclick
	 * @Description: ��ť����¼�
	 */
	public void btnOnclick(View view) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
		startActivity(intent);
		showTopWindow(topWindow);
	}

	/**
	 * 
	 * @Method: showTopWindow
	 * @Description: ��ʾ���view
	 */
	public void showTopWindow(View view) {
		//window������
		WindowManager windowManager = (WindowManager) getApplicationContext()
				.getSystemService(WINDOW_SERVICE);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
				| WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
//		params.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		//����ȫ����ʾ  ���Ը����Լ���Ҫ���ô�С
		params.width = WindowManager.LayoutParams.FILL_PARENT;
		params.height = WindowManager.LayoutParams.FILL_PARENT;
		//������ʾ��ʼλ�� ��Ļ���Ͻ�Ϊԭ��
		params.x = 0;
		params.y = 0;
		// topWindow��ʾ�����
		windowManager.addView(view, params);
	}

	/**
	 * 
	 * @Method: clearTopWindow
	 * @Description: �Ƴ����view
	 */
	public void clearTopWindow(View view) {
		if (view != null && view.isShown()) {
			WindowManager windowManager = (WindowManager) getApplicationContext()
					.getSystemService(WINDOW_SERVICE);
			windowManager.removeView(view);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			clearTopWindow(topWindow);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
   
    



