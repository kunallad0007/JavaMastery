package FileHandling;

import java.io.*;

record Person(String personName, int personAge) implements Serializable {
}

class Serialization {
    public void serializeObject(String personName, int personAge, String fileName){
        Person person = new Person(personName, personAge);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(person);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

class Deserialization {
    public void deserializeObject(String fileName){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Person person = (Person) objectInputStream.readObject();
            int lastSlashIndex = fileName.lastIndexOf('/');
            String file = fileName.substring(lastSlashIndex + 1);
            System.out.println("Deserialized file (" + file + "): ");
            System.out.println("Person Name: " + person.personName());
            System.out.println("Person Age: " + person.personAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class SerializationAndDeserialization {
    public static void main(String[] args) {
        Serialization serialization = new Serialization();
        serialization.serializeObject("John Doe", 36, "/home/kunal/Java/JavaMastery/src/FileHandling/person.ser");

        Deserialization deserialization = new Deserialization();
        deserialization.deserializeObject("/home/kunal/Java/JavaMastery/src/FileHandling/person.ser");
    }
}
/*
for (int i = fileName.length(); i > 0; i--){
                if(fileName.equals("/")){
                    break;
                }else{
                    file.append(fileName);
                }
            }
*/