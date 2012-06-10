package BusDB.TraviJuu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 

import BusDB.TraviJuu.AndroidList.MyCustomAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class buslist  extends Activity implements Runnable{
	
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.buslist);
	        Thread thread = new Thread(this);
	        thread.start();
	        
	    }
	 	
	 public ArrayList<String> getBusList()
	 {
		 String result = "";
		 HttpClient client = new DefaultHttpClient();
	     HttpPost post = new HttpPost("http://erkincakar.com/getBus.php?status=getBusList");
	     ArrayList<String> busTypeList = new ArrayList<String>();
	     
        try {
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
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//status.setText(e.getLocalizedMessage());
			e.printStackTrace();
		}
        
        JSONArray jArray;
		try {
			jArray = new JSONArray(result);
			 JSONObject json_data=null;
		        for(int i=0;i<jArray.length();i++){
		               json_data = jArray.getJSONObject(i);
		               busTypeList.add(json_data.getString("Otobus_plaka")+" - " + json_data.getString("Otobus_model" ) + "\n<" + json_data.getString("Otobus_Firma")+">");
		        }
		       
		} catch (JSONException e) {
			//status.setText(e.getMessage());
		}
		
		 return busTypeList;
	       
	 }
	  public void callIntent(View view)
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
	    		break;
	    	}
	    	
	    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			 Message msg2 = new Message();
	         msg2.obj = "Yükleniyor..";
	         threadHandler.sendMessageAtFrontOfQueue(msg2);
	 		
		findViewById(R.id.button2).setClickable(false);
        setTitle("MY BUS LIST");
        ArrayList<String> busList = new ArrayList<String>();
        busList = getBusList();
         Message msg = new Message();
         msg.obj = busList;
         msg.arg1 = 1;
         threadHandler.sendMessage(msg);

            Database db = new Database(buslist.this);
            db.openDB();
           // db.deleteAll();
            /*Message msg = new Message();
            msg.obj = busList.addAll(db.getBusList());
            msg.arg1 = 1;
            threadHandler.sendMessage(msg);   */
            db.closeDB();

		}
		catch(Exception e)
		{
			 Message msg = new Message();
	         msg.obj ="Hata!" + e.toString();
	         threadHandler.sendMessage(msg);


		}
         
		
	} 
	
	
	
	
	private Handler threadHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.arg1 == 1)
			{
			ListView Lview=(ListView)findViewById(R.id.listView1);      
	        
	        ArrayList<String> busList = new ArrayList<String>();
	        busList = (ArrayList<String>)msg.obj;
	        Lview.setAdapter(new ArrayAdapter<String>(buslist.this,android.R.layout.simple_list_item_1 , busList));
	        Lview.setTextFilterEnabled(true);
	        String[] a = null;
	        
	             
	        //setListAdapter(new MyCustomAdapter(AndroidList.this, R.layout.row, null,buslist));


	        
	        
	        
	        

	        Lview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					//setContentView(R.layout.busprofile);
					Intent i = new Intent(buslist.this,busprofile.class);
					String[] a =  ((TextView)arg1).getText().toString().split("-");
					i.putExtra("plaka",a[0].trim());
					startActivity(i);
				}
			});
		}
			else
				Toast.makeText(buslist.this, msg.obj.toString(), 1).show();
			
		}
	};
}
