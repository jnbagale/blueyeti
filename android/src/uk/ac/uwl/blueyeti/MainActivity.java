package uk.ac.uwl.blueyeti;

import uk.ac.uwl.blueyeti.service.IServiceInterface;
import uk.ac.uwl.blueyeti.service.Information;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int REQUEST_ENABLE_BT = 0;
	/** Called when the activity is first created. */
	
	IServiceInterface bluetoothService;
	TextView infoTitle;
	TextView infoDescription;
	SharedPreferences mSettings;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        
        if(ba == null){
        	//gracefully fail.
        	AlertDialog alertDialog = new AlertDialog.Builder(this).create();  
            alertDialog.setTitle("No Bluetooth");  
            alertDialog.setMessage("This app requires bluetooth");  
           alertDialog.setButton("OK", new DialogInterface.OnClickListener() {  
              public void onClick(DialogInterface dialog, int which) {  
                MainActivity.this.finish(); 
            } });
        }
        
       // if (!ba.isEnabled()){
        	
      //  	Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      //  }
        Log.v("app", "bluetooth enabled");
        
        if(mSettings.getBoolean("service_running", false))
       // bindService(new Intent(IServiceInterface.class.getName()), mainConnection, Context.BIND_AUTO_CREATE);
        Log.v("app", "setcontentview"); 
        setContentView(R.layout.main);
        	
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return super.onCreateOptionsMenu(menu);
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
	    case R.id.main_start_service:
	    	bindService(new Intent(IServiceInterface.class.getName()), setupConnection, Context.BIND_AUTO_CREATE);
	        break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    private ServiceConnection setupConnection = new ServiceConnection(){
    	public void onServiceConnected(ComponentName className, IBinder service){
    		try{
    		Log.v("app", "Service running");
    		bluetoothService = IServiceInterface.Stub.asInterface(service);
    		Log.v("app", "Service running");
    		bluetoothService.startService();
    		Log.v("app", "Service running");
    		//SharedPreferences.Editor editor = mSettings.edit();
    		//editor.putBoolean("service_running", true);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	public void onServiceDisconnected(ComponentName name){
    		
    	}
    };
    
	private ServiceConnection mainConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			try {
				bluetoothService = IServiceInterface.Stub.asInterface(service);
				Information info = bluetoothService.getLastInfo();
				MainActivity.this.displayInfo(info);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		public void onServiceDisconnected(ComponentName name) {
			bluetoothService = null;
			
		}
	};
	protected void displayInfo(Information info) {
		infoTitle = (TextView) findViewById(R.id.lblCurrentInfo);
		infoDescription = (TextView) findViewById(R.id.txtInfo);
		
		infoTitle.setText(info.getTitle());
		infoDescription.setText(info.getDescription());
	}
}