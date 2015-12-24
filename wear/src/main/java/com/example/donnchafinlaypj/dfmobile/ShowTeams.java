package com.example.donnchafinlaypj.dfmobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class ShowTeams extends Activity
{

	private TextView mTextView;
	String team;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_teams);
		final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
		//
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			team = extras.getString("TEAM");
		}
		else
		team="*****";
		stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener()
		{
			@Override
			public void onLayoutInflated(WatchViewStub stub)
			{
				mTextView = (TextView) stub.findViewById(R.id.myTeam);
				mTextView.setText(team);
			}
		});
	}
}
