package com.supinfo.supcrowdfundingapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.adapter.ProjectAdapter;
import com.supinfo.supcrowdfundingapp.entity.ActualUser;
import com.supinfo.supcrowdfundingapp.entity.Project;
import com.supinfo.supcrowdfundingapp.entity.ProjectDAO;
import com.supinfo.supcrowdfundingapp.model.JsonParser;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

public class IndexActivity extends Activity {

	MyView myView;
	ArrayList<Project> projects;
	ProjectAdapter projectAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		myView = new MyView();
		projects = new ArrayList<Project>();
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.indexRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		ListView listView = (ListView)findViewById(R.id.listViewProjects);
		projectAdapter = new ProjectAdapter(this, projects);
		listView.setAdapter(projectAdapter);

		refreshList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
        return false;
	}

	public void refreshList(){
		for(Project p : ProjectDAO.getAllProject())
		{
			projects.add(p);
		}
		
		projectAdapter.notifyDataSetChanged();
	}
}
