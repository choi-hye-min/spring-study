package com.tuyano.springboot;

public class MyTLUtility {

    public String hello(String name){
        return "Hello, <b>"+name+"!</b>";
    }

    public String prevUrl(int num){
        return "/pasge/"+(num > 1 ? num -1 : 1);
    }

    public String nextUrl(int num){
        return "/page/"+(num+1);
    }
}
