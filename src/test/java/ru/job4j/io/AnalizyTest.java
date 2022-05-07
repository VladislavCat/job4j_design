package ru.job4j.io;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenTwoDiapasonInLog() throws IOException {
        Analizy analizy = new Analizy();
        File log = new File("consoleLog.txt");
        File rslAnalysis = new File("rslConsole.txt");
        try (PrintWriter out = new PrintWriter(log)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(log.getAbsolutePath(), rslAnalysis.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(rslAnalysis))) {
            in.lines().forEach(s -> rsl.append(s).append("\r\n"));
        }
        Assert.assertEquals(rsl.toString(), "10:57:01;10:59:01\r\n11:01:02;11:02:02\r\n");
    }

    @Test
    public void whenOneDiapasonInLog() throws IOException {
        Analizy analizy = new Analizy();
        File log = new File("consoleLog.txt");
        File rslAnalysis = new File("rslConsole.txt");
        try (PrintWriter out = new PrintWriter(log)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("400 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(log.getAbsolutePath(), rslAnalysis.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(rslAnalysis))) {
            in.lines().forEach(s -> rsl.append(s).append("\r\n"));
        }
        Assert.assertEquals(rsl.toString(), "10:58:01;11:02:02\r\n");
    }
}
