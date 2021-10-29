package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteToFile {

    private static StringBuilder sb = new StringBuilder();
    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new File("test.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeHead(){
        sb.append("Round:,");
        sb.append("Team1,");
        sb.append("Team2,");
        sb.append("Score");
        sb.append('\n');

        writer.write(sb.toString());
        sb.delete(0,sb.length());

    }
    public static void writeDate(String round,String team1,String team2,String score){
        sb.append(round).append(",");
        sb.append(team1).append(",");
        sb.append(team2).append(",");
        sb.append(score);
        sb.append('\n');

        writer.write(sb.toString());
        sb.delete(0,sb.length());
    }

    public static void close(){
        writer.close();
    }

}
