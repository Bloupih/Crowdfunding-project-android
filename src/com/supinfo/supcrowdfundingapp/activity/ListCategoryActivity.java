package com.supinfo.supcrowdfundingapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.R.id;
import com.supinfo.supcrowdfundingapp.R.layout;
import com.supinfo.supcrowdfundingapp.R.menu;
import com.supinfo.supcrowdfundingapp.adapter.CategoryAdapter;
import com.supinfo.supcrowdfundingapp.adapter.ProjectAdapter;
import com.supinfo.supcrowdfundingapp.entity.Category;
import com.supinfo.supcrowdfundingapp.entity.CategoryDAO;
import com.supinfo.supcrowdfundingapp.entity.Project;
import com.supinfo.supcrowdfundingapp.model.JsonParser;
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
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

public class ListCategoryActivity extends Activity {

	private MyView myView;
	private ArrayList<Category> listCategories;
	private CategoryAdapter listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_category);
		
		myView = new MyView();
		listCategories = new ArrayList<Category>();
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.listCategRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		ListView listCateg = (ListView)findViewById(R.id.listViewCategories);
		listAdapter = new CategoryAdapter(this, listCategories);
		listCateg.setAdapter(listAdapter);
		
		refreshList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return false;
	}
	
	public void refreshList(){
		for(Category c : CategoryDAO.getAllCategory())
		{
			listCategories.add(c);
		}
		
		listAdapter.notifyDataSetChanged();
	}

}
