package PracticePrograms;

import java.io.*;

class WriteToFile{
    public void writeToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("/home/kunal/Java/JavaMastery/src/OutputFiles/file01.txt"))){
            writer.write("Hello, Practice is completed!");
            writer.newLine();
            writer.write("Thank You!");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

class ReadFromFile{
    public void readFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader("/home/kunal/Java/JavaMastery/src/OutputFiles/file01.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}

public class PracticeProgram3 {
    public static void main(String[] args) {
        WriteToFile write = new WriteToFile();
        ReadFromFile read = new ReadFromFile();

        write.writeToFile();
        read.readFromFile();
    }
}
