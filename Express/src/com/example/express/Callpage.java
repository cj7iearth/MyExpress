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
	
	//ͨ�������ȡ�����ļ��еİ�ť
			Button button=(Button)findViewById(R.id.btn2);
			//���������¼��ļ���
			button.setOnClickListener(new Button.OnClickListener(){
				
				@Override
				public void onClick(View v) {
					
					Intent intent=new Intent(Callpage.this,Callpage2.class);// TODO Auto-generated method stub
					startActivity(intent) ;//��ʼҳ���벦��ҳ����ҳ����ת
				    
				    //startActivity(intent3);//���ò���绰����
					
				}
				
				
			});//���õ���¼��ļ���
		}//��ʱû��

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
