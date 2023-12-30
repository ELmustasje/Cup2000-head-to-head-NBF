package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FindMatches {
    public static void main(String[] args) {
        ArrayList<String> matches = FindMatches.findMatches("https://www.cup2000.dk/turnerings-system/Vis-turneringer/?tournamentid=10556&lr=1&vi=0&dt=&pi=-1&c=-1&page=0");

    }
    public static ArrayList<String> findMatches(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String dataString = doc.select("div#divshowtournament").toString();
        dataString = dataString//rydde opp i strengen
                .replaceAll("[{.(:]","")
                .replace("\"","")
                .replace("[","")
                .replace("]","")
                .replace(";","");
        ArrayList<String> rawDataArray = new ArrayList<>(Arrays.asList(dataString.split(",")));
        ArrayList<String> sortedDataArray = new ArrayList<>();
        rawDataArray.removeFirst();
        rawDataArray.removeLast();

        for (String data : rawDataArray){//finner navn og legger til i sortedDataArray
            if(!data.isEmpty()){
                if(Character.isUpperCase(data.charAt(0))||data.startsWith("\\u0026")&&!data.contains("WO")){
                    if(!data.contains("Herre")&&!data.contains("Dame")&&!data.contains("Mixed")&&!data.startsWith("WO")){
                        sortedDataArray.add(data);
                    }
                }
            }
            if(data.contains("/")&&!data.contains("WO")){//finner score og legger til i sortedDataArray
                sortedDataArray.add(data);
            }
        }


        //lager kampene i json format og legger de til i en liste
        ArrayList<String> matches = new ArrayList<>();
        StringBuilder currentMatch = new StringBuilder();
        for (String data : sortedDataArray) {
            data.replaceAll(";","");
            if (currentMatch.isEmpty()) {
                currentMatch.append(data+";");
            } else if (Character.isDigit(data.charAt(0))||data.contains("WO")) {
                matches.add(currentMatch.toString());
                currentMatch = new StringBuilder();
                currentMatch.append(data+";");
            } else {
                currentMatch.append(data + ";");
            }
        }

        int first = currentMatch.indexOf(";");
        int sec = currentMatch.indexOf(";",first+1);
        int third = currentMatch.indexOf(";",sec+1);
        currentMatch.delete(third+1,currentMatch.length());
        matches.add(currentMatch.toString());

        return matches;
    }
}