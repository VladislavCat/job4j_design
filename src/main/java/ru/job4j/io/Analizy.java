package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.StringJoiner;

public class Analizy {

    public void unavailable(String source, String target) {
        String s = null;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String line = read.readLine();
            boolean flagCounter = false;
            while (line != null) {
                String[] arrStr = line.split(" ");
                if (("400".equals(arrStr[0]) || "500".equals(arrStr[0])) && !flagCounter) {
                    s = s == null ? arrStr[1] + ";" : s + arrStr[1] + ";";
                    flagCounter = true;
                } else if ((flagCounter) && ("200".equals(arrStr[0]) || "300".equals(arrStr[0]))) {
                    s += arrStr[1] + "\r\n";
                    flagCounter = false;
                    }
                line = read.readLine();
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