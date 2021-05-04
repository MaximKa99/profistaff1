package com.profistaff.first;

import com.profistaff.first.collector.Collector;
import com.profistaff.first.exception.NoSuchFolderException;

import java.util.Scanner;

public class App {

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        Collector collector = new Collector();

        String rootDir;
        while (true) {
            System.out.println("Please, write path to the root folder");
            System.out.println("If you want to quit, enter \"stop\"");
            rootDir = scanner.nextLine();

            if (rootDir.equals("")) {
                continue;
            }
            if (rootDir.equals("stop")) {
                System.out.println("Bye!");
                break;
            }

            String newFile;
            try {
                newFile = collector.collect(rootDir);
            } catch (NoSuchFolderException ex) {
                System.out.println("Write a correct path to folder");
                continue;
            }
            System.out.println("All files were collected in " + newFile);
        }
    }
}
