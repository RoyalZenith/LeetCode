package code.gaurav.filehandling;

import java.io.*;

public class ReadAndWriteFileUsingStream {
    public static void main(String[] args) throws IOException {
        String path = "/Users/gauravsinghal/Desktop/";
        BufferedInputStream r = new BufferedInputStream(new FileInputStream(path +"file1.txt"));
        BufferedOutputStream w= new BufferedOutputStream(new FileOutputStream(path+"file2.txt"));
        int i = 0;
        while((i=r.read()) != -1){
            w.write((char)(i));
        }
        r.close();
        w.close();
    }
}
