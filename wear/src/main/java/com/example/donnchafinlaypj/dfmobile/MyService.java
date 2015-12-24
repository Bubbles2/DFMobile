package com.example.donnchafinlaypj.dfmobile;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.concurrent.TimeUnit;

public class MyService extends WearableListenerService
{
	GoogleApiClient mGoogleApiClient;
	@Override
	public void onCreate() {
		super.onCreate();
		mGoogleApiClient = new GoogleApiClient.Builder(this)
								   .addApi(Wearable.API)
								   .build();
		mGoogleApiClient.connect();
		//Wearable.MessageApi.addListener(mGoogleApiClient,this);
		Wearable.DataApi.addListener(mGoogleApiClient,this);
	}

	@Override
	public void onDataChanged(DataEventBuffer dataEvents)
	{
		super.onDataChanged(dataEvents);
		Log.i("DFRECV Data  Item Test", "onDataChanged: " + dataEvents);


		// Loop through the events and send a message back to the node that created the data item.
		for (DataEvent event : dataEvents) {
			if (event.getType() == event.TYPE_CHANGED) {
				//
				DataMap dataMap = DataMapItem.fromDataItem(event.getDataItem()).getDataMap();
				String path = event.getDataItem().getUri().getPath();
				String team="***";
				if (path.equals("/my_teams")){
					int pos = dataMap.getInt("position");
					team = dataMap.getString("team");
					Log.i("DFRECV Data  Item  ", "Team     ---" +team);
				}
				//
				Intent intent = new Intent(getApplicationContext(), ShowTeams.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("TEAM", team);
				startActivity(intent);


			}
		}
	}
}
