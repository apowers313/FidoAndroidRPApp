package ms.ato.fidoRpApp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.pm.PackageManager;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.pm.ApplicationInfo;

public class rpAppMain extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("fidoRpApp", "Starting...");

        /**
         * Create Intent for FIDO_OPERATION::DISCOVER
         * Per UAF Client API Transport, Section 6.2.1
         */
        Intent fidoIntent = new Intent("org.fidoalliance.intent.FIDO_OPERATION");
        fidoIntent.setType ("application/fido.uaf_client+json");
        fidoIntent.putExtra ("UAFIntentType", "DISCOVER");

        /**
         * Lookup Packages that provide the intent
         * Per UAF Client API Transport, Section 6.2 (See: "NOTE")
         */ 
        PackageManager pm = getPackageManager();
        // 0x00020000 == MATCH_ALL
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities (fidoIntent, 0x00020000);
        Log.d("fidoRpApp", "Num Packages Found: " + resolveInfos.size());
        for(ResolveInfo info : resolveInfos) {
        	ApplicationInfo applicationInfo = info.activityInfo.applicationInfo;
        	// Note: on Samsung Galaxy S6, this isn't very interesting
        	// Maybe because it's all in the System rather than a user-land application?
        	Log.d("fidoRpApp", "Application Name: " + applicationInfo.name);
        	Log.d("fidoRpApp", "Application Class: " + applicationInfo.className);
        	Log.d("fidoRpApp", "Application Label: " + applicationInfo.labelRes);
        	Log.d("fidoRpApp", "Application Package: " + applicationInfo.packageName);
        	Log.d("fidoRpApp", "Application nonLocalizedLabel: " + applicationInfo.nonLocalizedLabel);

        }

        /**
    	 * Fire our FIDO_OPERATION::DISCOVER
    	 * Hopefully onActivityResult() below will catch a reply
    	 * If nothing is found, startActivityForResult() will throw an ActivityNotFoundException error
    	 */
        try {
        	startActivityForResult(fidoIntent, 1);
        } catch (ActivityNotFoundException e) {
        	Log.e("fidoRpApp", "Got 'Not Found' exception");
        	Log.d("fidoRpApp", "Exception: " + e.getMessage());
        }
    }

    @Override
    /**
     * Catch any replies to intents that we sent out
     */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("fidoRpApp", "Got Activity Result");
		Log.d("fidoRpApp", "Request Code: " + requestCode);
		Log.d("fidoRpApp", "Result Code: " + resultCode);

		/**
		 * This is a FIDO_OPERATION UAF Intent (as opposed to other Intents we might catch)
		 */
		if (data.getExtras().containsKey("UAFIntentType")) {
			Log.d ("fidoRpApp", "Received UAFIntentType:");
			/**
			 * We should only be catching DISCOVERY_RESULTS
			 * Otherwise the code below will choke -- we can make this more robust later
			 * Per UAF Client API Transport, Section 6.2.2
			 */
			String intentType = data.getStringExtra("UAFIntentType");
			Log.d ("fidoRpApp", "Intent Type: " + intentType);
			String componentName = data.getStringExtra("componentName");
			Log.d ("fidoRpApp", "Component Name: " + componentName);
			String errorCode = data.getStringExtra("errorCode"); // XXX wrong
			Log.d ("fidoRpApp", "Error Code: " + errorCode);
			String discoveryData = data.getStringExtra("discoveryData");
			Log.d ("fidoRpApp", "Discovery Data: " + discoveryData);
		}
	}
}
