package org.opennms.iliyan;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class OutageParser extends DefaultHandler {
	int count;
	int iterator = -1;
	private boolean openRoot = false;
	private boolean openOutage = false;
	private int[] outagesID;
	private boolean openIP = false;
	private String[] ipAddresses;
	private boolean openDesc = false;
	private String[] desc;
	
	
	@Override
    public void startDocument() throws SAXException {
    	
    }
     
    @Override
    public void endDocument() throws SAXException {
    	for(int i=0; i<count; i++){
			Log.w("DEBUG PARSER", "outagesID = " + outagesID[i]);
			Log.w("DEBUG PARSER", "ip = " + ipAddresses[i]);
			Log.w("DEBUG PARSER", "desc = " + desc[i]);
    	}

    
    }
    
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
    	Log.w("Opening Tag", "info");
    	if(localName.equals("outages")){
    		openRoot = true;
    		count = Integer.parseInt(atts.getValue("count"));
    		outagesID = new int[count];
    		ipAddresses = new String[count];
    		desc = new String[count];
    		Log.i("COUNT: ", "count = "+count );
    	} else if(localName.equals("outage")){
    		iterator++;
    		openOutage  = true;
    		outagesID[iterator] = Integer.parseInt(atts.getValue("id"));   
    		Log.w("DEBUG PARSER", "outagesID = " + outagesID[iterator]);
    	} else if(localName.equals("ipAddress")){
    		openIP  = true;
    	} else if(localName.equals("description")){
    		openDesc  = true;
    	}
    
    
    
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	if(localName.equals("outages")){
    	openRoot=false;
    	} else if(localName.equals("ipAddress")){
    		openIP=false;
    	} else if(localName.equals("description")){
    		openDesc  = false;
    	}
    }
    
 // Code executed when inbetween opening and closing tags
    @Override
    public void characters(char ch[], int start, int length) {
    	if(openIP) ipAddresses[iterator] = new String(ch, start, length);
    	if(openDesc) desc[iterator] = new String(ch, start, length);

    }

}
