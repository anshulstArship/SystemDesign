package com.main.service;

import java.util.HashMap;

public class FixedWindow {
    int maxRequest;
    long windowSizeInMs;
    HashMap<String,Long> userWindow = new HashMap<>();
    HashMap<String,Integer> userCounter = new HashMap<>();
    public FixedWindow(int maxRequest,long windowSizeInMs){
        this.maxRequest=maxRequest;
        this.windowSizeInMs = windowSizeInMs*1000;
    }

    public boolean allowedRequest(String user){
        long currentTime = System.currentTimeMillis();
        userWindow.putIfAbsent(user,System.currentTimeMillis());
        userCounter.putIfAbsent(user,0);
        long lastAccessAt = userWindow.get(user);
        if(currentTime-lastAccessAt>=windowSizeInMs){
            userWindow.put(user,currentTime);
            userCounter.put(user,0);
        }
        if(userCounter.get(user)<maxRequest){
           userCounter.put(user,userCounter.get(user)+1);
            return true;
        }
        return false;
    }
}
