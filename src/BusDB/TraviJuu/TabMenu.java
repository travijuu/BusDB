package BusDB.TraviJuu;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabMenu extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.tabmenu);

/** TabHost will have Tabs */
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

/** TabSpec used to create a new tab.
 * By using TabSpec only we can able to setContent to the tab.
 * By using TabSpec setIndicator() we can set name to tab. */

/** tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec tab1Spec = tabHost.newTabSpec("tid1");
        TabSpec tab2Spec = tabHost.newTabSpec("tid1");
        TabSpec tab3Spec = tabHost.newTabSpec("tid1");
        TabSpec tab4Spec = tabHost.newTabSpec("tid1");

/** TabSpec setIndicator() is used to set name for the tab. */
/** TabSpec setContent() is used to set content for a particular tab. */
        tab1Spec.setIndicator("New Bus").setContent(new Intent(this,newbus.class));
        tab2Spec.setIndicator("Bus List").setContent(new Intent(this,buslist.class));
        tab3Spec.setIndicator("Search").setContent(new Intent(this,searchbus.class));
        tab4Spec.setIndicator("Statistics").setContent(new Intent(this,searchbus.class));

/** Add tabSpec to the TabHost to display. */
        tabHost.addTab(tab1Spec);
        tabHost.addTab(tab2Spec);
        tabHost.addTab(tab3Spec);
        tabHost.addTab(tab4Spec);

    }
}