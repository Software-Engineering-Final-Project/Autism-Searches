package com.articlefetch.app.Busniess.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CsvLoader {

    public static HashMap<Integer, ArrayList<Integer>> csvToMap(){

        String csvFile = "src/DatabaseSchemas/returnResults.csv";
        String line = "";
        String cvsSplitBy = ",";
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int index = 0;
            while ((line = br.readLine()) != null) {

                if(index != 0) {
                    // use comma as separator
                    String[] eles = line.split(cvsSplitBy);

                    System.out.println(line);

                    System.out.println(eles[0]);

                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(Integer.parseInt(eles[1]));
                    a.add(Integer.parseInt(eles[2]));
                    a.add(Integer.parseInt(eles[3]));

                    map.put(Integer.parseInt(eles[0]), a);
                }
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }






}
