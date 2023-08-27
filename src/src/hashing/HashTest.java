package hashing;

import student.Student;

import java.io.*;
import java.util.Scanner;

public class HashTest {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

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
        int hashValued = hashTable.hashStudentID(studentID);

        Student student = hashTable.get(hashValued);
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

    }

    private static void deleteStudent(HashTable hashTable) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        hashTable.delete(studentID);
        updateInputFile(studentID);
    }

    private static void updateInputFile(int studentIDDeleted) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/student_data/student_data.csv"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src/student_data/student_data.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int studentID = Integer.parseInt(parts[0]);
                if (studentID != studentIDDeleted) {
                    bw.write(line);
                    bw.newLine();
                }
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
