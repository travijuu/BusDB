package BusDB.TraviJuu;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AndroidList extends ListActivity {

	public class MyCustomAdapter extends ArrayAdapter<String>
	{
		String[] arr;
		public MyCustomAdapter(Context context, int textViewResourceId, String[] objects, String[] arr) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.arr = arr;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View row=inflater.inflate(R.layout.item1, parent, false);
		TextView label=(TextView)row.findViewById(R.id.title);
		label.setText(arr[position]);
		ImageView icon=(ImageView)row.findViewById(R.id.img);
		
		icon.setImageResource(R.drawable.logo);
		
	
		return row;
		}
	}
}