package PracticePrograms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class WriteBinaryFile{
    public void writeBinaryFile(){
        try(FileOutputStream fileOutputStream = new FileOutputStream("/home/kunal/Java/JavaMastery/src/OutputFiles/file02.bin")){
            byte[] data = {65, 66, 67, 68, 69};
            fileOutputStream.write(data);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

class ReadBinaryFile{
    public void readBinaryFile(){
        try(FileInputStream fileInputStream = new FileInputStream("/home/kunal/Java/JavaMastery/src/OutputFiles/file02.bin")){
            int byteData;
            while((byteData = fileInputStream.read()) != -1){
                System.out.print((char) byteData + " ");
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

public class PracticeProgram4 {
    public static void main(String[] args) {
        WriteBinaryFile writeBinaryFile = new WriteBinaryFile();
        ReadBinaryFile readBinaryFile = new ReadBinaryFile();

        writeBinaryFile.writeBinaryFile();
        readBinaryFile.readBinaryFile();
    }
}
