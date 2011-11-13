package uk.ac.uwl.blueyeti;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import uk.ac.uwl.blueyeti.parser.Parser;
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
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int REQUEST_ENABLE_BT = 0;
	/** Called when the activity is first created. */
	
	private BluetoothHelper bluetoothHelper;
	IServiceInterface bluetoothService;
	TextView infoTitle;
	TextView infoDescription;
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
        bindService(new Intent(IServiceInterface.class.getName()), mainConnection, Context.BIND_AUTO_CREATE);
        	
    }
    
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