package com.zhan.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonOper {

	public static void main(String[] args) {
		 System.out.println("23230");
		buildJson();
		getJson();
	}
	
	public static void buildJson(){
		
		   //生成Json 
		    JSONArray arryAll = new JSONArray();
		    
		    JSONObject jsonObject = new JSONObject();
		    
		    for(int j = 1; j < 6; j++){
		    	jsonObject = new JSONObject();
			    jsonObject.put("productid", j);
			    JSONArray arry = new JSONArray();
			    for(int i = 1; i < 6; i++){
			    	JSONObject jsonObjectarry1 = new JSONObject();
				    jsonObjectarry1.put("sku", i);
				    jsonObjectarry1.put("num", i);
				    arry.add(jsonObjectarry1);
			    }
			    jsonObject.put("list", arry);
			    
			    arryAll.add(jsonObject);
		    }
		    System.out.println(arryAll.toString());
	}
	
	
	public static void getJson(){
	    //取Json 
	    String jsonString = "[{\"productid\":1,\"list\":[{\"num\":1,\"sku\":1},{\"num\":2,\"sku\":2},{\"num\":3,\"sku\":3},{\"num\":4,\"sku\":4},{\"num\":5,\"sku\":5}]},{\"productid\":2,\"list\":[{\"num\":1,\"sku\":1},{\"num\":2,\"sku\":2},{\"num\":3,\"sku\":3},{\"num\":4,\"sku\":4},{\"num\":5,\"sku\":5}]},{\"productid\":3,\"list\":[{\"num\":1,\"sku\":1},{\"num\":2,\"sku\":2},{\"num\":3,\"sku\":3},{\"num\":4,\"sku\":4},{\"num\":5,\"sku\":5}]},{\"productid\":4,\"list\":[{\"num\":1,\"sku\":1},{\"num\":2,\"sku\":2},{\"num\":3,\"sku\":3},{\"num\":4,\"sku\":4},{\"num\":5,\"sku\":5}]},{\"productid\":5,\"list\":[{\"num\":1,\"sku\":1},{\"num\":2,\"sku\":2},{\"num\":3,\"sku\":3},{\"num\":4,\"sku\":4},{\"num\":5,\"sku\":5}]}]";
	    
	    JSONObject jsonObject = new JSONObject();
	    JSONObject jsonObjectSKU = new JSONObject();
	    JSONArray jsonArry = JSONArray.parseArray(jsonString);
	    for(int i=0; i<jsonArry.size(); i++){
	    	jsonObject = jsonArry.getJSONObject(i);
	    	
	    	String productid = jsonObject.getString("productid");
	    	System.out.println("productid : " + productid);
	    	JSONArray jsonArrySub = jsonObject.getJSONArray("list");
	    	for(int j = 0; j < jsonArrySub.size(); j++){
	    		jsonObjectSKU = jsonArrySub.getJSONObject(j);
	    		System.out.println("sku : " + jsonObjectSKU.getString("sku"));
	    		System.out.println("num : " + jsonObjectSKU.getString("num"));
	    	}
	    }
		    
		    
		   
	}

}