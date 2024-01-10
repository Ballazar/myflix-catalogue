/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myflix.myflix.models;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.myflix.myflix.lib.Web;
import com.myflix.myflix.stores.Category;
import com.myflix.myflix.stores.Video;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author andy
 */
public class categories {
    public void categories() {

    }
    
    public LinkedList<Category> listcategories() throws IOException {

        LinkedList<Category> categorielist=new LinkedList();
        String videos = "http://34.16.96.108/myflix/categories";

        JsonObject obj = new JsonObject();
        obj = Web.GetJson(videos);

       
                    List<String> ll = obj.names();
                    JsonObject obj2 = obj.get("_embedded").asObject();
                    ll = obj2.names();
                    JsonArray items = obj2.get("rh:doc").asArray();
                    int number =items.size();
                    for (JsonValue item : items) {
                        JsonObject obj3 = item.asObject();
                        ll = obj3.names();
                        int i = 0;
                        for(String l:ll){
                            if (l.compareTo("category") == 0) {
                                HashMap<String,String> fields=new HashMap();
                                String category = obj3.get("category").asString();
                                
                                   
                              
                                Category vv=new Category();
                                vv.setCategory(category);
                                categorielist.add(vv);
                            }
                            
                        }
                        
                    }


        return categorielist;
    }

    
}
