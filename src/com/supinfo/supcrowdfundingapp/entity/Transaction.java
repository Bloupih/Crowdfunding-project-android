package com.supinfo.supcrowdfundingapp.entity;

public class Transaction {

	private int id;
	private int idProject;
	private int idUser;
	private float contributedValue;
	private String date;
	
	public Transaction(){}
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	
	public void setIdProject(int id){
		this.idProject = id;
	}
	
	public int getIdProject(){
		return this.idProject;
	}
	
	public void setIdUser(int id){
		this.idUser = id;
	}
	
	public int getIdUser(){
		return this.idUser;
	}
	
	public void setContributedValue(float price)
	{
		this.contributedValue = price;
	}
	
	public float getContributedValue()
	{
		return this.contributedValue;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String string) {
		this.date = string;
	}
	
}