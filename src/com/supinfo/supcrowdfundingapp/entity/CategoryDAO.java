package com.supinfo.supcrowdfundingapp.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.supinfo.supcrowdfundingapp.model.JsonParser;

public class CategoryDAO {
	
	public static List<Category> getAllCategory()
	{
		List<Category> listCategory =  new ArrayList<Category>() ;
		String result = JsonParser.getJSON("category/all");

		JSONObject jsonResponse = null;
		try {
			jsonResponse = new JSONObject(result);
		} catch (JSONException e) {
			//e.printStackTrace();
		}
        JSONArray jArray = jsonResponse.optJSONArray("category");
        int lengthJsonArr = jArray.length();  
        for(int i=0; i < lengthJsonArr; i++) 
        {
            JSONObject jsonData = null;
			try {
				jsonData = jArray.getJSONObject(i);
			} catch (JSONException e) {
				//e.printStackTrace();
			}
			
            int idCategory = jsonData.optInt("id");
            String name = jsonData.optString("name").toString();
            String content = jsonData.optString("content").toString();
            
            Category c = new Category(idCategory, content, name);
            
            listCategory.add(c);
            
       }
		return listCategory;
	}
	
	public static Category getCategory(int idCategory)
	{		
		String t = JsonParser.getJSON("category/" + idCategory);

		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject(t);
		} catch (JSONException e) {
			//e.printStackTrace();
		}
		
        String name = jsonData.optString("name").toString();
        String content = jsonData.optString("content").toString();
        
        Category c = new Category(idCategory,  content, name);
        return c;
	}
	
	public static List<Project> getProjects(int idCategory){
		List<Project> listProjects =  new ArrayList<Project>() ;
		String result = JsonParser.getJSON("category/projects/" + idCategory);
		
		Log.d("IdCategory","" + idCategory);
		Log.d("Result", result);

		JSONObject jsonResponse = null;
		try {
			jsonResponse = new JSONObject(result);
		} catch (JSONException e) {
			//e.printStackTrace();
		}
		Log.d("JsonResponse", jsonResponse.toString());
        JSONArray jArray = jsonResponse.optJSONArray("project");
        Log.d("JsonArray", jArray.toString());
        int lengthJsonArr = jArray.length();
        for(int i=0; i < lengthJsonArr; i++) 
        {
            JSONObject jsonData = null;
			try {
				jsonData = jArray.getJSONObject(i);
			} catch (JSONException e) {
				//e.printStackTrace();
			}
			JSONObject jsonCategory = jsonData.optJSONObject("category");
			String cat = jsonCategory.optString("name");
			
			int idProj = jsonData.optInt("id");
	        float price = Float.parseFloat(jsonData.optString("price").toString());
	        String name = jsonData.optString("name").toString();
	        int creator = jsonData.optInt("creator");
	        String content = jsonData.optString("content").toString();
	        String dateStart = jsonData.optString("dateStart").toString();
	        String dateEnd = jsonData.optString("dateEnd").toString();
	        
	        Project p = new Project(idProj, name, content, price);
	        p.setNomCategory(cat);
	        p.setDateStart(dateStart);
	        p.setDateEnd(dateEnd);
	        p.setCreator(creator);
            
            listProjects.add(p);
            
       }
		return listProjects;
		
	}

	public static Category getCategoryByName(String categoryName)
	{		
		categoryName = categoryName.replace(" ", "_");
		String t = JsonParser.getJSON("category/name/" + categoryName);

		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject(t);
		} catch (JSONException e) {
			//e.printStackTrace();
		}
		
		int id = jsonData.optInt("id");
        String name = jsonData.optString("name").toString();
        String content = jsonData.optString("content").toString();
        
        Category c = new Category(id,  content, name);
        return c;
	}
}
