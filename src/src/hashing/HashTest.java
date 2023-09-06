package hashing;

import student.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashTest {

    private static String path = "src/student_data/student_data.txt";

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
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
        System.out.println("Bảng băm ban đầu: \n");
        hashTable.printHashTable();
        System.out.println("********************************");
        hashTable.delete(20216568);
        System.out.println("Bảng băm sau khi xóa 20216568: \n");
        hashTable.printHashTable();
        System.out.println("********************************");
    }
}