// Enhanced Student Management System with CSV Export and Sorting

package FileHandling;
import java.io.*;
import java.util.*;

class Students implements Serializable {
    private String studentName;
    private int studentAge;
    private int studentRollNo;
    private double studentMarks;
    private List<String> studentSubjects;

    Students(String studentName, int studentAge, int studentRollNo, double studentMarks, List<String> studentSubjects){
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentRollNo = studentRollNo;
        this.studentMarks = studentMarks;
        this.studentSubjects = studentSubjects;
    }

    public int getStudentRollNo(){ return studentRollNo; }
    public String getStudentName(){ return studentName; }
    public int getStudentAge(){ return studentAge; }
    public double getStudentMarks(){ return studentMarks; }
    public List<String> getStudentSubjects(){ return studentSubjects; }

    public void displayStudent(){
        System.out.println("Student Name: " + studentName);
        System.out.println("Student Age: " + studentAge);
        System.out.println("Student Roll Number: " + studentRollNo);
        System.out.println("Student Marks: " + studentMarks);
        System.out.println("Subjects: " + studentSubjects);
    }
}

class StudentManagement {
    private final String fileName = "students.ser";
    Scanner input = new Scanner(System.in);
    private List<Students> studentList = new ArrayList<>();

    Comparator<Students> sortByName = Comparator.comparing(Students::getStudentName);
    Comparator<Students> sortByMarks = Comparator.comparingDouble(Students::getStudentMarks).reversed();
    Comparator<Students> sortByRollNo = Comparator.comparingInt(Students::getStudentRollNo);

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
        for (int i = 0; i < noOfSub; i++) {
            System.out.print("Enter subject " + (i + 1) + ": ");
            studentSubjects.add(input.nextLine());
        }
        return studentSubjects;
    }

    public void displayAllStudent(){
        if(studentList.isEmpty()){
            System.out.println("Add Students the file is empty");
        } else {
            System.out.println("\n--- All Students ---");
            for (Students s : studentList) {
                s.displayStudent();
                System.out.println("----------------------");
            }
        }
    }

    public void searchByRollNo(int studentRollNo){
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

    public void searchByName(String studentName){
        String studentNameTrim = studentName.toLowerCase().trim();
        boolean found = false;
        for(Students student : studentList){
            if(student.getStudentName().toLowerCase().contains(studentNameTrim)){
                student.displayStudent();
                found = true;
            }
        }
        if(!found){
            System.out.println("No Student found with Name: " + studentName);
        }
    }

    public void saveToFile(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            out.writeObject(studentList);
        }catch (IOException e){
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
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

    public void updateStudentByRollNo(int studentRollNo){
        for(Students student : studentList){
            if(student.getStudentRollNo() == studentRollNo){
                System.out.println("Student Found: ");
                student.displayStudent();
                System.out.println("Update: \n1.Name \n2.Age \n3.Marks \n4.Subjects \n5.All \n6.Exit");
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine();
                String name = student.getStudentName();
                int age = student.getStudentAge();
                double marks = student.getStudentMarks();
                List<String> subjects = student.getStudentSubjects();

                switch (choice){
                    case 1: System.out.print("Name: "); name = input.nextLine(); break;
                    case 2: System.out.print("Age: "); age = input.nextInt(); break;
                    case 3: System.out.print("Marks: "); marks = input.nextDouble(); break;
                    case 4: subjects = addSubjects(); break;
                    case 5:
                        System.out.print("Name: "); name = input.nextLine();
                        System.out.print("Age: "); age = input.nextInt(); input.nextLine();
                        System.out.print("Marks: "); marks = input.nextDouble(); input.nextLine();
                        subjects = addSubjects(); break;
                    default: return;
                }

                studentList.set(studentList.indexOf(student), new Students(name, age, studentRollNo, marks, subjects));
                saveToFile();
                System.out.println("Updated successfully");
                return;
            }
        }
        System.out.println("No student found with Roll No: " + studentRollNo);
    }

    public void deleteStudentByRollNo(int rollNo){
        for (Students student : studentList) {
            if (student.getStudentRollNo() == rollNo) {
                System.out.print("Confirm delete (yes/no): ");
                if (input.nextLine().trim().equalsIgnoreCase("yes")) {
                    studentList.remove(student);
                    saveToFile();
                    System.out.println("Deleted successfully.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                return;
            }
        }
        System.out.println("No student found with Roll No: " + rollNo);
    }

    public void exportToCSV(String csvFileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFileName))) {
            writer.println("Name,Age,RollNo,Marks,Subjects");
            for (Students student : studentList) {
                String subjects = String.join(" | ", student.getStudentSubjects()).replace(",", ";");
                writer.printf("%s,%d,%d,%.2f,%s%n",
                        student.getStudentName(), student.getStudentAge(), student.getStudentRollNo(), student.getStudentMarks(), subjects);
            }
            System.out.println("Exported to " + csvFileName);
        } catch (IOException e) {
            System.out.println("CSV Export Error: " + e.getMessage());
        }
    }

    public void sortAndDisplay(int criteria) {
        if (studentList.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        switch (criteria) {
            case 1: studentList.sort(sortByName); break;
            case 2: studentList.sort(sortByMarks); break;
            case 3: studentList.sort(sortByRollNo); break;
            default: System.out.println("Invalid sorting choice."); return;
        }
        displayAllStudent();
    }

    public List<Students> getStudentList() {
        return studentList;
    }
}

class SystemInterface {
    public void studentInterface(){
        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();
        sm.loadFromFile();
        int choice;
        do {
            System.out.println("--- Student Management ---");
            System.out.println("1. Add\n2. View All \n3. Search \n4. Update \n5. Delete \n6. Export CSV \n7. Sort \n8. Exit");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("How many? "); int n = sc.nextInt(); sc.nextLine();
                    for (int i = 1; i <= n; i++) sm.addStudent(i);
                    sm.saveToFile(); break;
                case 2: sm.displayAllStudent(); break;
                case 3:
                    System.out.println("1.Name \n2.RollNo");
                    System.out.print("Enter your choice: ");
                    int op = sc.nextInt(); sc.nextLine();
                    if (op == 1) { System.out.print("Name: "); sm.searchByName(sc.nextLine()); }
                    else { System.out.print("Roll No: "); sm.searchByRollNo(sc.nextInt()); sc.nextLine(); }
                    break;
                case 4: System.out.print("Roll No: "); sm.updateStudentByRollNo(sc.nextInt()); sc.nextLine(); break;
                case 5: System.out.print("Roll No: "); sm.deleteStudentByRollNo(sc.nextInt()); sc.nextLine(); break;
                case 6: System.out.print("CSV file name: "); sm.exportToCSV(sc.nextLine()); break;
                case 7:
                    System.out.println("Sort by \n1.Name \n2.Marks \n3.Roll No");
                    System.out.print("Enter your choice: ");
                    sm.sortAndDisplay(sc.nextInt()); sc.nextLine(); break;
                case 8: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option");
            }
        } while (choice != 8);
    }
}

public class StudentSystem {
    public static void main(String[] args) {
        new SystemInterface().studentInterface();
    }
}
