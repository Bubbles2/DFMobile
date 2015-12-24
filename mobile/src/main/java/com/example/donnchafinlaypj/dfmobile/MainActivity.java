package com.example.donnchafinlaypj.dfmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener
{
	private GoogleApiClient mGoogleApiClient;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//
		mGoogleApiClient = new GoogleApiClient.Builder(this)
								   .addApi(Wearable.API)
								   .addConnectionCallbacks(this)
								   .addOnConnectionFailedListener(this)
								   .build();


	}

	@Override
	public void onConnected(Bundle bundle)
	{

	}

	@Override
	public void onConnectionSuspended(int i)
	{

	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult)
	{

	}
	public void sendTeams(int pos,String name) {

		//

		mGoogleApiClient = new GoogleApiClient.Builder(this)
								   .addApi(Wearable.API)
								   .addConnectionCallbacks(this)
								   .addOnConnectionFailedListener(this)
								   .build();

		mGoogleApiClient.connect();
		//
		//
		PutDataMapRequest putDataMapRequest = PutDataMapRequest.create("/my_teams");

		putDataMapRequest.getDataMap().putInt("position", pos);
		putDataMapRequest.getDataMap().putString("team", "," +
																 name);
		PutDataRequest request = putDataMapRequest.asPutDataRequest();

		Log.i("DFSEND Test Data Item", "Generating Team DataItem: " + request);

		Wearable.DataApi.putDataItem(mGoogleApiClient, request)
				.setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
					@Override
					public void onResult(DataApi.DataItemResult dataItemResult) {
						if (!dataItemResult.getStatus().isSuccess()) {
							Log.i("Data  Item Test", "ERROR: failed to putDataItem, status code: " + dataItemResult.getStatus().getStatusCode());
						}
					}
				});

	}
	public void sendTeam(View view)
	{
		sendTeams(5,"Ulstebbbbxxxba");
	}

}
