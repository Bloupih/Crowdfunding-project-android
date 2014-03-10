package com.supinfo.supcrowdfundingapp.activity;

import java.util.ArrayList;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.R.layout;
import com.supinfo.supcrowdfundingapp.R.menu;
import com.supinfo.supcrowdfundingapp.adapter.ProjectAdapter;
import com.supinfo.supcrowdfundingapp.entity.Category;
import com.supinfo.supcrowdfundingapp.entity.CategoryDAO;
import com.supinfo.supcrowdfundingapp.entity.Project;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

public class ListProjectsCategoryActivity extends Activity {

	MyView myView;
	ArrayList<Project> projects;
	ProjectAdapter projectAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_projects_category);
		
		myView = new MyView();
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.listProjectsCategRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		Bundle extras = getIntent().getExtras();
		int idCategory = extras.getInt("idCategory");
		
		projects = new ArrayList<Project>();
		ListView listView = (ListView)findViewById(R.id.listViewProjects);
		
		
		projectAdapter = new ProjectAdapter(this, projects);
		listView.setAdapter(projectAdapter);

		refreshList(idCategory);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_projects_category, menu);
		return false;
	}

	public void refreshList(int idCategory){
		for (Project p : CategoryDAO.getProjects(idCategory)){
			projects.add(p);
		}
		projectAdapter.notifyDataSetChanged();
	}
}
