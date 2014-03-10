package com.supinfo.supcrowdfundingapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.entity.Category;
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

public class CategoryAdapter extends ArrayAdapter<Category> {
	
	private List<Category> categories;

	public CategoryAdapter(Context context, ArrayList<Category> categories) {
		super(context, android.R.id.list, categories);
		this.categories = categories;
	}
	
	@Override
	public Category getItem(int position) {
		return categories.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		MyView myview = new MyView();
		
		LayoutInflater layoutInflater = LayoutInflater.from(getContext());
		
		Category category = getItem(position);
		
		View view = myview.getBlocCategory(layoutInflater, getContext(), category);
		
		return view;
	}

}
