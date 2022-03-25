package com.example.Utils;

import com.example.SlangWord.SlangWord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<SlangWord> getSlang(){
        ArrayList<SlangWord> slangWords = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Constant.slangFile));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                slangWords.add(new SlangWord(line));
//                System.out.println(slangWords.get(slangWords.size() - 1).toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return slangWords;
    }

    public static void main(String[] args) {
        getSlang();
    }
}
