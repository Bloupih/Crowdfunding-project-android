package com.supinfo.supcrowdfundingapp.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.supinfo.supcrowdfundingapp.model.JsonParser;

public class TransactionDAO {
	
	public static List<Transaction> getAllTransaction()
	{
		List<Transaction> listTransactions =  new ArrayList<Transaction>() ;
		String result = JsonParser.getJSON("/transaction/all");

		JSONObject jsonResponse = null;
		try {
			jsonResponse = new JSONObject(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONArray jArray = jsonResponse.optJSONArray("transaction");
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
			
			float contributedValue = Float.parseFloat(jsonData.optString("contributedValue"));
            String date = jsonData.optString("date").toString();
            int idProject = jsonData.optInt("idProject");
            int id = jsonData.optInt("id");
            int idUser = jsonData.optInt("iduser");
            
            Transaction t = new Transaction();
            t.setContributedValue(contributedValue);
            t.setDate(date);
            t.setId(id);
            t.setIdProject(idProject);
            t.setIdUser(idUser);
            
            listTransactions.add(t);
            
        }
        return listTransactions;
	}
	
	public static List<Transaction> getTransactionsByProject(int idProject)
	{
		List<Transaction> listTransactions =  new ArrayList<Transaction>();
		String result = JsonParser.getJSON("transaction/project/" + idProject);
		
		if (result != null && result != ""){
			JSONObject jsonResponse = null;
			boolean canContinue = true;
			try {
				jsonResponse = new JSONObject(result);
			} catch (JSONException e) {
				//e.printStackTrace();
				canContinue = false;
			}
			if (canContinue == true){
		        JSONArray jArray = jsonResponse.optJSONArray("transaction");
		        int lengthJsonArr = jArray.length();
		        for(int i=0; i < lengthJsonArr; i++) 
		        {
		            JSONObject jsonData = null;
					try {
						jsonData = jArray.getJSONObject(i);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					float contributedValue = Float.parseFloat(jsonData.optString("contributedValue"));
		            String date = jsonData.optString("date").toString();
		            int id = jsonData.optInt("id");
		            int idUser = jsonData.optInt("iduser");
		            
		            Transaction t = new Transaction();
		            t.setContributedValue(contributedValue);
		            t.setDate(date);
		            t.setId(id);
		            t.setIdProject(idProject);
		            t.setIdUser(idUser);
		            
		            listTransactions.add(t);
		            
		        }
			}
		}
        return listTransactions;
	}

	public static float getContributedValueByProject(int idProject){
		
		List<Transaction> transactions = getTransactionsByProject(idProject);
		float contributed = 0 ;
		for(Transaction tr : transactions){
			contributed += tr.getContributedValue();
		}
		
		return contributed;
	}
}
