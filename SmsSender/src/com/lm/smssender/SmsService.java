package com.lm.smssender;

import java.util.List;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SmsService extends Service {
	private static final String TAG = "Service";
	private SmsServiceBinder binder = new SmsServiceBinder();
	CommandReceiver commandReceiver = null;

	UdpThread updThread = null;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "My Service created", Toast.LENGTH_SHORT).show();
		Log.i(TAG, "onCreate");
		System.out.println("service created");

		updThread = new UdpThread(this);
	}

	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "My Service Start", Toast.LENGTH_SHORT).show();
		Log.i(TAG, "onStart:" + startid);

		if (commandReceiver == null) {
			commandReceiver = new CommandReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction("com.lm.smssender.PlaceholderFragment");
			registerReceiver(commandReceiver, filter);
			registerReceiver(sendMessageOk, new IntentFilter("SENT_SMS_ACTION"));
			registerReceiver(receiverReceiveMessageOk, new IntentFilter(
					"DELIVERED_SMS_ACTION"));
		}
		updThread.start();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "My Service Stoped", Toast.LENGTH_SHORT).show();
		Log.i(TAG, "onDestroy");
		updThread.stop();

		unregisterReceiver(commandReceiver);
		commandReceiver = null;

		// super.onDestroy();
	}

	public void displayNotificationMessage(String message) {
		Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://www.sina.com.cn"));
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.sina);
		builder.setContentIntent(pendingIntent);
		builder.setAutoCancel(true);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher));
		builder.setContentTitle("SMSSender");
		builder.setContentText("text " + message);
		builder.setSubText("subText " + message);

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(R.id.app_notification_id, builder.build());
	}

	public void broadCast(String id, String content) {
		// 发送广播
		Intent intent = new Intent();
		intent.putExtra(id, content);
		intent.setAction("com.lm.smssender.SmsService");
		sendBroadcast(intent);
	}

	public boolean sendSMS(String phoneNumber, String content, String sequence) {
		boolean result = false;

		SmsManager smsManager = SmsManager.getDefault();

		// create the sentIntent parameter
		Intent sentIntent = new Intent("SENT_SMS_ACTION");
		sentIntent.putExtra("sequence", sequence);
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, sentIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		// create the deilverIntent parameter
		Intent deliverIntent = new Intent("DELIVERED_SMS_ACTION");
		deliverIntent.putExtra("sequence", sequence);
		PendingIntent deliverPI = PendingIntent.getBroadcast(this, 0,
				deliverIntent, 0);

		if (content.length() > 70) {
			List<String> contents = smsManager.divideMessage(content);
			for (String sms : contents) {
				smsManager.sendTextMessage(phoneNumber, null, sms, sentPI,
						deliverPI);
			}
		} else {
			smsManager.sendTextMessage(phoneNumber, null, content, sentPI,
					deliverPI);
		}

		/** 将发送的短信插入数据库 **/
		ContentValues values = new ContentValues();
		// 发送时间
		values.put("date", System.currentTimeMillis());
		// 阅读状态
		values.put("read", 0);
		// 1为收 2为发
		values.put("type", 2);
		// 送达号码
		values.put("address", phoneNumber);
		// 送达内容
		values.put("body", content);
		// 插入短信库
		getContentResolver().insert(Uri.parse("content://sms"), values);

		return result;
	}

	class SmsServiceBinder extends Binder {

		SmsService getService() {
			// Return this instance of LocalService so clients can call public
			// methods
			return SmsService.this;
		}

		void doLog(String logContent) {
			Log.d(TAG, "SMSServiceBinder doLog:" + logContent);
		}
	}

	public class CommandReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			String phoneNumber;
			String content;
			if (bundle.getString("testSMS") != null) {
				Log.i(TAG, "Send Test Message");
				phoneNumber = bundle.getString("phoneNumber");
				content = bundle.getString("content");
				// sendSMS(phoneNumber, content, "0");
				updThread.sendData("127.0.0.1",
								50000,
								"phonenumber===" + phoneNumber + ";;;content===" + content + ";;;sequence===0");
			}
		}
	}

	private BroadcastReceiver sendMessageOk = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String sequence = intent.getExtras().getString("sequence");

			// 判断短信是否发送成功
			switch (getResultCode()) {
			case Activity.RESULT_OK:
				// Toast.makeText(context, "短信发送成功", Toast.LENGTH_SHORT).show();
				updThread.sendData(" SMS Send Ok. " + sequence);
				broadCast("log", "\nSMS Send Ok. " + sequence);
				Log.i(TAG, "SMS Send Ok. " + sequence);
				break;
			default:
				broadCast("log", "\nSMS Send Failure. " + sequence);
				Toast.makeText(context, "发送失败", Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	private BroadcastReceiver receiverReceiveMessageOk = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String sequence = intent.getExtras().getString("sequence");
			// 表示对方成功收到短信
			// broadCast("log", "\nSent SMS Received Ok. " + sequence);
			// Toast.makeText(context, "对方接收成功", Toast.LENGTH_SHORT).show();
		}
	};
}