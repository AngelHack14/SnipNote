package com.example.snipnote2;

<<<<<<< HEAD
=======
import android.content.Intent;
import android.net.Uri;
>>>>>>> branch 'master' of https://github.com/AngelHack14/SnipNote.git
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
=======
import android.support.v7.app.ActionBarActivity;
>>>>>>> branch 'master' of https://github.com/AngelHack14/SnipNote.git
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
import android.view.ViewGroup;
=======
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
>>>>>>> branch 'master' of https://github.com/AngelHack14/SnipNote.git

public class MainActivity extends ActionBarActivity {

	private Button contactsButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
	    setContentView(R.layout.activity_main);
	    
	    contactsButton = (Button)findViewById(R.id.contactsButton);
	    
	    contactsButton.setOnClickListener(new OnClickListener(){
	    	public void onClick(View V){
	    		Uri uri = Uri.parse("tel:3393689972");
	        	Intent i = new Intent(Intent.ACTION_CALL,uri);
	            startActivity(i); 
	    	}
	    });
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
