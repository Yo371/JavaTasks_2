package javaio;

import java.io.*;
import java.util.Arrays;

public class FileExecutor {

    public static void writeTreeToFileByFolderPath(File file, File fileOut) {

        if (!file.isDirectory()) throw new IllegalArgumentException("This is not folder path");
        if (file.listFiles() == null) throw new IllegalArgumentException("Folder is empty");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut))) {

            Arrays.stream(file.listFiles()).forEach(e -> {
                try {
                    bufferedWriter.write(print(e, 0));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String print(File file, int nest) {
        StringBuilder sb = new StringBuilder();

        sb.append(getNastedSpace(nest++));

        if (file.isDirectory())
            sb.append("|--").append(file.getName()).append("\n");
        else
            sb.append(file.getName()).append("\n");

        if (file.isDirectory() && file.listFiles() != null) {
            for (File fileInArr : file.listFiles())
                sb.append(print(fileInArr, nest));
        }

        return sb.toString();
    }


    private static String getNastedSpace(int nesting) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nesting; i++)
            sb.append("|  ");

        return sb.toString();
    }

    public static String getInfo(File file) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            int folders = 0, files = 0, length = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("--")) folders++;
                else {
                    files++;
                    line = line.replaceAll("[|]", "").trim();

                    length += line.contains(".") ? line.substring(0, line.lastIndexOf(".")).length() :
                             line.length();

                }
            }
            sb.append(String.format("Folders = %d, Files = %d", folders, files))
                    .append(String.format(" \nAverage amount of files in folders = %.2f", (double) files / folders))
                    .append(String.format(" Average length of file's name = %.2f", (double) length / files));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
