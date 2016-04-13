package com.example.first_blood;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.app.Activity;  
import android.app.AlertDialog;  
import android.content.ContentResolver;  
import android.content.DialogInterface;  
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Message;  
import android.provider.CallLog;  
import android.util.Log;  
import android.view.Menu;  
import android.view.MenuItem;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Toast;  

public class ThreadCleanCallogActivity extends Activity implements  
	OnClickListener {  
private boolean isExits = false;  
private boolean isGoOn = true;  
private Handler handler = new Handler() {  
public void handleMessage(Message msg) {  
    super.handleMessage(msg);  
    cleanCallLog();  
}  

};  
//<span style="white-space:pre">  </span>//其实就核心方法就这个方法中的几行代码。  

private void cleanCallLog() {  
	ContentResolver resolver = getContentResolver();  
	resolver.delete(CallLog.Calls.CONTENT_URI, null, null);  
}  

protected void onCreate(Bundle savedInstanceState) {  
	super.onCreate(savedInstanceState);  
	setTitle("清除通话记录!");  
	// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  
	setContentView(R.layout.main);  
	init();  
}  

public boolean onCreateOptionsMenu(Menu menu) {  
	getMenuInflater().inflate(R.menu.thread_clean_callog, menu);  
	return true;  
}  

public boolean onOptionsItemSelected(MenuItem item) {  
	switch (item.getItemId()) {  
	case R.id.menu_about:  
    Log.e("Other", "you click the about item!");  
    AlertDialog.Builder builder = new AlertDialog.Builder(this);  
    builder.setTitle(R.string.app_name)  
            .setMessage("本程序由流浪天堂开发                               联系作者:sanbo.xyz@gmail.com")  
            .setCancelable(true)  
            .setPositiveButton("确定",  
                    new DialogInterface.OnClickListener() {  
                        public void onClick(DialogInterface dialog,  
                                int id) {  
                            dialog.cancel();  
                        }  
                    });  
    AlertDialog alert = builder.create();  
    alert.show();  
    break;  

	case R.id.menu_exit:  
    Log.e("Other", "you  click exit item!");  
    finish();  
    break;  

	default:  
    break;  
}  
	return false;  
}  

private void init() {  
	this.findViewById(R.id.btnThread).setOnClickListener(this);  
}  

public void onClick(View v) {  
	if (v.getId() == R.id.btnThread) {  
		if (!isExits) {  
			isExits = true;  
			new MyThread().start();  
			Toast.makeText(getApplicationContext(), "删除完成!", 0).show();  
    } else {  
        Toast.makeText(getApplicationContext(), "删除中，请等待!", 0).show();  
    }  
}  

}  

class MyThread extends Thread {  

public void run() {  
    super.run();  
    while (isGoOn) {  
        try {  
            sleep(30);  
            Message msg = new Message();  
            handler.sendMessage(msg);  
            isGoOn = false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

}  
}  

} 