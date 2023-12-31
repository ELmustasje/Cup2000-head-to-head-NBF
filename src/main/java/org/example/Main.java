package org.example;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CreateTable testTable = new CreateTable();
        ArrayList<String>ids = FindIDs.findIds(8650,9650);
        ids.addAll(FindIDs.findIds(9651,10600));
        testTable.updateTable(ids);
        System.out.println("Finding stats from: "+ids.size()+" tournaments");
        System.out.println(testTable.fullTable.get("Thomas Barth"));
    }
}
