package com.example.express;

import com.example.express.Callpage2.TeleListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class Callpage2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callpage2);
		// 获取电话管理的一个类实例
					TelephonyManager mTelephonyMgr = (TelephonyManager) this
							.getSystemService(Context.TELEPHONY_SERVICE);
					// Registers a listener object to receive notification of changes in
					// specified telephony states
					// 建立一个监听器来实时监听电话的通话状态
					mTelephonyMgr.listen(new TeleListener(),
							PhoneStateListener.LISTEN_CALL_STATE);
					//view = new TextView(this);
					//view.setText("listen the state of phone/n");
					//setContentView(view);
	}
	
	class TeleListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			// 当处于拨号状态中
			case TelephonyManager.CALL_STATE_RINGING: {// 跳转至activity_3
				Intent intent2 = new Intent(Callpage2.this,
						Callpage4.class);
				startActivity(intent2);
				break;
			}
			default:
				break;

			}

		}
	}
	
	
	public void btnOnclick(View view) {
		
	    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13041131918"));
        startActivity(intent);
	}

	/**
	 * 
	 * @Method: showTopWindow
	 * @Description: 显示最顶层view
	 */
	public void showTopWindow(View view) {
		//window管理器
		WindowManager windowManager = (WindowManager) getApplicationContext()
				.getSystemService(WINDOW_SERVICE);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
				| WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
//		params.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		//设置全屏显示  可以根据自己需要设置大小
		params.width = WindowManager.LayoutParams.FILL_PARENT;
		params.height = WindowManager.LayoutParams.FILL_PARENT;
		//设置显示初始位置 屏幕左上角为原点
		params.x = 0;
		params.y = 0;
		// topWindow显示到最顶部
		windowManager.addView(view, params);
	}

	/**
	 * 
	 * @Method: clearTopWindow
	 * @Description: 移除最顶层view
	 */
	public void clearTopWindow(View view) {
		if (view != null && view.isShown()) {
			WindowManager windowManager = (WindowManager) getApplicationContext()
					.getSystemService(WINDOW_SERVICE);
			windowManager.removeView(view);
		}
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.callpage2, menu);
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
