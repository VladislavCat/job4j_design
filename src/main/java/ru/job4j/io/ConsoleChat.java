package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean running = true;
        boolean talking = true;
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        do {
            String s = scanner.nextLine();
            if (STOP.equals(s)) {
                talking = false;
            } else if (OUT.equals(s)) {
                talking = false;
                running = false;
            } else if (CONTINUE.equals(s)) {
                talking = true;
            }
            log.add(s);
            if (talking) {
                String a = phrases.get((int) (Math.random() * phrases.size()));
                System.out.println(a);
                log.add(a);
            }
        } while (running);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            phrases = br.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("logChat.txt", "phrasesBot.txt");
        cc.run();
    }
}
