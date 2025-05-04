package com.main.urlshortener.service;

import java.util.HashSet;
import java.util.Random;

public class RandomShortUrl {
    private final static String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdesfghijklmnopqrstuvwxyz0123456789";
    private final Random random = new Random();
    private final HashSet<String> shortUrls = new HashSet<>();

    public String generateShortUrl(int length){
        char[] ch = new char[length];
        while (true){
            for(int i=0;i<length;i++){
                int index = random.nextInt(BASE62.length()-1);
                ch[i]=BASE62.charAt(index);
            }
            String str = new String(ch);
            if(!shortUrls.contains(str)){
                shortUrls.add(str);
                return str;
            }
        }
    }
}
