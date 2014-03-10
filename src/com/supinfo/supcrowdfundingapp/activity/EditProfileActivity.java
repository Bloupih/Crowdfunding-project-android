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
import com.supinfo.supcrowdfundingapp.entity.ActualUser;
import com.supinfo.supcrowdfundingapp.entity.User;
import com.supinfo.supcrowdfundingapp.model.IP;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class EditProfileActivity extends Activity {

	MyView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		
		myView = new MyView();
		final Context context = this;
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.editprofileRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		final EditText etName = (EditText)findViewById(R.id.etName);
		etName.setText(ActualUser.staticName);
		
		final EditText etFirstName = (EditText)findViewById(R.id.etFirstName);
		etFirstName.setText(ActualUser.staticFirstname);
		
		final EditText etMail = (EditText)findViewById(R.id.etMail);
		etMail.setText(ActualUser.staticMail);
		
		final EditText etPassword = (EditText)findViewById(R.id.etPassword);
		
		Button btSave = (Button)findViewById(R.id.btSave);
		btSave.setText("Sauvegarder");
		btSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (etName.length() != 0
						&& etMail.getText().length() != 0
						&& etFirstName.length() != 0){
					
					HttpClient httpclient = new DefaultHttpClient();
		            HttpPost httppost = new HttpPost(IP.ip + "/Supinfo/SupCrowdFunding/update.php");
		            ArrayList<NameValuePair> nameValuePairs;
		            
		            String sId = "" + ActualUser.staticId;
		            String name = etName.getText().toString();
		            String firstName = etFirstName.getText().toString();
		            String mail = etMail.getText().toString();
		            String password = etPassword.getText().toString();
		            
		            if (etPassword.length() != 0){
		            	
		            	password = User.passwordToMd5(password);
		            }

		            try
		            {
		                nameValuePairs = new ArrayList<NameValuePair>();
		                nameValuePairs.add(new BasicNameValuePair("id", sId));
		                nameValuePairs.add(new BasicNameValuePair("name", name));
		                nameValuePairs.add(new BasicNameValuePair("firstname", firstName));
		                nameValuePairs.add(new BasicNameValuePair("mail", mail));
		                nameValuePairs.add(new BasicNameValuePair("password", password));

		                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		                HttpResponse response = httpclient.execute(httppost);
		                
		                ActualUser.staticName = name;
		                ActualUser.staticFirstname = firstName;
		                ActualUser.staticMail = mail;
		                
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
					Toast.makeText(context, "Les champs Name/Firstname/Mail ne doivent pas etres vides", Toast.LENGTH_LONG).show();
					
					etName.setText(ActualUser.staticName);
					etFirstName.setText(ActualUser.staticFirstname);
					etMail.setText(ActualUser.staticMail);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return false;
	}

}
