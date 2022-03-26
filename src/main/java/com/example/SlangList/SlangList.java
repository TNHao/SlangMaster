package com.example.SlangList;

import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import com.example.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class SlangList {
    private static SlangList instance;
    private ArrayList<SlangWord> list;
    private HashMap<String, Integer> slangMap;

    private SlangList() {
        this.list = Utils.getSlang();
        this.slangMap = new HashMap<>();

        for (int i = 0; i < list.size(); ++i)
            this.slangMap.put(list.get(i).getSlang().toLowerCase(), i);
    }

    public ArrayList<SlangWord> getList() {
        return list;
    }

    public SlangWord slangLookUp(String key) {
        Integer idx = slangMap.get(key.toLowerCase());

        if (idx != null)
            return list.get(idx);

        return null;
    }

    public boolean addSlang(SlangWord slangWord) {
        try {
            list.add(slangWord);
            slangMap.put(slangWord.getSlang(), list.size() - 1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeSlang(String slang) {
        try {
            Integer idx = slangMap.remove(slang);
            list.remove(idx.intValue());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void resetList() throws IOException {
        list.clear();
        slangMap.clear();
        instance = null;
        Utils.copyFile(Constant.originSlang, Constant.slangFile);
    }

    public SlangWord randomSlang() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public static SlangList getInstance() {
        if (instance == null)
            instance = new SlangList();
        return instance;
    }
}
