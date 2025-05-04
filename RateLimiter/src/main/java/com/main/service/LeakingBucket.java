package com.main.service;

import java.util.LinkedList;
import java.util.Queue;

public class LeakingBucket {
    int bucketSize;
    int leakingRate;

    Queue<Long> queue;
    long lastLeakingTime;

    public LeakingBucket(int bucketSize,int leakingRate){
        this.bucketSize=bucketSize;
        this.leakingRate=leakingRate;
        this.queue = new LinkedList<>();
        this.lastLeakingTime=System.currentTimeMillis();
    }

    public boolean allowedRequests(String user){
        long timeElapsed = System.currentTimeMillis()-lastLeakingTime;
        int requestToLeak = (int)(timeElapsed/1000*leakingRate);
        // No. od request to process(leak) at fixed rate(leaking rate)
        for(int i=1;i<=requestToLeak && !queue.isEmpty();i++){
            queue.poll();
        }
        if(requestToLeak>0){
            lastLeakingTime=System.currentTimeMillis();
        }
        if(queue.size()<bucketSize){
            queue.add(System.currentTimeMillis());
            return true;
        }
        return false;

    }
}
