package com.example.SlangList;

import com.example.SlangWord.SlangWord;
import com.example.Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public final class SlangList {
    private static SlangList instance;
    private ArrayList<SlangWord> list;
    private HashMap<String, Integer> slangMap;

    private SlangList(){
        this.list = Utils.getSlang();
        this.slangMap = new HashMap<>();

        for (int i = 0; i < list.size(); ++i)
            this.slangMap.put(list.get(i).getSlang().toLowerCase(), i);
    }

    public SlangWord slangLookUp(String key){
        Integer idx = slangMap.get(key.toLowerCase());

        if (idx != null)
            return list.get(idx);

        return null;
    }

    public static SlangList getInstance(){
        if (instance == null)
            instance = new SlangList();
        return instance;
    }
}
