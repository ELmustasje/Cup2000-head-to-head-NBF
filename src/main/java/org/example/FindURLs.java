package org.example;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;


public class FindURLs {
    public static void main(String[] args) {
        ArrayList<String> tr = tournamentURLList("10250");

    }
    public static ArrayList<String> tournamentURLList(String tournamentID){
        ArrayList<String> URLList = new ArrayList<>();
        String page = "0";
        String url = "https://www.cup2000.dk/turnerings-system/Vis-turneringer/?tournamentid="+tournamentID+"&lr=1&vi=-1&dt=&pi=-1&c=-1&page="+page;
        Document doc;
        System.out.println("FINDING URLs, from tournament: " + tournamentID);
        while (FindMatches.findMatches(url).size() != 1) {
            System.out.println(url);
            URLList.add(url);
            page = String.valueOf(Integer.parseInt(page) + 1);
            url = "https://www.cup2000.dk/turnerings-system/Vis-turneringer/?tournamentid=" + tournamentID + "&lr=1&vi=-1&dt=&pi=-1&c=-1&page=" + page;
        }

        return URLList;

    }
}
