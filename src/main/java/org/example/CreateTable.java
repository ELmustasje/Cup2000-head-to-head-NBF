package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class CreateTable {


    public HashMap<String,HashMap<String,ArrayList<Integer>>> fullTable = new HashMap<>();
    private ArrayList<String> usedIDs = new ArrayList<>();
    public String name;

    //todo: bytt ArrayList matches til å bare trenge tournament id;
    public void updateTable(ArrayList<String> ids) {
        for (String id:ids){
            if(usedIDs.contains(id)){
                System.out.println(id + " in table used");
                ids.remove(id);
            }
            else {
                usedIDs.add(id);
            }
        }

        ArrayList<String> urls = new ArrayList<>();
        ArrayList<String> allMatches = new ArrayList<>();

        for (String id : ids) {
            urls.addAll(FindURLs.tournamentURLList(id));
        }

        for (String url : urls) {
            allMatches.addAll(FindMatches.findMatches(url));
        }

        ArrayList<String> formatedMatches = FormatMatches.formatedMatches(allMatches);
        //for vin
        for (String formatedMatch : formatedMatches) {
            ArrayList<String> listFormatedMatch = new ArrayList<>(Arrays.asList(formatedMatch.split(";")));
            String maplayer = listFormatedMatch.getFirst();
            String opponent = listFormatedMatch.get(1);

            if (fullTable.containsKey(maplayer)) {
                HashMap<String, ArrayList<Integer>> playerTable = fullTable.get(maplayer);
                if (fullTable.get(maplayer).containsKey(opponent)) {
                    ArrayList<Integer> score = playerTable.get(opponent);
                    score.set(0, score.getFirst() + 1);
                    playerTable.put(opponent, score);

                } else {
                    ArrayList<Integer> score = new ArrayList<>(Arrays.asList(1, 0));
                    playerTable.put(opponent, score);
                }
                fullTable.put(maplayer, playerTable);

            } else {
                HashMap<String, ArrayList<Integer>> playerTable = new HashMap<>();
                ArrayList<Integer> score = new ArrayList<>(Arrays.asList(1, 0));
                playerTable.put(opponent, score);
                fullTable.put(maplayer, playerTable);
            }


        }
        //for tap
        for (String formatedMatch : formatedMatches) {
            ArrayList<String> listFormatedMatch = new ArrayList<>(Arrays.asList(formatedMatch.split(";")));
            String maplayer = listFormatedMatch.get(1);
            String oponent = listFormatedMatch.getFirst();
            if (fullTable.containsKey(maplayer)) {
                HashMap<String, ArrayList<Integer>> playerTable = fullTable.get(maplayer);
                if (fullTable.get(maplayer).containsKey(oponent)) {
                    ArrayList<Integer> score = playerTable.get(oponent);
                    score.set(1, score.get(1) + 1);
                    playerTable.put(oponent, score);

                } else {
                    ArrayList<Integer> score = new ArrayList<>(Arrays.asList(0, 1));
                    playerTable.put(oponent, score);
                }
                fullTable.put(maplayer, playerTable);

            } else {
                HashMap<String, ArrayList<Integer>> playerTable = new HashMap<>();
                ArrayList<Integer> score = new ArrayList<>(Arrays.asList(0, 1));
                playerTable.put(oponent, score);
                fullTable.put(maplayer, playerTable);
            }
        }
    }

    //tar et table i format HashMap<String,HashMap<String,ArrayList<Integer>>>, og raw match(FindMatches liste) som input
    //hashmapet har som nøkkel navnet på en spiller, denne spillerens value er enda et hashmap som alle den har spilt mot som key og vin,tap mot denne spilleren
    //når du bruker updateTable så gir du et laget på dette formatet som kan være tomt eller allerede har turneringer inne. OBS!
    //OBS! legger du til samme turnering to ganger så vil den telles som to turneringer.
    public static HashMap<String,HashMap<String,ArrayList<Integer>>> updateTable(HashMap<String,HashMap<String,ArrayList<Integer>>> fullTable,ArrayList<String> matches){
        ArrayList<String> formatedMatches = FormatMatches.formatedMatches(matches);
        //for vin
        for (String formatedMatch : formatedMatches){
            ArrayList<String> listFormatedMatch = new ArrayList<>(Arrays.asList(formatedMatch.split(";")));
            String maplayer = listFormatedMatch.getFirst();
            String opponent = listFormatedMatch.get(1);

            if (fullTable.containsKey(maplayer)){
                HashMap<String, ArrayList<Integer>> playerTable = fullTable.get(maplayer);
                if(fullTable.get(maplayer).containsKey(opponent)) {
                    ArrayList<Integer> score = playerTable.get(opponent);
                    score.set(0, score.getFirst()+1);
                    playerTable.put(opponent, score);

                }else {
                    ArrayList<Integer> score = new ArrayList<>(Arrays.asList(1,0));
                    playerTable.put(opponent,score);
                }
                fullTable.put(maplayer, playerTable);

            }else {
                HashMap<String,ArrayList<Integer>> playerTable = new HashMap<>();
                ArrayList<Integer> score = new ArrayList<>(Arrays.asList(1,0));
                playerTable.put(opponent,score);
                fullTable.put(maplayer,playerTable);
            }


        }
        //for tap
        for (String formatedMatch : formatedMatches){
            ArrayList<String> listFormatedMatch = new ArrayList<>(Arrays.asList(formatedMatch.split(";")));
            String maplayer = listFormatedMatch.get(1);
            String oponent = listFormatedMatch.getFirst();
            if (fullTable.containsKey(maplayer)){
                HashMap<String, ArrayList<Integer>> playerTable = fullTable.get(maplayer);
                if(fullTable.get(maplayer).containsKey(oponent)) {
                    ArrayList<Integer> score = playerTable.get(oponent);
                    score.set(1, score.get(1) + 1);
                    playerTable.put(oponent, score);

                }else {
                    ArrayList<Integer> score = new ArrayList<>(Arrays.asList(0,1));
                    playerTable.put(oponent,score);
                }
                fullTable.put(maplayer, playerTable);

            }else {
                HashMap<String,ArrayList<Integer>> playerTable = new HashMap<>();
                ArrayList<Integer> score = new ArrayList<>(Arrays.asList(0,1));
                playerTable.put(oponent,score);
                fullTable.put(maplayer,playerTable);
            }
        }

        return fullTable;
    }
}
