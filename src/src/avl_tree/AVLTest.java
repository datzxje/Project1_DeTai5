package avl_tree;

import student.Student;

import java.io.*;
import java.util.Scanner;

public class AVLTest {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        try (BufferedReader br = new BufferedReader(new FileReader("src/student_data/student_data.csv"))) {
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
                    Student student = new Student(name, yearOfBirth, score, avgScore);
                    avlTree.insert(studentID, student);
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

        avlTree.updateScore(studentID, score);
        System.out.println("Điểm của sinh viên " + studentID + " đã được cập nhật");
        System.out.println("********************************");
    }

    private static void deleteStudent(AVLTree avlTree) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        avlTree.delete(avlTree.getRoot(), studentID);
        System.out.println("Đã xóa sinh viên " + studentID + " ra khỏi danh sách");
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
