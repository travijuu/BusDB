package BusDB.TraviJuu;
import java.io.*;
import java.util.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;
import android.widget.*;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 

import BusDB.TraviJuu.R.id;
import android.R.color;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class newbus extends Activity {

	final HashMap<Integer, String> companyTypeMap = new HashMap<Integer, String>();
	final HashMap<Integer, Integer> companyPictureMap = new HashMap<Integer, Integer>();
	
	static HashMap<Integer, String> busTypeMap = new HashMap<Integer, String>();
	static HashMap<Integer, Integer> busPictureMap = new HashMap<Integer, Integer>();
	
	 String companyName = "";
	 String busTypeName = "";
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        setContentView(R.layout.newbus);
	        String result = "";
	        setTitle("Yeni Otobüs Ekle");
	        //Spinner spinner1 = (Spinner)findViewById(R.id.spinner1); 67cbff c3dce9
	        //Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
	        ArrayList<String> busTypeList = new ArrayList<String>();
	 	    final TextView status =(TextView) findViewById(R.id.textView3);

	        //busTypeList = getBusTypes();
	        
	        RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroup01);


	        companyTypeMap.put(R.id.radioButton1, "Kamil Koç");
	        companyPictureMap.put(R.id.radioButton1,R.drawable.kamilkocicon);
	        RadioButton company1 = (RadioButton)findViewById(R.id.radioButton1);
	        company1.setButtonDrawable(R.drawable.kamilkocicon);
	        company1.setOnCheckedChangeListener(companylistener);



	        companyTypeMap.put(R.id.radioButton2, "Pamukkale");
	        companyPictureMap.put(R.id.radioButton2,R.drawable.pamukkaleicon);
	        RadioButton company2 = (RadioButton)findViewById(R.id.radioButton2);
	        company2.setButtonDrawable(R.drawable.pamukkaleicon);
	        company2.setOnCheckedChangeListener(companylistener);

	        companyTypeMap.put(R.id.radioButton3, "Varan");
	        companyPictureMap.put(R.id.radioButton3,R.drawable.varanicon);
	        RadioButton company3 = (RadioButton)findViewById(R.id.radioButton3);
	        company3.setButtonDrawable(R.drawable.varanicon);
	        company3.setOnCheckedChangeListener(companylistener);

	        companyTypeMap.put(R.id.radioButton4, "Nilüfer");
	        companyPictureMap.put(R.id.radioButton4,R.drawable.nilufericon);
	        RadioButton company4 = (RadioButton)findViewById(R.id.radioButton4);
	        company4.setButtonDrawable(R.drawable.nilufericon);
	        company4.setOnCheckedChangeListener(companylistener);


	        companyTypeMap.put(R.id.radioButton5, "Ulusoy");
	        companyPictureMap.put(R.id.radioButton5,R.drawable.ulusoyicon);
	        RadioButton comapny5 = (RadioButton)findViewById(R.id.radioButton5);
	        comapny5.setButtonDrawable(R.drawable.ulusoyicon);
	        comapny5.setOnCheckedChangeListener(companylistener);

	        Constants constants = new Constants();
	        busTypeList = constants.busTypeList;
            busTypeMap = constants.busTypeMap;
            busPictureMap = constants.busPictureMap;



        	   for (int i=0;i<busTypeList.size();i++) {
   	        	RadioButton rb = new RadioButton(this);
   	        	rb.setWidth(125);
   	        	rb.setId(i+100);

   		        rb.setButtonDrawable(busPictureMap.get(i+100));
   		        rb.setOnCheckedChangeListener(buslistener);
   		        rg.addView(rb);
   			}
	        //ArrayList<String> companyList = new ArrayList<String>();
	        //companyList = getCompanyList();
	        /*
	        companyList.add("Kamil Ko�");
	        companyList.add("Pamukkale");
	        companyList.add("Varan");
	        companyList.add("Ulusoy");
	        */
	        /*
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,busTypeList);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner2.setAdapter(adapter);
	         /*
	        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,companyList);
	        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner1.setAdapter(adapter2);*/


           /*
          try{
              Database db = new Database(newbus.this);
              db.openDB();

              Bus bus = new Bus("16 KK 258","Mercedes","Travego 15","Kamil Koc","12-01-2011",46,0);
             long g = db.addBus(bus);
             Toast.makeText(this,g+"",Toast.LENGTH_SHORT).show();
              Cursor c = db.getBusCursor();
              Toast.makeText(this,c.getCount()+"-",Toast.LENGTH_SHORT).show();
              db.closeDB();
          }catch(Exception e)
          {
                   Toast.makeText(this,"HATA",Toast.LENGTH_SHORT).show();
          }
              */


	        }
	  OnCheckedChangeListener companylistener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					final TextView status =(TextView) findViewById(R.id.textView3);
					companyName = companyTypeMap.get(buttonView.getId());
					status.setText(companyName);
					buttonView.setButtonDrawable(companyPictureMap.get(buttonView.getId()));
					
				}
				if(!isChecked)
				{
					//buttonView.setButtonDrawable(R.drawable.icon);
				}
			}
		};
		
		 OnCheckedChangeListener buslistener = new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						final TextView status =(TextView) findViewById(R.id.textView3);
						busTypeName = busTypeMap.get(buttonView.getId());
						status.setText(companyName + " - " + busTypeName );
						buttonView.setButtonDrawable(busPictureMap.get(buttonView.getId()));
						
					}
					if(!isChecked)
					{
						//buttonView.setButtonDrawable(R.drawable.icon);
					}
				}
			};
		 
			
		 public void addBus(View view)
		 {
			  /*
			 EditText plaka = (EditText) findViewById(R.id.editText1);
			 Spinner firma = (Spinner) findViewById(R.id.spinner1);
			 Spinner model = (Spinner) findViewById(R.id.spinner2);
			 */
             CheckBox offline = (CheckBox)findViewById(id.offlineCheck);
               if(offline.isChecked())
               {
                 try{
                     Database db = new Database(newbus.this);
                     db.openDB();
                     String plaka = ((EditText) findViewById(R.id.editText1)).getText().toString();
                     String firma = companyName;
                     String model = busTypeName;
                     String[] d = model.split("-");
                     Bus bus = new Bus(plaka,d[0].toString(),d[1].trim(),firma, DateUtils.HOUR_MINUTE_24,0,0);
                     long g = db.addBus(bus);
                     if(g>0)
                     Toast.makeText(this,plaka+"",Toast.LENGTH_SHORT).show();
                     else
                         Toast.makeText(this,"Sorun",Toast.LENGTH_SHORT).show();

                     db.closeDB();
                 }catch(Exception e)
                 {
                     Toast.makeText(this,"HATA",Toast.LENGTH_SHORT).show();
                 }

               }
              else
               {
			
			 String plaka = ((EditText) findViewById(R.id.editText1)).getText().toString().replaceAll(" ", "%20");
			 String firma = companyName.replaceAll(" ", "%20");
			 String model = busTypeName.replaceAll(" ", "%20");
			 
			 TextView c =(TextView) findViewById(R.id.textView3);
			 c.setText("");
			 String result = "";
			 HttpClient client = new DefaultHttpClient();
		     HttpPost post = new HttpPost("http://erkincakar.com/getBus.php?status=addBus&model="+model+"&plaka="+plaka+"&firma="+firma);
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
		        reader.close();
		        /*
		        c.setText(result);
		        c.setBackgroundColor(Color.GREEN);
		        if(!result.contains("Error"))
		        	c.setTextColor(Color.GREEN);
		        else
		        	c.setTextColor(Color.RED);
				*/
		       
		        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		         
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
               }
		 }
		 
		 public static ArrayList<String> getBusTypes()
		 {
			 String result = "";
			 HttpClient client = new DefaultHttpClient();
		     HttpPost post = new HttpPost("http://erkincakar.com/getBus.php?status=getBusTypes");
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
		        reader.close();
	 
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
			               busTypeList.add(json_data.getString("marka")+" - " +json_data.getString("model"));
			               busTypeMap.put(i+1000, json_data.getString("marka").toLowerCase()+"_" +json_data.getString("model").replaceAll(" ", "").toLowerCase());
			               busPictureMap.put(i+1000, i+2000);
			        }
			       
			} catch (JSONException e) {
				//status.setText(e.getMessage());
			}
			
			 return busTypeList;
		       
		 }
		 
		  
		 
		 public static ArrayList<String> getCompanyList()
		 {
			 String result = "";
			 HttpClient client = new DefaultHttpClient();
		     HttpPost post = new HttpPost("http://erkincakar.com/getBus.php?status=getCompanyList");
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
			               busTypeList.add(json_data.getString("ISIM"));
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
	    
}
