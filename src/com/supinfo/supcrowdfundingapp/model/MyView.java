package com.supinfo.supcrowdfundingapp.model;


import java.text.DecimalFormat;

import com.supinfo.supcrowdfundingapp.R;
import com.supinfo.supcrowdfundingapp.activity.AccountActivity;
import com.supinfo.supcrowdfundingapp.activity.AddProjectActivity;
import com.supinfo.supcrowdfundingapp.activity.EditProfileActivity;
import com.supinfo.supcrowdfundingapp.activity.IndexActivity;
import com.supinfo.supcrowdfundingapp.activity.ListCategoryActivity;
import com.supinfo.supcrowdfundingapp.activity.ListProjectsCategoryActivity;
import com.supinfo.supcrowdfundingapp.activity.SeConnecterActivity;
import com.supinfo.supcrowdfundingapp.activity.ShowProjectActivity;
import com.supinfo.supcrowdfundingapp.entity.ActualUser;
import com.supinfo.supcrowdfundingapp.entity.Category;
import com.supinfo.supcrowdfundingapp.entity.Project;
import com.supinfo.supcrowdfundingapp.entity.TransactionDAO;
import com.supinfo.supcrowdfundingapp.entity.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;


import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyView {

	
	public MyView(){

	}

	public View getBlocProject(LayoutInflater layoutInflater, final Context context, final Project project){
		
		View aView = layoutInflater.inflate(R.layout.bloc_project, null);

		TextView tvCategoryName = (TextView)aView.findViewById(R.id.categoryName);
		tvCategoryName.setText(project.getNomCategory());
		
		TextView tvProjectTitle = (TextView)aView.findViewById(R.id.projectTitle);
		tvProjectTitle.setText(project.getName());
		
		TextView tvMoneyObjectif = (TextView)aView.findViewById(R.id.moneyObjectif);
		tvMoneyObjectif.setText("$" + project.getPrice() + " - objectif ");
		
		float contributedValue = TransactionDAO.getContributedValueByProject(project.getId());
		TextView tvMoneyCollected = (TextView)aView.findViewById(R.id.moneyCollected);
		tvMoneyCollected.setText("$" + contributedValue + " déjà collecté !");
		
		TextView tvProjectContent = (TextView)aView.findViewById(R.id.projectContent);
		tvProjectContent.setText(project.getContent());
		
		Button bDetail = (Button)aView.findViewById(R.id.buttonDetail);
		bDetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ShowProjectActivity.class);
				intent.putExtra("idProject", project.getId());
				context.startActivity(intent);
			}
		});
		bDetail.setText("Voir détails >>>");
		
		float pourcentage = (contributedValue / project.getPrice()) * 100;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits (2) ;
		df.setMinimumFractionDigits (2) ;
		df.setDecimalSeparatorAlwaysShown (true) ; 
		
		TextView tvProjectCompleted = (TextView)aView.findViewById(R.id.projectCompleted);
		tvProjectCompleted.setText("Completé à " + df.format(pourcentage) + "% !");
		
		ProgressBar barProjectCompleted = (ProgressBar)aView.findViewById(R.id.projectCompletedBar);
		barProjectCompleted.setProgress(Math.round(pourcentage));
		
		return aView;
	}
	
	public View getBlocCategory(LayoutInflater layoutInflater, final Context context, final Category category){
		
		View aView = layoutInflater.inflate(R.layout.bloc_category, null);

		TextView tvCategoryName = (TextView)aView.findViewById(R.id.categoryName);
		tvCategoryName.setText(category.getName());
		
		TextView tvCategoryContent = (TextView)aView.findViewById(R.id.categoryContent);
		tvCategoryContent.setText(category.getContent());
		
		Button bProjects = (Button)aView.findViewById(R.id.buttonProjects);
		bProjects.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ListProjectsCategoryActivity.class);
				intent.putExtra("idCategory", category.getId());
				context.startActivity(intent);
			}
		});
		bProjects.setText("Voir les projets >>>");
		
		return aView;
	}
	
	public View getSlidingDrawer(LayoutInflater layoutInflater, final Context context){
		
		final User user = new User(ActualUser.getUser());
		
		View aView = layoutInflater.inflate(R.layout.sliding_drawer, null);
		LinearLayout lnLayout = (LinearLayout)aView.findViewById(R.id.content);
		LayoutParams buttonParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		Button btAcc = (Button)aView.findViewById(R.id.btAccueil);
		btAcc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, IndexActivity.class);
				context.startActivity(intent);
				((Activity) context).finish();
			}
		});
		btAcc.setText("Accueil");
		
		Button btCateg = (Button)aView.findViewById(R.id.btCategories);
		btCateg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ListCategoryActivity.class);
				context.startActivity(intent);
				((Activity) context).finish();
			}
		});
		btCateg.setText("Categories");
		
		if (user.getId() == -1){
			Button btConn = new Button(context);
			btConn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, SeConnecterActivity.class);
					context.startActivity(intent);
					((Activity) context).finish();
				}
			});
			btConn.setText("Se connecter");
			btConn.setBackgroundColor(Color.parseColor("#000000"));
			btConn.setPadding(10, 10, 10, 10);
			btConn.setTextColor(Color.parseColor("#FFFFFF"));		            
			lnLayout.addView(btConn, buttonParams);
			
			Button btEnr = new Button(context);
			btEnr.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, AccountActivity.class);
					context.startActivity(intent);
					((Activity) context).finish();
				}
			});
			btEnr.setText("S'enregistrer");
			btEnr.setBackgroundColor(Color.parseColor("#000000"));
			btEnr.setPadding(10, 10, 10, 10);
			btEnr.setTextColor(Color.parseColor("#FFFFFF"));
			lnLayout.addView(btEnr, buttonParams);
		}
		else{
			
			Button btAddProject = new Button(context);
			btAddProject.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, AddProjectActivity.class);
					context.startActivity(intent);
					((Activity) context).finish();
				}
			});
			btAddProject.setText("Ajouter un projet");
			btAddProject.setBackgroundColor(Color.parseColor("#000000"));
			btAddProject.setPadding(10, 10, 10, 10);
			btAddProject.setTextColor(Color.parseColor("#FFFFFF"));
			lnLayout.addView(btAddProject, buttonParams);
			
			
			Button btEditProfile = new Button(context);
			btEditProfile.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, EditProfileActivity.class);
					
					intent.putExtra("userName", user.getName());
					intent.putExtra("userFirstName", user.getFirstname());
					intent.putExtra("userMail", user.getMail());
					intent.putExtra("userPassword", user.getPassword());
					
					context.startActivity(intent);
					((Activity) context).finish();
				}
			});
			btEditProfile.setText("Editer mon profil");
			btEditProfile.setBackgroundColor(Color.parseColor("#000000"));
			btEditProfile.setPadding(10, 10, 10, 10);
			btEditProfile.setTextColor(Color.parseColor("#FFFFFF"));
			lnLayout.addView(btEditProfile, buttonParams);
			
			Button btDeconnexion = new Button(context);
			btDeconnexion.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ActualUser.staticFirstname = null;
					ActualUser.staticId = -1;
					ActualUser.staticMail = null;
					ActualUser.staticName = null;
					ActualUser.staticPassword = null;
					ActualUser.staticPseudo = null;
					ActualUser.staticRole = -1;
					
					Intent intent = new Intent(context, IndexActivity.class);
	
					context.startActivity(intent);
					((Activity) context).finish();
				}
			});
			btDeconnexion.setText("Deconnexion");
			btDeconnexion.setBackgroundColor(Color.parseColor("#000000"));
			btDeconnexion.setPadding(10, 10, 10, 10);
			btDeconnexion.setTextColor(Color.parseColor("#FFFFFF"));
			lnLayout.addView(btDeconnexion, buttonParams);
		}
		
		return aView;
	}
}
