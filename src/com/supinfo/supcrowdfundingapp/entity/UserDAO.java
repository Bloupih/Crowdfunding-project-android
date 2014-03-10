package com.supinfo.supcrowdfundingapp.entity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.supinfo.supcrowdfundingapp.model.JsonParser;

public class UserDAO {
	
	public static User findUserById(int idUser){
		String result = JsonParser.getJSON("user/id/" + idUser);

		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
        String firstName = jsonData.optString("firstname").toString();
        String name = jsonData.optString("name").toString();
        String mail = jsonData.optString("mail").toString();
        String password = jsonData.optString("password").toString();
        String pseudo = jsonData.optString("pseudo").toString();
        int role = jsonData.optInt("role");
        
        User u = new User();
        u.setFirstname(firstName);
        u.setId(idUser);
        u.setMail(mail);
        u.setName(name);
        u.setPassword(password);
        u.setPseudo(pseudo);
        u.setRole(role);
        
		return u;
	}	
	
	public static User findUserByPseudo(String pseudo){
		String result = JsonParser.getJSON("user/pseudo/" + pseudo);

		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject(result);

			int idUser = jsonData.optInt("id");
	        String firstName = jsonData.optString("firstname").toString();
	        String name = jsonData.optString("name").toString();
	        String mail = jsonData.optString("mail").toString();
	        String password = jsonData.optString("password").toString();
	        int role = jsonData.optInt("role");
	        
	        User u = new User();
	        u.setId(idUser);
	        u.setFirstname(firstName);
	        u.setMail(mail);
	        u.setName(name);
	        u.setPassword(password);
	        u.setPseudo(pseudo);
	        u.setRole(role);
	        
	        return u;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        
		return null;
	}	
}
