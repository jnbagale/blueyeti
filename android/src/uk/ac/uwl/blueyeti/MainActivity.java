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
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

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
    
    protected void runXML(String path) {
		try{
			File file = new File(Environment.getExternalStorageDirectory() + path);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			
			Parser p = new Parser();
			xr.setContentHandler(p);
			
			xr.parse(new InputSource(new InputStreamReader(new FileInputStream(file))));
		} catch(MalformedURLException e){
			e.printStackTrace();
		} catch(ParserConfigurationException e){
			e.printStackTrace();
		} catch(SAXException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}