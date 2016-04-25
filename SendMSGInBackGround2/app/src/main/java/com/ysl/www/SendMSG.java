package com.ysl.www;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class SendMSG{
	private Context context;
	public SendMSG(Context context) {
		this.context = context;
	}

	final public static String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
	final public static String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
	

	public void sendMSG(String phoneNum, String content) {
		SmsManager smsManager = SmsManager.getDefault();
		Intent itSend = new Intent(SMS_SEND_ACTIOIN);
		Intent itDeliver = new Intent(SMS_DELIVERED_ACTION);

		PendingIntent mSendPI = PendingIntent.getBroadcast(context, 0, itSend,
				0);

		PendingIntent mDeliverPI = PendingIntent.getBroadcast(context, 0,
				itDeliver, 0);

		smsManager
				.sendTextMessage(phoneNum, null, content, mSendPI, mDeliverPI);
	}
}

 class YSLServiceReceiver extends BroadcastReceiver {
	private SendResult result;
	public YSLServiceReceiver(SendResult result) {
		this.result = result;
	}

	public void onReceive(Context context, Intent intent) {
		switch (getResultCode()) {
		case Activity.RESULT_OK:// 发动成功
			result.isSendSuccess(true);
			break;
		case SmsManager.RESULT_ERROR_GENERIC_FAILURE:// 发送失败
			result.isSendSuccess(false);
			break;
		case SmsManager.RESULT_ERROR_RADIO_OFF:
			result.isSendSuccess(false);
			break;
		case SmsManager.RESULT_ERROR_NULL_PDU:
			result.isSendSuccess(false);
			break;
		}
	}
}

interface SendResult {
	void isSendSuccess(boolean isOK);
}
