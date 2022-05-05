package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.StringJoiner;

public class Analizy {

    public void unavailable(String source, String target) {
        String s = null;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            List<String[]> e = read.lines()
                    .map(str -> str.split(" "))
                    .toList();
            boolean flagCounter = true;
            for (String[] arr : e) {
                if (flagCounter && ("400".equals(arr[0]) || "500".equals(arr[0]))) {
                    s = s == null ? arr[1] + ";" : s + arr[1] + ";";
                    flagCounter = false;
                } else if (!(flagCounter) && ("200".equals(arr[0]) || "300".equals(arr[0]))) {
                    s += arr[1] + "\r\n";
                    flagCounter = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.print(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("console.log", "unavailable.csv");
    }
}