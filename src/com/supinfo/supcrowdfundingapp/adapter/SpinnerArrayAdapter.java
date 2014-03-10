package com.supinfo.supcrowdfundingapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.entity.Category;
import com.supinfo.supcrowdfundingapp.entity.Project;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class SpinnerArrayAdapter extends BaseAdapter implements SpinnerAdapter {
	
	private List<Category> categories;
	private Context context;

	public SpinnerArrayAdapter(Context context, ArrayList<Category> categories) {
		this.categories = categories;
		this.context = context;
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public Category getItem(int position) {
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = (TextView) View.inflate(context, android.R.layout.simple_spinner_item, null);
        textView.setText(categories.get(position).getName());
        return textView;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) LayoutInflater.from(context);
            convertView = vi.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }
        ((TextView) convertView).setText(categories.get(position).getName());
        ((TextView) convertView).setTag(categories.get(position).getId());
        return convertView;
	}

}
