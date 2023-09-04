package avl_tree;

import student.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AVLProgram {

    private static Scanner keyboard = new Scanner(System.in);
    private static List<Student> clonedStudentData = new ArrayList<>();
    private static String path = "src/student_data/student_data.csv";


    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int studentID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int yearOfBirth = Integer.parseInt(parts[2]);
                    float score = Float.parseFloat(parts[3]);
                    float avgScore = Float.parseFloat(parts[4]);
                    Student student = new Student(studentID, name, yearOfBirth, score, avgScore);
                    avlTree.insert(studentID, student);
                    clonedStudentData.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        showMenu();

        int option = keyboard.nextInt();

        while (option != 0) {
            switch (option) {
                case 1 -> showStudentInf(avlTree);
                case 2 -> updateScore(avlTree);
                case 3 -> deleteStudent(avlTree);
            }
            showMenu();
            option = keyboard.nextInt();
        }
    }

    private static void showStudentInf(AVLTree avlTree) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        avlTree.showStudentInf(studentID);
    }

    private static void updateScore(AVLTree avlTree) {

        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        System.out.println("Nhập vào điểm mới: ");
        float score = keyboard.nextFloat();

        for (Student student : clonedStudentData) {
            if (student.getStudentID() == studentID) {
                student.setScore(score);
                break;
            }
        }

        avlTree.updateScore(studentID, score);
        updateInputFile(avlTree);
        System.out.println("Điểm của sinh viên " + studentID + " đã được cập nhật");
        System.out.println("********************************");
    }

    private static void deleteStudent(AVLTree avlTree) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();
        Student delStudent = null;

        for (Student student: clonedStudentData) {
            if (student.getStudentID() == studentID) {
                delStudent = student;
            }
        }

        if (delStudent != null) {
            clonedStudentData.remove(delStudent);
            avlTree.delete(avlTree.getRoot(), studentID);
            System.out.println("Đã xóa sinh viên " + studentID + " ra khỏi danh sách");
            updateInputFile(avlTree);
        }
        else System.out.println("Không có sinh viên " + studentID + " trong danh sách");
    }

    private static void updateInputFile(AVLTree avlTree) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Ma sinh vien,Ho ten,Nam sinh,Diem trung tuyen,Diem TB");
            writer.newLine();
            for (Student student : clonedStudentData) {
                writer.write(student.getStudentID() + "," + student.getName() + "," + student.getYearOfBirth() + "," + student.getScore() + "," + student.getAvgScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showMenu() {
        System.out.println("Student Management Application: ");
        System.out.println("--------------------------------");
        System.out.println("1. Show student information by id");
        System.out.println("2. Update score by id");
        System.out.println("3. Delete student by id");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }
}
