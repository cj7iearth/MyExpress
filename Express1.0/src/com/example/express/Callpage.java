package com.example.express;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Callpage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callpage);
	
	//通过反射获取布局文件中的按钮
			Button button=(Button)findViewById(R.id.btn2);
			//设置碰触事件的监听
			button.setOnClickListener(new Button.OnClickListener(){
				
				@Override
				public void onClick(View v) {
					
					Intent intent=new Intent(Callpage.this,Callpage2.class);// TODO Auto-generated method stub
					startActivity(intent) ;//初始页面与拨号页面间的页面跳转
				    
				    //startActivity(intent3);//调用拨打电话界面
					
				}
				
				
			});//设置点击事件的监听
		}//暂时没用

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.callpage, menu);
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
