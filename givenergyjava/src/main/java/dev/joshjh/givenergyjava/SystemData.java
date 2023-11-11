package dev.joshjh.givenergyjava;

import org.json.JSONObject;

public class SystemData extends JSONObject{
    
    public SystemData(JSONObject JSONObject) {
        this.put("data", JSONObject.get("data"));
    } 
}
