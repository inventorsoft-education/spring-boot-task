package com.example.task5.repository;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataStore {

    ArrayList<Team> getData(ArrayList<Team> team);
    HashMap<String, List<Game>> getData(HashMap<String, List<Game>> games);
    void setData(ArrayList<Team> list);
    void setData(Map<String, List<Game>> map);

}
