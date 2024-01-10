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
import com.myflix.myflix.stores.Video;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author andy
 */
public class catalogue {

    
    String CatalogueServer="34.16.96.108";
    
    public void catalogue() {

    }

    public Video video(String sUUID) throws IOException {

        Video videol = new Video();

        String videos = "http://"+CatalogueServer+"/myflix/videos?filter={\"video.uuid\":\"" + sUUID + "\"}";

        JsonObject obj = new JsonObject();
        obj = Web.GetJson(videos);
        //System.out.println(sBuff);

        List<String> ll = obj.names();
        JsonArray obj2 = null;
        try{
            obj2=obj.get("_embedded").asArray();
        }catch(Exception et){
            System.out.println("Can't get _emmbedded "+et);
        }
        for (JsonValue item:obj2){
            System.out.println(item);
            JsonObject obj3 = item.asObject();
            JsonObject jVideo=obj3.get("video").asObject();
            List<String> names = jVideo.names();
            HashMap<String, String> fields = new HashMap();
            for (String name : names) {
                JsonValue Value = jVideo.get(name);
                String sValue = Value.toString();
                fields.put(name, sValue);

            }
            videol.setFields(fields);
        } 
        
        /*
        ll = obj2.names();
        JsonArray items = obj2.get("rh:doc").asArray();
        int number = items.size();
        for (JsonValue item : items) {
            JsonObject obj3 = item.asObject();
            ll = obj3.names();
            int i = 0;
            for (String l : ll) {
                if (l.compareTo("video") == 0) {
                    HashMap<String, String> fields = new HashMap();
                    JsonObject video = obj3.get("video").asObject();
                    List<String> names = video.names();
                    for (String name : names) {
                        JsonValue Value = video.get(name);
                        String sValue = Value.toString();
                        fields.put(name, sValue);

                    }

                    videol.setFields(fields);

                }

            }

        }
        */
        return videol;

    }

    public LinkedList<Video> videos() throws IOException {

        LinkedList<Video> videolist = new LinkedList();
        String videos = "http://"+CatalogueServer+"/myflix/videos";

        JsonObject obj = new JsonObject();
        obj = Web.GetJson(videos);

        List<String> ll = obj.names();
        JsonArray obj2 = null;
        try{
            obj2=obj.get("_embedded").asArray();
        }catch(Exception et){
            System.out.println("Can't get _emmbedded "+et);
        }
        
        for (JsonValue item:obj2){
            System.out.println(item);
            JsonObject obj3 = item.asObject();
            JsonObject jVideo=obj3.get("video").asObject();
            List<String> names = jVideo.names();
            HashMap<String, String> fields = new HashMap();
            for (String name : names) {
                JsonValue Value = jVideo.get(name);
                String sValue = Value.toString();
                fields.put(name, sValue);

            }
            Video vv = new Video();
            vv.setFields(fields);
            videolist.add(vv);
        } 
        
        return videolist;
    }

}
