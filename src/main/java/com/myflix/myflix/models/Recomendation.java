/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myflix.myflix.models;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author andyc
 */
public class Recomendation {

    Socket sc = null;
    boolean sent = false;
    OutputStream os = null;
    PrintWriter out = null;
    String ip = "34.132.46.46";

    public Recomendation() {
        
        
    }

    public ArrayList<String> getRecomendation(int User) {
        ArrayList<String> recs = new ArrayList<String>();
        DataInputStream in = null;
        try {
            sc = new Socket(ip, 80);
        } catch (Exception et) {
            System.out.println("Host not found");
            return null;
        }
        
        try {
            os = sc.getOutputStream();
            out = new PrintWriter(os);
        } catch (Exception et) {
            System.out.println("Can't get Output Stream");
            return(null);
        }
        out.println(User);
        out.flush();
        try{
       TimeUnit.MILLISECONDS.sleep(50);
        }catch(Exception et){
            System.out.println("Can't sleep");
        }
        try {
            in = new DataInputStream(new BufferedInputStream(sc.getInputStream()));
        } catch (Exception et) {
            System.out.println("Can't get input Stream");
            return (null);
        }
        try {
            int Available=in.available();
            while (in.available() > 0) {
                String ll = in.readLine();
                
                recs.add(ll);
                System.out.println(ll);
            }
        } catch (Exception et) {
            System.out.println("Error reading  data");
            return (null);
        }
        try{
         out.close();
        in.close();
        sc.close();
        }catch(Exception et){
            System.out.println("Error closing input");
            return (null);
            
        }
        return recs;
    }

}
