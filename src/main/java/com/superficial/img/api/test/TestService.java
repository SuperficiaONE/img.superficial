package com.superficial.img.api.test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TestService {

    private static Map<String ,Integer> map = new HashMap<>();
    private static ConcurrentHashMap<String ,Integer> concurrentHashMap = new ConcurrentHashMap<>();
    public static void testMap(){
           addMap("cc");
        System.out.println("HashMap :"+map.get("cc"));
    }
    public  static void  printMap(){
        System.out.println("HashMap :"+map.get("cc"));
    }
    public static void printConcurrentMap(){
        System.out.println("ConcurrentHashMap : "+concurrentHashMap.get("cc"));

    }

    public static void  testConMap(){
        addCourrntMap("cc");
        System.out.println("ConcurrentHashMap : "+concurrentHashMap.get("cc"));
    }

    public static synchronized void addMap(String key){
            Integer value = map.get(key);
            if(value==null){
                map.put(key,1);
            }else {
                map.put(key,value+1);
            }
    }
    public static void addCourrntMap(String key){
        Integer value = concurrentHashMap.get(key);
        if(value==null){
            concurrentHashMap.put(key,1);
        }else {
            concurrentHashMap.put(key,value+1);
        }
    }
}
