package javaio;

import java.io.File;


public class Main {

    public static void main(String[] args) {


        if (args.length > 0) {
            File file = new File(args[0]);
            if (file.isDirectory())
                FileExecutor.writeTreeToFileByFolderPath(file, new File("data\\tree.txt"));

            if (file.isFile())
                System.out.println(FileExecutor.getInfo(file));
        }

        //data\tree.txt


    }


}
