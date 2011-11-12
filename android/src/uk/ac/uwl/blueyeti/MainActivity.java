package uk.ac.uwl.blueyeti;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    private static final int REQUEST_ENABLE_BT = 0;
	/** Called when the activity is first created. */
	
	private BluetoothHelper bluetoothHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
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
        
        if (!ba.isEnabled()){
        	
        	Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        	startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT);
        }
        
        bluetoothHelper.bluetooth = ba;
        	
    }
}