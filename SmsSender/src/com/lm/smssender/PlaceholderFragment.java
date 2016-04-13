package com.lm.smssender;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lm.smssender.SmsService;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements OnClickListener {
	private static final String TAG = "Fragment";
	NoticeReceiver noticeReceiver;

	Button buttonStart, buttonStop, buttonTest;
	TextView ipAddress;
	TextView logView;
	EditText editPhoneNumber;

	public PlaceholderFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);

		buttonStart = (Button) rootView.findViewById(R.id.buttonStart);
		buttonStop = (Button) rootView.findViewById(R.id.buttonStop);
		buttonTest = (Button) rootView.findViewById(R.id.buttonTest);
		ipAddress = (TextView) rootView.findViewById(R.id.labelIPAddress);
		logView = (TextView) rootView.findViewById(R.id.logView);
		logView.setMovementMethod(ScrollingMovementMethod.getInstance());
		editPhoneNumber = (EditText) rootView.findViewById(R.id.editPhoneNumber);

		buttonStart.setOnClickListener(this);
		buttonStop.setOnClickListener(this);
		buttonTest.setOnClickListener(this);

		noticeReceiver = new NoticeReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.lm.smssender.SmsService");
		getActivity().registerReceiver(noticeReceiver, filter);

		return rootView;
	}

	public void onDestroyView() {
		getActivity().unregisterReceiver(noticeReceiver);
		super.onDestroyView();
	}

	@Override
	public void onClick(View src) {
		switch (src.getId()) {
		case R.id.buttonStart:
			Log.i(TAG, "onClick: starting service");
			getActivity().startService(
					new Intent(getActivity(), SmsService.class));
			break;
		case R.id.buttonStop:
			Log.i(TAG, "onClick: stopping service");
			getActivity().stopService(
					new Intent(getActivity(), SmsService.class));
			break;
		case R.id.buttonTest:
			Log.i(TAG, "onClick: test sms");
			String phoneNumber = editPhoneNumber.getText().toString();
			broadCastTestSMS(phoneNumber, "Test Message");
			break;
		}

	}

	
	public void broadCastTestSMS(String phoneNumber, String content) {
		// ·¢ËÍ¹ã²¥
		Intent intent = new Intent();
		intent.putExtra("testSMS", "testSMS");
		intent.putExtra("phoneNumber", phoneNumber);
		intent.putExtra("content", content);
		intent.setAction("com.lm.smssender.PlaceholderFragment");
		getActivity().sendBroadcast(intent);
	}

	public class NoticeReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			
			if (bundle.getString("ipAddress") != null) {
				ipAddress.setText(bundle.getString("ipAddress"));
			}
			if (bundle.getString("log") != null) {
				logView.setText(bundle.getString("log") + logView.getText());
			}
		}
	}
}
