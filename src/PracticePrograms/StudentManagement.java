/*

📝 Challenge: "Student Record Management" using File Handling

    Requirements:
        Create a simple program where:
            You accept student details (Name, Roll Number, Marks) from the user.
            Save the student details into a text file (students.txt), each student on a new line.
            Allow reading the file and displaying all student records.

    Bonus:
        Add an option to search for a student by Roll Number and show their details.
        Format the output nicely while displaying (like a small table).

    📚 Example Output:

        1. Add Student
        2. View All Students
        3. Search Student by Roll No
        4. Exit
        Enter your choice: 1

        Enter Student Name: Rahul
        Enter Roll No: 101
        Enter Marks: 89

        (Student saved successfully!)

        ---
        1. Add Student
        2. View All Students
        3. Search Student by Roll No
        4. Exit
        Enter your choice: 2

        --- Student Records ---
        Roll No | Name    | Marks
        101     | Rahul   | 89
        --------------------------


*/

package PracticePrograms;

import java.io.*;
import java.util.Scanner;

record Student(String studentName, int studentRollNo, String studentMarks){}

class StudentDetails{
    final File file = new File("student.txt");
    public void addStudentDetails(String studentName, String studentRollNo, String studentMarks){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write("Student Name: " + studentName);
            writer.newLine();
            writer.write("Roll No: "+ studentRollNo);
            writer.newLine();
            writer.write("Marks: " + studentMarks);
            writer.newLine();
            writer.write("-------------------------------");
            writer.newLine();
        }catch (IOException e){
            System.out.println("Error while writing: " + e.getMessage());
        }
    }

    public void displayStudentDetails(){
        System.out.println("\n--- Student Records ---");
        System.out.println("-------------------------------");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDetails newStudent = new StudentDetails();
        int choice;
        do{
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.print("How many students you want to add: ");
                    int noOfStudents = sc.nextInt();
                    sc.nextLine();
                    int i = 0;
                    String studentName, studentRollNo, studentMarks;
                    do{
                        System.out.println(i + "st Student: ");
                        System.out.println();
                        System.out.print("Enter Student Name: ");
                        studentName = sc.nextLine();
                        System.out.print("Enter Roll No: ");
                        studentRollNo = sc.nextLine();
                        System.out.print("Enter Marks: ");
                        studentMarks = sc.nextLine();
                        newStudent.addStudentDetails(studentName, studentRollNo, studentMarks);
                        System.out.println();
                        i++;
                    }while(i != noOfStudents);
                    System.out.println("Student saved successfully!");
                    break;

                case 2:
                    newStudent.displayStudentDetails();
                    break;

                case 3:
                    System.out.println("Bye...See you later!");
                    break;

                default:
                    System.out.println("Entered Invalid Number");
                    break;

            }
        }while(choice != 3);

    }
}
