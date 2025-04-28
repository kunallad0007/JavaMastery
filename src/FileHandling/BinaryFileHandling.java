package FileHandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFileHandling {
    public static void main(String[] args) {

        //Write to File
        try (FileOutputStream fos = new FileOutputStream("/home/kunal/Java/JavaMastery/src/FileHandaling/output.bin")){
            byte[] data = {65, 66, 67, 68};
            fos.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Read from File
        try (FileInputStream fis = new FileInputStream("/home/kunal/Java/JavaMastery/src/FileHandaling/output.bin")){
            int byteData;
            while((byteData = fis.read()) != -1){
                System.out.print((char) byteData + " ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
