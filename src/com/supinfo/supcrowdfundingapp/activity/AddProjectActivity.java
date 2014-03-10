package com.supinfo.supcrowdfundingapp.activity;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.R.id;
import com.supinfo.supcrowdfundingapp.R.layout;
import com.supinfo.supcrowdfundingapp.R.menu;
import com.supinfo.supcrowdfundingapp.adapter.SpinnerArrayAdapter;
import com.supinfo.supcrowdfundingapp.entity.ActualUser;
import com.supinfo.supcrowdfundingapp.entity.Category;
import com.supinfo.supcrowdfundingapp.entity.CategoryDAO;
import com.supinfo.supcrowdfundingapp.entity.User;
import com.supinfo.supcrowdfundingapp.model.IP;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.os.Bundle;
import android.os.Parcelable.Creator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProjectActivity extends Activity {

	MyView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_project);
		
		myView = new MyView();
		final Context context = this;

		RelativeLayout rl = (RelativeLayout)findViewById(R.id.addProjectRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		for (Category c : CategoryDAO.getAllCategory()){
			categoryList.add(c);
		}
		
		final Spinner sp = (Spinner)rl.findViewById(R.id.spCategory);

		SpinnerArrayAdapter spinAdapter = new SpinnerArrayAdapter(this, categoryList);
		
		sp.setAdapter(spinAdapter);
		
		final EditText etProjectName = (EditText)rl.findViewById(R.id.etProjectName);
		
		final EditText etProjectPrice = (EditText)rl.findViewById(R.id.etProjectPrice);
		
		final EditText etProjectContent = (EditText)rl.findViewById(R.id.etProjectContent);
		
		final EditText etProjectDateStart = (EditText)rl.findViewById(R.id.etProjectDateStart);
		
		final EditText etProjectDateEnd = (EditText)rl.findViewById(R.id.etProjectDateEnd);

		Button btValider = (Button)rl.findViewById(R.id.btValider);
		btValider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (etProjectName.length() != 0 
						&& etProjectPrice.length() != 0 
						&& etProjectContent.length() != 0 
						&& etProjectDateStart.length() != 0 
						&& etProjectDateEnd.length() != 0){
					
					HttpClient httpclient = new DefaultHttpClient();
		            HttpPost httppost = new HttpPost(IP.ip + "/Supinfo/SupCrowdFunding/insert.php");
		            ArrayList<NameValuePair> nameValuePairs;
		            
		            String name = etProjectName.getText().toString();
		            String price = etProjectPrice.getText().toString();
		            String idCategory = String.valueOf(CategoryDAO.getCategoryByName(sp.getSelectedItem().toString()).getId());
		            String content = etProjectContent.getText().toString();
		            String dateStart = etProjectDateStart.getText().toString();
		            String dateEnd = etProjectDateEnd.getText().toString();
		            String creator = String.valueOf(ActualUser.staticId);
		            
		            try
		            {
		                nameValuePairs = new ArrayList<NameValuePair>();
		                nameValuePairs.add(new BasicNameValuePair("addProject", ""));
		                nameValuePairs.add(new BasicNameValuePair("name", name));
		                nameValuePairs.add(new BasicNameValuePair("price", price));
		                nameValuePairs.add(new BasicNameValuePair("creator", creator));
		                nameValuePairs.add(new BasicNameValuePair("idCategory", idCategory));
		                nameValuePairs.add(new BasicNameValuePair("content", content));
		                nameValuePairs.add(new BasicNameValuePair("dateStart", dateStart));
		                nameValuePairs.add(new BasicNameValuePair("dateEnd", dateEnd));
		                
		                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		                HttpResponse response = httpclient.execute(httppost);
		            }
		            catch(Exception e)
		            {
		                e.printStackTrace();
		            }
		            Intent intent = new Intent(getBaseContext(), IndexActivity.class);
					startActivity(intent);
					finish();
				}
				else{
					Toast.makeText(context, "Les champs ne doivent pas etre vides", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_project, menu);
		return false;
	}

}
