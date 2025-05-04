package com.main.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindow {
    int maxRequest;
   long windowSizeInMillis;
    HashMap<String,Queue<Long>> userCounter = new HashMap<>();
    public SlidingWindow(int maxRequest){
        this.maxRequest=maxRequest;
    }

    public boolean allowedRequest(String user){
        long currentTime = System.currentTimeMillis();
        userCounter.putIfAbsent(user,new LinkedList<>());
        Queue<Long> log = userCounter.get(user);

        while (!log.isEmpty()&&currentTime-log.peek()>=windowSizeInMillis){
            log.poll();
        }
        if(log.size()<maxRequest){
            log.add(currentTime);
            return true;
        }
        return false;
    }
}
