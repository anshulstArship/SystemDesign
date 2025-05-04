package com.main.urlshortener.service;

import java.util.HashMap;

public class Base62Decoding {
    private HashMap<String,String> longToShort = new HashMap<>();

    public String generateLongUrl(String shortUrl){
        String longUrl = base62Decoding(shortUrl);
        longToShort.put(longUrl,shortUrl);
        return longUrl;
    }

    private String base62Decoding(String str){
        int ans=0;
        for(int i=0;i<str.length();i++){
            int num=convert(str.charAt(i));
            ans= ans*62+num;
        }
        System.out.println(ans);
        return String.valueOf(ans);
    }
    private int convert(char ch){
        if(ch>='0' && ch<='9'){
            return ch-'0';
        }else if(ch>='A' && ch<='Z'){
            return ch-'A'+10;
        }
        return ch-'a'+36;
    }
}
