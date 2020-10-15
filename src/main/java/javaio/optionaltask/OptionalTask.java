package javaio.optionaltask;

import java.io.*;
import java.util.Arrays;

public class OptionalTask {
    public static void main(String[] args) {

        File file = new File("data\\javatext.txt");
        //2 Прочитать текст java и заменить puplic на private
        replacePublicToPrivate(file);
        //3 записать java текст в обратном порядке
        writeInReverseOrder(file, new File("data\\reverse.txt"));
        //4 заменить все слова длиной больше 4 на прописные буквы
        replaceToUpper(file, new File("data\\upper.txt"));


    }

    public static void replaceToUpper(File in, File out) {
        try (BufferedReader br = new BufferedReader(new FileReader(in));
             BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {

            String line, templine;
            String splitted[] = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                templine = line.replaceAll("[^A-z\\s\\.(]+", "").trim()
                        .replaceAll("\\(", ".").replaceAll("\\s\\s", " ");

                if (templine.length() > 0) {
                    splitted = templine.split("\\.|\\s");
                    for (String s : splitted) {
                        if (s.length() > 4)
                            line = line.replace(s, s.toUpperCase());
                    }
                }

                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeInReverseOrder(File in, File out) {

        try (BufferedReader br = new BufferedReader(new FileReader(in));
             BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {

            String line, text;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                text = line.replaceAll("\\s", "");
                bw.write(line.replaceAll("\\S", ""));
                bw.write(sb.append(text).reverse().append("\n").toString());
                sb.delete(0, sb.toString().length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replacePublicToPrivate(File file) {

        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuffer sb = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null)
                sb.append(line.replaceAll("public", "private")).append("\n");

            bufferedReader.close();

            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(sb.toString());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
