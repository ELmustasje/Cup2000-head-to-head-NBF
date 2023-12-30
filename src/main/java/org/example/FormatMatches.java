package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class FormatMatches {
    public static void main(String[] args) {
        ArrayList<String> matches = FindMatches.findMatches("https://www.cup2000.dk/turnerings-system/Vis-turneringer/?tournamentid=10556&lr=1&vi=0&dt=&pi=-1&c=-1&page=0");

        ArrayList<String> formatedMatches = FormatMatches.formatedMatches(matches);

        System.out.println(formatedMatches);

    }
    public static ArrayList<String> formatedMatches (ArrayList<String> matches){//får matches i formatet vinner;taper;setvinner/settaper
        ArrayList<String> formatedMatches = new ArrayList<>();

        for(String data : matches){
            ArrayList<String> dataList = new ArrayList<>(Arrays.asList(data.split(";")));
            ArrayList<String> scoresList = new ArrayList<>(Arrays.asList(dataList.getFirst()));
            int leftPlayer = 0;//regner ut hvem som vinner settene
            int rigthPlayer = 0;
            for(String s : scoresList){
                ArrayList<String> matchScore = new ArrayList<>(Arrays.asList(s.split(" ")));
                for(String score : matchScore){
                    ArrayList<String> setScore = new ArrayList<>(Arrays.asList(score.split("/")));
                    if(Character.isDigit(setScore.getFirst().charAt(0))){
                        if((Integer.parseInt(setScore.getFirst()) > Integer.parseInt(setScore.getLast()))){
                            leftPlayer++;
                        }else {
                            rigthPlayer++;
                    }
                    }
                }
            }
            StringBuilder set = new StringBuilder();
            dataList.removeFirst();
            StringBuilder fMatch = new StringBuilder();
            String winner;
            String loser;
            if (leftPlayer == 0&&rigthPlayer==0){
                continue;
            }
            if(leftPlayer > rigthPlayer){
                winner = dataList.subList(0,dataList.size()/2).toString();
                loser = dataList.subList(dataList.size()/2,dataList.size()).toString();
                set.append(leftPlayer+"/"+rigthPlayer);
            }else {
                loser = dataList.subList(0,dataList.size()/2).toString();
                winner = dataList.subList(dataList.size()/2,dataList.size()).toString();
                set.append(rigthPlayer+"/"+leftPlayer);
            }
            String fwinner = winner.replace("[","").replace("]","");
            String floser = loser.replace("[","").replace("]","");
            fMatch.append(fwinner+";");
            fMatch.append(floser+";");
            fMatch.append(set);
            formatedMatches.add(fMatch.toString());
        }

        return formatedMatches;
    }
}
