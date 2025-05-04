package FileHandling;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// CRUD Operation on student system

class Students implements Serializable {
    private final String studentName;
    private final int studentAge;
    private final int studentRollNo;
    private final double studentMarks;
    private final List<String> studentSubjects;

    Students(String studentName, int studentAge, int studentRollNo, double studentMarks, List<String> studentSubjects){
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentRollNo = studentRollNo;
        this.studentMarks = studentMarks;
        this.studentSubjects = studentSubjects;
    }

    public int getStudentRollNo(){
        return studentRollNo;
    }

    public String getStudentName(){
        return studentName;
    }

    //display
    public void displayStudent(){
        System.out.println("Student Name: " + studentName);
        System.out.println("Student Age: " + studentAge);
        System.out.println("Student Roll Number: " + studentRollNo);
        System.out.println("Student Marks: " + studentMarks);
        System.out.println("Subjects: " + studentSubjects);
    }

}

class StudentManagement{
    private final String fileName = "students.ser";
    Scanner input = new Scanner(System.in);
    private List<Students> studentList = new ArrayList<>();
    public void addStudent(int count){
        System.out.println("Student " + count);
        System.out.print("Enter Student Name: ");
        String studentName = input.nextLine();

        System.out.print("Enter Student Age: ");
        int studentAge = input.nextInt();

        input.nextLine();

        System.out.print("Enter Student Roll No: ");
        int studentRollNo = input.nextInt();

        input.nextLine();

        List<String> studentSubjects = addSubjects();

        System.out.print("Enter Marks: ");
        double studentMarks = input.nextDouble();

        input.nextLine();

        Students student = new Students(studentName, studentAge, studentRollNo, studentMarks, studentSubjects);
        studentList.add(student);
        System.out.println();
    }

    public List<String> addSubjects(){
        List<String> studentSubjects = new ArrayList<>();
        System.out.print("How many subjects you want to add ?... ");
        int noOfSub = input.nextInt();
        input.nextLine();
        System.out.println("Enter Subjects: ");
        for (int i = 0; i < noOfSub; i++) {
            System.out.print("Enter subject " + (i + 1) + ": ");
            String subject = input.nextLine();
            studentSubjects.add(subject);
        }

        return studentSubjects;
    }

    public List<Students> getStudentList(){
        return studentList;
    }

    public void displayAllStudent(){
        if(studentList.isEmpty()){
            System.out.println("Add Students the file is empty");
        }else{
            System.out.println("\n--- All Students ---");
            for (Students s : studentList) {
                s.displayStudent();
                System.out.println("----------------------");
            }
        }
    }

    public void searchByRollNo(List<Students> studentList, int studentRollNo){
        boolean found = false;
        for (Students student : studentList){
            if(student.getStudentRollNo() == studentRollNo){
                student.displayStudent();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("NO Student found with Roll No: " + studentRollNo);
        }
    }

    public void searchByName(List<Students> studentsList, String studentName){

        String studentNameTrim = studentName.toLowerCase().trim();
        boolean found = false;
        for(Students student : studentsList){
            if(student.getStudentName().toLowerCase().contains(studentNameTrim)){
                student.displayStudent();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No Student found with Name: " + studentName);
        }
    }

    public void saveToFile(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName,true))){
            out.writeObject(studentList);
        }catch (IOException e){
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    @SuppressWarnings("Unchecked")
    public void loadFromFile(){
        File file = new File(fileName);
        if(file.exists()){
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
                studentList = (List<Students>) in.readObject();
            } catch (IOException | ClassNotFoundException e){
                System.out.println("Error Loading student data " + e.getMessage());
            }
        }
    }
}


class SystemInterface{
    public void studentInterface(){
        Scanner sc = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.loadFromFile();
        List<Students> studentsList = studentManagement.getStudentList();
        int choice;
        do{
            System.out.println("--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("---- Welcome to Student Portal ----");
                    System.out.println("Type 'exit' once you are done");
                    System.out.print("How many students do you want to add ?... ");
                    int option1 = sc.nextInt();
                    sc.nextLine();
                    int count = 1;
                    while (option1 > 0) {
                        studentManagement.addStudent(count);
                        option1--;
                        count++;
                    }
                    studentManagement.saveToFile();
                    break;

                case 2:
                    studentManagement.displayAllStudent();
                    break;

                case 3:
                    int option2;
                    do{
                        System.out.println("1. Search By Name.");
                        System.out.println("2. Search By Roll Number.");
                        System.out.println("3. Exit");
                        option2 = sc.nextInt();
                        sc.nextLine();
                        switch (option2){
                            case 1:
                                System.out.print("Enter Student Name: ");
                                String studentName = sc.nextLine();
                                studentManagement.searchByName(studentsList, studentName);
                                break;

                            case 2:
                                System.out.print("Enter Student Roll Number: ");
                                int studentRollNo = sc.nextInt();
                                sc.nextLine();
                                studentManagement.searchByRollNo(studentsList, studentRollNo);
                                break;

                            case 3:
                                System.out.println("See You later !");
                                System.out.println("----------------------");
                                System.out.println();
                                break;

                            default:
                                System.out.println("Invalid Key Entered");
                                break;
                        }

                    }while(option2 != 3);
                    break;

                case 4:
                    System.out.println("Bye... System Closed !");
                    break;

                default:
                    System.out.println("Invalid Key Entered");
                    break;

            }
        }while(choice != 4);
    }
}

public class StudentSystem {
    public static void main(String[] args) {
        SystemInterface newInterFace = new SystemInterface();
        newInterFace.studentInterface();
    }
}
