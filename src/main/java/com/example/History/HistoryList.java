package com.example.History;

import com.example.Utils.Constant;

import java.io.*;
import java.util.ArrayList;

public class HistoryList {
    private static HistoryList instance;
    private ArrayList<History> list;

    private HistoryList() {
        list = new ArrayList<>();
        getListFromFile();
    }

    public ArrayList<History> getList() {
        return list;
    }

    public void addHistory(History history){
        list.add(history);
        saveList();
    }

    public static HistoryList getInstance() {
        if (instance == null)
            instance = new HistoryList();
        return instance;
    }

    public void saveList(){
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constant.historyFile))
        ) {
            objectOutputStream.writeInt(this.list.size());
            for (History history : this.list)
                objectOutputStream.writeObject(history);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean getListFromFile(){
        if (this.list != null) this.list.clear();

        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(Constant.historyFile))
        ) {
            int n = objectInputStream.readInt();
            for (int i = 0; i < n; ++i) {
                History history = (History) objectInputStream.readObject();
                this.list.add(history);
//                System.out.println(history.toString());
            }
            return true;
        } catch (EOFException eof) {
            System.out.println("Reached end of file");
            return false;
        } catch (IOException | ClassNotFoundException ex) {
            return false;
        }
    }
}
