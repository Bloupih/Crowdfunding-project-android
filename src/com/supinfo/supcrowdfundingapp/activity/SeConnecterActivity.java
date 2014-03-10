package com.supinfo.supcrowdfundingapp.activity;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.R.layout;
import com.supinfo.supcrowdfundingapp.R.menu;
import com.supinfo.supcrowdfundingapp.entity.ActualUser;
import com.supinfo.supcrowdfundingapp.entity.User;
import com.supinfo.supcrowdfundingapp.entity.UserDAO;
import com.supinfo.supcrowdfundingapp.model.MyView;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class SeConnecterActivity extends Activity {

	MyView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_se_connecter);
		
		final Context context = this;
		myView = new MyView();		
		
		final RelativeLayout rl = (RelativeLayout)findViewById(R.id.connRelLayout);
		SlidingDrawer sd = (SlidingDrawer) myView.getSlidingDrawer(LayoutInflater.from(this), this);
		rl.addView(sd);
		
		final EditText etLogin = (EditText)rl.findViewById(R.id.etPseudo);
		final EditText etPassword = (EditText)rl.findViewById(R.id.etPassword);
		
		Button btValider = (Button)rl.findViewById(R.id.btValider);
		btValider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String login = etLogin.getText().toString();
				String password = etPassword.getText().toString();
				User u = UserDAO.findUserByPseudo(login);
				if (u == null){
					TextView tvError = (TextView)rl.findViewById(R.id.tvError);
					tvError.setVisibility(tvError.VISIBLE);
					tvError.setTextColor(Color.RED);
				}
				else{
					String pwdMd5 = User.passwordToMd5(password);
					if (u.getPassword().equals(pwdMd5)){
						ActualUser.staticId = u.getId();
						ActualUser.staticFirstname = u.getFirstname();
						ActualUser.staticMail = u.getMail();
						ActualUser.staticName = u.getName();
						ActualUser.staticPassword = u.getPassword();
						ActualUser.staticPseudo = u.getPseudo();
						ActualUser.staticRole = u.getRole();
						
						Intent intent = new Intent(context, IndexActivity.class);
						context.startActivity(intent);
						finish();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.se_connecter, menu);
		return false;
	}

}
