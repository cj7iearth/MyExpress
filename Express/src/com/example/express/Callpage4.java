package com.example.express;

import android.app.Activity;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Callpage4 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callpage4);
	}
		

	public void button(View v){//按下返回，删除通话记录
		getContentResolver().delete(CallLog.Calls.CONTENT_URI, "number=?", new String[] {"13041131918"});  
	}
	public void showTopWindow2(View view2){
		WindowManager windowmanager2 =(WindowManager) getApplicationContext()
				.getSystemService(WINDOW_SERVICE);
		WindowManager.LayoutParams params2 = new WindowManager.LayoutParams();
		params2.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
				| WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
		
		params2.height=WindowManager.LayoutParams.FILL_PARENT;
		params2.width = WindowManager.LayoutParams.FILL_PARENT;
		params2.x = 0;
		params2.y = 0;
		// topWindow显示到最顶部
		windowmanager2.addView(view2, params2);
	}
	
	/**
	 * 
	 * @Method: clearTopWindow
	 * @Description: 移除最顶层view
	 */
	public void clearTopWindow2(View view2) {
		if (view2 != null && view2.isShown()) {
			WindowManager windowManager = (WindowManager) getApplicationContext()
					.getSystemService(WINDOW_SERVICE);
			windowManager.removeView(view2);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.callpage4, menu);
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
