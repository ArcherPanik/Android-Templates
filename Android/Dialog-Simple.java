package com.example.webviw;

import android.app.Activity;
import android.app.AlertDialog...

public class MainActivity extends Activity implements OnClickListener{
	Button boton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		boton = (Button) findViewById(R.id.boton);
		boton.setOnClickListener(this);
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder = builder.setIcon(R.drawable.ic_launcher);
		builder = builder.setTitle("Este es el primer dialog");
		dialog = builder.create();
		
		return dialog;
	}
	
	@Overrride
	public void onClick(View v) {
		showDialog(0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemID();
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
