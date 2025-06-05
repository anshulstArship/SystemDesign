package com.main.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5ShortUrl {
    public String convert(String longUrl)  {
       try{
           MessageDigest digest = MessageDigest.getInstance("MD5");
           digest.update(longUrl.getBytes());
           byte[] messageDigest = digest.digest();
           StringBuilder str = new StringBuilder();
           for(byte b:messageDigest){
               str.append(Integer.toHexString(0XFF & b));
           }
           return str.toString();
       }catch (Exception e){
           System.out.println(e.getStackTrace());
       }
       return null;
    }
}
