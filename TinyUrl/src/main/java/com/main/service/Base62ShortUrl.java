package com.main.service;

import java.util.HashMap;

public class Base62ShortUrl {
    private final static String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdesfghijklmnopqrstuvwxyz0123456789";
    private int COUNTER=100000;
    private HashMap<String,Integer> longToShort  = new HashMap<>();
    private HashMap<Integer,String> shortToLong = new HashMap<>();

    public String longToShort(String longUrl){
        String shortUrl = base10To62(COUNTER);
        longToShort.put(longUrl,COUNTER);
        shortToLong.put(COUNTER,longUrl);
        COUNTER++;
        return shortUrl;
    }
    public String shortToLong(String shortUrl){
        int num = base62To10(shortUrl);
        return shortToLong.get(num);
    }
    private String base10To62(int num){
        StringBuilder str = new StringBuilder();
        while(num>0){
            str.insert(0,BASE62.charAt(num%62));
            num/=62;
        }
        while (str.length()!=7){
            str.insert(0,'0');
        }
        return str.toString();
    }

    private int base62To10(String str){
        int num=0;
        for(int i=0;i<str.length();i++){
            num = num*62+convert(str.charAt(i));
        }
        return num;
    }
    private int convert(char ch){
        if(ch>='0' && ch<='9'){
            return ch-'0';
        }else if(ch>='a' && ch<='z'){
            return ch-'a'+10;
        }else if(ch>='A'&& ch<='Z'){
            return ch-'A'+36;
        }
        return -1;
    }
}
