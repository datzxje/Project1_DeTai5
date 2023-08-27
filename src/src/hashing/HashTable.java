package hashing;

import student.Student;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static java.lang.Math.pow;

public class HashTable {
    private Hashtable<Integer, Student> table;

    public HashTable() {
        table = new Hashtable<>();
    }

    public void insert(int studentID, String name, int yearOfBirth, float score, float avgScore) {
        Student student = new Student(name, yearOfBirth, score, avgScore);
        int hashValued = hashStudentID(studentID);
        table.put(hashValued, student);
    }

    public int hashStudentID(int studentID) {
        return (int) ((studentID % pow(2,27)) / (pow(2,13)));    }

    public Student get(int studentID) {
        return table.get(studentID);
    }

    public void updateScore(int studentID, float newScore) {
        Student student = table.get(studentID);
        if (student != null) {
            student.setScore(newScore);
            System.out.println("Điểm của sinh viên " + studentID + " đã được cập nhật");
            System.out.println("********************************");
        }
    }

    public void delete(int studentID) {
        if (table.containsKey(studentID)) {
            table.remove(studentID);
            System.out.println("Đã xóa sinh viên ra khỏi danh sách với MSSV " + studentID);
            System.out.println("********************************");
        }
        else {
            System.out.println("Không có sinh viên với MSSV " + studentID + " trong danh sách");
            System.out.println("********************************");
        }
    }

    public List<Integer> getAllStudentIDs() {
        return new ArrayList<>(table.keySet());
    }
}
