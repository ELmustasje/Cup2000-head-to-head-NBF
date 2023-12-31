package org.example;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CreateTable testTable = new CreateTable();
        ArrayList<String>ids = FindIDs.findIds(8000,10700); //2018 - 2023
        testTable.updateTable(ids);
        System.out.println(testTable.fullTable.get("Thomas Barth"));

    }
}
