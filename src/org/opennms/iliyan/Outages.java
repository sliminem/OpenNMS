package org.opennms.iliyan;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Outages extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outages);
    	Log.w("Starting Outages", "info");

        
        try{
        	DefaultHttpClient hc = new DefaultHttpClient();  
            ResponseHandler <String> res = new BasicResponseHandler();
	        String queryString = "http://10.0.2.2:8980/opennms/rest/outages/";
            HttpPost postMethod = new HttpPost(queryString);
            String response=hc.execute(postMethod,res); 

            /* Get a SAXParser from the SAXPArserFactory. */
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();

            /* Get the XMLReader of the SAXParser we created. */
            XMLReader xr = sp.getXMLReader();
            /* Create a new ContentHandler and apply it to the XML-Reader*/ 
            OutageParser parser = new OutageParser();
            xr.setContentHandler(parser);

            /* Parse the xml-data from our URL. */
            InputSource inputSource = new InputSource();
            inputSource.setEncoding("UTF-8");
            inputSource.setCharacterStream(new StringReader(response));

            /* Parse the xml-data from our URL. */
            xr.parse(inputSource);
            Log.d("DEBUG", "Outages: ");
            setTitle("Outages: ");
            
            /* Parsing has finished. */
        	
        } catch (Exception e) {
            /* Display any Error to the GUI. */
            Log.e("Exception", "OutagesQueryError", e);
        }
	}

}
