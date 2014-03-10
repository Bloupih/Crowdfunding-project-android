package com.supinfo.supcrowdfundingapp.entity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	private int id;

	private String  pseudo;
	
	private String  password;
	
	private int role;
	
	private String mail;

	private String name;
	
	private String firstname;
	
	public User(){}
	
	public User (User user){
		this.id = user.getId();
		this.pseudo = user.getPseudo();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.mail = user.getMail();
		this.name = user.getName();
		this.firstname = user.getFirstname();
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getPseudo()
	{
		return this.pseudo;
		
	}
	
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo ;
		
	}
	public String getPassword()
	{
		return this.password;
		
	}
	
	public void setPassword(String password)
	{
		this.password = password;
		
	}
	
	 public static String passwordToMd5(String password){
	        String digest = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] hash = md.digest(password.getBytes("UTF-8"));
	           
	            //converting byte array to Hexadecimal String
	           StringBuilder sb = new StringBuilder(2*hash.length);
	           for(byte b : hash){
	               sb.append(String.format("%02x", b&0xff));
	           }
	          
	           digest = sb.toString();
	          
	        } catch (UnsupportedEncodingException ex) {
	        } catch (NoSuchAlgorithmException ex) {
	        }
	        return digest;
	    }
	
	public int getRole(){
		return this.role;
	}
	
	public void setRole(int role){
		this.role = role;
	}
	
	
	
	public String getMail(){
		return this.mail;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
		
	
	
	public String getFirstname(){
		return this.firstname;
	}
	
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}	
	
	
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}
