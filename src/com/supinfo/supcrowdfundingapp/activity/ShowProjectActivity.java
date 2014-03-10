package com.supinfo.supcrowdfundingapp.activity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.R.layout;
import com.supinfo.supcrowdfundingapp.R.menu;
import com.supinfo.supcrowdfundingapp.entity.ActualUser;
import com.supinfo.supcrowdfundingapp.entity.Project;
import com.supinfo.supcrowdfundingapp.entity.ProjectDAO;
import com.supinfo.supcrowdfundingapp.entity.Transaction;
import com.supinfo.supcrowdfundingapp.entity.TransactionDAO;
import com.supinfo.supcrowdfundingapp.entity.User;
import com.supinfo.supcrowdfundingapp.entity.UserDAO;
import com.supinfo.supcrowdfundingapp.model.IP;
import com.supinfo.supcrowdfundingapp.model.JsonParser;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class ShowProjectActivity extends Activity {

	MyView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_project);
		
		final Context context = this;
		myView = new MyView();
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.showProjectRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		Bundle extras = getIntent().getExtras();
		final int idProject = extras.getInt("idProject");
		
		Project project = ProjectDAO.getProject(idProject);
		
		TextView tvProjectName = (TextView)findViewById(R.id.tvProjectName);
		tvProjectName.setText(project.getName());
		
		User user = UserDAO.findUserById(project.getCreatorId());
		TextView tvProjectCreator = (TextView)findViewById(R.id.tvProjectCreator);
		tvProjectCreator.setText(user.getPseudo());
		
		TextView tvCategoryName = (TextView)findViewById(R.id.tvCategoryName);
		tvCategoryName.setText(project.getNomCategory());
		
		TextView tvProjectContent = (TextView)findViewById(R.id.tvProjectContent);
		tvProjectContent.setText(project.getContent());
		
		
		
		TextView tvMoneyObjectif = (TextView)findViewById(R.id.tvMoneyObjectif);
		tvMoneyObjectif.setText(project.getPrice() +"€ "+ " d'objectif");
		
		float contributedValue = TransactionDAO.getContributedValueByProject(idProject);
		TextView tvMoneyCollected = (TextView)findViewById(R.id.tvMoneyCollected);
		tvMoneyCollected.setText("$" + contributedValue + " collecté !");
		
		TextView tvProjectDate = (TextView)findViewById(R.id.tvDate);
		tvProjectDate.setText(project.getDateStart() + "   >   " + project.getDateEnd());
		
		float pourcentage = (contributedValue / project.getPrice()) * 100;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits (2) ;
		df.setMinimumFractionDigits (2) ;
		df.setDecimalSeparatorAlwaysShown (true) ; 
		TextView tvProjectCompleted = (TextView)findViewById(R.id.tvProjectCompleted);
		tvProjectCompleted.setText("Completé à " + df.format(pourcentage) + "% !");
		
		ProgressBar pBarCompleted = (ProgressBar)findViewById(R.id.projectCompletedBar);
		pBarCompleted.setProgress(Math.round(pourcentage));
		
		if (ActualUser.staticId != -1){
			Button btContribute = new Button(this);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			btContribute.setLayoutParams(params);
			btContribute.setText("Contribuer à ce projet");
			btContribute.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setTitle("Contribution");
					
					LinearLayout ln = new LinearLayout(context);
					ln.setOrientation(LinearLayout.VERTICAL);
					LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					ln.setLayoutParams(params);
					
					final TextView tvScroll = new TextView(context);
					tvScroll.setLayoutParams(params);
					tvScroll.setTextColor(Color.parseColor("#FFFFFF"));
					tvScroll.setText("0 €");
					
					final SeekBar skBar = new SeekBar(context);
					skBar.setLayoutParams(params);
					skBar.setMax(2000);
					skBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
						
						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub
						}
						
						@Override
						public void onProgressChanged(SeekBar seekBar, int progress,
								boolean fromUser) {
							tvScroll.setText(seekBar.getProgress() + " €");
						}
					});
					
					ln.addView(tvScroll);
					ln.addView(skBar);
					
					builder.setView(ln);
					
					builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   HttpClient httpclient = new DefaultHttpClient();
				           HttpPost httppost = new HttpPost(IP.ip + "/Supinfo/SupCrowdFunding/insert.php");
				           ArrayList<NameValuePair> nameValuePairs;
				            
			        	   String contributedValue = String.valueOf(skBar.getProgress());
			        	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			        	   Date d = new Date();
			        	   String date = dateFormat.format(d);
			        	   String projectId = String.valueOf(idProject);
			        	   String userId = String.valueOf(ActualUser.staticId);
			        	   
			        	   try
				            {
				                nameValuePairs = new ArrayList<NameValuePair>();
				                nameValuePairs.add(new BasicNameValuePair("addTransaction", ""));
				                nameValuePairs.add(new BasicNameValuePair("contributedValue", contributedValue));
				                nameValuePairs.add(new BasicNameValuePair("date", date));
				                nameValuePairs.add(new BasicNameValuePair("idProject", projectId));
				                nameValuePairs.add(new BasicNameValuePair("userId", userId));
				                
				                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				                HttpResponse response = httpclient.execute(httppost);
				            }
				            catch(Exception e)
				            {
				                e.printStackTrace();
				            }
			        	   
			               dialog.cancel();
			               
			               Intent intent = new Intent(context, ShowProjectActivity.class);
			               intent.putExtra("idProject", idProject);
			               context.startActivity(intent);
			               finish();
			           }
			       });
					
					builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   dialog.cancel();
			           }
			       });
					
					Dialog box = builder.create();
					box.setCancelable(false);
					box.show();
				}
			});
		
			LinearLayout ln = (LinearLayout)findViewById(R.id.lnLayoutMain);
			ln.addView(btContribute);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_project, menu);
		return false;
	}
}
