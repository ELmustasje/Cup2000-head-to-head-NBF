package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class FindIDs {
    public static void main(String[] args) throws IOException {

    }

    public static ArrayList<String> findIds(int startID,int endID) {
        int id = startID;
        ArrayList<String> ids = new ArrayList<>();
        while (id <= endID) {
            System.out.println(id);
            String url = "https://www.cup2000.dk/turnerings-system/Vis-turneringer/?tournamentid=" + id;
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String info = doc.select("div#divshowtournament").toString();
            if (info.contains("Badminton Klubb") || info.contains("Badmintonklubb") || info.contains("IL")||info.contains("Norges")||info.contains("Forbund")) {
                ids.add(Integer.toString(id));
            }
            id++;
        }
        return ids;

    }
}
