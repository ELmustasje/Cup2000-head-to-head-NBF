package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CreateTable testTable = new CreateTable();
        ArrayList<String>ids = FindIDs.findIds(10080,10100);
        System.out.println("Finding stats from: "+ids.size()+" tournaments");
        testTable.updateTable(ids);
        ArrayList<String>moreids= new ArrayList<>();
        moreids.add("10089");
        moreids.add("10090");
        testTable.updateTable(moreids);
        System.out.println(testTable.fullTable.get("Thomas Barth"));
    }
}
