package com.main.service;

public class TokenBucket {
    int bucketSize;
    double refillRatePerSec;
    int tokens;
    long lastRefillTime;

    public TokenBucket(int bucketSize,int refillRate){
        this.bucketSize=bucketSize;
        this.tokens=bucketSize;
        this.refillRatePerSec=1; // 1token is added in each sec
        this.lastRefillTime=System.currentTimeMillis();
    }

    public boolean allowedRequests(String user){
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime-lastRefillTime;
        int tokensToAdd = (int)((elapsedTime/1000)*refillRatePerSec);
        if(tokensToAdd>0){
            tokens = Math.min(bucketSize,tokensToAdd+tokens);
            lastRefillTime=currentTime;
        }
        if(tokens>0){
            tokens--;
            return true;
        }
        return false;
    }
}
