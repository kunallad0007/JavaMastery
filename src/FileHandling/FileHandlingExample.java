package FileHandling;

import java.io.*;

public class FileHandlingExample {
    public static void main(String[] args) {

        //Writing to the file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("/home/kunal/Java/JavaMastery/src/FileHandaling/output.txt"))){
            writer.write("Hello, this is a test file.");
            writer.newLine();
            writer.write("This is the second line.");
        }catch (IOException e){
            e.printStackTrace();
        }

        //Reading from the file
        try(BufferedReader reader = new BufferedReader(new FileReader("/home/kunal/Java/JavaMastery/src/FileHandaling/output.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
