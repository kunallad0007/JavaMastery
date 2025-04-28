package FileHandling;

import java.io.File;
import java.util.Scanner;

class CreateDirectory{
    public void createDirectory(String directoryLocation, String directoryName){
        File directory = new File(directoryLocation+directoryName);
        if(!directory.exists()){
            boolean created = directory.mkdir();
            if(created){
                System.out.println("Directory created Successfully");
            }else{
                System.out.println("Failed to create Directory");
            }
        }
    }
}

class ListingDirectory{
    public void listingDirectory(String folderLocation){
        File directory = new File(folderLocation);

        if(directory.exists() && directory.isDirectory()){
            String[] files = directory.list();
            if(files != null){
                for (String file : files){
                    System.out.println(file);
                }
            }
        }
    }
}

public class DirectoryHandling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CreateDirectory createDirectory = new CreateDirectory();
        System.out.println("Let's create new directory");
        System.out.print("Enter Directory Location: ");
        String directoryLocation = sc.nextLine();
        System.out.print("Enter Directory Name: ");
        String directoryName = sc.next();
        sc.nextLine();
        createDirectory.createDirectory(directoryLocation, directoryName);

        ListingDirectory listingDirectory = new ListingDirectory();
        System.out.println();
        System.out.println("Let's List the file in the directory");
        System.out.print("Enter Directory Location: ");
        String folderLocation = sc.nextLine();
        listingDirectory.listingDirectory(folderLocation);
    }
}
