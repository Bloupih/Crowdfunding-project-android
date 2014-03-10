package com.supinfo.supcrowdfundingapp.entity;

public class ActualUser {

	public static int staticRole = -1;
	
	public static int staticId = -1;

	public static String  staticPseudo = null;
	
	public static String  staticPassword = null;
	
	public static String staticMail = null;

	public static String staticName = null;
	
	public static String staticFirstname = null;
	
	public static User getUser(){
		User u = new User();
		u.setRole(staticRole);
		u.setId(staticId);
		u.setPseudo(staticPseudo);
		u.setFirstname(staticFirstname);
		u.setMail(staticMail);
		u.setName(staticName);
		
		return u;
	}
}
