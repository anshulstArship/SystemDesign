package com.main.service;

import java.util.HashSet;
import java.util.Random;

public class RandomShortUrl {
    private final static String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdesfghijklmnopqrstuvwxyz0123456789";
    private HashSet<String> set = new HashSet<>();
    private Random random = new Random();

    public void generateShortUrl(int length){
        char ch[] = new char[length];
        while (true){
            for (int i=0;i<length;i++){
                int val=random.nextInt(BASE62.length()-1);
                ch[i]=BASE62.charAt(val);
            }
            String str = new String(ch);
            if(!set.contains(str)){
                set.add(str);
                return;
            }
        }
    }
}
