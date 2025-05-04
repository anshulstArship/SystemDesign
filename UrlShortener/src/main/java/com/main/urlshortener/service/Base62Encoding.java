package com.main.urlshortener.service;

import java.util.HashMap;

public class Base62Encoding {
    HashMap<String,String> longToShort = new HashMap<>();
    private static Integer counter=11157;
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public String generateShortUrl(String longUrl){
        String shortUrl = base62Encoding();
        longToShort.put(longUrl,shortUrl);

        return shortUrl;
    }
    public String base62Encoding(){
        int num = counter;
        StringBuilder str = new StringBuilder();
        while (num>0){
            int rem=num%62;
            str.append(BASE62.charAt(rem));
            num/=62;
        }
        counter++;
        return str.reverse().toString();

    }

}
