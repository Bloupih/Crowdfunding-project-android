package com.supinfo.supcrowdfundingapp.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfundingapp.model.JsonParser;

public class ProjectDAO {

	public static List<Project> getAllProject()
	{
		List<Project> listproj =  new ArrayList<Project>() ;
		String result = JsonParser.getJSON("project/all");

		JSONObject jsonResponse = null;
		try {
			jsonResponse = new JSONObject(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONArray jArray = jsonResponse.optJSONArray("project");
        int lengthJsonArr = jArray.length();  
        for(int i=0; i < lengthJsonArr; i++) 
        {
            JSONObject jsonData = null;
			try {
				jsonData = jArray.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject jsonCategory = jsonData.optJSONObject("category");
			String cat = jsonCategory.optString("name");
			
            float price = Float.parseFloat((jsonData.optString("price").toString()));
            int idProject = jsonData.optInt("id");
            String name = jsonData.optString("name").toString();
            String content = jsonData.optString("content").toString();
            
            Project p = new Project(idProject, name, content, price);
            p.setNomCategory(cat);
            
            listproj.add(p);
            
       }
		return listproj;
	}
	
	public static Project getProject(int idProject)
	{		
		String t = JsonParser.getJSON("project/" + idProject);

		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject(t);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        return p;
	}
}
