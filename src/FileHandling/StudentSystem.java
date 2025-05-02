package FileHandling;
import java.io.*;
import java.util.ArrayList;
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

    //display
    public void displayStudent(){
        System.out.println("Student Name: " + studentName);
        System.out.println("Student Age: " + studentAge);
        System.out.println("Student Roll Number: " + studentRollNo);
        System.out.println("Student Marks: " + studentMarks);
        System.out.println("Subjects: " + studentSubjects);
    }

}

class AddSubjects{
    Scanner input = new Scanner(System.in);

    public List<String> addSubjects(){
        List<String> studentSubjects = new ArrayList<>();
        System.out.print("How many subjects you want to add ?... ");
        int noOfSub = input.nextInt();
        input.nextLine();
        System.out.print("Enter Subjects: ");
        for (int i  = 0; i < noOfSub; i++){
            String subject = input.nextLine();
            studentSubjects.add(subject);
        }
        return studentSubjects;
    }

}

class SystemInterface{
    public void studentInterface(){
        Scanner input = new Scanner(System.in);
        List<Students> studentList = new ArrayList<>();
        AddSubjects newStudent = new AddSubjects();

        System.out.println("Enter details of students (Type 'exit' anytime to stop)");
        int count = 1;
        while(true){
            System.out.print("\nType 'continue' to add student or 'exit' to finish: ");
            String choice = input.next();
            input.nextLine();
            if(choice.equalsIgnoreCase("exit")){
                break;
            }
            System.out.println("Enter " + count + "st student details: ");

            System.out.print("Enter Student Name: ");
            String studentName = input.nextLine();

            System.out.print("Enter Student Age: ");
            int studentAge = input.nextInt();

            System.out.print("Enter Student Roll No: ");
            int studentRollNo = input.nextInt();

            input.nextLine();

            List<String> studentSubjects = newStudent.addSubjects();

            System.out.print("Enter Marks: ");
            double studentMarks = input.nextDouble();

            Students student = new Students(studentName, studentAge, studentRollNo, studentMarks, studentSubjects);
            studentList.add(student);
            count++;
        }

        System.out.println("\n--- All Students ---");
        for (Students s : studentList) {
            s.displayStudent();
            System.out.println("----------------------");
        }
    }
}

public class StudentSystem {
    public static void main(String[] args) {
        SystemInterface newInterFace = new SystemInterface();
        newInterFace.studentInterface();
    }
}
