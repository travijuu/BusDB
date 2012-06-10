package BusDB.TraviJuu;
 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;



public class BusDBActivity extends Activity implements Runnable {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       Thread thread = new Thread(this);
       thread.start();
    }
    
    
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	    super.onCreateOptionsMenu(menu);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mainmenu, menu);

	    	return true;
	    }

	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	        switch (item.getItemId())
	        {
	            case R.id.item1:
	            		finish();
	                    startActivity(new Intent(this, newbus.class));
	                    break;
	            case R.id.item2:
	            	//startActivity(new Intent(this,buslist.class));
	            	
	            		return true;
	            case R.id.localdb:
	            	
	            		new AlertDialog.Builder(this).setTitle("Otobüsler").setCancelable(true).setIcon(R.drawable.icon).setNegativeButton(R.string.app_name, new OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub

							}
						}).setMultiChoiceItems(R.array.buslist,null, new DialogInterface.OnMultiChoiceClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which, boolean isChecked) {
								// TODO Auto-generated method stub
								
							}
						}).show();
	                    return true;
	        }
	      return false;
	    }


    
    public void callIntent(View view) throws Exception
    {
    	switch(view.getId())
    	{
    	case R.id.button1:
    		// intent = new Intent("android.media.action.IMAGE_CAPTURE");
    		// startActivityForResult(intent, 0);
    			//Intent intent = new Intent(view.getContext(),newbus.class);
    		finish();
    			startActivity(new Intent(view.getContext(),newbus.class));
    			
    			
    			break;
    	case R.id.button2: 
    	//	intent = new Intent(Intent.ACTION_VIEW,	Uri.parse("http://www.facebook.com"));
    		//Intent intent = new Intent(view.getContext(),buslist.class);
    		finish();
			startActivity(new Intent(view.getContext(),buslist.class));
    		break;
    	case R.id.button3: 
    		//intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/1"));
			//startActivity(intent);
    		finish();
			startActivity(new Intent(view.getContext(),searchbus.class));
    		break;
    	case R.id.button4:
    		//intent = new Intent();
            //setResult(RESULT_OK, intent);
    		//stopService(intent);
    		finish();
    		
    		//startActivity(new Intent(view.getContext(),busprofile.class));
    		break;
    	}
    	
    }


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
        String result = "";
        HttpClient client = new DefaultHttpClient();
	    HttpPost post = new HttpPost("http://erkincakar.com/getBus.php?status=stats");

       try {
    	    Thread.sleep(1000);
	        Message msg = new Message();
			msg.obj = "Yükleniyor..";
			threadHandler.sendMessage(msg);
		
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null)
	                sb.append(line + "\n");
	        is.close();
	        result=sb.toString();
	        Message msg2 = new Message();
			msg2.obj = result;
			msg2.arg1 =1 ;
			threadHandler.sendMessage(msg2);
			
		} catch (Exception e) {
			Message msg = new Message();
			msg.obj = "Hata!";
			threadHandler.sendMessage(msg);
		}
	}
	private Handler threadHandler = new Handler()
	{
		public void handleMessage(Message os)
		{
			
			if(os.arg1== 1)
			{
	        	String[] strArr = os.obj.toString().split(";");
	        	
		        TextView txtView = (TextView)findViewById(R.id.totalRecord);
		        txtView.setText( strArr[0].trim());
		        

		        txtView = (TextView)findViewById(R.id.totalCompany);
		        txtView.setText( strArr[1].trim());
		        

		        txtView = (TextView)findViewById(R.id.onlyKamilKoc);
		        txtView.setText( strArr[2]);
		        
		        txtView = (TextView)findViewById(R.id.lastRecord);
		        txtView.setText( strArr[3]);
		    	
			}
			else
				Toast.makeText(BusDBActivity.this, os.obj.toString(), 1000).show();
		}
		
	};
    
}