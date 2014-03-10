package com.supinfo.supcrowdfundingapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.entity.Project;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProjectAdapter extends ArrayAdapter<Project> {
	
	private List<Project> projects;

	public ProjectAdapter(Context context, ArrayList<Project> projects) {
		super(context, android.R.id.list, projects);
		this.projects = projects;
	}
	
	@Override
	public Project getItem(int position) {
		return projects.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		MyView myview = new MyView();
		
		LayoutInflater layoutInflater = LayoutInflater.from(getContext());
		
		Project project = getItem(position);
		
		View view = myview.getBlocProject(layoutInflater, getContext(), project);
		
		return view;
	}

}
