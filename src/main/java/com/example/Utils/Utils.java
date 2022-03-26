package com.example.Utils;

import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import javafx.scene.control.Alert;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<SlangWord> getSlang() {
        ArrayList<SlangWord> slangWords = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Constant.slangFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                slangWords.add(new SlangWord(line));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return slangWords;
    }

    public static boolean writeListToFile(ArrayList<SlangWord> list) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constant.slangFile));

            for (SlangWord slangWord : list) {
                bufferedWriter.write(slangWord.toCompactString() + "\n");
                System.out.println(slangWord.toCompactString());
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void showResultAlert(String title, String successText, String failText, boolean status) {
        if (status) {
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle(title);
            success.setHeaderText(successText);
            success.show();
        } else {
            Alert fail = new Alert(Alert.AlertType.ERROR);
            fail.setTitle(title);
            fail.setHeaderText(failText);
            fail.show();
        }
    }

    public static void copyFile(String source, String dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }

        is.close();
        os.close();

    }

    public static String getLocaleDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static String getStatusString(boolean status){
        return status ? "Thành công" : "Thất bại";
    }

    public static void main(String[] args) throws IOException {
//        getSlang();
//        SlangList slangList = SlangList.getInstance();
//        writeListToFile(slangList.getList());
//        copyFile(Constant.originSlang, Constant.slangFile);
    }
}
