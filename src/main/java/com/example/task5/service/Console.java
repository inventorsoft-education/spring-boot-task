package com.example.task5.service;

import com.example.task5.model.Game;
import com.example.task5.repository.ListOfGames;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Console {

    public<T> void printList(String message, List<T> list){
        System.out.println(message);
        for ( T t: list) {
            System.out.println(t);
        }
    }
    
    public  <K> void printMap(String message, Map<String, List<K>> map){
        System.out.println(message);
        for (String s : map.keySet()) {
            map.get(s).stream().forEach(System.out::println);
        }
    }

}
