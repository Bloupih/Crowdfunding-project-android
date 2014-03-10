package com.supinfo.supcrowdfundingapp.entity;

import java.util.ArrayList;

public class Category {

	private int id;
	private String name;
	private String content;
	private ArrayList<Project> projects;
	
	public Category(String content, String name){
		this.name = name;
		this.content = content;
	}
	
	public Category(int id, String content, String name){
		this.id = id;
		this.name = name;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void addProject(Project project){
		projects.add(project);
	}
	
	public ArrayList<Project> getProjects(){
		return projects;
	}

	@Override
	public String toString(){
		return name;
	}
}
