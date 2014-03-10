package com.supinfo.supcrowdfundingapp.entity;

public class Role {
	
	private int id;
	private String titre;
	
	public Role(){}
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	
	public void setTitre(String titre){
		this.titre = titre;
	}
	
	public String getTitre(){
		return this.titre;
	}
}
