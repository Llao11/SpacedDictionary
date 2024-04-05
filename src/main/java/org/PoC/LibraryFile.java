package org.PoC;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LibraryFile {
    static String dictsListPath = "src/main/resources/DictsList.txt";
    static String dictsPath = "src/main/resources/Dictionaries";

    //Files.write(Paths.get(dictsListPath), name.getBytes(), StandardOpenOption.APPEND);

    /**
     *
     * Add new dictionary
     * @param name - name of a new dictionary
     */
    public void newDict(String name) {
        try {
            if (Files.exists(Paths.get(dictsPath+"/"+name+".txt"))) {
                System.out.println("The dictionary with this name already exists");
            }else{
                Files.createFile(Paths.get(dictsPath+"/"+name+".txt"));
                System.out.println("The dictionary: " + name + " created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of all dictionaries names
     */
    public List<String> getDicts(){
        Path dir = Paths.get(dictsPath);
        ArrayList<String> dictList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path entry : stream) {
                dictList.add(entry.getFileName().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictList;
    }

    /**
     * Print all dictionaries
     */
    public void printDicts(){
        if (getDicts().isEmpty()){
            System.out.println("There is no dictionaries");
        }else {
            getDicts().forEach(System.out::println);
        }
    }

    /**
     *  Checks if there are dictionaries
     * @return
     */
    public boolean isEmpty(){
       try {
            List<String> allLines = Files.readAllLines(Paths.get(dictsListPath));
           return allLines.isEmpty();
        } catch (IOException e) {
           e.printStackTrace();
       }
       return true;
    }
}
