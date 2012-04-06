package org.opennms.iliyan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OpenNMSActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("OpenNMS");
    	Log.w("Starting OpenNMS", "info");
        
        Button outagesButton = (Button)findViewById(R.id.outageID);
        Button alarmsButton = (Button)findViewById(R.id.alarmsID);
        Button nodesButton = (Button)findViewById(R.id.nodeID);

        outagesButton.setOnClickListener( new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), RestHandler.class);
				startActivity(myIntent);				
				
			}
        	
        });
        
    }
}