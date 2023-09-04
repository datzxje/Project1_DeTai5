package hashing;

import student.Student;

import java.io.*;
import java.util.Scanner;

public class HashProgram {

    private static Scanner keyboard = new Scanner(System.in);
    private static String path = "src/student_data/student_data.csv";

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

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
                    hashTable.insert(studentID, student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        showMenu();

        int option = keyboard.nextInt();

        while (option != 0) {
            switch (option) {
                case 1 -> showStudentInf(hashTable);
                case 2 -> updateScore(hashTable);
                case 3 -> deleteStudent(hashTable);
            }

            showMenu();
            option = keyboard.nextInt();
        }

    }

    private static void showStudentInf(HashTable hashTable) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();
        Student student = hashTable.get(studentID);

        if (student != null) {
            System.out.println("--------------------------------");
            System.out.println("Student ID: " + studentID);
            System.out.println("Name: " + student.getName());
            System.out.println("Year Of Birth: " + student.getYearOfBirth());
            System.out.println("Score: " + student.getScore());
            System.out.println("Average Score: " + student.getAvgScore());
            System.out.println("********************************");
        }
        else System.out.println("Không có sinh viên " + studentID + " trong danh sách");
    }

    private static void updateScore(HashTable hashTable) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        System.out.println("Nhập vào điểm mới: ");
        float score = keyboard.nextFloat();

        hashTable.updateScore(studentID, score);
        updateInputFile(hashTable);
    }

    private static void deleteStudent(HashTable hashTable) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        hashTable.delete(studentID);
        updateInputFile(hashTable);
    }

    private static void updateInputFile(HashTable hashTable) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Ma sinh vien,Ho ten,Nam sinh,Diem trung tuyen,Diem TB");
            writer.newLine();
            for (Student student : hashTable.getAllStudents()) {
                writer.write(student.getStudentID() + "," + student.getName() + "," + student.getYearOfBirth() + "," + student.getScore() + "," + student.getAvgScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showMenu () {
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
