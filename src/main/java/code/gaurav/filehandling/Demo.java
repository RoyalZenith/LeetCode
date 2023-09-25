package code.gaurav.filehandling;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws IOException {

        String fileName = "/Users/gauravsinghal/Desktop/file1.txt";
        String content = "Hi Gaurav ";
        File file = new File(fileName);
        // create the file
        System.out.println(file.createNewFile());
        // delete the file
//        file.delete();
        // write
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(content);
        fileWriter.append(content);
        fileWriter.append(content);
        fileWriter.close();

        //read the file
        FileReader reader = new FileReader(file);
        while (reader.read() != -1){
            System.out.println(reader.read());
        }
        reader.close();
    }
}
