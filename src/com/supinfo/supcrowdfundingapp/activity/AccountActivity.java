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

public class AccountActivity extends Activity {

	MyView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		myView = new MyView();
		final Context context = this;
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.accountRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		final EditText etPseudo = (EditText)findViewById(R.id.etPseudo);
		
		final EditText etNom = (EditText)findViewById(R.id.etNom);
		
		final EditText etPrenom = (EditText)findViewById(R.id.etPrenom);
		
		final EditText etMail = (EditText)findViewById(R.id.etMail);
		
		final EditText etPassword1 = (EditText)findViewById(R.id.etPassword1);
		
		final EditText etPassword2 = (EditText)findViewById(R.id.etPassword2);
		
		Button btValider = (Button)findViewById(R.id.btValider);
		btValider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (etPseudo.length() != 0 
						&& etNom.length() != 0 
						&& etPrenom.length() != 0 
						&& etMail.length() != 0 
						&& etPassword1.length() != 0 
						&& etPassword2.length() != 0 ){
					
					if (etPassword1.getText().toString().equals(etPassword2.getText().toString())){
					
						HttpClient httpclient = new DefaultHttpClient();
			            HttpPost httppost = new HttpPost(IP.ip + "/Supinfo/SupCrowdFunding/insert.php");
			            ArrayList<NameValuePair> nameValuePairs;
			            
			            String pseudo = etPseudo.getText().toString();
			            String nom = etNom.getText().toString();
			            String prenom = etPrenom.getText().toString();
			            String mail = etMail.getText().toString();
			            String password = etPassword1.getText().toString();
			            
			            password = User.passwordToMd5(password);
	
			            try
			            {
			                nameValuePairs = new ArrayList<NameValuePair>();
			                nameValuePairs.add(new BasicNameValuePair("addAccount", ""));
			                nameValuePairs.add(new BasicNameValuePair("pseudo", pseudo));
			                nameValuePairs.add(new BasicNameValuePair("nom", nom));
			                nameValuePairs.add(new BasicNameValuePair("prenom", prenom));
			                nameValuePairs.add(new BasicNameValuePair("mail", mail));
			                nameValuePairs.add(new BasicNameValuePair("password", password));
	
			                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			                HttpResponse response = httpclient.execute(httppost);
			            }
			            catch(Exception e)
			            {
			                e.printStackTrace();
			            }
			            Intent intent = new Intent(getBaseContext(), SeConnecterActivity.class);
						startActivity(intent);
						finish();
					}
					else{
						Toast.makeText(context, "Les mots de passe doivent etre égaux", Toast.LENGTH_LONG).show();
					}
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
		getMenuInflater().inflate(R.menu.account, menu);
		return false;
	}

}
