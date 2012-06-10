package BusDB.TraviJuu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;



public class searchbus extends Activity {
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.searchbus);
	        setTitle("SEARCH BUS");
	        
	  
	  }
	  
	  public void searchBusButtonHandler(View v)
	  {
		  String search = ((EditText) findViewById(R.id.searchbox)).getText().toString();
		  TextView tView=(TextView)findViewById(R.id.statusView);
		  tView.setText(search);
		  
		  ArrayList<String> searchList = new ArrayList<String>();
		  searchList = searchBus(search);
	  
          final  ListView Lview=(ListView)findViewById(R.id.listViewSearch);
        
          tView.setText("Total Record: " +searchList.size());
          Lview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , searchList));
          Lview.setTextFilterEnabled(true);
          Lview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.busprofile);
				Intent i = new Intent(searchbus.this,busprofile.class);
				String[] a =  ((TextView)arg1).getText().toString().split("-");
				i.putExtra("plaka",a[0].trim());
				startActivity(i);
			}  
		}); 
    			
    			
    		
          
	  }
	  
	  
	  public ArrayList<String> searchBus(String query)
		 {
			 String result = "";
			 HttpClient client = new DefaultHttpClient();
			 query = query.replaceAll(" ", "%20");
		     //HttpPost post = new HttpPost("http://192.168.2.5/getBus.php?status=searchBus&query="+query);
		     HttpGet get = new HttpGet("http://erkincakar.com/getBus.php?status=searchBus&query="+query);
		     ArrayList<String> busTypeList = new ArrayList<String>();
		     
	        try {
				HttpResponse response = client.execute(get);
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
	        
	        TextView tView=(TextView)findViewById(R.id.statusView);
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
				tView.setText(e.getMessage());
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
	  
}
