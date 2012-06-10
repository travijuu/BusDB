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
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class busprofile extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busprofile);
        setTitle("Bus Profile");
        Bundle extras = getIntent().getExtras();
        
        String bus = getBus(extras.getString("plaka"));
        EditText plaka = (EditText)findViewById(R.id.editText1);
        Spinner firma = (Spinner)findViewById(R.id.spinner1);
        Spinner busTypes = (Spinner)findViewById(R.id.spinner2);
        
        ArrayList<String> companyList = newbus.getCompanyList();
        ArrayList<String> busList = newbus.getBusTypes();
        
        try{
        	JSONObject f = new JSONObject(bus);
		plaka.setText(f.getString("Otobus_plaka"));
		//plaka.setText(extras.getString("plaka"));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,companyList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firma.setAdapter(adapter);
        firma.setPrompt("Firma Seçiniz");
        firma.setSelection(3, true); 
        
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,busList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busTypes.setAdapter(adapter2);
        }
        catch (Exception e) {
			// TODO: handle exception
            plaka.setText("ok");
		}
	        
        
        
          
        
   
  
  }

	
	 public String getBus(String query)
	 {
		 String result = "";
		 HttpClient client = new DefaultHttpClient();
		 query = query.replaceAll(" ", "%20");
	     //HttpPost post = new HttpPost("http://192.168.2.5/getBus.php?status=searchBus&query="+query);
	     HttpGet get = new HttpGet("http://erkincakar.com/getBus.php?status=getBus&plaka="+query);
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
         /*
        JSONArray jArray;
		try {
			jArray = new JSONArray(result);
			 JSONObject json_data=null;
		        for(int i=0;i<jArray.length();i++){
		               json_data = jArray.getJSONObject(i);
		               result += json_data.getString("Otobus_plaka")+" | " + json_data.getString("Otobus_model" ) + "\n<" + json_data.getString("Otobus_Firma")+">";
		               //busTypeList.add(json_data.getString("Otobus_plaka")+" | " + json_data.getString("Otobus_model" ) + "\n<" + json_data.getString("Otobus_Firma")+">");
		        }
		       
		} catch (JSONException e) {
			//status.setText(e.getMessage());
		 
		}
		*/
		 return result;
	       
	 }
	 
	 
	 public void deleteBus(View view)
	 {
		 String plaka = ((EditText)findViewById(R.id.editText1)).getText().toString().replaceAll(" ", "%20");
		 
		 HttpClient client = new DefaultHttpClient();
		 
		 String result;
	     
        try {
        	HttpPost post = new HttpPost("http://erkincakar.com/getBus.php?status=deleteBus&plaka="+plaka);
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
	        ((TextView)findViewById(R.id.textView1)).setText(result);
	        ((TextView)findViewById(R.id.textView1)).setTextSize(9);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//status.setText(e.getLocalizedMessage());
			e.printStackTrace();
			((TextView)findViewById(R.id.textView1)).setText(e.toString());
			
			
		}
		
	 }
}
