package com.example.snipnote2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private Button contactsButton,callSummaryButton, TSButton;
	
	private static final int REQUEST_CODE = 0;
    private DevicePolicyManager mDPM;
    private ComponentName mAdminName;
    private DateFormat DF[]= new DateFormat[20];
    private int pos = -1;    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
	    setContentView(R.layout.activity_main);    
	    
	    
	    
	    contactsButton = (Button)findViewById(R.id.contactsButton);
	    //callSummaryButton = (Button)findViewById(R.id.callSummaryButton);
	    TSButton = (Button)findViewById(R.id.TSButton);
	    
	    contactsButton.setOnClickListener(new OnClickListener(){
	    	public void onClick(View V){
	    		Uri uri = Uri.parse("tel:8139973626");
	        	Intent i = new Intent(Intent.ACTION_CALL,uri);
	            startActivity(i); 
	    	}
	    });
	    
	    
	    TSButton.setOnClickListener(new OnClickListener(){
	    	public void onClick(View V){
	    		try{
	    			DateFormat df = DateFormat.getTimeInstance();
	    			DF[++pos]=df;
	    			Calendar c = Calendar.getInstance();
	    		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
	    		    String formatted = sdf.format(c.getTime());
	    			
	    			//String ts1 = TS[pos]+"";
		    		//Log.i("Logs","value is:"+ts1);
		    		Toast.makeText(getApplicationContext(), "Timestamp Recorded at: "+formatted, Toast.LENGTH_LONG).show();
	    		} catch(Exception e){
	    			 e.printStackTrace();
	    	         Log.i("Logs", "Timestamp error");
	    		}
	    
	    	}
	    });
	    
	    
	    
	    try {
            // Initiate DevicePolicyManager.
            mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            mAdminName = new ComponentName(this, DeviceAdminDemo.class);
            mDPM.removeActiveAdmin(mAdminName);
            
            if (!mDPM.isAdminActive(mAdminName)) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Click on Activate button to secure your application.");
                startActivityForResult(intent, REQUEST_CODE);
                Log.i("Logs","working");
            } else {
                 //mDPM.lockNow();
            	Log.i("Logs", "Admin is Active");
                 Intent intent = new Intent(MainActivity.this,
                		 TService.class);
                 startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Logs", "Exception in Device Admin Found");
        } 
	
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

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE == requestCode) {
        		Log.i("Logs", "Tservice has started");
                Intent intent = new Intent(MainActivity.this, TService.class);
                startService(intent);
               
        }
    }
	@Override
	protected void onResume(){
		super.onResume();
		
	}
	
}
