package code.gaurav.filehandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writeInFile {
    public void createNewFile(String name) throws IOException {
        File file = new File(name);
        if(file.createNewFile()){
            System.out.println("new file created at "+name);
        }else{
            System.out.println("file already present at "+name);
        }
    }
    public void appendContentInFile(String content, String filePath) throws IOException {

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content,1,1);
        }
    }
}
