package com.supinfo.supcrowdfundingapp.entity;

public class Project {

	private int id;

	private String name;

	private String content;
	private float price;
	private int creator;
	private String dateStart;
	private String dateEnd;
	private int idCategory;
	private String nomCategory = null;
	
	public Project (int id, String name, String content, float price){
		this.id = id;
		this.content = content;
		this.name = name;
		this.price = price;
	}

	/*public Project (String content, int creator, String dateEnd, String dateStart, int idCategory, String name, float price){
		this.content = content;
		this.creator = creator;
		this.dateEnd = dateEnd;
		this.dateStart = dateStart;
		this.idCategory = idCategory;
		this.name = name;
		this.price = price;
	}*/
	public Project (int id, String content, int creator, String dateEnd, String dateStart, int idCategory, String name, float price){
		this.id = id;
		this.content = content;
		this.creator = creator;
		this.dateEnd = dateEnd;
		this.dateStart = dateStart;
		this.idCategory = idCategory;
		this.name = name;
		this.price = price;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomCategory(String cat){
		this.nomCategory = cat;
	}
	
	public String getNomCategory(){
		return this.nomCategory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	
	public String getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public void setCategory(Category category) {
		this.idCategory = category.getId();
	}
	
	public int getCategoryId(){
		return this.idCategory;
	}
	
	public void setCreator(int creator)
	{
		this.creator = creator;
	}
	
	public int getCreatorId(){
		return this.creator;
	}
	
	public String getMiniDescription()
	{
		int description_length = this.content.length();
		String description_truncated ;
		if(description_length > 110)
		{
			description_truncated = this.content.substring(0, 107)+ "..." ;
		}
		else
		{
			description_truncated = this.content ;
		}
		return description_truncated;
	}
}
