package BusDB.TraviJuu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.widget.Toast;

public class Statistic {
	Thread thread = null;
	String str ="";
	
	String getStatistics()
	{
		String result = "";
		
		thread = new Thread() 
		{
			
			public void run()
			{
				 
				 HttpClient client = new DefaultHttpClient();
			     HttpPost post = new HttpPost("http://erkincakar.com/getBus.php?status=stats");
			     ArrayList<String> results = new ArrayList<String>();
			     String[] strArr;
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
			        str=sb.toString();
			        //strArr = result.split(";");
			        
		       }
		       catch(Exception e)
		       {
		    	   
		       }
		       
			}
		};
		thread.start();
		return str;
	}
	
	
}
